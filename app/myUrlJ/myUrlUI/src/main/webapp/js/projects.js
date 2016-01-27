$(document).ready(function(){
	var project = {};
	var projects = JSON.parse(localStorage.getItem("projects"));
	var html = [];
	
	html = ('<div class="row">');
	$(".jumbotron").append(html);
	for (var i = 0 ; i < projects.length; i ++) {	
		project = projects[i];
		
		$(".jumbotron").append( 
				'<div class="col-xs-3">'+
				'<div class = "panel panel-primary"><div class = "panel-heading">'+
				'<h3 class = "panel-title">'+'<a href="PRO/key='+project.key+'">'+project.key+'</a></h3>'+
				'</div><div class = "panel-body">'+
				'<div style="float:left; margin-right:10px"><img src='+project.avatarUrls["32x32"]+' width="32" height="32"></div>'+
				'<div style="padding: 2px">' +project.name + '</div>' +
				capitalizeFirstLetter(project.projectTypeKey)+
				'</div></div>'+
				'</div>' 
				);
	}
	
	html = ('</div>');
	$(".jumbotron").append(html);
	
});

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}