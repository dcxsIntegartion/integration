package team.union.nonbusiness.httpClient;



/**
 * @author zhubin
 *
 */
public class ResMapUtils {
	
	public static ResMap<String, Object> buildUnknownErrorMsg(){
		ResMap<String, Object> resMap = new ResMap<String, Object>();
		resMap.put("rcode", -1000);
		resMap.put("resultMsg", "-1000");
		return resMap;
	}
	
	public static ResMap<String, Object> buildInvalidateTokenMsg(){
		ResMap<String, Object> resMap = new ResMap<String, Object>();
		resMap.put("rcode", -2000);
		resMap.put("resultMsg", "-2000");
		return resMap;
	}
	
	public static ResMap<String, Object> buildIllegalTokenMsg(){
		ResMap<String, Object> resMap = new ResMap<String, Object>();
		resMap.put("rcode", -2001);
		resMap.put("resultMsg", "-2001");
		return resMap;
	}

	
	public static ResMap<String, Object> buildCustomErrorMsg(String msg){
		ResMap<String, Object> resMap = new ResMap<String, Object>();
		resMap.put("rcode", -3000);
		resMap.put("resultMsg", msg);
		return resMap;
	}
}
