package team.union.we_chat.com.rs;

import java.io.Serializable;

import team.union.we_chat.com.cfg.BaseConfig.RESULT_STATE;

/**
 * 控制器返回结果到前台对象
 * 
 * @author yuel
 * @version 1.00
 * @see 参考类1
 * @Date 2015年5月18日 下午3:20:15
 */
public class WeChatRS implements Serializable{

	private static final long serialVersionUID = 3507890824241560895L;
	/** 返回信息 */
	private String info;
	/** 返回状态*/
	private Object status;
	/** 返回数据 */
	private Object data;

	public WeChatRS() {
	}
	public static WeChatRS success(Object data){
		WeChatRS rs = new WeChatRS();
		rs.setData(data);
		rs.setStatus(RESULT_STATE.SUCCESS.getNumber());
		rs.setInfo(RESULT_STATE.SUCCESS.getMsg());
		return rs;
	}
	public static WeChatRS error(){
		WeChatRS rs = new WeChatRS();
		rs.setStatus(RESULT_STATE.FAIL.getNumber());
		rs.setInfo(RESULT_STATE.FAIL.getMsg());
		return rs;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Object getStatus() {
		return status;
	}
	public void setStatus(Object status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

}