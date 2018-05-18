package team.union.utils.upload.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import team.union.sys_sp.util.ToolsUtil;
import team.union.utils.upload.model.NbizUpload;
import team.union.utils.upload.service.IUploadService;
import team.union.utils.upload.utils.WebImagesUploader;

/**
 * @author: Zhang Qi <br>
 * Date: 2014-5-10 <br>
 * Version: v1.0
 * 文件上传接口
 */
@Controller
@RequestMapping("upload")
public class UploadAPI {
	
	@Autowired
	private IUploadService uploadService; 
	
	@ResponseBody
	@RequestMapping(value="/img")
	public NbizUpload imagesUp(HttpServletRequest request,HttpServletResponse response) throws Exception{
		WebImagesUploader uploader = new WebImagesUploader(request);
		uploadService.uplodImg(uploader);
		NbizUpload vo = new NbizUpload();
		vo.setObjId(uploader.getUuid());
		vo.setName(uploader.getOriginalName());
		vo.setUrl(uploader.getUrl());
		vo.setSize(uploader.getSize());
		return vo;
		}
	@ResponseBody
	@RequestMapping(value="/file")
	public NbizUpload fileUp(HttpServletRequest request,HttpServletResponse response) throws Exception{
		WebImagesUploader uploader = new WebImagesUploader(request);
		uploadService.uplodFile(uploader);
		NbizUpload vo = new NbizUpload();
		vo.setName(uploader.getOriginalName());
		vo.setUrl(uploader.getUrl());
		vo.setSize(uploader.getSize());
		return vo;
		}
	@ResponseBody
	@RequestMapping(value="/selFiles")
	public List<NbizUpload> saveRel(@RequestParam String uuid, HttpServletRequest req){
		return uploadService.selVoLst(uuid);
	}
	
	@ResponseBody
	@RequestMapping(value="/saveRel")
	public String saveRel(@RequestBody List<NbizUpload> lst){
		String uuid = "";
		if(lst.size()>0){
			uuid = uploadService.banding(lst);
		}
		return uuid;
	}
	@ResponseBody
	@RequestMapping(value="/del")
	public void del(@RequestBody List<NbizUpload> lst){
		if(lst.size()>0){
			for(NbizUpload vo : lst){
				if(ToolsUtil.isNotEmpty(vo.getUrl())){
					uploadService.delFile(vo.getUrl());
				}
			}
		}
	}
	
}
