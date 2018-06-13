package team.union.business.com.rs;

import java.io.Serializable;

import team.union.sys_sp.com.cfg.PromptMsgConfig.PROMPT;

/**
 * Title: 基础数据  操作返回
 * @author chenS
 * @date 2017-3-25
 * @version 1.0
 */
public class Result implements Serializable{

	private static final long serialVersionUID = 3507890824241560895L;
	/** 返回信息 */
	private String msg;
	/** 返回状态(0=失败;1=成功;n=失败;y=成功;) */
	private String state;
	/** 返回类型 */
	private String type;
	/** 返回数据 */
	private Object data;

	private boolean isSuccess;
	
	public static Result success(Object data){
		Result rs = new Result();
		rs.setData(data);
		rs.setState(PROMPT.SUCCESS.getNo());
		rs.setMsg(PROMPT.SUCCESS.getMsg());
		rs.isSuccess = true;
		return rs;
	}
	public static Result success(){
		Result rs = new Result();
		rs.setState(PROMPT.SUCCESS.getNo());
		rs.setMsg(PROMPT.SUCCESS.getMsg());
		rs.isSuccess = true;
		return rs;
	}
	public static Result error(){
		Result rs = new Result();
		rs.setState(PROMPT.FAIL.getNo());
		rs.setMsg(PROMPT.FAIL.getMsg());
		rs.isSuccess = false;
		return rs;
	}
	public static Result error(String errorMsg){
		Result rs = new Result();
		rs.setState(PROMPT.FAIL.getNo());
		rs.setMsg(errorMsg);
		rs.isSuccess = false;
		return rs;
	}
	
	
	public Result() {
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

}
