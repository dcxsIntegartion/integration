package team.union.sys_sp.com.rs;

import java.io.Serializable;

import team.union.sys_sp.com.cfg.PromptMsgConfig.PROMPT;


/**
 * 控制器返回结果到前台对象
 * 
 * @author yuel
 * @version 1.00
 * @see 参考类1
 * @Date 2015年5月18日 下午3:20:15
 */
public class ResultVo implements Serializable{

	private static final long serialVersionUID = 3507890824241560895L;
	/** 返回信息 */
	private String info;
	/** 返回状态(0=失败;1=成功;n=失败;y=成功;) */
	private Object status;
	/** 返回类型 */
	private String type;
	/** 返回数据 */
	private Object data;

	public ResultVo() {
		this.info = PROMPT.FAIL.getMsg();
		this.status = PROMPT.FAIL.getNo();
	}
	
	public void isError(){
		this.info = PROMPT.FAIL.getMsg();
		this.status = PROMPT.FAIL.getNo();
		
	}
	public void isSuccess(){
		this.info = PROMPT.SUCCESS.getMsg();
		this.status= PROMPT.SUCCESS.getNo();
	}
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	/** 返回状态(0=失败;1=成功;n=失败;y=成功;) */
	public Object getStatus() {
		return status;
	}

	/** 返回状态(0=失败;1=成功;n=失败;y=成功;) */
	public void setStatus(Object status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}