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
	/** 客户层级  级数**/
	public static int CUST_MODEL_LV = 6;
	
	
}
