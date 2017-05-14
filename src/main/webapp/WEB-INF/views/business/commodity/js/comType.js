var treeObj = null;
var submitHtml = $('#btnUpdate').html();
$(function(){
	initPage();
	validateFrm();
});
function validateFrm(){
	var resource_form = $('#resource_form').Validform({
		tiptype:function(msg,o,cssctl){
			if(!o.obj.is("form")){
				var objtip=o.obj.parent().siblings(".Validform_checktip");
				cssctl(objtip,o.type);
				objtip.text(msg);
			}
		},
		callback:function($form){
			var _node = treeObj.getSelectedNodes();
			var _data=_node[0];
			var da={};
			da.typeId =_data.typeId = $('#resource_form #typeId').val();
			da.parTypeId =_data.parTypeId = $('#resource_form #parTypeId').val();
			da.typeName =_data.typeName = $('#resource_form #typeName').val();
			da.typeDes =_data.typeDes = $('#resource_form #typeDes').val();
			da.typeState =_data.typeState = $("#resource_form .checked input[name='typeState']").val();
			$.ajax({
	            type: "post",
	            url: basePath+"/bis/commodityType/update",
	            data: JSON.stringify(da),
//	            contentType:"application/x-www-form-urlencoded",
	            contentType:"application/json",
	            dataType: "json",
	            success: function(data){
	            	layer.msg("修改分类信息成功！");
					if(treeObj != null){
						treeObj.updateNode(_data);
					}
//						treeObj.refresh();
				},
				error:function(data) {
				},
				complete:function(){
//					submitToggle(0,submitHtml);
				}
	        });
			
			//表单验证通过进行回掉处理
			return false;
		}
	});
}
function initPage(){
	$.ajax({
		type: "post",
        url: basePath+"/bis/commodityType/tree",
        contentType:"application/json",
        dataType: "json",
        success: function(data){
        		if(data.state==1 || data.state == 'y'){
        			initTree(data.data);
        			$("#resource_form")[0].reset();
        			$("#resource_form .checked").removeClass('checked');
        		}else{
        			layer.msg(data.msg);
        		}
		},
		error:function(data) {
			layer.msg("服务器异常");
		}
	});
}

function initTree(data){
	var setting = {
		data:{
			key:{
				children:"children",
				name:"typeName",
				title:"typeName"
			},
			simpleData:{
				idKey:"typeId",
				pIdKey:"parTypeId",
				enable:true
			}
		},
		callback:{
			onClick:function(event, treeId, treeNode){
				treeNodeClick(treeId, treeNode);
			}
		}
	};
	treeObj = $.fn.zTree.init($("#resource_view"), setting, data);
	treeObj.expandAll(true);
}

function treeNodeClick(treeId, treeNode){
	$("#resource_form").autofill(treeNode);
	if(treeNode.typeState == 0){
		$("input[name='typeState'][value='0']").parent().addClass("checked");
	}else{
		$("input[name='typeState'][value='1']").parent().addClass("checked");
	}
}
function deleNode(){
	var _node = treeObj.getSelectedNodes();
	if(_node.length == 0){
		layer.msg("请点击左侧资源视图选择你要操作的资源!");
	}else{
		var id = $("#resource_form #typeId").val();
		$.ajax({
			type: "post",
	        url: basePath+"/bis/commodityType/delete/"+id,
	        contentType:"application/json",
	        dataType: "json",
	        success: function(data){
	        		if(data.state==1 || data.state == 'y'){
	        			treeObj.removeNode( _node[0]);
	        			$("#resource_form")[0].reset();
	        			$("#resource_form .checked").removeClass('checked');
	        		}else{
	        			layer.msg(data.msg);
	        		}
			},
			error:function(data) {
				layer.msg("服务器异常");
			}
		});
	}
}
function addNode(){
	var _node = treeObj.getSelectedNodes();
	if(_node.length == 0){
		layer.msg("请点击左侧资源视图选择你要操作的资源!");
	}else{
		var id = $("#resource_form #typeId").val();
		var url =basePath+"/bis/commodityType/addPage?parId="+id;
		layer.open({
		    type: 2,
		    title: '新增分类',
		    shadeClose: true,
		    maxmin: true, //开启最大化最小化按钮
		    shade: 0.8,
		    area: ['680px', '99%'],
		    content: url //iframe的url
		}); 
	}
}
$.fn.serializeObject = function() {
	 var o = {};
	 var a = this.serializeArray();
	 $.each(a, function() {
	 if (o[this.name] !== undefined) {
	 if (!o[this.name].push) {
	 o[this.name] = [o[this.name]];
	}
	 o[this.name].push(this.value || '');
	 } else {
	 o[this.name] = this.value || '';
	}
	});
	 return o;
};