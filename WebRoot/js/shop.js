$(document).ready(function(){
	$("#jfhl").click(function(){
		$("#jfhl").addClass("active");
		$("#dhjl").removeClass("active");
	});
	$("#dhjl").click(function(){
		$("#dhjl").addClass("active");
		$("#jfhl").removeClass("active");
	});
});