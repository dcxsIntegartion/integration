package team.union.sys_sp.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import team.union.sys_sp.com.excp.CommonRunTimeException;
import team.union.sys_sp.com.excp.ExcptionEnums;

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
	  * 字符串编码转换的实现方法
	  * @param str  待转换编码的字符串
	  * @param newCharset 目标编码
	  * @return
	  * @throws UnsupportedEncodingException
	  */
	 public static String changeCharset(String str, String newCharset)
	   throws UnsupportedEncodingException {
	  if (str != null) {
	   //用默认字符编码解码字符串。
	   byte[] bs = str.getBytes();
	   //用新的字符编码生成字符串
	   return new String(bs, newCharset);
	  }
	  return null;
	 }
	 /**
	  * 字符串编码转换的实现方法
	  * @param str  待转换编码的字符串
	  * @param oldCharset 原编码
	  * @param newCharset 目标编码
	  * @return
	  * @throws UnsupportedEncodingException
	  */
	 public static enum CHARSET{
		 /** 7位ASCII字符，也叫作ISO646-US、Unicode字符集的基本拉丁块 */
		US_ASCII("US-ASCII"),
		 /** ISO 拉丁字母表 No.1，也叫作 ISO-LATIN-1 */
		ISO_8859_1("ISO-8859-1"),
		 /** 8 位 UCS 转换格式 */
		UTF_8("UTF-8"),
		 /** 16 位 UCS 转换格式，Big Endian（最低地址存放高位字节）字节顺序 */
		UTF_16BE("UTF-16BE"),
		 /** 16 位 UCS 转换格式，Little-endian（最高地址存放低位字节）字节顺序 */
		UTF_16LE("UTF-16LE"),
		/** 16 位 UCS 转换格式，字节顺序由可选的字节顺序标记来标识 */
		UTF_16("UTF-16"),
		 /** 中文超大字符集 */
		GBK("GBK");
		private CHARSET(String value){
			this.value=value;
		}
		private String value;
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}
	 /** 修改字符串编码 **/
	 public static String changeCharset(String str, String oldCharset, String newCharset)
	   throws UnsupportedEncodingException {
	  if (str != null) {
	   //用旧的字符编码解码字符串。解码可能会出现异常。
	   byte[] bs = str.getBytes(oldCharset);
	   //用新的字符编码生成字符串
	   return new String(bs, newCharset);
	  }
	  return null;
	 }
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
    /**
     * 执行cmd
     * @return
     */
    public static boolean execCmd(String cmd){
	   Runtime run = Runtime.getRuntime();
       Process process = null;
       try {
           process = run.exec(cmd); // 执行cmd命令
           process.waitFor();
       } catch (Exception e) {
    	   return false;
       }
       return true;
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
