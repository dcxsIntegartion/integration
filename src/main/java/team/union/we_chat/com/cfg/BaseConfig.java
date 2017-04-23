package team.union.we_chat.com.cfg;

/**
 * 系统常用字典变量
 * @author chenS
 *
 */
public class BaseConfig {
	public static enum WE_CHAT{
		Appid("wx1902b2fb914ffc65"),
		AppSecret("1814e6c7f64333d6d0a11c8958662365"),
		tokenUrl("https://api.weixin.qq.com/cgi-bin/token"),
		ticketUrl("https://api.weixin.qq.com/cgi-bin/ticket/getticket"),
		token(""),
		jsapi_ticket(""),
		timestamp(""),
		access_token("");
		private String value;
		private WE_CHAT(String value){
			this.value = value;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}
	public static enum RESULT_STATE{
		FAIL(0,"操作失败"),
		SUCCESS(1,"操作成功");
		private Integer number;
		private String msg;
		private RESULT_STATE(Integer number,String msg){
			this.number=number;
			this.msg=msg;
		}
		public Integer getNumber() {
			return number;
		}
		public void setNumber(Integer number) {
			this.number = number;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
	}
}