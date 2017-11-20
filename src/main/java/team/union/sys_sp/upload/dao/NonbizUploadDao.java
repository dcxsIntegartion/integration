package team.union.sys_sp.upload.dao;

import java.util.List;
import java.util.Map;

import team.union.basic_data.com.interf.IDao;
import team.union.sys_sp.upload.model.NonbizUpload;

public interface NonbizUploadDao extends IDao<NonbizUpload>{
	public List<NonbizUpload> selVoLst(Map<String, Object> parm);
	/** 根据uuid删除文件（图片）数据  **/
	public void deleteByUUID(String uuid);
}