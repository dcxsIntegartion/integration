$(function(){
	$("input[name='typeState'][value='0']").parent().addClass("checked");
	$("#btnBack").bind("click",function(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	});
	var submitHtml = $('#btnSaveAs').html();
	var resource_form = $('#resource_form').Validform({
		tiptype:function(msg,o,cssctl){
			if(!o.obj.is("form")){
				var objtip=o.obj.parent().siblings(".Validform_checktip");
				cssctl(objtip,o.type);
				objtip.text(msg);
			}
		},
		callback:function($form){
			var da={};
			da.typeId  = $('#resource_form #typeId').val();
			da.parTypeId  = $('#resource_form #parTypeId').val();
			da.typeName  = $('#resource_form #typeName').val();
			da.typeDes  = $('#resource_form #typeDes').val();
			da.typeLevel  = $('#resource_form #typeLevel').val();
			da.typeState  = $("#resource_form .checked input[name='typeState']").val();
			var data= encrypt(JSON.stringify(da),publicKey,privateKey,"md5");
			$.ajax({
	            type: "POST",
	            url: basePath+"/bis/commodityType/add",
	            headers:{'sign': data.sign,'str':data.str,'times':data.times},
	            data: data.data,
	            contentType:"application/json",
	            dataType: "json",
	            success: function(data){
	            			layer.msg("添加分类信息成功！");
	            			window.parent.initPage();
	            			window.setTimeout(function(){
	            				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	   	         				parent.layer.close(index);
	            			},400);
	            },
				error:function(data) {
				},
				complete:function(){
				}
	        });
			
			//表单验证通过进行回掉处理
			return false;
		}
	});
});
/** 设置提交提示信息 */
function submitToggle(status, submitHtml) {
	if (status == 0) {
		$('#saveBtn').html(submitHtml).attr('disabled', false);
	} else {
		$('#saveBtn').html('<i class="fa fa-spin fa-spinner"></i>    正在提交').attr('disabled', true);
	}
}