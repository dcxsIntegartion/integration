package team.union.we_chat.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import team.union.sys_sp.sys.service.UserService;
import team.union.we_chat.com.rs.WeChatRS;
import team.union.we_chat.config.JSSDK_Config;
import team.union.we_chat.oauth2.Authorize;
import team.union.we_chat.oauth2.WXUserAuth;
import team.union.we_chat.oauth2.WXUserInfo;

/**
* ClassName: WeChatController
* @Description: 前端用户微信配置获取
* @author dapengniao
* @date 2016年3月19日 下午5:57:36
*/
@Controller
@RequestMapping("/we_chat")
public class WeChatController {
	
	@Autowired
	private UserService userService;
	
   /**
    * @Description: 前端获取微信JSSDK的配置参数
    * @param @param response
    * @param @param request
    * @param @param url
    * @param @throws Exception
    * @author dapengniao
    * @date 2016年3月19日 下午5:57:52
    */
   @RequestMapping("/jssdk")
   @ResponseBody
   public WeChatRS JSSDK_config(
           @RequestParam(value = "url", required = true) String url) {
           Map<String, String> configMap = JSSDK_Config.jsSDK_Sign(url);
           System.out.println("----------------------------------------------------------------------");
           return WeChatRS.success(configMap);
   }
   
   @RequestMapping("/snsapiBase")
   @ResponseBody
   public WeChatRS getBase(
           @RequestParam(value = "code", required = true) String code,
           @RequestParam(value = "state", required = true) String state) {
           WXUserAuth userAuth = Authorize.setUserAuth(code);
           return userService.selByWXopenid(userAuth);
   }
   @SuppressWarnings("unused")
   @RequestMapping("/snsapiUserinfo")
   @ResponseBody
   public WeChatRS getUserinfo(
           @RequestParam(value = "code", required = true) String code,
           @RequestParam(value = "state", required = true) String state) {
           WXUserAuth userAuth = Authorize.setUserAuth(code);
		   WXUserInfo userInfo = null;
           if(null!=userAuth && null != userAuth.getAccessToken()){
        	   userInfo = Authorize.setUserInfo(userAuth.getAccessToken());
           }
           return userService.saveWXuser(userAuth);
   }
   
   
   
}