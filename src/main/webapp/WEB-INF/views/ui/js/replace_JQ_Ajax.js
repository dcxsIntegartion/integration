$(function(){
	var _ajax=$.ajax;  
	    $.ajax=function(opt){  
	        var _success = opt && opt.success || function(a, b){};  
	        var _opt = $.extend(opt, {  
	            success:function(data, textStatus, jqXHR){  
	            	if(typeof(data.info) == "string" && data.info.indexOf("ABCDEFGHIJKLMNOPQRSTOVWXYZ0123456789") >= 0){
	            		location.href="/em-web/accessError.action";
	            		return;
	            	}
	            	var response_type = jqXHR.getResponseHeader("Content-Type");
	            	//alert(data.indexOf("ABCDEFGHIJKLMNOPQRSTOVWXYZ0123456789"));
	            	// AJAX 响应类型增加javascript 可能有关联影响
	            	if(response_type.indexOf("json") == -1 && response_type.indexOf("application/javascript") == -1){//AJAX未获取到JSON数据
	            		location.href="/em-web/accessError.action";
	            	}else{//获取到JSON数据
	               		_success(data, textStatus);
	            	}
	            },
	            error : function(XMLHttpRequest, textStatus, errorThrown) {
					if (XMLHttpRequest.status >= 10000) {
						window.top.location.href = window.top.location.href
								.substring(0, window.top.location.href
										.indexOf("/em-web/"))
								+ "/em-web/accessError.action?errorMsg=您长时间未操作平台，会话过期";
					}
				}
	        });  
	        _ajax(_opt);  
	    };  
	    if(!$(window.top.document).find('#iframe').length){
	    	if($.fn.bsgrid) $.fn.bsgrid.defaults.isProcessLockScreen = false;
	    	window.top.location.href = 
	    		window.top.location.href.substring(0,window.top.location.href.indexOf('/em-web/')) 
	    		+ '/em-web/accessError.action?errorMsg=页面不能单独访问';
	    }
	   
	    //屏蔽键盘  F5事件	
	    $(document).keydown(function(event){
	    	if (event.keyCode == 116) {
	    		var top_iframe = $($(window.parent.document).find('iframe')[0]);
	    		top_iframe.attr('src', top_iframe.attr('src'));
	    		return false;
	    	}
	    });	
});