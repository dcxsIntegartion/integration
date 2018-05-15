package team.union.we_chat.config;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.UUID;
import team.union.we_chat.com.cfg.BaseConfig.WE_CHAT;

/**
* ClassName: JSSDK_Config
* @Description: 用户微信前端页面的jssdk配置使用
* @author dapengniao
* @date 2016年3月19日 下午3:53:23
*/
public class JSSDK_Config {

   /**
    * @Description: 前端jssdk页面配置需要用到的配置参数
    * @param @return hashmap {appid,timestamp,nonceStr,signature}
    * @param @throws Exception   
    * @author dapengniao
    * @date 2016年3月19日 下午3:53:23
    */
   public static HashMap<String, String> jsSDK_Sign(String url){
       String nonce_str = create_nonce_str();
       String jsapi_ticket=WE_CHAT.jsapi_ticket.getValue();
       // 注意这里参数名必须全部小写，且必须有序
       String  string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str
               + "&timestamp=" + WE_CHAT.timestamp.getValue()  + "&url=" + url;
       MessageDigest crypt;
       String signature = "";
	   try {
		   crypt = MessageDigest.getInstance("SHA-1");
		   crypt.reset();
		   crypt.update(string1.getBytes("UTF-8"));
		   signature = byteToHex(crypt.digest());
	   } catch (NoSuchAlgorithmException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	   } catch (UnsupportedEncodingException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	   }
       
       HashMap<String, String> jssdk=new HashMap<String, String>();
       jssdk.put("appId",WE_CHAT.Appid.getValue());
       jssdk.put("timestamp", WE_CHAT.timestamp.getValue());
       jssdk.put("nonceStr", nonce_str);
       jssdk.put("signature", signature);
       return jssdk;
   }
  
   private static String byteToHex(final byte[] hash) {
       Formatter formatter = new Formatter();
       for (byte b : hash) {
           formatter.format("%02x", b);
       }
       String result = formatter.toString();
       formatter.close();
       return result;
   }
    
   private static String create_nonce_str() {
       return UUID.randomUUID().toString();
   }
}