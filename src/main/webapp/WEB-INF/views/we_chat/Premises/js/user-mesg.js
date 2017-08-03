var openSendShort = true;
//获取验证码
function getInviterNumber(mobile){
	if(openSendShort && null!=mobile && /^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/.test(mobile)){
		openSendShort = false;
		var model = {};
		model.mobile=mobile;
		var data= encrypt(JSON.stringify(model),publicKey,privateKey,"md5");
		$.ajax({
			type: "get",
		    headers:{'sign': data.sign,'str':data.str,'times':data.times},
		    url:  basePath+"/dcxs/shortMsg/sendShortMessage",
		    data: {data:data.data},
		    contentType:"application/json",
		    success: function(data){
		    	if(data.status==1){
		    		
		    	}
			},
			error:function(data) {
			}
		});
	}
}
//核对验证码
function checkInviterNumber(mobile,validateCode){
	if(null!=validateCode && /^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/.test(mobile)){
		var model = {};
		model.validateCode=validateCode;
		model.mobile=mobile;
		var data= encrypt(JSON.stringify(model),publicKey,privateKey,"md5");
		$.ajax({
			type: "get",
		    headers:{'sign': data.sign,'str':data.str,'times':data.times},
		    url:  basePath+"/dcxs/shortMsg/checkInviterNumber",
		    data: {data:data.data},
		    contentType:"application/json",
		    success: function(data){
		    	if(data.status==1){
		    		
		    	}
			},
			error:function(data) {
			}
		});
	}
}
var openid;
var projectId;
function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]); return null;
	} 

$(function(){
	if(getQueryString("state").split("|").length>1){
		openid = getQueryString("state").split("|")[1];
		projectId = getQueryString("projectId").split("|")[0];
	}else{
		projectId = getQueryString("projectId").split("|")[0];
	}
	saveUserInfo();
});
function saveUserInfo(){
	var model = {};
	model.projectId = projectId;
//	model.gender =
	model.phone = $("#phone").val();
	model.wechatId =openid;
	var data= encrypt(JSON.stringify(model),publicKey,privateKey,"md5");
	$.ajax({
		type: "post",
	    headers:{'sign': data.sign,'str':data.str,'times':data.times},
	    url:  basePath+"/cust/modelLv/add",
	    data: data.data,
	    contentType:"application/json",
        dataType: "json",
	    success: function(data){
	    	if(data.status==1){
	    		window.location.href  = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1902b2fb914ffc65&redirect_uri=http%3A%2F%2Fwww.amaj.net.cn%2Fintegration%2Fwe_chat%2FPremises%2Fuser%2Fuser-mesg.html&response_type=code&scope=snsapi_userinfo&state="+projectId+"|"+openid+"#wechat_redirect";
	    	}
		},
		error:function(data) {
		}
	});
}

$("#getCode").bind("click",function(){
	getInviterNumber($("#phone").val());
});
$("#saveBtn").bind("click",function(){
	checkInviterNumber($("#phone").val(),$("#validateCode").val());
});