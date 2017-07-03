package team.union.nonbusiness.interceptor.utils;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import team.union.nonbusiness.com.cfg.BaseConfig.NO_NEED_INTERCEPTOR_URL;
import team.union.nonbusiness.sys.model.Users;


/**
 * @author Jack Zhang
 *
 */
public class InterceptorUtils {
	public static Users getUser(HttpServletRequest req) {
		Users user = (Users) req.getSession().getAttribute("user");
		return user;
	}
    /**
     * 跳过不需要拦截的url请求
     * @param requesUrl
     * @return
     */
    public static boolean skipUrl(String requesUrl){
    	for(NO_NEED_INTERCEPTOR_URL url :NO_NEED_INTERCEPTOR_URL.values()){
    		if(requesUrl.endsWith(url.getValue())){
    			return true;
    		}
    	}
    	return false;
    }
	
	/**
	 * 改变sessionId，防止session盗取
	 * 复制旧的session数据到新的session中
	 * @param req
	 */
	public static final void newSession(HttpServletRequest req){
		//修改sessionID
		HttpSession session = req.getSession();
    	//将原session中的数据转移至一临时map中  
        Map<String,Object> tempMap = new HashMap<>();  
        Enumeration<String> sessionNames = session.getAttributeNames();
        while(sessionNames.hasMoreElements()){  
            String sessionName = sessionNames.nextElement();  
            tempMap.put(sessionName, session.getAttribute(sessionName));  
        }  
        //注销原session，为的是重置sessionId  
        session.invalidate();  
        //将临时map中的数据转移至新session  
        session = req.getSession();  
        for(Map.Entry<String, Object> entry : tempMap.entrySet()){  
            session.setAttribute(entry.getKey(), entry.getValue());  
        } 
	}
}
