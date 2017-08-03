/** 性别选择 **/
var gender =1;
$("#sirBtn").bind("click",function(){
	gender =1;
	$("#sir").addClass("active");
	$("#lady").removeClass("active");
	
});

$("#ladyBtn").bind("click",function(){
	gender =2;
	$("#lady").addClass("active");
	$("#sir").removeClass("active");
});

$("#saveBtn").bind("click",function(){
	var remarks= $("#remarks").val();
	var phone = $("#phone").val();
	var trueName = $("#trueName").val();
	var inviterMp = userPhone;
	
	if(/^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/.test(phone)){
		var model = {};
		model.projectId = projectId;
		model.remarks =remarks;
		model.trueName = trueName;
		model.phone = $("#phone").val();
		model.wechatId =openid;
		model.inviterMp =inviterMp;
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
		    		alert(data.info)
		    	}
			},
			error:function(data) {
			}
		});
		
		
		}
		
});
function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]); return null;
	} 
function redirectUserInfo(){
	window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1902b2fb914ffc65&redirect_uri=http%3A%2F%2Fwww.amaj.net.cn%2Fintegration%2Fwe_chat%2FPremises%2Fuser%2Frecommen.html&response_type=code&scope=snsapi_userinfo&state="+projectId+"#wechat_redirect";
}
function getUserInfo(code) {
	var model = {};
	model.code=code;
	var data= encrypt(JSON.stringify(model),publicKey,privateKey,"md5");
	$.ajax({
		type: "get",
	    headers:{'sign': data.sign,'str':data.str,'times':data.times},
	    url:  basePath+"/wechat/config/userInfo",
	    data: {data:data.data},
	    contentType:"application/json",
	    success: function(data){
	    	openid = data.data;
	    	if(data.status==1){
//	    		alert(+"-----"+JSON.stringify(data));
	    		userPhone = data.info;
	    	}else{
	    		window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1902b2fb914ffc65&redirect_uri=http%3A%2F%2Fwww.amaj.net.cn%2Fintegration%2Fwe_chat%2FPremises%2Fuser%2Fuser-mesg.html&response_type=code&scope=snsapi_userinfo&state="+projectId+"|"+openid+"#wechat_redirect";
	    	}
	    },
		error:function(data) {
		}
	});
}
var openid;
var projectId;
var userPhone;
$(function(){
	if(getQueryString("state").split("|").length>1){
		openid = getQueryString("state").split("|")[1];
		projectId = getQueryString("projectId").split("|")[0];
	}else{
		projectId = getQueryString("projectId").split("|")[0];
	}
	var code = getQueryString("code");
	if(code){
		getUserInfo(code);
	}
	
	
});