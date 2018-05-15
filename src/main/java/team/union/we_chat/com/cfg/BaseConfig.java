package team.union.we_chat.com.cfg;

/**
 * 系统常用字典变量
 * @author chenS
 *
 */
public class BaseConfig {
	public static enum WE_CHAT{
		//测试账号
//		Appid("wx1902b2fb914ffc65"),
//		AppSecret("1814e6c7f64333d6d0a11c8958662365"),
		//正式账号
		Appid("wx50e204de25ecc077"),  //公总号id
		AppSecret("39b6e13f720656e1b90ec191b5ee580f"),
		
		MchID("1448262102"),//商户id
		APIKey("7b3e300540c54c379f29a77dc65ade0f"),//API 密钥
		
		token(""),
		jsapi_ticket(""),
		timestamp(""),
		access_token(""),
		
		oauth2_access_token("https://api.weixin.qq.com/sns/oauth2/access_token"),
		oauth2_refresh_token("https://api.weixin.qq.com/sns/oauth2/refresh_token"),
	    
		tokenUrl("https://api.weixin.qq.com/cgi-bin/token"),
		ticketUrl("https://api.weixin.qq.com/cgi-bin/ticket/getticket");
		
		
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
	/** 微信网页授权请求地址 **/
	public static enum OAUTH2{
		get_code("https://open.weixin.qq.com/connect/oauth2/authorize"),
		access_token("https://api.weixin.qq.com/sns/oauth2/access_token"),
		refresh_token("https://api.weixin.qq.com/sns/oauth2/refresh_token"),
		get_userinfo("https://api.weixin.qq.com/sns/userinfo"),
		check_access_token("https://api.weixin.qq.com/sns/auth"),
		
		refresh_token_time(7200);
		private String url;
		private int second;
		private OAUTH2(String value){
			this.url = value;
		}
		public String getUrl() {
			return url;
		}
		public int getSecond() {
			return second;
		}
		private OAUTH2(int second){
			this.second = second;
		}
		
	}
}