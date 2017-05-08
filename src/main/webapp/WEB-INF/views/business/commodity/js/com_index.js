$(function() {
	 $('input, textarea').placeholder();//让IE8，IE9支持placeholder
	 //初始化商品分类下拉框
	 initCommodityTypeSelect();
	 window.fnStatus = function(record, rowIndex, colIndex, options){
		 switch(record.commodity_status){
		 case 1:
	    	return '<span>是</span>';
		 case 2:
	    	return '<span>否</span>';
	    }
	 }
	 //操作
	 window.operate =function(record, rowIndex, colIndex, options) {
	    var op_html ='';
	    op_html+= '<a  href="javascript:void(0);" class="btn btn-primary btn-xs mhx" onclick="forUpdate(\'' + gridObj.getRecordIndexValue(record, 'id') + '\');">编辑</a>';
	    op_html+= '<a  href="javascript:void(0);" class="btn btn-outline btn-danger btn-xs mhx" onclick="forDel(\'' + gridObj.getRecordIndexValue(record, 'id') + '\');">删除</a>';
	    var isSale = gridObj.getRecordIndexValue(record, 'commodity_status')==1?"下架":"上架";
	    op_html+= '<a  href="javascript:void(0);" class="btn btn-warning btn-xs mhx" onclick="forChangeStatus(\'' + gridObj.getRecordIndexValue(record, 'id') + '\');">'+isSale+'</a>';
		return op_html;
	 }
	//搜索
	window.doSearch = function(){
		   gridObj.options.otherParames = $("#role_search_form").serializeArray();
		   gridObj.page(1);
	}
	//表格
	window.gridObj = $.fn.bsgrid.init('searchTable', {
		url : basePath+'/bis/commodity/allPaging',
		pageSizeSelect: true,
		stripeRows: true,
		otherParames: $("#role_search_form").serializeArray(),
		pageSize: 10,
		pagingLittleToolbar:false
	});
});
//初始化 分类 下拉框
function initCommodityTypeSelect(){
	$.ajax({
		type:"post",
		url:basePath+"/bis/commodity/typeSelect",
		contentType:"application/x-www-form-urlencoded",
    	dataType: "json",
    	success:function(re){
    		if(re.state == "1" || re.state == 'y'){
    			var data = re.data;
    			var parent = $("#commodityType");
    			$.each(data,function(index,item){
    				var option = $("<option/>");
    				option.attr("value",item.material_type_id);
    				option.text(item.material_name);
    				parent.append(option);
    			});
    		}else{
    			layer.msg("服务器异常，初始化失败");
    		}
    	},
    	error:function(){
    		layer.msg("服务器异常，初始化失败");
    	}
	});
}
//编辑
function forUpdate(commodityId){
	//跳转页面
	window.location.href = "commodityEdit.html?commodityId="+commodityId;
}
//删除
function forDel(commodityId){
	var arr = new Array();
	arr.push(commodityId);
	$("#deleteFrm #deleteIds").val(arr);
	layer.alert('确认删除', {
		  skin: 'layui-layer-molv' //样式类名 自定义样式
		  ,closeBtn: 1  // 是否显示关闭按钮
		  ,anim: 1 //动画类型
		  ,btn: ['确认','取消'] //按钮
		  ,yes:function(){
		    $.ajax({
		    	type:"post",
		    	url:basePath+"/bis/commodity/delete",
		    	contentType:"application/x-www-form-urlencoded",
		    	data:$("#deleteFrm").serializeArray(),
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
		  ,btn2:function(){
		    
		  }});
}