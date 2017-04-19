/**
 * load jquery-2.0.2.min.js first
 * Author: Zhang Qi
 */
(function($){
	var $ajax = $.ajax;
	$ajax._reuqestsCache = {};
	$.ajaxSetup({
		mode: "block",
		index: 0,
		cache: false,
		beforeSend: function(xhr, s) {
			if (s.model) {
				if (s.model === "abort" && s.index) {
					if ($ajax._reuqestsCache[s.index]) {
						$ajax._reuqestsCache[s.index].abort();
					}
				}
				$ajax._reuqestsCache[s.index] = xhr;
			}
		}
	}); 
})(jQuery);

String.prototype.trim=function()
{
     return this.replace(/(^\s*)|(\s*$)/g, '');
};

var encode = function(str)
{
		return encodeURIComponent(str);
};

var isEmpty = function(obj)
{
	for (var name in obj)
    {
        return false;
    }
    return true;
};


var getFullUrl = function(url) {
	var contextPath = window.location.pathname.split("/")[1];
	return "/" + contextPath + url;
};
// 动态加载javascript
var load = function(url, options) {
	options = $.extend(options || {}, {
		dataType : "script",
		cache : true,
		url : url,
		async : false
	});
	return jQuery.ajax(options);
};
var loadHtml = function(url, options) {
	options = $.extend(options || {}, {
		dataType : "html",
		cache : false,
		url : url,
		async : false
	});
	return jQuery.ajax(options);
};

// find the top window
var findTopWindow = function(currentWindow) {
	if (currentWindow.parent == currentWindow) {
		return currentWindow;
	} else {
		return findTopWindow(currentWindow.parent);
	}

};

var TopFrame = {
	OpenIFrameInDialog : function(options, url) {
		options.content = '<iframe frameborder="0" framespacing="0" src='
				+ getFullUrl(url) + ' style="width:100%;height:100%"></iframe>';
		var topWindow = findTopWindow(window);
		topWindow.openWin(options);
	},
	CloseDialog:function(){
		var topWindow = findTopWindow(window);
		topWindow.closeDialog();
	},
	Alert : function(title, msg, icon, fn) {
		var topWindow = findTopWindow(window);
		topWindow._alert(title, msg, icon, fn);
	},
	Confirm:function(title,msg,fn)
	{
		var topWindow= findTopWindow(window);
		topWindow._confirm(title,msg,fn);
	}
};

//get the window object of the centerFrame
var GetCenterFrame=function(){
	var topWindow = findTopWindow(window);
	var index = topWindow._getSelectTabIndex();
  
	var centerWindow =topWindow.frames[index];
	return centerWindow;
};

var validate = function(){
	var validate_reslut =true;
	
    $(".easyui-validatebox").each(function(){
		if(!$(this).validatebox("isValid")){
			//如果是隐藏元素则不验证
			if($(this).parents("tr").attr("style")!="display: none;"){
			    validate_reslut=false;
			 }
			
		}
	});
   
	$(".combobox").each(function(){
		  
		  if($(this).val()=="null" &&  $(this).attr("re")=="true"){
			  //如果是隐藏元素则不验证
			  if($(this).parents("tr").attr("style")!="display: none;"){
				  $(this).css("border-width","2px");
				  $(this).css("border-color","#ffa8a8");
				  validate_reslut=false;
			  }
		  }
	});
	

    return validate_reslut;
};

/**
 * wrapper the ajax method
 * */
