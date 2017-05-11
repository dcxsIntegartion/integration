var id = getQueryString("id");
var type = getQueryString("type");
var view_url = basePath+"/bis/activityBargain/";
/** 页面数据封装 填充 **/
var model ={};
var field = ["activityName","activityStartTime","activityEndTime","activityStoreId",
	"activityExplain","shareTitle","shareDescribe", "activityPic", "sharePic"];
var modelUtils = new ModelUtils(field);
/** 提交按钮 **/
var submitHtml = $('#saveBtn').html();
$(function(){
	/** 时间插件 **/
	$('#activityStartTime')
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
	   $('#couponValidityEnd').datetimepicker('setStartDate', value);
	 });
   $('#activityEndTime').datetimepicker({
     clearBtn: true,
     keyboardNavigation: false,
     language: "zh-CN",
     autoclose: true,
     todayHighlight: true,
     format: 'yyyy-mm-dd hh:ii'
   });	
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
			model.activityPic=saveRel(activityPic);
			model.sharePic=saveRel(sharePic);
			//活动商品列表
			var bisActivityCommodityRList=[];
			bisActivityCommodityRList = gridObj2.getAllRecords();
			//接口body
			var vo={
					bisActivityBargain:model,
					bisActivityCommodityRList:bisActivityCommodityRList
			};
			console.log("requestBody",vo);
			$.ajax({
	            type: "post",
	            url:  view_url+type,
	            data: JSON.stringify(vo),
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
        		$("#areaCode").val(data.data.machAreaCode);
        		modelUtils.fillData(data.data);
        	}
		},
		error:function(data) {
		}
    });
}

var selectedCommodities = [];//已选择的商品列表

var gridObj;//商品选择列表
var gridObj2;//已选择商品列表
/***更新商品列表****/
function updateCommidities(){
	gridObj = $.fn.bsgrid.init('searchTable', {
		 url : view_url+"获取未选择的商品列表",
        pageSizeSelect: true,
        stripeRows: true,
        otherParames: {
        	selectedCommodities:selectedCommodities
        },
        pageSize: 10,
        pagingLittleToolbar:false
   });
	gridObj2 = $.fn.bsgrid.init('selectedTable', {
		localData: selectedCommodities,
		pageSize: 10,
		pagingLittleToolbar:false
	});
}
/** 列表操作按钮 **/
function operate(record, rowIndex, colIndex, options) {
	var op_html ='';
//	var state = record.mach_state;
	op_html+= '<a  href="javascript:void(0);" class="btn btn-outline btn-primary btn-xs mhx" onclick="selectCommodity(\'' + record + '\');">选择</a>';
	return op_html;
};
function operate2(record, rowIndex, colIndex, options) {
	var op_html ='';
//	var state = record.mach_state;
	op_html+= '<a  href="javascript:void(0);" class="btn btn-outline btn-primary btn-xs mhx" onclick="removeCommodity(\'' + record + '\');">移除</a>';
	return op_html;
};

/** 选择商品 **/
function selectCommodity(record){
	//将商品加入集合
	selectedCommodities.push(record);
	//刷新列表
	updateCommidities();
}
/** 移除商品 **/
function removeCommodity(record){
	//迭代删除需要移除的商品
	var id = record.id;
	for(var i=0; i<selectedCommodities.length; i++) {
    if(selectedCommodities[i].id == id) {
	    	selectedCommodities.splice(i, 1);
	      break;
	    }
	}
	//刷新列表
	updateCommidities();
}

/***门店选择弹出框***/
//$('#activityStoreName').click(function () {
//	layer.open({
//	    type: 2,
//	    title: '门店选择',
//	    shadeClose: true,
//	    maxmin: true, //开启最大化最小化按钮
//	    shade: 0.8,
//	    area: ['500px', '99%'],
//	    content: "../../commonSelect/storeSelect.html" //iframe的url
//	}); 
//});
/***门店选择回调函数****/
function fnLoadStore(storeId, storeName){
	$("#activityStoreId").val(storeId);
	$("#activityStoreName").val(storeName);
	//清空选择的商品列表
	selectedCommodities=[];
	//请求/重新请求商品列表
	updateCommidities();
}
function aaaa(){
	alert(11111111);
}
	//商品选择步骤：
	//1·每选择一个弹出输入数量和价格框
	//2.每选择一次将商品id传入后台刷新列表，将选中的商品、价格、数量显示在选中列表
}