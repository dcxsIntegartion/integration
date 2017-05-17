$(function() {
	 $('input, textarea').placeholder();//让IE8，IE9支持placeholder
	 initDatePicker();
	 window.isTop = function(record, rowIndex, colIndex, options){
		 switch(record.art_top){
		 case 1:
	    	return '<span style="color:green">是</span>';
		 case 0:
	    	return '<span style="color:red">否</span>';
	    }
	 }
	 //操作
	 window.operate =function(record, rowIndex, colIndex, options) {
	    var op_html ='';
	    op_html+= '<a  href="javascript:void(0);" class="btn btn-primary btn-xs mhx" onclick="forUpdate(\'' + gridObj.getRecordIndexValue(record, 'art_id') + '\');">编辑</a>';
	    op_html+= '<a  href="javascript:void(0);" class="btn btn-outline btn-danger btn-xs mhx" onclick="forDel(\'' + gridObj.getRecordIndexValue(record, 'art_id') + '\');">删除</a>';
		return op_html;
	 }
	//搜索
	window.doSearch = function(){
		   gridObj.options.otherParames = $("#role_search_form").serializeArray();
		   gridObj.page(1);
	}
	//表格
	window.gridObj = $.fn.bsgrid.init('searchTable', {
		url : basePath+'/bis/article/paging',
		pageSizeSelect: true,
		stripeRows: true,
		otherParames: $("#role_search_form").serializeArray(),
		pageSize: 10,
		pagingLittleToolbar:false
	});
});

function forUpdate(artId){
	window.location.href = "articleEdit.html?artId="+artId;
}
function create(){
	window.location.href = "articleEdit.html?artId=add";
}
function forDel(artId){
	layer.alert('确认删除', {
		  skin: 'layui-layer-molv' //样式类名 自定义样式
		  ,closeBtn: 1  // 是否显示关闭按钮
		  ,anim: 1 //动画类型
		  ,btn: ['确认','取消'] //按钮
		  ,yes:function(){
			  deleteArt(artId);
		  }
		  ,btn2:function(){
		  }});
}

function deleteArt(artId){
	$.ajax({
    	type:"post",
    	url:basePath+"/bis/article/deleteById",
    	contentType:"application/x-www-form-urlencoded",
    	data:{"id":artId},
    	dataType: "json",
    	success:function(data){
    		if(data.state == "1" || data.state == 'y'){
    			layer.msg("删除成功！");
	    		gridObj.options.otherParames = $("#role_search_form").serializeArray();
    			gridObj.page(1);
    		}else{
    			layer.msg("服务器异常，删除失败");
    		}
    	},
    	error:function(){
    		layer.msg("服务器异常，删除失败");
    	}
    });
}

function initDatePicker(){
	$('#role_search_form input[name="startDate"]')
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
	   $('#role_search_form input[name="endDate"]').datetimepicker('startDate', value);
	 });
 $('#role_search_form input[name="endDate"]').datetimepicker({
   clearBtn: true,
   keyboardNavigation: false,
   language: "zh-CN",
   autoclose: true,
   todayHighlight: true,
   format: 'yyyy-mm-dd hh:ii'
 });
}