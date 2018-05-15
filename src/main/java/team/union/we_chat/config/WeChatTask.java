package team.union.we_chat.config;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import team.union.sys_sp.util.ToolsUtil;
import team.union.sys_sp.util.httpClient.BiiwayHttpClientFactory;
import team.union.we_chat.com.cfg.BaseConfig.WE_CHAT;


/**
* ClassName: WeChatTask
* @Description: 微信两小时定时任务体
* @author dapengniao
* @date 2016年3月10日 下午1:42:29
*/
public class WeChatTask {
   /**
    * @Description: 任务执行体
    * @param @throws Exception
    * @author dapengniao
    * @date 2016年3月10日 下午2:04:37
    */
   public void getTokenAndTicket(){
       Map<String, String> params = new HashMap<String, String>();
       //获取token执行体
       params.put("grant_type", "client_credential");
       params.put("appid", WE_CHAT.Appid.getValue());
       params.put("secret", WE_CHAT.AppSecret.getValue());
       String jstoken = BiiwayHttpClientFactory.getInstance() .getBiiwayHttpClient()
    		   				.sendGet(WE_CHAT.tokenUrl.getValue(),params);
       //获取到token并赋值保存
       String access_token = (String) ToolsUtil.gsonToMap(jstoken.toString()).get("access_token"); 
       WE_CHAT.access_token.setValue(access_token);
       //获取jsticket的执行体
       params.clear();
       params.put("access_token", access_token);
       params.put("type", "jsapi");
       String jsticket = BiiwayHttpClientFactory.getInstance().getBiiwayHttpClient()
    		   			 .sendGet(WE_CHAT.ticketUrl.getValue(), params);
       //获取到js-SDK的ticket并赋值保存
       String jsapi_ticket = (String) ToolsUtil.gsonToMap(jsticket.toString()).get("ticket");
       WE_CHAT.jsapi_ticket.setValue(jsapi_ticket);
       WE_CHAT.timestamp.setValue(Long.toString(System.currentTimeMillis() / 1000));
       System.out.println("jsapi_ticket================================================" + jsapi_ticket);
       System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"access_token为=============================="+access_token);
       
   }

}