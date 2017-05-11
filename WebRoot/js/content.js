var pic_count=0;
$(document).ready(function(){
	$("#search>input").focus(function(){
		$("#search").animate({width:'354px'},"fast");
	});
	$("#search>input").blur(function(){
		$("#search").animate({width:'284px'},"fast");
	});
	$("#container>ul>li").each(function(index){
		if(pic_count==index){
			$(this).fadeIn();
		}else{
			$(this).fadeOut();
		}
	});
	$("#list>ul>li").each(function(index){
		if(pic_count==index){
			$(this).css("background","rgba(255,102,0,.8)");
			$(this).css("color","#fff");
		}
	});
	$("#list>ul>li").each(function(index){
		$(this).click(function(){
			pic_count=index;
			show(pic_count);
			list_show(pic_count);
		});
	});
	setInterval(function(){
		pic_count=(++pic_count)%$("#container>ul>li").length;
		show(pic_count);
		list_show(pic_count);
	},4000);
});
function show(pic_count){
	$("#container>ul>li").each(function(index){
		if(pic_count==index){
			$(this).fadeIn();
		}else{
			$(this).fadeOut();
		}
	});
}
function list_show(pic_count){
	$("#list>ul>li").each(function(index){
		if(pic_count==index){
			$(this).css("background","rgba(255,102,0,.8)");
			$(this).css("color","#fff");
		}
		else{
			$(this).css("background","rgba(255,255,255,.5)");
			$(this).css("color","#999");
		}
	});
}