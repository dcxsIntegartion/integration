$(function(){
	$.fn.anonymity = s;
});
function s(e_id,e_name) {
	document.documentElement.style.overflow='hidden';
	layer.open({
	    type: 2,
	    title: "马甲信息",
	    closeBtn: 1,
	    shade: [0],
	    area: ['700px', '400px'],
	    shift: 2,
	    content: ['/em-web/em/mjxx/anonymity.html?'+ e_id+"?"+ e_name],
	    end: function(){
	    	document.documentElement.style.overflow='auto';
	    }
	});
}