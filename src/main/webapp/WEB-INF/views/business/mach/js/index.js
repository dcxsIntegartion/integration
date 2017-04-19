var gridObj;
var grid_url = basePath+"/bis/mach/";
/** 新增 **/
function add(){
	window.location.href = "view.html?type=add&id=";
}
/** 查看 **/
function view(id){
	window.location.href = "view.html?type=view&id="+id;
}
/** 编辑 **/
function edite(id){
	window.location.href = "view.html?type=update&id="+id;
}
/** 删除 **/
function del(id){
	window.location.href = "view.html?type=del&id="+id;
}
/** 列表操作按钮 **/
function operate(record, rowIndex, colIndex, options) {
	var op_html ='';
	var id = record.id;
	var state = record.mach_state;
	op_html+= '<a  href="javascript:void(0);" class="btn btn-outline btn-primary btn-xs mhx" onclick="view(\'' + id + '\');">查看</a>';
	op_html+= '<a  href="javascript:void(0);" class="btn btn-outline btn-warning btn-xs mhx" onclick="edite(\'' + id + '\');">编辑</a>';
	op_html+= '<a  href="javascript:void(0);" class="btn btn-outline btn-danger btn-xs mhx" onclick="del(\'' + id + '\');">删除</a>';
	return op_html;
};


/** 查询 **/
function doSearch(){
	gridObj.options.otherParames = $("#search_form").serializeArray();
	gridObj.page(1);
}

/*** 业务 ****/

/*** 机构关联人员 ***/
function machRelCust(id){
	window.location.href = "rel/machRelCust.html?type=update&id="+id;
}


$(function(){
	gridObj = $.fn.bsgrid.init('searchTable', {
		 url : grid_url+"page",
         pageSizeSelect: true,
         stripeRows: true,
         otherParames: $("#search_form").serializeArray(),
         pageSize: 10,
         pagingLittleToolbar:false
    });
	
	/** 时间插件 **/
	$('#datetime_01 .date[name=start]')
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
	   console.log(value);
	   $('#datetime_01 .date[name=end]').datetimepicker('setStartDate', value);
	 });
   $('#datetime_01 .date[name=end]').datetimepicker({
     clearBtn: true,
     keyboardNavigation: false,
     language: "zh-CN",
     autoclose: true,
     todayHighlight: true,
     format: 'yyyy-mm-dd hh:ii'
   });	
});