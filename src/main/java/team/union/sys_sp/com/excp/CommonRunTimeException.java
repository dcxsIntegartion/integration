/**
 * 公共运行时异常类
 * @projectName：点点帮电商平台
 * @className:CommonRunTimeException.java
 * @description:
 * @author:yuhaiyang
 * @Created: 2015年12月11日
 * @version:
 * @ModificationHistory:
 * @company  成都百维
 */
package team.union.sys_sp.com.excp;


public class CommonRunTimeException extends RuntimeException {
 
	private static final long serialVersionUID = -3162554601483811222L;
	/***
	 * 错误的枚举
	 */
	private ExcptionEnums enums;
	/**
	 * 补充错误信息
	 */
	private String errorInfo;
	
	public CommonRunTimeException(ExcptionEnums enums,String errorInfo) {
		this.enums=enums;
		this.errorInfo=errorInfo;
	}

	@Override
	public String getMessage() {
		return enums.getDesc()+"====***************************=================="+errorInfo;
	}
	
}
