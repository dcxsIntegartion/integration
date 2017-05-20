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
    	op_html+= '<a  href="javascript:void(0);" class="btn btn-outline btn-primary btn-xs mhx" onclick="forUpdate(\'' + gridObj.getRecordIndexValue(record, 'coupon_id') + '\');">编辑</a>';
    	op_html+= '<a  href="javascript:void(0);" class="btn btn-outline btn-warning btn-xs mhx" onclick="forDetail(\'' + gridObj.getRecordIndexValue(record, 'coupon_id') + '\');">详情</a>';
    	op_html+= '<a  href="javascript:void(0);" class="btn btn-outline btn-danger btn-xs mhx" onclick="del(\'' + gridObj.getRecordIndexValue(record, 'coupon_id') + '\');">删除</a>';
		return op_html;
    }
    /** 删除 **/
    window.del =  function(id){
    	layer.alert('确认删除', {
    		  skin: 'layui-layer-molv' //样式类名 自定义样式
    		  ,closeBtn: 1  // 是否显示关闭按钮
    		  ,anim: 1 //动画类型
    		  ,btn: ['确认','取消'] //按钮
    		  ,yes:function(){
    		    delCoupon(id);
    		  }
    		  ,btn2:function(){
    		    
    		  }});
    }
    //编辑
    window.forUpdate = function(Id){
    	window.location.href = "baseViewUpdate.html?type=update&couponId="+Id;
	}
    //查看详细
    window.forDetail = function(Id){
    	window.location.href = "baseViewUpdate.html?type=view&couponId="+Id;
	}
	//搜索
	window.doSearch = function(){
	    gridObj.options.otherParames = $("#role_search_form").serializeArray();
	    gridObj.page(1);
	}
	//新增
	window.doCreate = function(){
		window.location.href = "add.html";
	}
	//优惠券启用、禁用
	window.doBan = function(){
		window.location.href = "changeBan.html";
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
});
function delCoupon(id){
	$.ajax({
		type: "post",
        url:  basePath+"/bis/coupon/delete",
        data: {"couponId":id},
        contentType:"application/x-www-form-urlencoded",
        dataType: "json",
        success: function(data){
        	if(data.state==1){
    			layer.msg("删除成功！");
    			gridObj.options.otherParames = $("#role_search_form").serializeArray();
    			gridObj.page(1);
    		}else{
    			layer.msg(data.msg);
    		}
		},
		error:function(data) {
			layer.msg("服务器异常");
		}
	});
}