package team.union.basic_data.com.rs;

import java.io.Serializable;

import team.union.basic_data.com.cfg.BasicDataConfig.RESULT_STATE;

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

	public Result() {
	}
	public void isError(){
		this.msg = RESULT_STATE.FAIL.getMsg();
		this.state = RESULT_STATE.FAIL.getNumber().toString();
		
	}
	public void isSuccess(){
		this.msg = RESULT_STATE.SUCCESS.getMsg();
		this.state= RESULT_STATE.SUCCESS.getNumber().toString();
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

}
