package team.union.nonbusiness.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import team.union.nonbusiness.filter.util.XssShieldUtil;
import team.union.nonbusiness.interceptor.util.Decrypt;
import team.union.nonbusiness.util.ToolsUtil;


/**
 * Title: 过滤器
 * Description:
 *  目的 1：过滤request中body的数据
 * 	1.过滤器先于拦截器执行
 *  2.request中getInputStream和getReader只能执行一次，需要重写
 *  3.需要将此过滤器放在第一个位子，传递已经封装request
 *  目的 2：过滤request中参数
 * @author chens
 * @date 2017年5月12日
 * @version 1.0
 */
public class RequestFilter extends OncePerRequestFilter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain) throws IOException, ServletException{
		String uri = request.getRequestURI();
		if(ToolsUtil.isNotEmpty(uri) && XssShieldUtil.skipUrl(uri)){
			chain.doFilter(request, response);
		}else{
			ServletRequest requestWrapper = Decrypt.DecryptData(request);
	        chain.doFilter(requestWrapper, response);
		}
	}
}
