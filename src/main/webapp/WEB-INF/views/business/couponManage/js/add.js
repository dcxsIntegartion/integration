var view_url = basePath+"/bis/coupon/add";
/** 提交按钮 **/
var submitHtml = $('#saveBtn').html();
var field = ["couponId","couponName","couponMoney","couponBaseLine",
	"couponValidityStart","couponValidityEnd","couponReceiveEnd",
	"couponGrantNum","couponReceiveTimes","couponEveryNum","couponSort","couponDes",
	"couponShareImg","couponShareTitle","couponShareDes"];
var modelUtils = new ModelUtils(field);
$(function(){
	datePickerTool();
	var org_form = $('#viewForm').Validform({
		tiptype:function(msg,o,cssctl){
			if(!o.obj.is("form")){
				var objtip=o.obj.parent().siblings(".Validform_checktip");
				cssctl(objtip,o.type);
				objtip.text(msg);
			}
		},
		callback:function($form){
			var model = modelUtils.initModel();
			/** 上传图片字段赋值 **/
			model.couponShareImg=saveRel(couponShareImg);
			if(model.couponValidityStart != undefined && model.couponValidityStart != null 
					&& model.couponValidityStart != ""){
				model.couponValidityStart=model.couponValidityStart+":00";
			}
			if(model.couponValidityEnd != undefined && model.couponValidityEnd != null 
					&& model.couponValidityEnd != ""){
				model.couponValidityEnd=model.couponValidityEnd+":00";
			}
			if(model.couponReceiveEnd != undefined && model.couponReceiveEnd != null 
					&& model.couponReceiveEnd != ""){
				model.couponReceiveEnd=model.couponReceiveEnd+":00";
			}
			var data= encrypt(JSON.stringify(model),publicKey,privateKey,"md5");
			$.ajax({
	            type: "post",
	            url:  view_url,
	            headers:{'sign': data.sign,'str':data.str,'times':data.times},
	            data: data.data,
	            contentType:"application/json",
	            dataType: "json",
	            success: function(data){
	            		if(data.state==1){
	            			layer.msg("添加优惠券成功！");
	            			//返回上一页
		            		window.history.back(-1);
	            		}else{
	            			layer.msg(data.msg);
	            		}
				},
				error:function(data) {
					layer.msg("服务器异常");
				},
				complete:function(){
//					submitToggle(0,submitHtml);
				}
			});
		}
	});
});
function datePickerTool(){
	/** 时间插件 **/
	$('#datetime_01 .date[name=couponValidityStart]')
	 .datetimepicker({
	   clearBtn: true,
	   keyboardNavigation: false,
	   language: "zh-CN",
	   autoclose: true,
	   todayHighlight: true,
	   format: 'yyyy-mm-dd hh:ii'
	 })
	 .on('changeDate', function(ev){
	   var value = $(this).val();
	   $('#datetime_01 .date[name=couponValidityEnd]').datetimepicker('setStartDate', value);
	 });
   $('#datetime_01 .date[name=couponValidityEnd]').datetimepicker({
     clearBtn: true,
     keyboardNavigation: false,
     language: "zh-CN",
     autoclose: true,
     todayHighlight: true,
     format: 'yyyy-mm-dd hh:ii'
   });	
   $("#couponReceiveEnd").datetimepicker({
	     clearBtn: true,
	     keyboardNavigation: false,
	     language: "zh-CN",
	     autoclose: true,
	     todayHighlight: true,
	     format: 'yyyy-mm-dd hh:ii'
   });	
}