var Ajax = {
	put : function(url, model, opts) {
	
		if(!opts.ignorecheck)
		{
			if(!validate()) return;
		}
		if($(".easyui-linkbutton").hasClass("l-btn-disabled"))
		{
			return;
		}
		$(".easyui-linkbutton").each(function(){
			$(this).linkbutton("disable");
		});
		//显示遮罩
        load("正在处理，请稍待.......");
		var options = {};
		options = $.extend({
			model : "block",
		    index : 0,
		    cache : false,
		    complete:function(){
		    
		    	$(".easyui-linkbutton").each(function(){
					$(this).linkbutton("enable");
				});
		    },
			type : 'PUT',
			dataType : 'json',
			contentType : 'application/json',
			url : getFullUrl(url),
			error:function(err){
				  //关闭遮罩
 		         dispalyLoad();
				if(err.responseJSON)
				{
					TopFrame.Alert("提示信息",err.responseJSON.message);
				}else
				{
					
					TopFrame.Alert("提示信息","操作失败!");
				}
			},
			data : JSON.stringify(model)
		}, opts || {});
		
		return jQuery.ajax(options);
	},
	post : function(url, model, options) {
		
		if(!options.ignorecheck)
		{
			if(!validate()) return;
		}
		if($(".easyui-linkbutton").hasClass("l-btn-disabled"))
		{
			return;
		}
		$(".easyui-linkbutton").each(function(){
			$(this).linkbutton("disable");
		});
		//显示遮罩
	   load("正在处理，请稍待.......");
		var opts = $.extend({
			model : "block",
		    index : 0,
		    cache : false,
			type : 'POST',
			dataType : 'json',
			contentType : 'application/json',
			url : getFullUrl(url),
		    complete:function(){
		    	$(".easyui-linkbutton").each(function(){
					$(this).linkbutton("enable");
				});
		    },
			error:function(err){
				  //关闭遮罩
		         dispalyLoad();
				if(err.responseJSON)
				{
					TopFrame.Alert("提示信息",err.responseJSON.message);
				}else
				{
					TopFrame.Alert("提示信息","操作失败!");
				}
				
			},
			data : JSON.stringify(model)
		}, options || {});
		  //关闭遮罩
         //dispalyLoad();
		return jQuery.ajax(opts);
	},
	get : function(url, options) {
		if($(".easyui-linkbutton").hasClass("l-btn-disabled"))
		{
			return;
		}
		$(".easyui-linkbutton").each(function(){
			$(this).linkbutton("disable");
		});
		var opts = $.extend({
			async : true,
			type : 'GET',
			dataType : 'json',
		    complete:function(){
		    	$(".easyui-linkbutton").each(function(){
					$(this).linkbutton("enable");
				});
		    },
			error:function(err){
				  //关闭遮罩
		         dispalyLoad();
				if(err.responseJSON)
				{
					TopFrame.Alert("提示信息",err.responseJSON.message);
				}else
				{
					TopFrame.Alert("提示信息","操作失败!");
				}
			},
			url : getFullUrl(url)
		}, options || {});
		return jQuery.ajax(opts);
	},
	destroy : function(url,opts) {
		if($(".easyui-linkbutton").hasClass("l-btn-disabled")){
			return;
		}
		$(".easyui-linkbutton").each(function(){
			//$(this).linkbutton("disable");
		});
		var options = {};
		options = $.extend({
			async : true,
			type : 'DELETE',
		    complete:function(){
		    	$(".easyui-linkbutton").each(function(){
		    		$(this).linkbutton("enable");
				});
		    },
			url : getFullUrl(url),
			error:function(err){
				if(err.responseJSON)
				{
					TopFrame.Alert("提示信息",err.responseJSON.message);
				}else
				{
					TopFrame.Alert("提示信息","操作失败!");
				}
			}
		},opts||{});
		return jQuery.ajax(options);
	}
};

/**
 * 获取页面提交数据以及绑定值
 */
