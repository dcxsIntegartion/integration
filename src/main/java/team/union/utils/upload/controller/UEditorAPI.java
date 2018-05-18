package team.union.utils.upload.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import team.union.utils.upload.rs.UEditorRs;
import team.union.utils.upload.service.IUEditorService;
import team.union.utils.upload.service.IUploadService;
import team.union.utils.upload.utils.WebImagesUploader;


/** 
 * Description:富文本编辑器控制层
 * @author chens
 * @date 2017年4月22日
 * @version 1.0
 */
@Controller
@RequestMapping("ueditor")
public class UEditorAPI {
	@Autowired
	private IUEditorService ueditorService;
	@Autowired
	private IUploadService uploadService; 
	
	@ResponseBody
	@RequestMapping(value="/Config")
	public String getConfig(@RequestParam String action){
		String result = "";
		if(action.equals("config")){
			result = ueditorService.readUEditorJson("/team/union/sys_sp/upload/config/UEditor.json");
		}
		return result;
	}
	@ResponseBody
	@RequestMapping(value="/img")
	public UEditorRs uploadImg(HttpServletRequest request) throws Exception{
		WebImagesUploader uploader = new WebImagesUploader(request);
		uploadService.uplodImg(uploader);
		return UEditorRs.isSuccess(uploader.getUrl(), uploader.getOriginalName());
	}
	@ResponseBody
	@RequestMapping(value="/file")
	public UEditorRs uploadFile(HttpServletRequest request) throws Exception{
		WebImagesUploader uploader = new WebImagesUploader(request);
		uploadService.uplodFile(uploader);
		return UEditorRs.isSuccess(uploader.getUrl(), uploader.getOriginalName());
	}
}