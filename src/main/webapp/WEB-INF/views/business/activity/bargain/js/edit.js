var id = getQueryString("id");
var type = getQueryString("type");
var view_url = basePath + "/bis/activityBargain/";
/** 页面数据封装 填充 * */
var model = {};
var field = [ "id", "activityName", "activityStartTimeStr",
		"activityEndTimeStr", "activityStoreId", "activityStoreName",
		"activityExplain", "shareTitle", "shareDescribe", "activityPic",
		"sharePic" ];
var modelUtils = new ModelUtils(field);
/** 提交按钮 * */
var submitHtml = $('#saveBtn').html();
$(function() {
	gridObj = $.fn.bsgrid.init('searchTable', {
		url : basePath + "/bis/commodity/getavtivityCommodity",
		pageSizeSelect : true,
		stripeRows : true,
		pageSize : 10,
		pagingLittleToolbar : false
	});
	gridObj2 = $.fn.bsgrid.init('selectedTable', {
		url : basePath + "/bis/commodity/getavtivityCommodity",
//		pageAll : true,
		pageSize : 10,
		extend : {
			settings : {
				supportGridEdit : true,
				supportGridEditTriggerEvent : 'rowClick'
			}
		}
	});
	/** 时间插件 * */
	$('#activityStartTimeStr').datetimepicker({
		clearBtn : true,
		keyboardNavigation : false,
		language : "zh-CN",
		autoclose : true,
		todayHighlight : true,
		format : 'yyyy-mm-dd hh:ii'
	}).on('changeDate', function(ev) {
		var value = $(this).val();
		$('#couponValidityEnd').datetimepicker('setStartDate', value);
	});
	$('#activityEndTimeStr').datetimepicker({
		clearBtn : true,
		keyboardNavigation : false,
		language : "zh-CN",
		autoclose : true,
		todayHighlight : true,
		format : 'yyyy-mm-dd hh:ii'
	});
	/** 查、改、增页面控制 * */
	viewLoadControlle();
	var org_form = $('#viewForm').Validform({
		tiptype : function(msg, o, cssctl) {
			if (!o.obj.is("form")) {
				var objtip = o.obj.parent().siblings(".Validform_checktip");
				cssctl(objtip, o.type);
				objtip.text(msg);
			}
		},
		callback : function($form) {
			var model = modelUtils.initModel();
			/** 上传图片字段赋值 * */
			model.activityPic = saveRel(activityPic);
			model.sharePic = saveRel(sharePic);
			// 活动商品列表
			var bisActivityCommodityRList = getActivityCommodityRList();
			if (bisActivityCommodityRList == false) {
				return;
			}
			// 接口body
			var vo = {
				bisActivityBargain : model,
				bisActivityCommodityRList : bisActivityCommodityRList
			};
			console.log("requestBody", vo);
			console.log("requestBodyStr", JSON.stringify(vo));
			submitToggle(1, submitHtml);
			$.ajax({
				type : "post",
				url : view_url + "/updateById",
				data : JSON.stringify(vo),
				contentType : "application/json",
				dataType : "json",
				success : function(data) {
					if (data.state == 1) {
						onunload_state = false;
						window.location.href = "index.html";
					} else {
						layer.msg(data.msg);
					}
				},
				error : function(data) {
				},
				complete : function() {
					submitToggle(0, submitHtml);
				}
			});
			// 表单验证通过进行回掉处理
			return false;
		}
	});
});
/** 提交按钮控制 * */
function submitToggle(status, submitHtml) {
	if (status == 0) {
		$('#saveBtn').html(submitHtml).attr('disabled', false);
	} else if (status == 1) {
		$('#saveBtn').html('<i class="fa fa-spin fa-spinner"></i>    正在提交')
				.attr('disabled', true);
		// 隐藏按钮
	} else if (status == 2) {
		$('#saveBtn').hide();
	}
}
/** 页面数据加载控制器 * */
function viewLoadControlle() {
	/** 加载数据 * */
	if (type == "view" || type == "update") {
		if (type == "view")
			submitToggle(2, submitHtml);
		$.ajax({
			type : "post",
			async : false,
			url : view_url + "selectById?Id=" + id,
			success : function(data) {
				if (data.state == 1) {
					console.log("data", data);
					modelUtils.fillData(data.data.activity);
					selectedCommodities = data.data.activityCommodityId;
					storeId = data.data.activity.activityStoreId;
					updateCommidities();
				}
			},
			error : function(data) {
			}
		});
	}
}

