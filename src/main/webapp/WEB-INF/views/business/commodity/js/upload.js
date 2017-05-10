/** start 需要修改的 **/
/** 图片上传处理 **/
var img_url = basePath+"/upload/";
var commodityPic = [];
var commodityPic2 = [];
var commodityPic3 = [];
/** end   **/

$.ajaxSetup({
	  async: false
	  });
/** 离开页面前，判断是否在做保存 **/
var onunload_state = true;
var files = [];
function unload(){
	if(onunload_state && files.length>0){
		delUploadFiles();
	}
} 
/** 根据uuid，查询（文件）图片 **/
function selFiles(name,initFiles){
	var uuid = $("input[name="+name+"]").val();
	if(uuid){
		$.ajax({
			async:false,
	        type: "post",
	        url:  img_url+"selFiles?uuid="+uuid,
	        success: function(data){
	        	initFiles = data;
			}
	    });
	}
	return initFiles;
}
/** start 需要修改的 **/
Dropzone.options.commodityPic = {
      paramName: "file", // The name that will be used to transfer the file
      url:img_url+"img",
      addRemoveLinks: true,
      maxFilesize: 2, // MB
      maxFiles: 1,
      acceptedFiles: 'image/*',
      dictDefaultMessage: '拖动图片到这里，或者点击上传',
      dictInvalidFileType: '请上传jpg,jpeg,png,gif等图片格式的文件',
      dictFileTooBig: '文件过大，请上传小于2M的文件',
      dictRemoveFile: '删除',
      dictCancelUpload: '取消',
      dictCancelUploadConfirmation: '您确定要取消上传吗？',
      dictMaxFilesExceeded: '最多只能上传1个文件',
      init: function() {
          this.on("success", function(file, response) {
        	if(response){
        	  img_upload_success(commodityPic,response);
        	}
          });
          this.on("removedfile", function(file) { 
        	img_remove(commodityPic,file);
            if ($.inArray(file.name, mockFilesNames) > -1) {//如果删除的是已存在的图片，则需要更新maxFiles的设置
              this.options.maxFiles++;
            };
          });
          //如果要显示已上传的文件，就需要以下的代码初始化
          commodityPic = selFiles("commodityPic",commodityPic);
          var mockFiles = commodityPic,mockFilesNames = [];
          for (var i = 0; i < mockFiles.length; i++) {
              this.emit("addedfile", mockFiles[i]);
              this.emit("thumbnail", mockFiles[i] , mockFiles[i].url);
              this.emit("complete", mockFiles[i]);
              mockFilesNames.push(mockFiles[i].name);//存储初始时已有文件的name
          };
          // 规定了最大文件数，在这里要减去已存在的文件数 If you use the maxFiles option, make sure you adjust it to the correct amount:
          var existingFileCount = mockFiles.length; // The number of files already uploaded
          this.options.maxFiles = this.options.maxFiles - existingFileCount;
      }
  };
Dropzone.options.commodityPic2 = {
	      paramName: "file", // The name that will be used to transfer the file
	      url:img_url+"img",
	      addRemoveLinks: true,
	      maxFilesize: 2, // MB
	      maxFiles: 1,
	      acceptedFiles: 'image/*',
	      dictDefaultMessage: '拖动图片到这里，或者点击上传',
	      dictInvalidFileType: '请上传jpg,jpeg,png,gif等图片格式的文件',
	      dictFileTooBig: '文件过大，请上传小于2M的文件',
	      dictRemoveFile: '删除',
	      dictCancelUpload: '取消',
	      dictCancelUploadConfirmation: '您确定要取消上传吗？',
	      dictMaxFilesExceeded: '最多只能上传1个文件',
	      init: function() {
	          this.on("success", function(file, response) {
	        	if(response){
	        	  img_upload_success(commodityPic2,response);
	        	}
	          });
	          this.on("removedfile", function(file) { 
	        	img_remove(commodityPic2,file);
	            if ($.inArray(file.name, mockFilesNames) > -1) {//如果删除的是已存在的图片，则需要更新maxFiles的设置
	              this.options.maxFiles++;
	            };
	          });
	          //如果要显示已上传的文件，就需要以下的代码初始化
	          commodityPic2 = selFiles("commodityPic2",commodityPic2);
	          var mockFiles = commodityPic2,mockFilesNames = [];
	          for (var i = 0; i < mockFiles.length; i++) {
	              this.emit("addedfile", mockFiles[i]);
	              this.emit("thumbnail", mockFiles[i] , mockFiles[i].url);
	              this.emit("complete", mockFiles[i]);
	              mockFilesNames.push(mockFiles[i].name);//存储初始时已有文件的name
	          };
	          // 规定了最大文件数，在这里要减去已存在的文件数 If you use the maxFiles option, make sure you adjust it to the correct amount:
	          var existingFileCount = mockFiles.length; // The number of files already uploaded
	          this.options.maxFiles = this.options.maxFiles - existingFileCount;
	      }
	  };
