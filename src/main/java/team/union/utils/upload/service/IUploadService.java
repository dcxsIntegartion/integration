package team.union.utils.upload.service;

import java.util.List;
import team.union.utils.upload.model.NbizUpload;
import team.union.utils.upload.utils.WebImagesUploader;



/**
 * 图片
 * @return
 * @author	chenS
 * @Date	2016-4-27 下午11:43:41
 */
public interface IUploadService {
	/**
	 * 上传图片
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public WebImagesUploader uplodImg(WebImagesUploader uploader) throws Exception;
	/**
	 * 上传文件
	 * @param request
	 * @return
	 */
	public WebImagesUploader uplodFile(WebImagesUploader uploader)throws Exception;
	/**
	 * 为传入对象id绑定文件（图片）url
	 * @return   uuid
	 * @author	chenS
	 * @Date	2016-4-27 下午11:43:41
	 */
	public String banding(List<NbizUpload> list);
	/***
	 * 查询文件（图片）
	 * 参数：object_id
	 */
	public List<NbizUpload> selVoLst(String uuid);
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
