package team.union.we_chat.com.rs;

import team.union.we_chat.com.cfg.BaseConfig.RESULT_STATE;

/**
 * 控制器返回结果到前台对象
 * 
 * @author yuel
 * @version 1.00
 * @see 参考类1
 * @Date 2015年5月18日 下午3:20:15
 */
public class WeChatRS{
	
	/** 返回信息 */
	private String info;
	/** 返回状态*/
	private Object status;
	/** 返回数据 */
	private Object data;

	public WeChatRS() {
		info = RESULT_STATE.FAIL.getMsg();
		status = RESULT_STATE.FAIL.getNumber().toString();
	}
	public void isError(){
		info = RESULT_STATE.FAIL.getMsg();
		status = RESULT_STATE.FAIL.getNumber().toString();
		
	}
	public void isSuccess(){
		info = RESULT_STATE.SUCCESS.getMsg();
		status= RESULT_STATE.SUCCESS.getNumber().toString();
	}
	public void isSuccess(Object resultData){
		info = RESULT_STATE.SUCCESS.getMsg();
		status= RESULT_STATE.SUCCESS.getNumber().toString();
		data = resultData;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String infoData) {
		info = infoData;
	}
	public Object getStatus() {
		return status;
	}
	public void setStatus(Object statusData) {
		status = statusData;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object resultData) {
		data = resultData;
	}

}