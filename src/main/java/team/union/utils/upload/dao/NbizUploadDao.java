package team.union.utils.upload.dao;

import java.util.List;
import java.util.Map;

import team.union.basic_data.com.interf.IDao;
import team.union.utils.upload.model.NbizUpload;

public interface NbizUploadDao extends IDao<NbizUpload>{
	public List<NbizUpload> selVoLst(Map<String, Object> parm);
	/** 根据uuid删除文件（图片）数据  **/
	public void deleteByUUID(String uuid);
}