var selectedCommodities = [];// 已选择的商品列表id
var gridObj;// 商品选择列表
var gridObj2;// 已选择商品列表
/** *更新商品列表*** */
function updateCommidities() {
	gridObj.options.otherParames = {
		selected : 'false',
		storeId : storeId,
		// activityId : id,
		// activityType : 1,
		selectedCommodities : JSON.stringify(selectedCommodities)
	};
	gridObj.page(1);
	gridObj2.options.otherParames = {
		selected : 'true',
		storeId : storeId,
		activityId : id,
		activityType : 1,
		selectedCommodities : JSON.stringify(selectedCommodities)
	};
	gridObj2.page(1);
}
/** 列表操作按钮 * */
function operate(record, rowIndex, colIndex, options) {
	var op_html = '';
	var commodity = JSON.stringify(record);
	console.log("未选中的商品",record);
	op_html += '<a  href="javascript:void(0);" class="btn btn-outline btn-primary btn-xs mhx" onclick="selectCommodity(\''
			+ rowIndex + '\');">选择</a>';
	return op_html;
};
function operate2(record, rowIndex, colIndex, options) {
	var op_html = '';
	console.log("选中的商品",record);
	op_html += '<a  href="javascript:void(0);" class="btn btn-outline btn-primary btn-xs mhx" onclick="removeCommodity(\''
			+ record + '\');">移除</a>';
	return op_html;
};

/** 选择商品 * */
function selectCommodity(rowIndex) {
	record = gridObj.getRecord(rowIndex);
	// 将商品加入集合
	selectedCommodities.push(record.id);
	// 刷新列表
	updateCommidities();
}
/** 移除商品 * */
function removeCommodity(record) {
	// 迭代删除需要移除的商品
	var id = record.id;
	for (var i = 0; i < selectedCommodities.length; i++) {
		if (selectedCommodities[i].id == id) {
			selectedCommodities.splice(i, 1);
			break;
		}
	}
	// 刷新列表
	updateCommidities();
}

/** *店铺选择** */
var storeId = "";
function fnLoadStore(selectStoreId, storeName) {
	storeId = selectStoreId;
	$("#activityStoreId").val(storeId);
	$("#activityStoreName").val(storeName);
	updateCommidities();
}

/**
 * 获取选中商品的信心
 * 
 * @returns
 */
function getActivityCommodityRList() {
	var bisActivityCommodityRList=[];
	var getRowsChangedColumnsValue = gridObj2.getRowsChangedColumnsValue();//修改过的行数据
	var getChangedRowsOldRecords = gridObj2.getChangedRowsOldRecords();// 修改过的行原来的值
	//所有商品
	for (var i = 0; i < selectedCommodities.length; i++) {
		var commodityId = gridObj2.getRecord(i).id;
		//活动价格
		var activityPrice = gridObj2.getRecord(i).activityPrice;
		if (activityPrice == undefined && getRowsChangedColumnsValue["row_"+ i] != undefined) {//价格修改过
			activityPrice = getRowsChangedColumnsValue["row_"+ i].activityPrice;
		}
		//活动库存
		var commodityNum = gridObj2.getRecord(i).activityNum;
		if (commodityNum == undefined && getRowsChangedColumnsValue["row_"+ i] != undefined) {//库存修改过
			commodityNum = getRowsChangedColumnsValue["row_"+ i].activityNum;
		}
		var commoditTotleNum = gridObj2.getRecord(i).commodityNum;//总库存
		//库存判断
		if (commodityNum > commoditTotleNum) {
			var commodityName = gridObj2.getRecord(i).commodityName;
			layer.msg("商品"+commodityName+"库存不足！");
			return false;
		}
		if (activityPrice == "" || activityPrice == undefined
				|| commodityNum == "" || commodityNum == undefined) {
			layer.msg("活动商品未填写完全：第" + i + "行");
			return false;
		}
		var activityCommodity = {
			commodityId : commodityId,
			activityPrice : activityPrice,
			commodityNum : commodityNum
		};
		bisActivityCommodityRList.push(activityCommodity);
	}

	return bisActivityCommodityRList;
}
