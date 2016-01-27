$(document).ready(function() {
	$("#shorturl_btn").click(function() {
		var linkJQ = $("#link_input"),
			url = linkJQ.val(),
			shortBtnJQ = $(this);
		if (url.length == 0) {
			showLinkError(ERROR.URL.EMPTY, true);
			return;
		}
		if (!re_weburl.test(url)) {
			showLinkError(ERROR.URL.INVALID, true);
			return;
		}
		$.ajax({
			url: '/myUrlRest/api/url/requestToken/', 
			type: "post",
			data: JSON.stringify({url: url}),
			dataType: 'json',
            contentType: 'application/json',
			beforeSend: function(){
				shortBtnJQ.button("loading");
				linkJQ.attr("disabled", "disabled");
			},
			success: function(jsonRes){
				if(!jsonRes.error){
					linkJQ.val("");
				}
				if (jsonRes.error) {
					showServerMessage(jsonRes.error, jsonRes.error_code, jsonRes.data, url);
				}
				console.log(jsonRes);
				
				// Store
				localStorage.setItem("url", url);
				localStorage.setItem("token", jsonRes.data.token);
				localStorage.setItem("token_secret", jsonRes.data.tokenSecret);
				localStorage.setItem("redirect_url", jsonRes.data.redirectUrl);
				
				//showServerMessage(jsonRes.error, jsonRes.error_code, jsonRes.data, url);
				window.location.href = jsonRes.data.redirectUrl;
			},
			complete: function(){
				shortBtnJQ.button("reset");
				linkJQ.removeAttr("disabled");
			}
			
		});
		
	});
	
	if(re_vercode.test(window.location.href)) {
		var url, current_location, token, token_secret, redirect_url;
		
		current_location = window.location.href;
		
		url = localStorage.getItem("url");
		token = localStorage.getItem("token");
		token_secret = localStorage.getItem("token_secret");
		redirect_url = localStorage.getItem("redirect_url");
		
		if (current_location) {
			verification_code = current_location.split(re_vercode)[1];
			
			$.ajax({
				url: '/myUrlRest/api/url/accessToken/', 
				type: "post",
				data: JSON.stringify({url: url, token : token, tokenSecret : token_secret, verificationCode: verification_code}),
				dataType: 'json',
		        contentType: 'application/json',
				success: function(jsonRes){
					if (jsonRes.error) {
						showServerMessage(jsonRes.error, jsonRes.error_code, jsonRes.data, url);
					}
					console.log(jsonRes);
					
					var projects = jsonRes.data;
					
					localStorage.setItem("projects", JSON.stringify(projects));
					
					window.location.href = REDIRECT_URL;
				}
			});
		}
	}
	
	$("#link_input").keypress(function(){
		clearUrlErrors();
		clearServerMessage();
	});

	function showLinkError(error, clearOther){
		var message = null;
		switch (error) {
			case ERROR.URL.EMPTY:
				message = "Please enter your link first!";
				break;
			case ERROR.URL.INVALID:
				message = "Please enter a valid link!";
				break;
	
			default:
				message = null;
				break;
			}
		var errorContainer = $("#urlerrorcontainer");
		if(clearOther){
			clearUrlErrors();
		}
		if(null != message){
			errorContainer.append(
					'<div role="alert" class="alert alert-danger alert-dismissible fade in">' +
				      '<button aria-label="Close" data-dismiss="alert" class="close" type="button"><span aria-hidden="true">×</span></button>' +
				      '<strong>Link error. </strong> ' + message +
				    '</div>');
		}
	}
	
	function clearUrlErrors(){
		var errorContainer = $("#urlerrorcontainer");
		errorContainer.html("");
	}
	
	function clearServerMessage(){
		$("#servermessagescontainer").html("");
	}
	
	function showServerMessage(error, error_code, data, url){
		var message = "";
		if(!error){
			var shortLink = DEFAULT_LINK + data;
			message = '<strong>Here is your shorter link: <a href="' + shortLink + '" target="blank">' + shortLink + '</a></strong>';
		}else{
			switch (error_code) {
			case ERROR.SERVER.DUPLICATE_URL:
				message = "<strong>Error on server.</strong> Url <b>" + url + "</b> already exists. Please try another.";
				break;
			default:
				message = "<strong>Error on server.</strong> Please try again.";
				break;
			}
		}
		$("#servermessagescontainer").html(
				'<div role="alert" class="alert alert-' + (!error ? "success" : "danger") + ' alert-dismissible fade in">' +
			      '<button aria-label="Close" data-dismiss="alert" class="close" type="button"><span aria-hidden="true">×</span></button>' +
			      '<p>' + message + '</p>' +
			    '</div>');
	}

});

var ERROR = {};
	ERROR.URL = {},
	ERROR.URL.EMPTY = 0;
	ERROR.URL.INVALID = 1;
	ERROR.SERVER = {};
	ERROR.SERVER.DUPLICATE_URL = {};
	ERROR.SERVER.DUPLICATE_URL = "E_BUILDING_DUPLICATE_URL";
var REDIRECT_URL = "/myUrlUI/r/";

var re_current_url = new RegExp("oauth_token");
var re_vercode = new RegExp("oauth_verifier=");
var re_weburl = new RegExp(
		"^" + "((https?|ftp)\:\/\/)?([a-z0-9-.]*)([a-z]{2,4})(\:[0-9]{2,5})?");