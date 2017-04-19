package team.union.nonbusiness.upload.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import team.union.nonbusiness.com.cfg.BaseConfig;
import team.union.nonbusiness.upload.dao.NonbizUploadDao;
import team.union.nonbusiness.upload.model.NonbizUpload;
import team.union.nonbusiness.upload.service.IUploadService;
import team.union.nonbusiness.util.ToolsUtil;


@Repository
@Transactional(rollbackFor = Exception.class)
public class UploadService implements IUploadService {
	@Autowired
	private NonbizUploadDao nonbizUploadDao;
	
	/**
	 * 绑定文件（图片）业务关系
	 */
	public String banding(List<NonbizUpload> list) {
		String uuid = "";
		if(list.size()>0){
			uuid = (ToolsUtil.isNotEmpty(list.get(0).getObjId()))?list.get(0).getObjId():ToolsUtil.GetUUID();
			nonbizUploadDao.deleteByUUID(uuid);
			for(NonbizUpload vo : list){
				if(null!=vo && ToolsUtil.isNotEmpty(vo.getUrl())){
					vo.setObjId(uuid);
					nonbizUploadDao.insert(vo);
				}
			}
		}
		return uuid;
	}
	/***
	 * 查询文件（图片）
	 * 参数：object_id
	 */
	public List<NonbizUpload> selVoLst(String uuid){
		List<NonbizUpload> lst = new ArrayList<NonbizUpload>();
		if(ToolsUtil.isNotEmpty(uuid)){
			Map<String, Object> parm = new HashMap<String, Object>();
			parm.put("objId", uuid);
			lst = nonbizUploadDao.selVoLst(parm);
		}
		return lst;
	}
	/**
	 * 物理删除文件
	 * @param uuid
	 */
	public void delByUUID(String uuid) {
		if(ToolsUtil.isNotEmpty(uuid)){
			List<NonbizUpload> list = this.selVoLst(uuid);
			if(list.size()>0){
				nonbizUploadDao.deleteByUUID(uuid);
				for(NonbizUpload vo : list){
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
	};
}
