$(function() {
	 $('input, textarea').placeholder();//让IE8，IE9支持placeholder
	 datePickerTool();
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
	//搜索
	window.doSearch = function(){
		   gridObj.options.otherParames = $("#role_search_form").serializeArray();
		   gridObj.page(1);
	}
	//表格
	window.gridObj = $.fn.bsgrid.init('searchTable', {
		   url : basePath+'/bis/coupon/queryUseRecord',
		   pageSizeSelect: true,
		   stripeRows: true,
		   otherParames: $("#role_search_form").serializeArray(),
		   pageSize: 10,
		   pagingLittleToolbar:false
	});
});
function datePickerTool(){
	/** 时间插件 **/
	$('#datetime_01 .date[name=startDate]')
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
	   $('#datetime_01 .date[name=endDate]').datetimepicker('setStartDate', value);
	 });
   $('#datetime_01 .date[name=endDate]').datetimepicker({
     clearBtn: true,
     keyboardNavigation: false,
     language: "zh-CN",
     autoclose: true,
     todayHighlight: true,
     format: 'yyyy-mm-dd hh:ii'
   });	
}