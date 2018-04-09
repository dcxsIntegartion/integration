package team.union.we_chat.controller;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import team.union.we_chat.com.rs.WeChatRS;
import team.union.we_chat.config.JSSDK_Config;

/**
* ClassName: WeChatController
* @Description: 前端用户微信配置获取
* @author dapengniao
* @date 2016年3月19日 下午5:57:36
*/
@Controller
@RequestMapping("/wechat/config")
public class WeChatController {

   /**
    * @Description: 前端获取微信JSSDK的配置参数
    * @param @param response
    * @param @param request
    * @param @param url
    * @param @throws Exception
    * @author dapengniao
    * @date 2016年3月19日 下午5:57:52
    */
   @RequestMapping("jssdk")
   @ResponseBody
   public WeChatRS JSSDK_config(
           @RequestParam(value = "url", required = true) String url) {
       try {
           Map<String, String> configMap = JSSDK_Config.jsSDK_Sign(url);
           System.out.println("----------------------------------------------------------------------");
           return WeChatRS.success(configMap);
       } catch (Exception e) {
           return WeChatRS.error();
       }
   }
}