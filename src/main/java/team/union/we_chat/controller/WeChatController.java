package team.union.we_chat.controller;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import team.union.business.com.rs.Result;
import team.union.sys_sp.sys.service.UserService;
import team.union.sys_sp.util.IPAndMacUtil;
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
   @RequestMapping("/snsapiUserinfo")
   @ResponseBody
   public WeChatRS getUserinfo(
           @RequestParam(value = "code", required = true) String code,
           @RequestParam(value = "state", required = true) String state) {
           WXUserAuth userAuth = Authorize.setUserAuth(code);
		   WXUserInfo userInfo = null;
           if(null!=userAuth && null != userAuth.getAccessToken()){
        	   userInfo = Authorize.setUserInfo(userAuth.getAccessToken(),userAuth.getOpenid());
        	   userService.saveWXuser(userInfo);
           }
           return WeChatRS.success();
   }
   /**
    * 店员扫码 强制用户授权 获取用户微信信息
    * 1.保存扫码店员信息
    * 2.维护店员与店铺中间关系
    * 3.生成为客户扫码关注的二维码
    * @param code
    * @param state
    * @return
    */
   @RequestMapping("/staffScavenging")
   @ResponseBody
   public WeChatRS staffScavenging(
		   HttpServletRequest req,
		   @RequestParam(value = "code", required = true) String code,
           @RequestParam(value = "state", required = true) String state){
	   
	   IPAndMacUtil.getIpAddr(req);
	   WXUserAuth userAuth = Authorize.setUserAuth(code);
	   WXUserInfo userInfo = null;
       if(null!=userAuth && null != userAuth.getAccessToken()){
    	   userInfo = Authorize.setUserInfo(userAuth.getAccessToken(),userAuth.getOpenid());
       }
       String ServiceName ="ncyzo."+req.getServerName();
	   if(null!=userInfo && null!=ServiceName && !"".equals(ServiceName) && 
			   ServiceName.split("\\.").length>0 && StringUtils.isNoneEmpty(userInfo.getOpenid())){
		   Result result = userService.staffScavenging(userInfo,ServiceName.split("\\.")[0]);
		   if(result.isSuccess()){
			   return WeChatRS.success(result.getData());
		   }
		   	
	   }
	   return WeChatRS.error();
   }
   /**
    * 游客扫码 非强制用户授权 获取用户微信信息
    * @param code
    * @param state
    * @return
    */
   public WeChatRS touristScavenging(
		   HttpServletRequest req,
		   @RequestParam(value = "code", required = true) String code,
           @RequestParam(value = "state", required = true) String state){
	   String ServiceName = req.getServerName();
	   WXUserAuth userAuth = Authorize.setUserAuth(code);
	   if(null!=ServiceName && !"".equals(ServiceName) && ServiceName.split("\\.").length>0){
		   Result result = userService.touristScavenging(userAuth,ServiceName.split("\\.")[0],Long.parseLong(state));
		   if(result.isSuccess()){
			   
		   }
	   }
	   
	   return null;
   }
}