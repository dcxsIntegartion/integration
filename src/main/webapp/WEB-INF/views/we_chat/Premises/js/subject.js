/** 楼盘介绍 **/
$("#introduce").bind("click",function(){
	window.location.href="./survey.html";
});
/** 楼盘介绍图片 **/
$("#img").bind("click",function(){
	window.location.href="./photo.html";
	});
/** 楼盘价值分析 **/
$("#value").bind("click",function(){
	window.location.href="./introduce.html";
	});
var bldInfo = {};
function getBldInfo(){
	var model = {};
	model.buildId=9;
	var data= encrypt(JSON.stringify(model),publicKey,privateKey,"md5");
	$.ajax({
		type: "get",
	    headers:{'sign': data.sign,'str':data.str,'times':data.times},
	    url:  basePath+"/bld/selOne",
	    data: {data:data.data},
	    contentType:"application/json",
	    success: function(data){
	    	if(data.status==1){
	    		bldInfo.info= data.data.info;
	    		bldInfo.sellPoint= data.data.sellPoint;
	    		bldInfo.houseType= data.data.houseType;
	    		bldInfo.planChartLst= data.data.planChartLst;
	    		bldInfo.supportingLst= data.data.supportingLst;
	    		bldInfo.realMapLst= data.data.realMapLst;
	    		bldInfo.designSketch= data.data.designSketch;
	    	}
		},
		error:function(data) {
		}
	});
}
$(function(){
	getBldInfo();
});
