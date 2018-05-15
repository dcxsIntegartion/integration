package team.union.sys_sp.filter.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import team.union.sys_sp.com.cfg.BaseConfig.NO_NEED_FILTER_URL;
import team.union.sys_sp.util.ToolsUtil;

/**
 * 处理非法字符
 *
 * @author   单红宇(365384722)
 * @myblog  http://blog.csdn.net/catoop/
 * @create    2015年9月18日
 */
public class XssShieldUtil {

    private static List<Pattern> patterns = null;

    private static List<Object[]> getXssPatternList() {
        List<Object[]> ret = new ArrayList<Object[]>();

        ret.add(new Object[]{"<(no)?script[^>]*>.*?</(no)?script>", Pattern.CASE_INSENSITIVE});
        ret.add(new Object[]{"eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL});
        ret.add(new Object[]{"expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL});
        ret.add(new Object[]{"(javascript:|vbscript:|view-source:)*", Pattern.CASE_INSENSITIVE});
        ret.add(new Object[]{"<(\"[^\"]*\"|\'[^\']*\'|[^\'\">])*>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL});
        ret.add(new Object[]{"(window\\.location|window\\.|\\.location|document\\.cookie|document\\.|alert\\(.*?\\)|window\\.open\\()*", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL});
        ret.add(new Object[]{"<+\\s*\\w*\\s*(oncontrolselect|oncopy|oncut|ondataavailable|ondatasetchanged|ondatasetcomplete|ondblclick|ondeactivate|ondrag|ondragend|ondragenter|ondragleave|ondragover|ondragstart|ondrop|onerror=|onerroupdate|onfilterchange|onfinish|onfocus|onfocusin|onfocusout|onhelp|onkeydown|onkeypress|onkeyup|onlayoutcomplete|onload|onlosecapture|onmousedown|onmouseenter|onmouseleave|onmousemove|onmousout|onmouseover|onmouseup|onmousewheel|onmove|onmoveend|onmovestart|onabort|onactivate|onafterprint|onafterupdate|onbefore|onbeforeactivate|onbeforecopy|onbeforecut|onbeforedeactivate|onbeforeeditocus|onbeforepaste|onbeforeprint|onbeforeunload|onbeforeupdate|onblur|onbounce|oncellchange|onchange|onclick|oncontextmenu|onpaste|onpropertychange|onreadystatechange|onreset|onresize|onresizend|onresizestart|onrowenter|onrowexit|onrowsdelete|onrowsinserted|onscroll|onselect|onselectionchange|onselectstart|onstart|onstop|onsubmit|onunload)+\\s*=+", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL});
        return ret;
    }
    /**
     * sql关键字过滤
     * @param sqlStr
     * @return
     */
    public static String SafeSql(String sqlStr){
    	sqlStr = Pattern.compile("exec",Pattern.CASE_INSENSITIVE).matcher(sqlStr).replaceAll("&#101;xec");
        sqlStr = Pattern.compile("xp_cmdshell",Pattern.CASE_INSENSITIVE).matcher(sqlStr).replaceAll("&#120;p_cmdshell");
        sqlStr = Pattern.compile("select",Pattern.CASE_INSENSITIVE).matcher(sqlStr).replaceAll("&#115;elect");
        sqlStr = Pattern.compile("insert",Pattern.CASE_INSENSITIVE).matcher(sqlStr).replaceAll("&#105;nsert");
        sqlStr = Pattern.compile("update",Pattern.CASE_INSENSITIVE).matcher(sqlStr).replaceAll("&#117;pdate");
        sqlStr = Pattern.compile("delete",Pattern.CASE_INSENSITIVE).matcher(sqlStr).replaceAll("&#100;elete");
        sqlStr = Pattern.compile("drop",Pattern.CASE_INSENSITIVE).matcher(sqlStr).replaceAll("&#100;rop");
        sqlStr = Pattern.compile("create",Pattern.CASE_INSENSITIVE).matcher(sqlStr).replaceAll("&#99;reate");
        sqlStr = Pattern.compile("rename",Pattern.CASE_INSENSITIVE).matcher(sqlStr).replaceAll("&#114;ename");
        sqlStr = Pattern.compile("truncate",Pattern.CASE_INSENSITIVE).matcher(sqlStr).replaceAll("&#116;runcate");
        sqlStr = Pattern.compile("alter",Pattern.CASE_INSENSITIVE).matcher(sqlStr).replaceAll("&#97;lter");
        sqlStr = Pattern.compile("exists",Pattern.CASE_INSENSITIVE).matcher(sqlStr).replaceAll("&#101;xists");
        sqlStr = Pattern.compile("master",Pattern.CASE_INSENSITIVE).matcher(sqlStr).replaceAll("&#109;aster.");
        sqlStr = Pattern.compile("restore",Pattern.CASE_INSENSITIVE).matcher(sqlStr).replaceAll("&#114;estore");
        return sqlStr;
    }
    
    private static List<Pattern> getPatterns() {
        if (patterns == null) {
            List<Pattern> list = new ArrayList<Pattern>();
            String regex = null;
            Integer flag = null;
            int arrLength = 0;
            for(Object[] arr : getXssPatternList()) {
                arrLength = arr.length;
                for(int i = 0; i < arrLength; i++) {
                    regex = (String)arr[0];
                    flag = (Integer)arr[1];
                    list.add(Pattern.compile(regex, flag));
                }
            }
            patterns = list;
        }
        return patterns;
    }

    public static String stripXss(String value) {
        if(StringUtils.isNotBlank(value)) {
            Matcher matcher = null;
            for(Pattern pattern : getPatterns()) {
                matcher = pattern.matcher(value);
                // 匹配
                if(matcher.find()) {
                    // 删除相关字符串
                    value = matcher.replaceAll("");
                }
            }
            value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
            value = SafeSql(value);
        }
//      if (LOG.isDebugEnabled())
//          LOG.debug("strip value: " + value);
        return value;
    }
    /**
     * 过滤请求参数和requestbody
     * @param req
     * @throws IOException 
     */
    public static void reqParmSFilter(HttpServletRequest req) throws IOException{
		Enumeration<String> paraNames=req.getParameterNames();
	    for(Enumeration<String> e=paraNames;e.hasMoreElements();){
	          String thisName=e.nextElement().toString();
	          String thisValue=req.getParameter(thisName);
	          req.setAttribute(thisName, stripXss(thisValue));
	    }
	}
    /**
     * 过滤body中的特殊字符
     * @param body
     * @return
     */
    public static String reqBodyFilter(String body){
    	Gson gson = new Gson();
      if(ToolsUtil.isNotEmpty(body) && gson.equals(body)){
           Map<String, String> parameters = gson.fromJson(body, new TypeToken<Map<String, String>>() {
           }.getType());
           for (Map.Entry<String, String> entry : parameters.entrySet()) {
           	   entry.setValue(stripXss(entry.getValue()));
           	  }
           body = gson.toJson(parameters);
      }
       return body;
    }
    /**
     * 读取body
     * @param request
     * @return
     */
    public static String getRequestBody(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
    
    /**
     * 跳过不需要过滤的url请求
     * @param requesUrl
     * @return
     */
    public static boolean skipUrl(String requesUrl){
    	for(NO_NEED_FILTER_URL url :NO_NEED_FILTER_URL.values()){
    		if(requesUrl.endsWith(url.getValue())){
    			return true;
    		}
    	}
    	return false;
    }
    public static void main(String[] args) {
        String value = null;
        value = XssShieldUtil.stripXss("<script language=text/javascript>alert(document.cookie);</script>");
        System.out.println("type-1: '" + value + "'");

        value = XssShieldUtil.stripXss("<script src='' onerror='alert(document.cookie)'></script>");
        System.out.println("type-2: '" + value + "'");

        value = XssShieldUtil.stripXss("</script>");
        System.out.println("type-3: '" + value + "'");

        value = XssShieldUtil.stripXss(" eval(abc);");
        System.out.println("type-4: '" + value + "'");

        value = XssShieldUtil.stripXss(" expression(abc);");
        System.out.println("type-5: '" + value + "'");

        value = XssShieldUtil.stripXss("<img src='' onerror='alert(document.cookie);'></img>");
        System.out.println("type-6: '" + value + "'");

        value = XssShieldUtil.stripXss("<img src='' onerror='alert(document.cookie);'/>");
        System.out.println("type-7: '" + value + "'");

        value = XssShieldUtil.stripXss("<img src='' onerror='alert(document.cookie);'>");
        System.out.println("type-8: '" + value + "'");

        value = XssShieldUtil.stripXss("<script language=text/javascript>alert(document.cookie);");
        System.out.println("type-9: '" + value + "'");

        value = XssShieldUtil.stripXss("<script>window.location='url'");
        System.out.println("type-10: '" + value + "'");

        value = XssShieldUtil.stripXss(" onload='alert(\"abc\");");
        System.out.println("type-11: '" + value + "'");

        value = XssShieldUtil.stripXss("<img src=x<!--'<\"-->>");
        System.out.println("type-12: '" + value + "'");

        value = XssShieldUtil.stripXss("<img src=# onerror=alert(%2F");
        System.out.println("type-13: '" + value + "'");
        
        value = XssShieldUtil.stripXss("<img deletenet src=# onerror=alert(%2F");
        System.out.println("type-14: '" + value + "'");
        System.out.println(SafeSql("select * from user"));
    }
}