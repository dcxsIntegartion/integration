package team.union.sys_sp.util.httpClient;

import java.util.HashMap;

/**
 * @ClassName: ResMap 
 * @Description:
 * @author zhubin
 * @date Apr 21, 2016 3:05:41 PM 
 */
public class ResMap<K, V> extends HashMap<K, V> {

	private static final long serialVersionUID = 110418444604582396L;
	
	
	public String toJson(){
		return GsonUtils.convertObjectToJson(this);
	} 
}
