package team.union.business.com.cfg;

/**
 * 系统常用字典变量
 * @author chenS
 *
 */
public class BisConfig {
	
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
	
	/**
	 * 活动类型
	 * @author Shuqianli
	 * @date 2017年4月30日
	 * Describe:
	 */
	public static enum ACTIVITY_TYPE{
		BARGAIN(1,"特价");
		private Integer number;
		private String name;
		private ACTIVITY_TYPE(Integer number,String name){
			this.number=number;
			this.name=name;
		}
		public Integer getNumber() {
			return number;
		}
		public void setNumber(Integer number) {
			this.number = number;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
	
	
	
	
}
