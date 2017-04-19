$(function(){
	$.fn.init_author = author.init_author;
})

var author = {};
	// 初始化 所有 a button 设计的操作url
	author.getButton = function(element){
		author.buttons_str = new Array(); //按钮 对应的 uri
		author.buttons = new Array();//按钮
		var str_back ='';
		element.find('[uri-parent]').each(function(){
			str_back = $(this).attr('uri-parent')+$(this).attr('uri-operate');
			str_back = str_back.replace( /^\s+|\s+$/g, "" );
			author.buttons_str.push(str_back);
			author.buttons.push($(this));
		});
		var back = author.buttons_str;
		var new_buttons = new Array();
		for(var i= 0; i< author.buttons_str.length; i++){
			for(var j= 0; j< back.length; j++){
				if(i!= j && author.buttons_str[i] == back[j]){
					back[j] = null;
				}
			}
		}
		for(var i= 0; i< author.buttons_str.length; i++){
			if(author.buttons_str[i]) new_buttons.push(author.buttons_str[i]);
		}
		author.buttons_str = new_buttons;
	};
	author.init_author = function(){
		$this = $(this);
		author.getButton($this);
		author.current_authority();
		author.editor_html($this,author.buttons_str,author.current_authority_uri);
	};
	
	//得到当前登录用户的操作权限
	author.current_authority = function(){
		if(!author.current_authority_uri){
			author.current_authority_uri = new Array();
			$.ajax({
				type : "get",
				url : '../operateAuthor/authors',
				async : false,
				success : function(data) {
					for (var i = 0; i < data.length; i++) {
						for (var j = 0; j < data[i].menuResources.length; j++) {
							author.current_authority_uri.push(data[i].menuResources[j].resourceUri);
						}
					}
				}
			});
		}
	};
	author.editor_html = function(element,buttons,current_authority_uri){
		author.show_buttons_str = new Array();
		//得到当前用户可以操作的按钮 uri
		for(var i= 0; i< buttons.length; i++){
			for(var j= 0; j< current_authority_uri.length; j++){
				if(buttons[i] == current_authority_uri[j]){
					author.show_buttons_str.push(buttons[i]);
					break;
				}
			}
		}
		//显示有权限按钮
		for(var i= 0;i< author.buttons.length; i++){
			var $this = $(author.buttons[i]);
			var $this_str = $this.attr('uri-parent')+$this.attr('uri-operate');
			for(var j= 0; j< author.show_buttons_str.length; j++){
				if($this_str == author.show_buttons_str[j]){
					$this.show();
					$this.removeAttr('style');
					$this.removeAttr('uri-parent');
					$this.removeAttr('uri-operate');
				}
			}
		}
		//删除元素
		element.find("[style='display:none;']").each(function(){
			$(this).remove();
		});
	}

	
