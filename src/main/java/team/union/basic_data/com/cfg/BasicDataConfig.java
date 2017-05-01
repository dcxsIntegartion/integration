package team.union.basic_data.com.cfg;

/**
 * 系统常用字典变量
 * @author chenS
 *
 */
public class BasicDataConfig {
	
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
	//优惠券状态
	public static enum COUPON_STATE{
		online(1,"已上线"),
		expired(2,"已过期"),
		offline(3,"已下线"),
		ineffective(4,"未生效");
		private COUPON_STATE(Integer value,String text){
			this.value=value;
			this.text=text;
		}
		private Integer value;
		private String text;
		public Integer getValue() {
			return value;
		}
		public void setValue(Integer value) {
			this.value = value;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
	}
}
