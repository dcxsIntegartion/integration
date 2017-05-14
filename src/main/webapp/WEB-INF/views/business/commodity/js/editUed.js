var ue = null;
function initEditor(){
	ue = UE.getEditor('commodityIntroduction');
	//自定义上传地址
	UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
	UE.Editor.prototype.getActionUrl = function(action) {
	    if (action == 'uploadimage' || action == 'uploadscrawl' || action == 'uploadimage') {
	        return '/integration/ueditor/img';
	    } else if (action == 'uploadvideo') {
	        return '/integration/ueditor/file';
	    } else {
	        return this._bkGetActionUrl.call(this, action);
	    }
	}
}
function getAllHtml(){
	return UE.getEditor('commodityIntroduction').getAllHtml();
}
function getContent(){
	return UE.getEditor('commodityIntroduction').getContent();
}
function setContent(data){
	 UE.getEditor('commodityIntroduction').setContent('欢迎使用ueditor', data);
}
//插入  html
function insertHtml(value) {
	ue.ready(function() {
	    //异步回调
	    UE.getEditor('commodityIntroduction').execCommand('insertHtml', value);
	});
}