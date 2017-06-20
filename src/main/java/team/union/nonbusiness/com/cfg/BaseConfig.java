package team.union.nonbusiness.com.cfg;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 系统常用字典变量
 * @author chenS
 *
 */
public class BaseConfig {
	/*** 数据不足接收短信号码 **/
	public static String receiveNumber ="15296913758"; 
	public static String receiveStr ="项目（合作商账号）可推送数据不足";
	/*** 加密key **/
	public static String encryptKey ="denghongbing6666"; 
	
	/********发送短信用户、密码***********/
	public static String shortMessageName;
	public static String shortMessagePwd;
	public static String shortMessageMsgStart="您验证码为：";
	public static String shortMessageMsgEnd=",60分钟内有效";
	
	public static String shortMessageMsgUpdatPsw="您重置密码的验证码是：";
	public static String shortMessageMsgUpdatPswEnd=",如非本人操作请勿泄露给他人。";
	public static String shortMessageService;
	public static String shortMessagePath;
	public static String applicationLookStart="百盘好房，平台特价。凭验证码";
	public static String applicationLookEnd= "至各盘领取好礼。详4008339518【http://www.爱美爱家.中国】不需要回复TD";
	/********图片路径************/
	public static String ArticlePicturePhysicalPath;
	public static String ArticlePictureVituralPath;
	/********文件路径************/
	public static String ArticleAttachmentPhysicalPath;
	public static String ArticleAttachmentVituralPath;
	/**********报表路径******************/
	public static String ExportFormPath;
	private static Logger logger= Logger.getLogger(BaseConfig.class);
	static{
		try {
			String path = BaseConfig.class.getClassLoader().getResource("").toURI().getPath();
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(new File(path + "jdbc_mysql.properties"));  
			prop.load(fis);
			ExportFormPath = prop.getProperty("export_form_path");
			ArticleAttachmentVituralPath  = prop.getProperty("article_attachment_vitural_path");
			ArticleAttachmentPhysicalPath = prop.getProperty("article_attachment_physical_path");
			ArticlePicturePhysicalPath = prop.getProperty("article_picture_physical_path");
			ArticlePictureVituralPath = prop.getProperty("article_picture_vitural_path");
			shortMessageName =prop.getProperty("short_message_name");
			shortMessagePwd = prop.getProperty("short_message_pwd");
			shortMessageService=prop.getProperty("short_message_service");
			shortMessagePath=prop.getProperty("short_message_path");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("载入基础数据失败", e);
			e.printStackTrace();
		}
		
	}
	
	/*********************** 系统静态开始*******************/
	/** 系统缓存签名验证条件--条数 **/
	public static final int MAX_CACHE_COUNT = 5000;
	/** 系统缓存签名验证条件--间隔时间 **/
	public static final long MAX_TIMESTAMP_COUNT = 60*1000;
	
