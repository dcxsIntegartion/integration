var artId = getQueryString("artId");
var submitHtml = $('#saveBtn').html();
var field = ["artId","artTitle","artPlace","artCreateTime",
	"artTop","artSort","artContent"];
var modelUtils = new ModelUtils(field);
$(function(){
	if(artId != 'add'){
		initPageData();
	}else{
		$("#createTimeDiv").hide();
		$("#first").parent().addClass("checked");
	}
	var org_form = $('#viewForm').Validform({
		tiptype:function(msg,o,cssctl){
			if(!o.obj.is("form")){
				var objtip=o.obj.parent().siblings(".Validform_checktip");
				cssctl(objtip,o.type);
				objtip.text(msg);
			}
		},
		callback:function($form){
			var model = modelUtils.initModel();
			model.artTop = $(".checked input[name='artTop']").val();
			if(artId != 'add'){
				model.artId = artId;
			}
			model.artContent = getAllHtml();
			if(artId == 'add'){
				add(model);
			}else{
				editAjax(model);
			}
			//阻止表单的自动提交，防止页面跳转过快导致ajax statu为0
			return false;
		}
	});
});
function add(model){
	$.ajax({
        type: "post",
        url:  basePath+"/bis/article/add",
        data: JSON.stringify(model),
        contentType:"application/json",
        dataType: "json",
        success:function(data){
        		if(data.state==1){
        			layer.msg("添加文章成功！");
        			//返回上一页
            		window.history.back(-1);
        		}else{
        			layer.msg(data.msg);
        		}
		},
		error:function(data) {
			layer.msg("服务器异常");
		},
		complete:function(){
		}
	});
}

function editAjax(model){
	$.ajax({
        type: "post",
        url:  basePath+"/bis/article/update",
        data: JSON.stringify(model),
        contentType:"application/json",
        timeout: 20000,
        dataType: "json",
        success:function(data){
        		if(data.state==1 || data.state == 'y'){
        			layer.msg("修改文章成功！");
        			//返回上一页
            		window.history.back(-1);
        		}else{
        			layer.msg(data.msg);
        		}
		},
		error:function(data,re,da) {
			layer.msg("服务器异常");
		},
		complete:function(da){
			console.log(da);
		}
	});
}
function initPageData(){
	$.ajax({
        type: "post",
        async:false,
        url:  basePath+'/bis/article/findById',
        data:{"id":artId},
        contentType:"application/x-www-form-urlencoded",
        success: function(data){
        	if(data.state==1){
        		modelUtils.fillData(data.data);
        		$('#viewForm input[name="artId"]').val(data.data.artId);
        		if(data.data.artTop == 0 || data.data.artTop == "0"){
        			$("#first").parent().addClass("checked");
        		}else{
        			$("#second").parent().addClass("checked");
        		}
        		insertHtml(data.data.artContent);
        	}
		},
		error:function(data) {
		}
    });
}