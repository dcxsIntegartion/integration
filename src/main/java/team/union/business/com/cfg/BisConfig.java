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
	
	
	
	
}