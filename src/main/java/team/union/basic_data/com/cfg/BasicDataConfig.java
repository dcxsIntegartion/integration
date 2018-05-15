package team.union.basic_data.com.cfg;

/**
 * 系统常用字典变量
 * @author chenS
 *
 */
public class BasicDataConfig {
	

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
