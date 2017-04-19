package team.union.nonbusiness.upload.service;

import java.util.List;

import team.union.nonbusiness.upload.model.NonbizUpload;


/**
 * 图片
 * @return
 * @author	chenS
 * @Date	2016-4-27 下午11:43:41
 */
public interface IUploadService {
	/**
	 * 为传入对象id绑定文件（图片）url
	 * @return   uuid
	 * @author	chenS
	 * @Date	2016-4-27 下午11:43:41
	 */
	public String banding(List<NonbizUpload> list);
	/***
	 * 查询文件（图片）
	 * 参数：object_id
	 */
	public List<NonbizUpload> selVoLst(String uuid);
	/**
	 * 物理删除文件
	 * @param uuid
	 */
	public void delByUUID(String uuid);
	/**
	 * 删除文件
	 * @param url
	 */
	public void delFile(String url);
}
