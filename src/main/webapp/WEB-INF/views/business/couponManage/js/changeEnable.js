var field = ["couponIsBan"];
var modelUtils = new ModelUtils(field);
$(function(){
	$.ajax({
        type: "post",
        async:false,
        url:  basePath+'/bis/coupon/findBan',
        success: function(data){
        	if(data.state==1){
        		if(data.data.couponIsBan == 0 ||
        				data.data.couponIsBan == "0"){
        			$("#first").parent().addClass("checked");
        		}else{
        			$("#second").parent().addClass("checked");
        		}
        	}
		},
		error:function(data) {
		}
    });
})
//提交修改
function doSearch(){
	var model = {};
	    model.couponIsBan=$(".checked input[name='couponIsBan']").val();
	var data= encrypt(JSON.stringify(model),publicKey,privateKey,"md5");
	$.ajax({
        type: "post",
        url:  basePath+'/bis/coupon/isBan',
        headers:{'sign': data.sign,'str':data.str,'times':data.times},
        data: data.data,
        contentType:"application/x-www-form-urlencoded",
        dataType: "json",
        success: function(data){
        		if(data.state==1){
        			layer.msg("保存成功");
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