var ModelUtils = function(defined) {
	var modelPropertis = function(definded) {
		var propertis ={};
		for ( var i = 0; i < definded.length; i++) {
			var keyDefined = definded[i].split(":");
			var property = {};
			property.key = keyDefined[0];
			if (keyDefined[1]) {
				property.type = keyDefined[1];
			}
			propertis[property.key]=property;
		}
		return propertis;
	};
	var keyPropertis = modelPropertis(defined);
	this.initModel = function() {
		var properties = keyPropertis;
		var model = {};
		for (pro in properties) {
			var property = properties[pro];
			var type = null;
			if(property.type)
			{
				type = property.type;
			}
			else if($("[name='"+keyPropertis[pro].key+"']").length==0)
			{
				type =$("[id='"+keyPropertis[pro].key+"']")[0].type.toLowerCase();
				
			}else
			{
				type=$("[name='"+keyPropertis[pro].key+"']")[0].type.toLowerCase();
			}
			switch(type)
			{
			case "easyui-datebox":
				model[property.key] =$("input[name='" + property.key + "']").val();
				break;
			case "combobox":
				model[property.key] =$("input[name='" + property.key + "']").val();
				break;
			case "radio":
				model[property.key] = $("input[name='" + property.key + "']:checked").val();
				break;
			case "checkbox":
//				var value =$("input[name='" + property.key + "']:checked").val();
//				model[property.key] =(value==undefined?"0":value) ;
				var checkbox = document.getElementById( property.key);
				model[property.key] =(checkbox.checked==false?"0":"1") ;
				break;
			case "textarea":
				var $textarea = $("textarea[name='" + property.key + "']");
				model[property.key]=$textarea.val()==""?$textarea.text():$textarea.val();
				break;
			case "select-one":
				model[property.key] = $("select[name='" + pro + "']").val();
				break;
			default:
				model[property.key] = $("input[name='" + property.key + "']").val();
				break;
			}
		}
		return model;
	};

	this.fillData = function(data) {
		for (pro in keyPropertis) {
			var type = null;
			if(keyPropertis[pro].type)
			{
				type = keyPropertis[pro].type;
			}else
			{
				type = $("[name='"+keyPropertis[pro].key+"']")[0].type.toLowerCase();
			}
			switch(type){
				case "easyui-datebox":
					if(data[pro])
					{
						$("#"+keyPropertis[pro].key).datebox("setValue", new Date(data[pro]).format("yyyy-MM-dd"));
					}
					break;
				case "combobox":
					$("#"+keyPropertis[pro].key).combobox("setValue",data[pro]);
					$("#"+keyPropertis[pro].key).combobox("select",data[pro]);
					break;
				case "numberbox":
					$("#"+keyPropertis[pro].key).numberbox("setValue",data[pro]);
					break;
				case "radio":
					
					$("input[name='" + keyPropertis[pro].key + "']").removeAttr("checked");
					$("input[name='" + keyPropertis[pro].key + "']").blur();
					$("input[name='" + keyPropertis[pro].key + "']").focus();
					$("input[name='" + keyPropertis[pro].key + "'][value='" + data[pro] + "']").click();
					$("input[name='" + keyPropertis[pro].key + "'][value='" + data[pro] + "']").attr("checked","checked");
					
					break;
				case "textarea":
					$("textarea[name='"+keyPropertis[pro].key+"']").text(data[pro]==null?"":data[pro]);
					break;
				case "checkbox":
					$("input[name='" + keyPropertis[pro].key + "']").removeAttr("checked");
					var _found =$("input[name='" + keyPropertis[pro].key + "'][value='"+data[pro]+"']");
					if(_found.length==1)
						_found.click();
					break;
				case "select-one":
					$("select[name='" + pro + "']").val(data[pro]);
					break;
				default:
					$("input[name='" + pro + "']").val(data[pro]);
					break;
			}

			
		}
	};
	this.clearData = function(){
		for (pro in keyPropertis) {
			var type ="";
			if(keyPropertis[pro].type)
			{
				type = keyPropertis[pro].type;
			}else
			{
				type = $("[name='"+keyPropertis[pro].key+"']")[0].type.toLowerCase();
			}
			switch(type){
				case "radio":
					$("input[name='" + keyPropertis[pro].key + "'][value='']").click();
					break;
				case "textarea":
					$("textarea[name='"+keyPropertis[pro].key+"']").text("");
					break;
				case "checkbox":
					$("input[name='" + keyPropertis[pro].key + "']").removeAttr("checked");
					break;
				case "select-one":
					$("select[name='" + pro + "']").val("");
					break;
				case "numberbox":
					$("#"+keyPropertis[pro].key).numberbox("clear");
					break;
				default:
					$("input[name='" + pro + "']").val("");
					break;
			}
		}
	};
};
/**
 * 处理服务器返回的树形节点
 */
