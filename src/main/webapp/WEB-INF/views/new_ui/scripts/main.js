"use strict";
// Custom scripts
var $body = $('body');
//var basePath = "/integration";
var basePath = "";
function init_comp () {
	/** bootstrap js组件初始化**/
	// tooltips
    $('[data-toggle=tooltip]').tooltip();
    $("[data-toggle=popover]").popover();
    /** bootstrap js组件初始化 end**/

    /** 自定义js组件初始化**/
	/*iCheck*/
	$('input.i-checks').iCheck({
		checkboxClass: 'icheckbox',
		radioClass: 'iradio'
	});
	/** fancybox **/
	$('.fancybox').click(function(e) {
        e.preventDefault();
        window.parent.$.fancybox({
            href: this.href,
            title: this.title
        });
    });
    $('.fancybox_iframe').click(function(e) {
        e.preventDefault();
        window.parent.$.fancybox({
            type: 'iframe',
            padding: 20,
            href: this.href

        });
    });
    /** 自定义js组件初始化 end**/
}
$(function() {
	init_comp ();
	/* scrollUp */
	if (!$body.hasClass('index') && !$body.hasClass('login-body')) {
		var scrollCont = '<a id="scrollUp" href="#"><i class="fa fa-angle-up"></i></a>';
		$body.append(scrollCont);
	};
	$(window).scroll(function() {
		if ($body.scrollTop() > '200' && $('#scrollUp').length) {
			$('#scrollUp').show();
		} else {
			$('#scrollUp').hide();
		}
	});
	$('#scrollUp').on('click', function(event) {
		event.preventDefault();
		$body.animate({
			scrollTop: 0
		}, 500);
	});
	//自定义文件上传
	$('.file_upload input[type=file]').on('change', function(event) {
		var path = $(this).val(),
			filename = path.replace(/^.*\\/, ""),
			url = this.files[0],
			file_upload = $(this).closest('.file_upload'),
			filenameinput = file_upload.find('input.file_name'),
			upload_btn = file_upload.find('.upload_btn'),
			upload_preview = file_upload.find('.upload_preview'),
			origin_url = '../images/wireframe/white-image.png',
			upload_message = file_upload.find('.upload_message');
		filenameinput.val(filename);
		upload_btn.button('reset');
		if (!path == 0) {
			upload_btn.css('display', 'inline-block');
			if (upload_preview.length > 0) {
				if ((typeof url.type !== "undefined" ? url.type.match(/^image\/(jpg|png|jpeg)$/) : url.name.match(/\.(png|jpe?g)$/i)) && typeof FileReader !== "undefined") {
					var oFReader = new FileReader();
					oFReader.readAsDataURL(url);
					oFReader.onload = function(oFREvent) {
						upload_preview.attr('src', oFREvent.target.result);
					};
					upload_message.addClass('hidden');
				} else {
					filenameinput.val('');
					upload_preview.attr('src', origin_url);
					upload_message.removeClass('hidden');
					upload_message.find('.alert_cont').text('请上传jpg,png等图片格式的文件');
					upload_btn.css('display', 'none');
				};
			};
		} else {
			upload_btn.css('display', 'none');
			upload_preview.attr('src', origin_url);
		};
	});
	$('.file_upload').on('click', '.upload_btn', function(event) {
		event.preventDefault();
		$(this).button('loading');
	});
	$('.file_upload').on('click', 'button.close', function(event) {
		event.preventDefault();
		$(this).closest('.alert').addClass('hidden');
	});
	//table 全选
	$('#checkAll').on('ifChanged', function(event) {
		if (this.checked) {
			$(this).parents('table').find('input[type=checkbox]').iCheck('check');
		} else { //反之 取消全选   
			$(this).parents('table').find('input[type=checkbox]').iCheck('uncheck');
		}
	});
	$('tr input[type=checkbox]').on('ifChanged', function(event) {
		if (this.checked) {
			$(this).parents('tr').addClass('checked');
		} else { //反之 取消全选   
			$(this).parents('tr').removeClass('checked');
		}
	});
	// Collapse ibox function
    $('.collapse-link').on('click', function(e) {
        var ibox = $(this).closest('div.ibox');
        var button = $(this).find('i');
        var content = ibox.find('div.ibox-content');
        content.slideToggle(200);
        button.toggleClass('fa-chevron-up').toggleClass('fa-chevron-down');
        ibox.toggleClass('').toggleClass('border-bottom');
        setTimeout(function() {
            ibox.resize();
            ibox.find('[id^=map-]').resize();
        }, 50);
    });

    // Close ibox function
    $('.close-link').on('click', function(e) {
        var content = $(this).closest('div.ibox');
        content.remove();
    });
    
    $(document).ajaxError(function(event,request, settings){
    	if(request.status==403)
    	{
    		alert("会话超时,请重新登录");
    		window.location.href= basePath+"/login.html";
    	}
    });
});
/** 获取url参数 **/
function getQueryString(name) {
var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
var r = window.location.search.substr(1).match(reg);
if (r != null) return unescape(r[2]); return null;
} 