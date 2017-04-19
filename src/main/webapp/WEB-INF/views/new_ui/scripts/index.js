"use strict";
// Custom scripts
var $body = $('body'),
	$main_cont = $('#main_cont'),
	$iframe = $('#iframe'),
	$left_menu = $('#left_nav'),
	$main_menu = $('#index_menu_toggle'),
	$1st_level_nav = $main_menu.find('.nav_item.dropdown'),
	$main_menu_items = $main_menu.find('a[href]');

function changeSize() {
   	var wrapper_height = $(window).height() - 119;
	$main_cont.height(wrapper_height);
	$iframe.height(wrapper_height);
};

function changeIframe(src) {
	$iframe.attr('src', src);
}

$(function() {
	changeSize();
	$(window).on('resize', function(event) {
		event.preventDefault();
		changeSize();
	});
	$1st_level_nav
		.on('mouseenter', function(event) {
			event.preventDefault();
			$(this).addClass('hover');
		})
		.on('mouseleave', function(event) {
			event.preventDefault();
			$(this).removeClass('hover');
		});
	$main_menu_items.on('click', function(event) {
		event.preventDefault();
		var this_link = $(this),
			src = this_link.attr('href'),
			nav_item = this_link.closest('li.nav_item');
		nav_item.siblings().removeClass('active').end().addClass('active').removeClass('hover');
		if (nav_item.length && nav_item.hasClass('dropdown')) {
			var sec_nav_items = nav_item.find('.dropdown-menu').children('li').clone(),
				index = this_link.parent().index();
			nav_item.removeClass('hover');
			$left_menu.children('ul').html(sec_nav_items)
				.children().addClass('nav_item')
				.find('a').prepend('<i class="fa fa-file-text-o"></i>');
			if (this_link.parent().hasClass('dropdown')) {
				$left_menu.find('.nav_item').eq(0).addClass('active');
			}else $left_menu.find('.nav_item').eq(index).addClass('active');
			$main_cont.addClass('left_menu_hide');
		} else {
			$main_cont.removeClass('left_menu_hide');
		};
		changeIframe(src);
	});
	$left_menu.on('click', 'a[href]', function(event) {
		event.preventDefault();
		var nav_item = $(this).closest('li.nav_item'),
			src = $(this).attr('href');
		nav_item.siblings().removeClass('active').end().addClass('active');
		changeIframe(src);
	});
})