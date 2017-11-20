/**
 * 业务异常类
 * @projectName：点点帮电商平台
 * @className:BusinessException.java
 * @description:
 * @author:yuhaiyang
 * @Created: 2015年12月11日
 * @version:
 * @ModificationHistory:
 * @company 成都百维
 */

package team.union.sys_sp.com.excp;

public class BusinessException extends Exception{
	 
	private static final long serialVersionUID = -2592099601667739175L;

	public BusinessException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BusinessException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public BusinessException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public BusinessException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public BusinessException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	

}
