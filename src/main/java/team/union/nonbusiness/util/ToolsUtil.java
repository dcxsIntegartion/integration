package team.union.nonbusiness.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
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
	 * 35位数值生成器,利用uuid + 4位随机数
	 * 
	 */
	public static String GetUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "")+
				(int)(Math.random()*900+100);
	}
	/**
	 * 密码生成器
	 * @param 密码长度
	 * @return
	 * @author yinxb
	 * @Date 2014-7-7 下午4:51:07
	 */
	public static String genRandomPass(int pwd_len) {
		// 35是因为数组是从0开始的，26个字母+10个数字
		final int maxNum = 36;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
				'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			// 生成随机数，取绝对值，防止生成负数，
			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1
			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString();
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
	 * 格式化文件大小
	 * @param fileSize
	 * @return
	 * @author 岳雷
	 */
	public static String FormetFileSize(long fileSize) {
		String formetFileSize = "";
		DecimalFormat df = new DecimalFormat("#.00");
		if (fileSize < 1024) {
			formetFileSize = df.format((double) fileSize) + "B";
		} else if (fileSize < 1048576) {
			formetFileSize = df.format((double) fileSize / 1024) + "K";
		} else if (fileSize < 1073741824) {
			formetFileSize = df.format((double) fileSize / 1048576) + "M";
		} else {
			formetFileSize = df.format((double) fileSize / 1073741824) + "G";
		}
		if (fileSize == 0) {
			formetFileSize = fileSize + formetFileSize;
		}
		return formetFileSize;
	}

	/**
	 * //1.从旧文件拷贝内容到新文件 
	 * //2.删除旧文件
	 * @param oldPath
	 * @param newPath
	 * @throws Exception
	 */
	public static void transferFile(String oldPath, String newPath) throws Exception {
		int byteread = 0;
		File oldFile = new File(oldPath);
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			if (oldFile.exists()) {
				fileInputStream = new FileInputStream(oldFile);
				fileOutputStream = new FileOutputStream(newPath);
				byte[] buffer = new byte[2048];
				while ((byteread = fileInputStream.read(buffer)) != -1) {
					fileOutputStream.write(buffer, 0, byteread);
				}
			} else {
				throw new Exception("需要转移的文件不存在!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// 如果流不关闭,则删除不了旧文件
			if (fileInputStream != null) {
				fileInputStream.close();
				ToolsUtil.deleteFile(oldPath);
			}
			if (fileOutputStream != null) {
				fileOutputStream.close();
			}
		}
	}

	/**
	 * 删除文件,只支持删除文件,不支持删除目录
	 * @param file
	 * @throws Exception
	 */
	public static void deleteFile(String filePath) throws Exception {
		File deleteFile = new File(filePath);
		if (!deleteFile.exists()) {
			throw new Exception("文件" + deleteFile.getName() + "不存在,请确认!");
		}
		if (deleteFile.isFile()) {
			if (deleteFile.canWrite()) {
				deleteFile.delete();
			} else {
				throw new Exception("文件" + deleteFile.getName() + "只读,无法删除,请手动删除!");
			}
		} else {
			throw new Exception("文件" + deleteFile.getName() + "不是一个标准的文件,有可能为目录,请确认!");
		}
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
	public static String encryptByAes(String keys, String strMessage) {
		try {
			byte[] raw = keys.getBytes("utf-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// "算法/模式/补码方式"
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] encrypted = cipher.doFinal(strMessage.getBytes("utf-8"));
			return bytesToHexString(encrypted);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
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
}