Dropzone.options.commodityPic3 = {
	      paramName: "file", // The name that will be used to transfer the file
	      url:img_url+"img",
	      addRemoveLinks: true,
	      maxFilesize: 2, // MB
	      maxFiles: 1,
	      acceptedFiles: 'image/*',
	      dictDefaultMessage: '拖动图片到这里，或者点击上传',
	      dictInvalidFileType: '请上传jpg,jpeg,png,gif等图片格式的文件',
	      dictFileTooBig: '文件过大，请上传小于2M的文件',
	      dictRemoveFile: '删除',
	      dictCancelUpload: '取消',
	      dictCancelUploadConfirmation: '您确定要取消上传吗？',
	      dictMaxFilesExceeded: '最多只能上传1个文件',
	      init: function() {
	          this.on("success", function(file, response) {
	        	if(response){
	        	  img_upload_success(commodityPic3,response);
	        	}
	          });
	          this.on("removedfile", function(file) { 
	        	img_remove(commodityPic3,file);
	            if ($.inArray(file.name, mockFilesNames) > -1) {//如果删除的是已存在的图片，则需要更新maxFiles的设置
	              this.options.maxFiles++;
	            };
	          });
	          //如果要显示已上传的文件，就需要以下的代码初始化
	          commodityPic3 = selFiles("commodityPic3",commodityPic3);
	          var mockFiles = commodityPic3,mockFilesNames = [];
	          for (var i = 0; i < mockFiles.length; i++) {
	              this.emit("addedfile", mockFiles[i]);
	              this.emit("thumbnail", mockFiles[i] , mockFiles[i].url);
	              this.emit("complete", mockFiles[i]);
	              mockFilesNames.push(mockFiles[i].name);//存储初始时已有文件的name
	          };
	          // 规定了最大文件数，在这里要减去已存在的文件数 If you use the maxFiles option, make sure you adjust it to the correct amount:
	          var existingFileCount = mockFiles.length; // The number of files already uploaded
	          this.options.maxFiles = this.options.maxFiles - existingFileCount;
	      }
	  };

/** start 需要修改的 **/
function img_upload_success(imgs,response){
	if(response){
		  var mode = {};
  		  mode.size=response.size;
  		  mode.url=response.url;
  		  mode.name=response.name;  
  		  mode.objId=response.objId;
  		  imgs.push(mode);
  		  files.push(mode);
	  }
}
function img_remove(imgs,file){
	if('undefined' == file.xhr || undefined==file.xhr || null==file.xhr){
    	var url = file.url;
    	for(var a=0;a<imgs.length;a++){
        	if(imgs[a].url==url){
        		imgs.splice(a,1);
			  }
		  }
    }else{
    	var json = JSON.parse(file.xhr.response);
        for(var a=0;a<imgs.length;a++){
        	if(imgs[a].url==json.url){
        		imgs.splice(a,1);
			  }
        }
    }
}
/** 保存所有文件（图片）关系 返回uuid **/
function saveRel(upold_files){
	if(upold_files.length>0){
		$.ajax({
	        type: "post",
	        url:  img_url+"saveRel",
	        data: JSON.stringify(upold_files),
	        contentType:"application/json",
	        dataType: "json",
			error:function(data) {
				return "";
			}
	    });
	}else{
		return "";
	}
	return upold_files[0].objId;
	
}
/** 删除所有以上传文件（图片） **/
function delUploadFiles(){
	$.ajax({
        type: "post",
        url:  img_url+"del",
        data: JSON.stringify(files),
        contentType:"application/json",
        dataType: "json"
    });
}