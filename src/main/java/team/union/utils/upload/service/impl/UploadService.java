package team.union.utils.upload.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import team.union.sys_sp.com.cfg.BaseConfig;
import team.union.sys_sp.util.ToolsUtil;
import team.union.utils.algorithm.MD5Utils;
import team.union.utils.upload.dao.NbizUploadDao;
import team.union.utils.upload.model.NbizUpload;
import team.union.utils.upload.service.IUploadService;
import team.union.utils.upload.utils.WebImagesUploader;


@Repository
@Transactional(rollbackFor = Exception.class)
public class UploadService implements IUploadService {
	@Autowired
	private NbizUploadDao nbizUploadDao;
	
	/**
	 * 绑定文件（图片）业务关系
	 */
	public String banding(List<NbizUpload> list) {
		String uuid = "";
		if(list.size()>0){
			uuid = (ToolsUtil.isNotEmpty(list.get(0).getObjId()))?list.get(0).getObjId():MD5Utils.GetUUID();
			nbizUploadDao.deleteByUUID(uuid);
			for(NbizUpload vo : list){
				if(null!=vo && ToolsUtil.isNotEmpty(vo.getUrl())){
					vo.setObjId(uuid);
					nbizUploadDao.insert(vo);
				}
			}
		}
		return uuid;
	}
	/***
	 * 查询文件（图片）
	 * 参数：object_id
	 */
	public List<NbizUpload> selVoLst(String uuid){
		List<NbizUpload> lst = new ArrayList<NbizUpload>();
		if(ToolsUtil.isNotEmpty(uuid)){
			Map<String, Object> parm = new HashMap<String, Object>();
			parm.put("objId", uuid);
			lst = nbizUploadDao.selVoLst(parm);
		}
		return lst;
	}
	/**
	 * 物理删除文件
	 * @param uuid
	 */
	public void delByUUID(String uuid) {
		if(ToolsUtil.isNotEmpty(uuid)){
			List<NbizUpload> list = this.selVoLst(uuid);
			if(list.size()>0){
				nbizUploadDao.deleteByUUID(uuid);
				for(NbizUpload vo : list){
					this.delFile(vo.getUrl());
				}
			}
		}
	} 
	/**
	 * 删除文件
	 * @param url
	 */
	public void delFile(String url){
		if(ToolsUtil.isNotEmpty(url)){
			url = url.replace(BaseConfig.ArticlePictureVituralPath, BaseConfig.ArticlePicturePhysicalPath)
			   .replace(BaseConfig.ArticleAttachmentVituralPath, BaseConfig.ArticleAttachmentPhysicalPath);
			File deleteFile = new File(url);
			if (deleteFile.exists() && deleteFile.canWrite()){
				deleteFile.delete();
			}
		}
	}
	public WebImagesUploader uplodImg(WebImagesUploader uploader) throws Exception{
		String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp",".xls"};
	    uploader.setAllowFiles(fileType);
	    uploader.setMaxSize(30000); //单位KB
	    uploader.setPhysicalPath(BaseConfig.ArticlePicturePhysicalPath);
	    uploader.setVituralPath(BaseConfig.ArticlePictureVituralPath);
	    uploader.upload();
		return uploader;
	}
	public WebImagesUploader uplodFile(WebImagesUploader uploader) throws Exception {
		String[] fileType = {".png", ".jpg", ".jpeg", ".gif", ".bmp",
		        ".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg",
		        ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid",
		        ".rar", ".zip", ".tar", ".gz", ".7z", ".bz2", ".cab", ".iso",
		        ".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx", ".pdf", ".txt", ".md", ".xml"};
	    uploader.setAllowFiles(fileType);
	    uploader.setMaxSize(51200); //单位KB
	    uploader.setPhysicalPath(BaseConfig.ArticleAttachmentPhysicalPath);
	    uploader.setVituralPath(BaseConfig.ArticleAttachmentVituralPath);
	    uploader.upload();
		return uploader;
	};
}
