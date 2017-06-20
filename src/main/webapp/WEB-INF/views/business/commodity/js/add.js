var view_url = basePath+"/bis/commodity/";
/** 提交按钮 **/
var submitHtml = $('#saveBtn').html();
var field = ["id","commodityName","commodityPrice","commodityOldPrice",
	"commoditySaleAdd","commodityNum","commodityNumber",
	"commoditySortNum","timingBegain","timingOff",
	"commodityNumDecrease","commodityStatus","homepageShow","isTiming"];//"commodityIntroduction",
var modelUtils = new ModelUtils(field);
var treeAddObj = null;
$(function(){
	$("#first").parent().addClass("checked");
	$("#commodityStatusfirst").parent().addClass("checked");
	$("#homepageShowsecond").parent().addClass("checked");
	$("#isTimingsecond").parent().addClass("checked");
	datePickerTool();
	//初始化树
	initPage();
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
			/*model.commodityPic2=saveRel(commodityPic2);
			model.commodityPic3=saveRel(commodityPic3);*/
			
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
			model.commodityTypeId = $("#viewForm #commodityTypeId").val();
			
			model.commodityIntroduction = getAllHtml();
			
			var selectNode = treeAddObj.getSelectedNodes();
			if(selectNode.length == 0){
				layer.msg("请选择所属分类");
			}else{
				addAjax(model);
			}
			return false;
		}
	});
});

function addAjax(model){
	var data= encrypt(JSON.stringify(model),publicKey,privateKey,"md5");
	$.ajax({
        type: "post",
        url:  view_url+"add",
        headers:{'sign': data.sign,'str':data.str,'times':data.times},
        data: data.data,
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
function initPage(){
	$.ajax({
		type: "post",
        url: basePath+"/bis/commodityType/tree",
        contentType:"application/json",
        dataType: "json",
        success: function(data){
        		if(data.state==1 || data.state == 'y'){
        			initTree(data.data);
        		}else{
        			layer.msg(data.msg);
        		}
		},
		error:function(data) {
			layer.msg("服务器异常");
		}
	});
}
function initTree(data){
	var setting = {
		data:{
			key:{
				children:"children",
				name:"typeName",
				title:"typeName"
			},
			simpleData:{
				idKey:"typeId",
				pIdKey:"parTypeId",
				enable:true
			}
		},
		callback:{
			onClick:function(event, treeId, treeNode){
				treeNodeClick(treeId, treeNode);
			}
		}
	};
	treeAddObj = $.fn.zTree.init($("#resource_view"), setting, data);
	treeAddObj.expandAll(true);
}
function treeNodeClick(treeId, treeNode){
	if(treeNode.children == null){
		$("#viewForm #commodityTypeId").val(treeNode.typeId);
		$("#viewForm #commodityTypeName").val(treeNode.typeName);
	}
}