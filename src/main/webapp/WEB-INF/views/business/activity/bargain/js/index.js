var gridObj;
var grid_url = basePath+"/bis/activityBargain/";
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
        url:  grid_url+"deleteById?Id=" + id,
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

/** 修改状态 **/
function changeStatus(id,status){
	var vo = {
			id:id,
			activityStatus:status
	};
	var data= encrypt(JSON.stringify(vo),publicKey,privateKey,"md5");
	$.ajax({
		type: "post",
		url:  grid_url+"updateStatus",
		headers:{'sign': data.sign,'str':data.str,'times':data.times},
        data: data.data,
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
	var status = record.activity_status;
//	var state = record.mach_state;
	op_html+= '<a  href="javascript:void(0);" class="btn btn-outline btn-primary btn-xs mhx" onclick="view(\'' + id + '\');">查看</a>';
	op_html+= '<a  href="javascript:void(0);" class="btn btn-outline btn-danger btn-xs mhx" onclick="del(\'' + id + '\');">删除</a>';
	if (status == 1) {
		op_html+= '<a  href="javascript:void(0);" class="btn btn-outline btn-danger btn-xs mhx" onclick="changeStatus(\'' + id + '\',\'' + 2 + '\');">关闭</a>';
	}else if(status == 2){
		op_html+= '<a  href="javascript:void(0);" class="btn btn-outline btn-danger btn-xs mhx" onclick="changeStatus(\'' + id + '\',\'' + 1 + '\');">开启</a>';
		op_html+= '<a  href="javascript:void(0);" class="btn btn-outline btn-warning btn-xs mhx" onclick="edite(\'' + id + '\');">编辑</a>';
	}
	return op_html;
};
/** 活动状态 **/
function activity_status(record, rowIndex, colIndex, options) {
	var op_html ='';
	var activity_status = record.activity_status;
	if (activity_status == 1) {
		op_html+= "<span>已开启</span>"
	}else if (activity_status == 2) {
		op_html+= "<span>已关闭</span>"
	}else if (activity_status == 3) {
	op_html+= "<span>已结束</span>"
	}
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
		 url : basePath+"/bis/activityBargain/page",
         pageSizeSelect: true,
         stripeRows: true,
         beforeSend: function(xhr,options){
			 xhr.setRequestHeader("sign", options.sign);
			 xhr.setRequestHeader("str", options.str);
			 xhr.setRequestHeader("times", options.times);
		 },
		 data: $("#search_form").serializeArray(),
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