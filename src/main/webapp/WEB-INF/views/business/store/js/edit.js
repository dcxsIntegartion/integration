var id = getQueryString("id");
var type = getQueryString("type");
var view_url = basePath+"/bis/store/";
/** 页面数据封装 填充 **/
var model ={};
var field = ["id","storeName","storeAddress","storePhone","storePic","storeWxQr","storeIntroduction"
	,"storeLongitude","storeLatitude","storeSortNum","storeStatus","storeUserId","appid","subDomain"];
var modelUtils = new ModelUtils(field);
/** 提交按钮 **/
var submitHtml = $('#saveBtn').html();
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
			/**需要修改 上传图片字段赋值 **/
			model.storePic=saveRel(storePic);
			model.storeWxQr=saveRel(storeWxQr);
			var data= encrypt(JSON.stringify(model),publicKey,privateKey,"md5");
			$.ajax({
	            type: "post",
	            url:  view_url+"updateById",
	            headers:{'sign': data.sign,'str':data.str,'times':data.times},
	            data: data.data,
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
				},
				complete:function(){
					submitToggle(0,submitHtml);
				}
	        });
			//表单验证通过进行回掉处理
			return false;
		}
	});
});
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

/** 页面数据加载控制器 **/
function viewLoadControlle(){
	/** 加载数据 **/
	if(type=="view" || type=="update"){
		if(type=="view")submitToggle(2,submitHtml);
		$.ajax({
	        type: "post",
	        async:false,
	        url:  view_url+"selectById?Id="+id,
	        success: function(data){
	        	if(data.state==1){
	        		/**需要修改 图片字段赋值 **/
	        		modelUtils.fillData(data.data);
	        		selFiles("storePic",storePic);
	        		selFiles("storeWxQr",storeWxQr);
	        	}
			},
			error:function(data) {
			}
	    });
	}
}
