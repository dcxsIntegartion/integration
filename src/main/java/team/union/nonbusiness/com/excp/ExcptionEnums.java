/**
 * 异常信息枚举
 * @projectName：点点帮电商平台
 * @className:ExcptionEnums.java
 * @description:
 * @author:yuhaiyang
 * @Created: 2015年12月11日
 * @version:
 * @ModificationHistory:
 * @company 成都百维
 */

package team.union.nonbusiness.com.excp;

public enum ExcptionEnums {

	/**
	 * 主键为空
	 */
	PRIMARY_NULL("主键为空"),
	/**
	 * 参数对象为空
	 */
	PARAM_OBJECT_IS_NULL("参数对象为空"),
	/**
	 * 参数不对
	 */
	PARAM_IS_WRONG("参数不对"),
	/**
	 * 主键生成错误
	 */
	GET_PRIMARY_KEY_ERROR("主键生成错误"),
	/**
	 * 数据类型不对
	 */
	TYPE_WRONG("数据类型出错"),
	
	/**
	 * 保存业务数据出错
	 */
	SAVE_ERROR("保存业务数据出错"),
	/**
	 * 删除业务数据出错
	 */
	DELETE_ERROR("删除出错"),
	/**
	 * 更新出错
	 */
	UPDATE_ERROR("更新出错"),
	/**
	 * 库存查询出错
	 */
	STOCK_QUERY_ERROR("库存查询出错"),
	/**
	 * 下架失败
	 */
	DOWN_ERROR("下架失败");
	
	private String desc;
	
	private ExcptionEnums(String desc) {
		this.desc=desc;
	}
	
	public String getDesc() {
		return desc;
	}
	
	
}
