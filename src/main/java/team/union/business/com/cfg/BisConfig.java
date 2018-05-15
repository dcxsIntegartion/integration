package team.union.business.com.cfg;

/**
 * 系统常用字典变量
 * @author chenS
 *
 */
public class BisConfig {
	
	
	
	/**
	 * 活动类型
	 * @author Shuqianli
	 * @date 2017年4月30日
	 * Describe:
	 */
	public static enum ACTIVITY_TYPE{
		//（1特价，2折扣，3秒杀，4转盘）
		BARGAIN(1,"特价"),
		DISCOUNT(2,"折扣"),
		SECKILL(3,"秒杀"),
		TURNTABLE(4,"转盘");
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
	
	/**
	 * 活动状态
	 * @author Shuqianli
	 * @date 2017年4月30日
	 * Describe:
	 */
	public static enum ACTIVITY_STATUS{
		CLOSE(1,"开启"),
		OPEN(2,"关闭"),
		TIME_PASE(3,"已结束");
		private Integer number;
		private String name;
		private ACTIVITY_STATUS(Integer number,String name){
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
