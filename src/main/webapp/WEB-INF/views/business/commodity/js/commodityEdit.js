var view_url = basePath+"/bis/commodity/";
var commodityId = getQueryString("commodityId");
/** 提交按钮 **/
var submitHtml = $('#saveBtn').html();
var field = ["id","commodityName","commodityPrice","commodityOldPrice",
	"commoditySaleAdd","commodityNum","commodityNumber",
	"commoditySortNum","timingBegain","timingOff","commodityIntroduction",
	"commodityNumDecrease","commodityStatus","homepageShow","isTiming"];
var modelUtils = new ModelUtils(field);
$(function(){
	initPageData();
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
			model.commodityPic=saveRel(commodityPic);
			model.commodityPic2=saveRel(commodityPic2);
			model.commodityPic3=saveRel(commodityPic3);
			
			if(model.timingBegain != undefined && model.timingBegain != null 
					&& model.timingBegain != ""){
				model.timingBegain=model.timingBegain+":00";
			}
			if(model.timingOff != undefined && model.timingOff != null 
					&& model.timingOff != ""){
				model.timingOff=model.timingOff+":00";
			}//.checked 
			model.commodityNumDecrease = $(".checked input[name='commodityNumDecrease']").val();
			model.commodityStatus = $("#commodityStatusDiv .checked input[name='commodityStatus']").val();
			model.homepageShow = $(".checked input[name='homepageShow']").val();
			model.isTiming = $(".checked input[name='isTiming']").val();
			editAjax(model);
		}
	});
});
function editAjax(model){
	$.ajax({
        type: "post",
        url:  view_url+"update",
        data: JSON.stringify(model),
        contentType:"application/json",
        dataType: "json",
        success: function(data){
        		if(data.state==1){
        			layer.msg("商品信息修改成功！");
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
		}
	});
}
//初始化页面数据
function initPageData(){
	$.ajax({
        type: "post",
        async:false,
        url:  view_url+"findOne?id="+commodityId,
        success: function(data){
        	if(data.state==1){
        		modelUtils.fillData(data.data);
        		radioFill(data.data);
        	}
		},
		error:function(data) {
		}
    });
}
//单选按钮填充
function radioFill(data){
	//所属分类
	$("#viewForm #commodityTypeName").val(data.commodityTypeName);
	if(data.commodityNumDecrease == 0 ||
			data.commodityNumDecrease == "0"){
		$("#first").parent().addClass("checked");
	}else{
		$("#second").parent().addClass("checked");
	}
	
	if(data.commodityStatus == 1 ||
			data.commodityStatus == "1"){
		$("#commodityStatusfirst").parent().addClass("checked");
	}else{
		$("#commodityStatussecond").parent().addClass("checked");
	}
//	$('input[name="commodityStatus"][value="'+data.commodityStatus+'"]').iCheck('check');
	
	if(data.homepageShow == 1 ||
			data.homepageShow == "1"){
		$("#homepageShowfirst").parent().addClass("checked");
	}else{
		$("#homepageShowsecond").parent().addClass("checked");
	}
	
	if(data.isTiming == 1 ||
			data.isTiming == "1"){
		$("#isTimingfirst").parent().addClass("checked");
	}else{
		$("#isTimingsecond").parent().addClass("checked");
	}
}
//日期空间
function datePickerTool(){
	$('#viewForm input[name="timingBegain"]')
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
	   $('#viewForm input[name="timingOff"]').datetimepicker('setStartDate', value);
	 });
  $('#viewForm input[name="timingOff"]').datetimepicker({
    clearBtn: true,
    keyboardNavigation: false,
    language: "zh-CN",
    autoclose: true,
    todayHighlight: true,
    format: 'yyyy-mm-dd hh:ii'
  });	
}