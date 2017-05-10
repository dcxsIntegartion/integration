var view_url = basePath+"/bis/commodity/";
/** 提交按钮 **/
var submitHtml = $('#saveBtn').html();
var field = ["id","commodityName","commodityPrice","commodityOldPrice",
	"commoditySaleAdd","commodityNum","commodityNumber",
	"commoditySortNum","timingBegain","timingOff","commodityIntroduction",
	"commodityNumDecrease","commodityStatus","homepageShow","isTiming"];
var modelUtils = new ModelUtils(field);
$(function(){
	$("#first").parent().addClass("checked");
	$("#commodityStatusfirst").parent().addClass("checked");
	$("#homepageShowsecond").parent().addClass("checked");
	$("#isTimingsecond").parent().addClass("checked");
	datePickerTool();
	//
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
			addAjax(model);
		}
	});
});

function addAjax(model){
	$.ajax({
        type: "post",
        url:  view_url+"add",
        data: JSON.stringify(model),
        contentType:"application/json",
        dataType: "json",
        success: function(data){
        		if(data.state==1){
        			layer.msg("添加商品成功！");
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