var parseChildren = function(data,topNodeId) {
	if (data.childSize > 0 && data.children.length == 0) {
		data.state = "closed";
	}
	if(data.children&&data.children.length>0)
	for ( var i = 0; i < data.children.length; i++) {
		parseChildren(data.children[i]);
	}
};

var treeResolver=function(data,topNodeId)
{
	var result =[];
	parseChildren(data);
	if(data.id==topNodeId)
	{
		result.push(data);
	}else if(data instanceof Array){
		result = data;
	}else
	{
		result=data.children;
	}
   	return result;
};

//文档载入后执行
$(function(){
	if($('._datagrid').length!=0){
		$(window).resize(function(par) {
			$('._datagrid').datagrid("resize",{width:par.currentTarget.innerWidth});
		});
	}
});





Date.prototype.format = function(format) {
	/*
	 * eg:format="yyyy-MM-dd hh:mm:ss";
	 */
	if (!format) {
		format = "yyyy-MM-dd hh:mm:ss";
	}

	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	};
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
};


if($.fn.validatebox)
{
	$.extend($.fn.validatebox.defaults.rules, {  
	    equals: {  
	        validator: function(value,param){  
	            return value == $(param[0]).val();  
	        },  
	        message: '两次输入信息不匹配'  
	    },
	    maxLength: {   
	        validator: function(value, param){  
	        	
	            return value.length <= param[0];   
	        },   
	        message: '请不要输入超过 {0} 个字符.'  
	    },
	    phone:{
	    	validator:function(value,param){
	    		var length = value.length;
	    		var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
	    		var tel = /^\d{3,4}-?\d{7,9}$/;
	    		return (tel.test(value) || mobile.test(value));
	    	},
	    	message:'电话号码不正确'
	    }
	});
	
}

CodeTableUtils={
		init:function($containter,tableName,selfOptions)
		{
			var opts = { 
					async:false,
					method:"GET",
				    url:getFullUrl("/api/code_table/loadCodeTable?codeTableName="+tableName),   
				    valueField:'codeTableItemCode',   
				    textField:'codeTableItemName',
				    panelHeight:"auto"
				};
			opts = $.extend(opts,selfOptions||{});
			$containter.combobox(opts);
		}
};


var LoadingUtils = {
		Open:function(message){
			var top=  $(this).offset()==undefined?0:$(this).offset().top;
			var left=  $(this).offset()==undefined?0:$(this).offset().left;
			var appender=null;

			if($(this).parent().length==0)
			{
				appender="body";
			}else
			{
				appender=$(this);
			}
//查找出顶级元素具有绝对定位的元素
			
			function find(a)
			{
				if(a.parent().length==0||a.is("body")==true)
					return null;
				if(a.parent().css("position")=="absolute")
				{
					var _f = a.parent().offset();
					return _f;
				}
				return find(a.parent());
			}
			var _offset = find(appender);
			if(_offset!=null)
			{
				left = left -_offset.left;
			}
			
			$("<div class=\"mask\"></div>").css({
				display : "block",
				width : $(this).outerWidth(),//100%
				height : $(this).outerHeight(),//height
				top:top,
				left:left
			}).appendTo(appender);//body
			
			$("<div class=\"mask-msg\"></div>").html(message||"正在处理，请稍候...").appendTo(appender).css({
				display : "block",
				left : ($(this).outerWidth()-153) / 2+left,
				top :  ($(this).outerHeight()-42) / 2+top
			});
		},
		Close:function(){
			$(".mask").remove();
			$(".mask-msg").remove();
		}
};

