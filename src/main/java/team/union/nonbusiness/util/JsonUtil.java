package team.union.nonbusiness.util;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
  
/**  
 * Title: JsonUtil
 * Description:
 * @author chens
 * @date 2017年4月26日
 * @version 1.0
 */
public class JsonUtil {  
    /** 
     * 根据json字符串返回Map对象 
     * @param json 
     * @return 
     */  
    public static Map<String, Object> gsonToMap(String json){
    Gson gson = new Gson();
    Map<String, Object> map = new HashMap<String, Object>();
    return gson.fromJson(json, map.getClass());
    }
}
