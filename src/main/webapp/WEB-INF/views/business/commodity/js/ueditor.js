var ue = UE.getEditor('editor');
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
function getAllHtml(){
	return UE.getEditor('editor').getAllHtml();
}
function getContent(){
	return UE.getEditor('editor').getContent();
}
function setContent(data){
	 UE.getEditor('editor').setContent('欢迎使用ueditor', data);
}