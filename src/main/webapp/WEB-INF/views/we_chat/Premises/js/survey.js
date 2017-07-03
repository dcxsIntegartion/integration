$(function(){
	$(".address").hide();
});
/** 展示楼盘概况 **/
$("#presentBtn").bind("click",function(){
	$(".present").show();
	$(".address").hide();
	$("#presentBtn").addClass("active");
	$("#addressBtn").removeClass("active");
	
});
/** 展示楼盘地址 **/
$("#addressBtn").bind("click",function(){
	$(".present").hide();
	$(".address").show();
	$("#addressBtn").addClass("active");
	$("#presentBtn").removeClass("active");
});
