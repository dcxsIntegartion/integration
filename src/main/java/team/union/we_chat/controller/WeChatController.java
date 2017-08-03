package team.union.we_chat.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import team.union.basic_data.cust.model.CustModelLv;
import team.union.basic_data.cust.service.ICustModelLvService;
import team.union.nonbusiness.util.ToolsUtil;
import team.union.we_chat.com.rs.WeChatRS;
import team.union.we_chat.config.JSSDK_Config;
import team.union.we_chat.config.UserInfoUtil;

/**
* ClassName: WeChatController
* @Description: 前端用户微信配置获取
* @author dapengniao
* @date 2016年3月19日 下午5:57:36
*/
@Controller
@RequestMapping("/wechat/config")
public class WeChatController {
  @Autowired
  private ICustModelLvService custModelLvService;
	
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
	   WeChatRS rs = new WeChatRS();
       try {
           Map<String, String> configMap = JSSDK_Config.jsSDK_Sign(url);
           rs.isSuccess(configMap);
           return rs;
       } catch (Exception e) {
           return rs;
       }
   }
   
   /**
    * 获取微信用户信息
    * @param req
    * @return
    */
   @RequestMapping("userInfo")
   @ResponseBody
   public WeChatRS userInfo(HttpServletRequest req) {
	   String code = "";
	   String openid = "";
	   WeChatRS rs = new WeChatRS();
	   if(null!=req.getAttribute("code")){
		   code = req.getAttribute("code").toString();
		   openid = UserInfoUtil.getUserInfo(req,code);
	   }
	   Map<String, Object> parm = new HashMap<String, Object>();
	   List<CustModelLv> cust = new ArrayList<CustModelLv>();
	   if(ToolsUtil.isNotEmpty(openid)){
		   parm.put("wechatId", openid);
		   cust = custModelLvService.selVoLst(parm);
	   }
	   if(cust.size()>0){
		   req.getSession().setAttribute("openid", openid);
		   rs.isSuccess(req.getAttribute("openid").toString());
		   rs.setInfo(cust.get(0).getPhone().toString());
		   return rs;
	   }
	   rs.setData(openid);
       return rs;
   }

}