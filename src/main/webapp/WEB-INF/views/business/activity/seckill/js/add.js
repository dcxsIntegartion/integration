var view_url = basePath + "/bis/activitySeckil/";
/** 页面数据封装 填充 * */
var model = {};
var field = [ "activityName", "activityDayTimeStr", "activityPointTime",
		"activityStoreId", "activityExplain", "shareTitle", "shareDescribe",
		"activityPic", "sharePic" ];
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
		pageAll : true,
		extend : {
			settings : {
				supportGridEdit : true,
				supportGridEditTriggerEvent : 'rowClick'
			}
		}
	});
	/** 时间插件 * */
	$('#activityDayTimeStr').datetimepicker({
		clearBtn : true,
		keyboardNavigation : false,
		language : "zh-CN",
		autoclose : true,
		todayHighlight : true,
		format : 'yyyy-mm-dd'
	});
	var org_form = $('#viewForm')
			.Validform(
					{
						tiptype : function(msg, o, cssctl) {
							if (!o.obj.is("form")) {
								var objtip = o.obj.parent().siblings(
										".Validform_checktip");
								cssctl(objtip, o.type);
								objtip.text(msg);
							}
						},
						callback : function($form) {
							submitToggle(1, submitHtml);
							var model = modelUtils.initModel();
							/** 上传图片字段赋值 * */
							model.activityPic = saveRel(activityPic);
							model.sharePic = saveRel(sharePic);
							// 活动商品列表
							var bisActivityCommodityRList = [];
							var getChangedRowsIndexs = gridObj2
									.getChangedRowsIndexs();

							var getChangedRowsOldRecords = gridObj2
									.getChangedRowsOldRecords();// 原来的值
							if (getChangedRowsIndexs.length < getChangedRowsOldRecords.length) {
								layer.msg("请填写所有活动商品信息！");
								return;
							}

							var getRowsChangedColumnsValue = gridObj2
									.getRowsChangedColumnsValue();
							console.log("getRowsChangedColumnsValue",
									getRowsChangedColumnsValue);// 新的值
							// 封装活动商品
							for (var i = 0; i < getChangedRowsIndexs.length; i++) {
								var activityPrice = getRowsChangedColumnsValue["row_"
										+ i].activityPrice;
								var commodityNum = getRowsChangedColumnsValue["row_"
										+ i].activityCommodityNum;
								if (activityPrice == ""
										|| activityPrice == undefined
										|| commodityNum == ""
										|| commodityNum == undefined) {
									layer.msg("活动商品未填写完全：第" + i + "行");
									return;
								}
								var activityCommodity = {
									commodityId : gridObj2
											.getRecord(getChangedRowsIndexs[i]).id,
									activityPrice : activityPrice,
									commodityNum : commodityNum
								};
								bisActivityCommodityRList
										.push(activityCommodity);
							}
							// 接口body
							var vo = {
								bisActivitySeckil : model,
								bisActivityCommodityRList : bisActivityCommodityRList
							};
							console.log("requestBody", vo);
							console.log("requestBodyStr", JSON.stringify(vo));
							$.ajax({
								type : "post",
								url : view_url + "/insert",
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

var selectedCommodities = [];// 已选择的商品列表id
var gridObj;// 商品选择列表
var gridObj2;// 已选择商品列表
/** *更新商品列表*** */
function updateCommidities() {
	gridObj.options.otherParames = {
		selected : 'false',
		storeId : storeId,
		selectedCommodities : JSON.stringify(selectedCommodities)
	};
	gridObj.page(1);
	gridObj2.options.otherParames = {
		selected : 'true',
		storeId : storeId,
		selectedCommodities : JSON.stringify(selectedCommodities)
	};
	gridObj2.page(1);
}
/** 列表操作按钮 * */
function operate(record, rowIndex, colIndex, options) {
	var op_html = '';
	var commodity = JSON.stringify(record);
	console.log("commodity", record);
	console.log("rowIndex", rowIndex);
	op_html += '<a  href="javascript:void(0);" class="btn btn-outline btn-primary btn-xs mhx" onclick="selectCommodity(\''
			+ rowIndex + '\');">选择</a>';
	return op_html;
};
function operate2(record, rowIndex, colIndex, options) {
	var op_html = '';
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
