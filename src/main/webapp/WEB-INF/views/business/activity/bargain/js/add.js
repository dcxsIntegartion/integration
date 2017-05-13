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
	gridObj = $.fn.bsgrid.init('searchTable', {
		   url : basePath+"/bis/commodity/getavtivityCommodity",
	       pageSizeSelect: true,
	       stripeRows: true,
//	       otherParames: {
//	       	storeId:storeId,
//	       	selectedCommodities:JSON.stringify(selectedCommodities)
//	       },
	       pageSize: 10,
	       pagingLittleToolbar:false
	  });
		gridObj2 = $.fn.bsgrid.init('selectedTable', {
			url : basePath+"/bis/commodity/getavtivityCommodity",
//			localData: selectedCommoditiesInfo,
			pageAll: true,
			extend: {
	            settings: {
	                supportGridEdit: true, 
	                supportGridEditTriggerEvent: 'rowClick' 
	            }
	        }
		});
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
	//商品选择步骤：
	//1·每选择一个弹出输入数量和价格框
	//2.每选择一次将商品id传入后台刷新列表，将选中的商品、价格、数量显示在选中列表
}

var selectedCommodities = [];//已选择的商品列表id
//var selectedCommoditiesInfo = [ {commodityNumber: "001", commodityOldPrice: 100, commodityStoreId: 2, commodityStatus: 2}];//已选择的商品详情

var gridObj;//商品选择列表
var gridObj2;//已选择商品列表
/***更新商品列表****/
function updateCommidities(){
	gridObj.options.otherParames = {
			selected: 'false',
			storeId:storeId,
        	selectedCommodities:JSON.stringify(selectedCommodities)
	};
	gridObj.page(1);
	gridObj2.options.otherParames ={
			selected: 'true',
			storeId:storeId,
        	selectedCommodities:JSON.stringify(selectedCommodities)
	};
	gridObj2.page(1);
}
/** 列表操作按钮 **/
function operate(record, rowIndex, colIndex, options) {
	var op_html ='';
//	var state = record.mach_state;
	var commodity = JSON.stringify(record);
	console.log("commodity",record);
	console.log("rowIndex",rowIndex);
	op_html+= '<a  href="javascript:void(0);" class="btn btn-outline btn-primary btn-xs mhx" onclick="selectCommodity(\'' + rowIndex + '\');">选择</a>';
	return op_html;
};
function operate2(record, rowIndex, colIndex, options) {
	var op_html ='';
//	var state = record.mach_state;
	op_html+= '<a  href="javascript:void(0);" class="btn btn-outline btn-primary btn-xs mhx" onclick="removeCommodity(\'' + record + '\');">移除</a>';
	return op_html;
};

/** 选择商品 **/
function selectCommodity(rowIndex){
//	alert(111);
	record = gridObj.getRecord(rowIndex);
	console.log("record", record);
	//将商品加入集合
	selectedCommodities.push(record.id);
//	selectedCommoditiesInfo.push(record);
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
/***店铺选择***/
var storeId = "";
function fnLoadStore(selectStoreId, storeName){
	storeId =selectStoreId;
	$("#activityStoreName").val(storeId);
	$("#activityStoreName").val(storeName);
	updateCommidities();
}
function aaaa(){
	alert(11111111);
}