	/**第一组rsa钥匙  ：使用公钥在前端加密参数**/
	public static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDE6iE68s8YrsJ435rkHgmmJQhl1/yHY7zUzvyEDhKJC+d1wTO8d3+Ibw88X6egVI+05Rq79ANhOv5uVt9fValspSQRnzKLGd7JkW0kPMHdaU6Sae1ft3xVUl8Qw3VDzHh2UKKmxu8LbU4k10V5Rs9K6zD3W+wELOZKrqj9SbZcLQIDAQAB";
	public static final String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMTqITryzxiuwnjfmuQeCaYlCGXX/IdjvNTO/IQOEokL53XBM7x3f4hvDzxfp6BUj7TlGrv0A2E6/m5W319VqWylJBGfMosZ3smRbSQ8wd1pTpJp7V+3fFVSXxDDdUPMeHZQoqbG7wttTiTXRXlGz0rrMPdb7AQs5kquqP1JtlwtAgMBAAECgYEAtJWa3/qpHhO22S8HV0iMxMeVigCn4GoBVJB2V4yoRvKZ1A3YqnBUjwR6zn1StuCV4elxuQiwyMxXZU1aEI34jsR80M29Rbp3OtNyidnIV9cwBFaGTvwHg7JVDVY7zXBm9KJhst0CaUONPWrivjyw/kIneRLan7X2k+xMRMX0nWkCQQDmFCfzoHxSVlaO+GpDc1KS6L8BpIgCIGWpWJcrrsJ7rWXoQQRj1jXvFWTK7gRx3vsBURwQJv8TBeFFWJfcFoZXAkEA2xl3Jh9WLVIX7oHs1qsXvcU2ofRURQcFfHfwX89q0QI+qv0mMBBMAemWwb3J3o9N0CPf2iesoVvz34D3VGK3GwJAGBf+Qw4IVtsBv4EWJ7AY8pd7ASIIuChKXRyQ4Bsx9J+o71R3sDjLdxkHcBTS1FXkdTPYO3zJ82UHPSrU5FBbiwJALr2PAo36125E9re269DSHsTcs84o0BUAYZ5ApF/eXLpK3jVlGSnQ3TOU2r3/O8B8jCOrFKIUHNQ+AXdEzDbG8wJBAIcdmEQVk8Y0D8YjrynAW9YbmK8Ui6vx1XeQUxsZ88wqZRb9bhN1RaSyb17NpQjfJJyBTGUiougqGYZUlupMqJE=";
	/**第二组rsa钥匙  ： 使用密钥在前端生成签名**/
	public static final String PUBLIC_KEY_TWO = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCN/K7/yFTtoS7e40rF76tNMx4NvmiDm7Y4zPLyqiYDXOMs23DpTxC824REI40df2WrB3z/AvWw3FyO9LU4nO58vAvafzGb3pqEmTqBX1+abSITzn+bnVs27alssXlt+83my5YFe9dsGfnWaQ309OWNCuzNrNELoAi0XLVuzKkWWQIDAQAB";
	public static final String PRIVATE_KEY_TWO = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAI38rv/IVO2hLt7jSsXvq00zHg2+aIObtjjM8vKqJgNc4yzbcOlPELzbhEQjjR1/ZasHfP8C9bDcXI70tTic7ny8C9p/MZvemoSZOoFfX5ptIhPOf5udWzbtqWyxeW37zebLlgV712wZ+dZpDfT05Y0K7M2s0QugCLRctW7MqRZZAgMBAAECgYAoeQOFI5yZ090haZaxzzx8F/sUHdd61FLf5APIihml0b9r1O1dg80YmFYeeifKZbscQlkt87EHBflYQoa5qXoNIyo3CUeaHAP6pYr/nPaxu7PANI0ZnrpjW1t1Xe02GWd6p3joFbcWUhCGcfMfEa2hXHwFHOVZIA2bHqaxZ4pZxQJBAOopiARiLSleqc2TkBnW8SbLaYlXaRRF1/eAqdsv/zphRq9fEg0c9eY/7FQuLR+ua5IhW57qZmEmQKM2mMkTsiMCQQCbOofSWtlloJ+BgYPuB/Wq9Wko0D6K1mMlCtX51xaMubVTBaMbM5o9qd++DZQMkahCr+EKz7D/ex+Q9liDASdTAkA1EcaO38VGe/rV6ZyeDpXG6hD4HIRnINEqedGFKKKak5NWiaBosmiUj2Y7Sd/WL0yX6NF/+bXMTMQXeXc1Ey6rAkBdHYo2HDtGpEiqdhe+5NVwfRBc5DZwMFR+9vYOjgC/3/KuX7ZM7fJ7RPirWBfURlfJ0RlM0/OX/bKc0bmctEdzAkEAmAP5NAy7RdcB80yx+Jy/fVNd9HWTv3orl1skoh1XgtxYyKmdQPZ4L/Kwl0WUbJUW0kIRN4SqR/+Ht/J5+toiSQ==";
	
	/**1成功*/
	public static final int SUCCESS_STATUS = 1; 
	/**0失败 */
	public static final int FAILED_STATUS = 0;  
	/**0登陆失败 */
	public static final int LOGIN_FAILED_STATUS = 405;  
	/**2登陆失效 */
	public static final String ALL_CN_CITY = "100000";
	/**开通*/
    public static final int OPEN = 2;
    /**关闭*/
	public static final int CLOSE = 1;
	/**省*/
	public static final int CITY_PROVINCE = 1;
	/**市*/
	public static final int CITY_PROVINCE_AND_CITY = 2;
	/**区*/
	public static final int CITY_AREA = 3;
	
	/**token有效时间（毫秒） 毫秒*秒钟*分钟*小时*天 */
	public static final long EFFECTIVE_TIME = 1000*60*60*24*10;
		
	public static enum NO_NEED_FILTER_URL{
		IMG("/img"),
		FILE("/file"),
		UEDITOR("/Config");
		private NO_NEED_FILTER_URL(String value){
			this.value=value;
		}
		private String value;
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}
	public static enum DECRYPT_ERROR{
		REQUEST_TIMEOUT(1,"解析失败：超时"),
		REPLAY_DATA(2,"解析失败：重放数据"),
		INVALID_SIGNATURE(3,"解析失败：签名无效");
		private int value;
		private String msg;
		private DECRYPT_ERROR(int value,String msg){
			this.value = value;
			this.msg = msg;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
	}
	
	
	
}
