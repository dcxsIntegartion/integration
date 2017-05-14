var grid_url = basePath + "/bis/store/";
var gridObj;
/****门店选择操作***/
function operate(record, rowIndex, colIndex, options) {
	var storeId = record.id;
	var storeName = record.storeName;
	return "<a  href=\"#\" class=\"btn btn-primary btn-xs mhx \" "
			+ "onclick=\"forStoreChoose('"
			+ storeId + "','"
			+ storeName + "')\">选择</a>";
}

/**
 * 功能 : 1、选择门店后调用父界面函数fnLoadStore将选择的楼盘信息进行回填 2、关闭该选择界面
 */
function forStoreChoose(storeId, storeName) {
	parent.frames[0].fnLoadStore(storeId, storeName);
	parent.$.fancybox.close();
	return false;
}

function doSearch() {
	gridObj.options.otherParames = $("#search_build_form").serializeArray();
	gridObj.page(1);
}

function resetForm() {
	$("#search_build_form")[0].reset();
	gridObj.options.otherParames = $("#search_build_form").serializeArray();
	gridObj.page(1);
}

$(function() {
	gridObj = $.fn.bsgrid.init('searchTable', {
		url : grid_url + "page",
		pageSizeSelect : true,
		stripeRows : true,
		otherParames : $("#search_form").serializeArray(),
		pageSize : 10,
		pagingLittleToolbar : false
	});
});