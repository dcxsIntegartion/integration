var gridObj;
var grid_url = basePath+"/cust/modelLv/page";
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
	op_html+= '<a  href="javascript:void(0);" class="btn btn-outline btn-primary btn-xs mhx" onclick="view(\'' + id + '\');">查看</a>';
	op_html+= '<a  href="javascript:void(0);" class="btn btn-outline btn-warning btn-xs mhx" onclick="edite(\'' + id + '\');">编辑</a>';
	op_html+= '<a  href="javascript:void(0);" class="btn btn-outline btn-danger btn-xs mhx" onclick="del(\'' + id + '\');">删除</a>';
	return op_html;
};
/** 列表图片 **/
function showImg(record, rowIndex, colIndex, options){
	var show_html = "";
	var img_url = "../../new_ui/images/example_image.jpg";
	show_html = "<img src="+ img_url +"  class='img-rounded img-responsive'/>";
	return show_html;
}




/** 查询 **/
function doSearch(){
	gridObj.options.otherParames = $("#search_form").serializeArray();
	gridObj.page(1);
}
$(function(){
	gridObj = $.fn.bsgrid.init('searchTable', {
		 url : grid_url,
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