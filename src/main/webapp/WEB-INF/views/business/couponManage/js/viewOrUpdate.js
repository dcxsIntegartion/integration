var couponId = getQueryString("couponId");
var type = getQueryString("type");
var view_url = basePath+"/bis/coupon/";
var submitHtml = $('#saveBtn').html();
var field = ["couponId","couponName","couponMoney","couponBaseLine",
	"couponStatus","couponValidityStart","couponValidityEnd","couponReceiveEnd",
	"couponCreateTime","couponGrantNum","couponReceiveTimes","couponEveryNum",
	"couponSort","couponDes","couponShareImg","couponShareTitle","couponShareDes"];
var modelUtils = new ModelUtils(field);
$(function(){
	viewLoadControlle();
	var org_form = $('#viewForm').Validform({
		tiptype:function(msg,o,cssctl){
			if(!o.obj.is("form")){
				var objtip=o.obj.parent().siblings(".Validform_checktip");
				cssctl(objtip,o.type);
				objtip.text(msg);
			}
		},
		callback:function($form){
			submitToggle(1,submitHtml);
			var model = modelUtils.initModel();
			$.ajax({
	            type: "post",
	            url:  view_url+type,
	            data: JSON.stringify(model),
	            contentType:"application/json",
	            dataType: "json",
	            success: function(data){
	            		if(data.state==1){
	            			onunload_state = false;
	            			window.location.href="index.html";
	            		}else{
	            			layer.msg(data.msg);
	            		}
				},
				error:function(data) {
					layer.msg("服务器异常");
				},
				complete:function(){
					submitToggle(0,submitHtml);
				}
	        });
			//表单验证通过进行回掉处理
			return false;
		}
	});
})
/** 页面数据加载控制器 **/
function viewLoadControlle(){
	/** 加载数据 **/
	if(type=="view" || type=="update"){
		if(type=="view")submitToggle(2,submitHtml);
		$.ajax({
	        type: "post",
	        async:false,
	        url:  view_url+"view?id="+couponId,
	        success: function(data){
	        	if(data.state==1){
//	        		$("#couponImg").attr("src",basePath+"/bis/coupon/img/"+data.data.couponShareImg+"");//
	        		modelUtils.fillData(data.data);
	        	}
			},
			error:function(data) {
			}
	    });
	}
}
/** 提交按钮控制 **/
function submitToggle(status, submitHtml) {
	if (status == 0) {
		$('#saveBtn').html(submitHtml).attr('disabled', false);
	}else if(status == 1){
		$('#saveBtn').html('<i class="fa fa-spin fa-spinner"></i>    正在提交').attr('disabled', true);
	//隐藏按钮
	}else if(status == 2){
		$('#saveBtn').hide();
	}
}