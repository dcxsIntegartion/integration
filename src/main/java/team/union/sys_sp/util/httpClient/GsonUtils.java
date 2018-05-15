package team.union.sys_sp.util.httpClient;



import com.google.gson.Gson;

/**
 * JSon工具
 * 
 * @author zhubin
 * 
 */
public class GsonUtils {
	
	private static final String LEFT_BRACKET = "(";
	
	private static final String RIGHT_BRACKET = ")";

	/**
	 * <p>
	 * 将对象转成json字符串，采用gson作为转为支持 日期类型默认转为毫秒数
	 * </p>
	 * 
	 * @param obj
	 *            需要转json的实体对象
	 * @return
	 */
	public static String convertObjectToJson(Object obj) {
		Gson gson = GsonFactory.getGson();
		return gson.toJson(obj);
	}

	/**
	 * @Title convertJsonToObject 
	 * @Description
	 * <pre>
	 * 将Json字符串反转成为一个实体
	 * </pre>
	 * @param json
	 * @param classType
	 * @return
	 */
	public static <T> T convertJsonToObject(String json, Class<T> classType){
		Gson gson = GsonFactory.getGson();
		return gson.fromJson(json, classType);
	}
	
	
	public static String parseJsonPToJson(String jsonpCallback, String jsonp){
		String json = null;
		if(jsonp != null && jsonp.startsWith(jsonpCallback)){
			String subBegin = jsonpCallback + LEFT_BRACKET;
			json = jsonp.substring(jsonp.indexOf(subBegin)+subBegin.length(), jsonp.lastIndexOf(RIGHT_BRACKET));
		}else{
			json = jsonp;
		}
		return json;
	}
	
	public static void main(String[] args){
		System.out.println(GsonUtils.parseJsonPToJson("93FEF16BF53B45829704160BB2121F6B", "93FEF16BF53B45829704160BB2121F6B({\"rcode\":\"0\"})"));
		System.out.println("{\"rcode\":\"0\"}");
	}
}