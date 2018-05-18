package team.union.utils.upload.service;
/**
 * Description: 富文本编辑器-业务层接口
 * @author chens
 * @date 2017年4月26日
 * @version 1.0
 */
public interface IUEditorService {
	/** 获取ueditor.json 配置文件 **/
	public String readUEditorJson(String packagePath);
	
}
