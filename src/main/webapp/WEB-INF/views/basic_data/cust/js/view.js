var id = getQueryString("id");
var type = getQueryString("type");
var view_url = basePath+"/cust/modelLv/";
/** 页面数据封装 填充 **/
var model ={};
var field = ["id","trueName","phone","inviterMp","gender","projectId","mechanismId","headImg","introImg"];
var modelUtils = new ModelUtils(field);
/** 提交按钮 **/
var submitHtml = $('#saveBtn').html();
$(function(){
	/** 查、改、增页面控制 **/
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
			/** 上传图片字段赋值 **/
			model.headImg=saveRel(headImg);
			model.introImg=saveRel(introImg);
			/** 所在区域赋值 **/
			model.area=selectedcityfullname;
			model.areaCode=selectedcityCode;
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
	        url:  view_url+"view?id="+id,
	        success: function(data){
	        	if(data.state==1){
	        		$("#areaCode").val(data.data.areaCode);
	        		modelUtils.fillData(data.data);
	        	}
			},
			error:function(data) {
			}
	    });
	}
}