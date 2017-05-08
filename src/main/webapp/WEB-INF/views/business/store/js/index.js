var gridObj;
var grid_url = basePath+"/bis/store/";
/** 新增 **/
function add(){
	window.location.href = "add.html";
}
/** 查看 **/
function view(id){
	window.location.href = "edit.html?type=view&id="+id;
}
/** 编辑 **/
function edite(id){
	window.location.href = "edit.html?type=update&id="+id;
}
/** 删除 **/
function del(id){
	$.ajax({
        type: "post",
        url:  grid_url+"deleteById",
        data: {
        		Id:id
        	},
        contentType:"application/json",
        dataType: "json",
        success: function(data){
        		if(data.state==1){
        			gridObj.page(1);
        		}else{
        			layer.msg(data.msg);
        		}
		},
		error:function(data) {
		},
		complete:function(){
		}
    });
}
/** 列表操作按钮 **/
function operate(record, rowIndex, colIndex, options) {
	var op_html ='';
	var id = record.id;
//	var state = record.mach_state;
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


/***列表***/
$(function(){
	gridObj = $.fn.bsgrid.init('searchTable', {
		 url : grid_url+"page",
         pageSizeSelect: true,
         stripeRows: true,
         otherParames: $("#search_form").serializeArray(),
         pageSize: 10,
         pagingLittleToolbar:false
    });
});