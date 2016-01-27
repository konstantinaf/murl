$(document).ready(function(){
	var location = window.location.href;
	var url = localStorage.getItem("url");
	
	if(re_url.test(location)) {
		alert(location.split(re_url)[1].trim());
		$.ajax({
			url: '/myUrlRest/api/url/getIssues/',
			type: 'post',
			data: JSON.stringify({
                url: url, token: location.split(re_url)[1].trim()
            }),
            dataType: 'json',
            contentType: 'application/json',
            success: function(jsonRes){
            	console.log(jsonRes);
            }
		});
	} else {
		showError(ERROR.INVALID_TOKEN);
	}
	
	function showError(error){
		var message = "";
		switch (error) {
		case ERROR.INVALID_TOKEN:
			message = "It seems that your link is invalid."
			break;

		default:
			break;
		}
		$("#messagescontainer").html(
							'<div role="alert" class="alert alert-danger alert-dismissible fade in">' +
						      '<button aria-label="Close" data-dismiss="alert" class="close" type="button"><span aria-hidden="true">×</span></button>' +
						      '<strong>Redirect error. </strong> ' + message +
						    '</div>'
						);
	}
});

var re_url = new RegExp("key=");
var ERROR = {};
ERROR.INVALID_TOKEN = 0;