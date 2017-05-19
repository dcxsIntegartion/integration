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
	
	public static enum CHARSET{
		 /** 7位ASCII字符，也叫作ISO646-US、Unicode字符集的基本拉丁块 */
		US_ASCII("US-ASCII"),
		 /** ISO 拉丁字母表 No.1，也叫作 ISO-LATIN-1 */
		ISO_8859_1("ISO-8859-1"),
		 /** 8 位 UCS 转换格式 */
		UTF_8("UTF-8"),
		 /** 16 位 UCS 转换格式，Big Endian（最低地址存放高位字节）字节顺序 */
		UTF_16BE("UTF-16BE"),
		 /** 16 位 UCS 转换格式，Little-endian（最高地址存放低位字节）字节顺序 */
		UTF_16LE("UTF-16LE"),
		/** 16 位 UCS 转换格式，字节顺序由可选的字节顺序标记来标识 */
		UTF_16("UTF-16"),
		 /** 中文超大字符集 */
		GBK("GBK");
		private CHARSET(String value){
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
}
