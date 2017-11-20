package team.union.sys_sp.com.cfg;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author		yinyao
 * @Description	模块描述
 * @version		1.00 
 * @see			
 * @Date		2016-3-7 上午10:40:07
 */
@Controller
@RequestMapping("/commons/baseConfig")
public class BaseConfigController {
	
	@ResponseBody
	@RequestMapping("/baseValue")
	public List<HashMap<String, String>> getUeditorStateInfo(String className) throws Exception{
		/*UEDITOR_STATE_INFO[] infos = UEDITOR_STATE_INFO.values();
		HashMap<String, String> enumHashMap = new HashMap<String,String>();
		for (UEDITOR_STATE_INFO info : infos) {
			enumHashMap.put(info.getValue(), info.getName());
		}*/
		String url = "team.union.sys_sp.commons.BaseConfig$";
		Class<?> class1 = Class
                .forName(url+className);
		Method[] methods = class1.getDeclaredMethods();
		List<HashMap<String, String>> optionList = new ArrayList<HashMap<String, String>>();
		List<String> nameList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();
        if(class1.isEnum()){        	
            List<?> list = Arrays.asList(class1.getEnumConstants());
            for(Object enu : list){
            	for(Method method : methods){
                    if(method.getName().endsWith("Name")){
                        nameList.add(method.invoke(enu).toString());
                    } 
                    if(method.getName().endsWith("Value")){
                        valueList.add(method.invoke(enu).toString());
                    }                   
                }
            }
            if(nameList.size()>0&&valueList.size()>0){
            	for (int i=0;i<nameList.size();i++) {
            		HashMap<String, String> enumHashMap = new HashMap<String,String>();
            		enumHashMap.put("name",nameList.get(i));
            		enumHashMap.put("value", valueList.get(i));
            		optionList.add(enumHashMap);
				}
            }
        }
		return optionList;
	}
	
}
