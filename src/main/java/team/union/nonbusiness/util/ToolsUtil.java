package team.union.nonbusiness.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import team.union.nonbusiness.com.excp.CommonRunTimeException;
import team.union.nonbusiness.com.excp.ExcptionEnums;

/**
 * 
 * @author		yinxb
 * @Description	工具类
 * @version		1.00 
 * @see			参考类1
 * @Date		2015-12-12 下午5:48:29
 */
public class ToolsUtil {
	
	/**
	 * 验证是否不为空
	 * @param obj 验证对象 字符串java.util.List<?>列表
	 * @return true=非空;false=为空
	 */
	public static boolean isNotEmpty(Object obj) {
		if (obj == null) {
			return false;
		}
		if(obj instanceof String){
			if ("".equals(obj)) {
				return false;
			}
		}else if(obj instanceof java.util.List){
			if( ((java.util.List<?>) obj).size()==0){
				return false;
			}
		}else{
			throw new CommonRunTimeException(ExcptionEnums.SAVE_ERROR,"类型不对");
		}
		return true;
	}

	/**
	 * 验证是否为空
	 * @param obj 验证对象 字符串java.util.List<?>列表
	 * @return true=为空;false=非空
	 */
	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		if(obj instanceof String){
			if ("".equals(obj)) {
				return true;
			}
		}else if(obj instanceof java.util.List){
			if( ((java.util.List<?>) obj).size()==0){
				return true;
			}
		}else{
			throw new CommonRunTimeException(ExcptionEnums.SAVE_ERROR,"类型不对");
		}
		return false;
	}
	
	
	
	/**
	 * 去除字符串中的重复数据(数据以逗号隔开)
	 * @author yuel
	 * @param str
	 * @return
	 * @Date 2014-7-12 下午12:58:47
	 */
	@SuppressWarnings("rawtypes")
	public static List<String> deduplication(String str){
		List<String> tempList = new ArrayList<String>();
		Map<String, String> tempMap = new HashMap<String, String>();
		String[] temp = str.split(",");
		for (String s1 : temp) {
			tempMap.put(s1, s1);
		}
		
		Iterator iterator = tempMap.entrySet().iterator();
		while (iterator.hasNext()) {// 只遍历一次,速度快
			Map.Entry e = (Map.Entry) iterator.next();
			tempList.add(e.getKey().toString());
		}
		return tempList;
	}
	/**
	 * 特殊字符过滤
	 * @param str
	 * @return
	 * @throws PatternSyntaxException
	 */
	public static String StringFilter(String   str) throws PatternSyntaxException {      
		  // 清除掉所有特殊字符   
		  String regEx="[`~@#$%^&*()+=|{}''\\[\\]<>/~@#￥%……&*（）——+|{}【】‘”“’]";//"[`~$^()=''[]<>~@#￥%&*（）——+|{}【】]";   
		  Pattern   p   =   Pattern.compile(regEx);      
		  Matcher   m   =   p.matcher(str);      
		  return   m.replaceAll("").trim();      
	}  
	/**
	 * 判断是不是特别行政区
	 * 是返回true 不是返回false
	 * @param code
	 * @return
	 * @author	yinyao
	 * @Date	2016-3-10 下午2:47:28
	 */
	public static boolean isSAR(String code){
		List<String> municipalities = new ArrayList<String>();//存所有直辖区code
		municipalities.add("700000");//香港
		municipalities.add("800000");//台湾
		municipalities.add("900000");//澳门
		for(int i=0;i<municipalities.size();i++){
			if(code.equals(municipalities.get(i))){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断是不是直辖市
	 * 是返回true 不是返回false
	 * @param code
	 * @return
	 * @author	yinyao
	 * @Date	2016-1-9 下午3:18:17
	 */
	public static boolean isDirectly(String code){
		List<String> municipalities = new ArrayList<String>();//存所有直辖区code
		municipalities.add("110000");//北京
		municipalities.add("120000");//天津
		municipalities.add("310000");//上海
		municipalities.add("500000");//重庆
		for(int i=0;i<municipalities.size();i++){
			if(code.equals(municipalities.get(i))){
				return true;
			}
		}
		return false;
	}
	/**
	 * 区域code码字符串封装成数组
	 */
	public static String[] getArrayByAreaCode(String areaCode){
		String[] strArarry = null;
		if(isNotEmpty(areaCode)){
			strArarry = areaCode.split(",");
		}
		return strArarry;
	}
}
