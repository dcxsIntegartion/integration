/**
 * @(#)SecurityInterceptor.java
 * Copyright 2013 Biiway Technology Co.,Ltd. All rights reserved.
 */
package team.union.nonbusiness.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import team.union.nonbusiness.sys.utils.WebUtils;


/**
 * Author: ZhangQi <br>
 * Date: 2013-8-16 <br>
 * Version: v2.0
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			String uri = request.getRequestURI();
			//微信jssdk/接收短信/发送短信/修改密码
			if(uri.endsWith("/wechat/config/jssdk")|| uri.endsWith("/receiveShortMessage") || uri.endsWith("/sendShortMessage")){
			 	return true;
			}
			
	        // 判断路径是登出还是登录验证，是这两者之一的话执行Controller中定义的方法
	        if(uri.endsWith("/login") || uri.endsWith("/login_out") || uri.endsWith("au/fail") ) {
	            return true;
	        }
						
			HandlerMethod actionHandler = (HandlerMethod) handler;
			if(isAccessable(request)){
				return super.preHandle(request, response, handler);
			}
			
			ResponseBody body = actionHandler.getMethodAnnotation(ResponseBody.class);
			if(body!=null){
				setClientAccessError(request, response);
			}else{
				response.sendRedirect("/integration/login.html");
			}
			return false;
		}
		return super.preHandle(request, response, handler);
	}
	/**
	 * 通过session判断浏览器用户是否登陆
	 * 通过token判断app用户是否登陆
	 * */
	public boolean isAccessable(HttpServletRequest req)
			throws IOException {
		if(WebUtils.getUser(req)!=null){
			return true;
		}
		return false;
	}

	private void setClientAccessError(HttpServletRequest req,
			HttpServletResponse response) throws IOException {
		//response.sendRedirect("/eshop-web/au/fail");
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
	}
	

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}



	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//HandlerMethod actionHandler = (HandlerMethod) handler;
	
		super.postHandle(request, response, handler, modelAndView);
	}
}
