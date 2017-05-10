package team.union.nonbusiness.interceptor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Title: 拦截器需要用的一些业务方法
 * Description:
 * @author chens
 * @date 2017年5月10日
 * @version 1.0
 */
public class InterceptorUtil {

	/**
	 * 特殊字符过滤
	 * @param str
	 * @return
	 * @throws PatternSyntaxException
	 */
	public static String StringFilter(String   str){      
		  // 清除掉所有特殊字符   
		  String regEx="[`~@#$%^&*()+=|{}''\\[\\]<>/~@#￥%……&*（）——+|{}【】‘”“’]";//"[`~$^()=''[]<>~@#￥%&*（）——+|{}【】]";   
		  Pattern   p   =   Pattern.compile(regEx);      
		  Matcher   m   =   p.matcher(str);      
		  return   m.replaceAll("").trim();      
	}  
	
}
