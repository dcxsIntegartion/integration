$(function() {
    $('input, textarea').placeholder();//让IE8，IE9支持placeholder
    window.fnStatus = function(record, rowIndex, colIndex, options){
    	switch(record.coupon_status){
    	case 1:
    		return '<span style="color:#3EC07F">已上线</span>';
    	case 2:
    		return '<span style="color:#EB5413">已过期</span>';
    	case 3:
    		return '<span style="color:#C03EB5">已下线</span>';
    	case 4:
    		return '<span style="color:#845600">未生效</span>';
    	}
    }
    //操作
    window.operate =function(record, rowIndex, colIndex, options) {
    	var op_html ='';
    	op_html+= '<a  href="javascript:void(0);" class="btn btn-primary btn-xs mhx" onclick="forUpdate(\'' + gridObj.getRecordIndexValue(record, 'coupon_id') + '\');">编辑</a>';
    	op_html+= '<a  href="javascript:void(0);" class="btn btn-warning btn-xs mhx" onclick="forDetail(\'' + gridObj.getRecordIndexValue(record, 'coupon_id') + '\');">详情</a>';
		return op_html;
    }
    //编辑
    window.forUpdate = function(Id){
    	window.location.href = "baseViewUpdate.html?type=update&id="+Id;
	}
    //查看详细
    window.forDetail = function(Id){
    	window.location.href = "baseViewUpdate.html?type=view&id="+Id;
	}
	//搜索
	window.doSearch = function(){
	    gridObj.options.otherParames = $("#role_search_form").serializeArray();
	    gridObj.page(1);
	}
	//表格
	window.gridObj = $.fn.bsgrid.init('searchTable', {
	    url : basePath+'/bis/coupon/queryAll',
	    pageSizeSelect: true,
	    stripeRows: true,
	    otherParames: $("#role_search_form").serializeArray(),
	    pageSize: 10,
	    pagingLittleToolbar:false
	});
})