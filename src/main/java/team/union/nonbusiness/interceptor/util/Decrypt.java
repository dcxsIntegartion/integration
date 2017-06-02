package team.union.nonbusiness.interceptor.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

import team.union.nonbusiness.com.cfg.BaseConfig;
import team.union.nonbusiness.filter.util.BodyReaderWrapper;
import team.union.nonbusiness.filter.util.XssShieldUtil;
import team.union.nonbusiness.util.ToolsUtil;
import team.union.nonbusiness.util.ZipUtils;

public class Decrypt {
	
	private static ConcurrentHashMap<String, Long> nonceMap = new ConcurrentHashMap<String, Long>();
	
	
	private static boolean addNonceMap(String sign) {
		long date = new Date().getTime();
		long timeExpired = date-BaseConfig.MAX_TIMESTAMP_COUNT;
		Set<String> set = nonceMap.keySet(date);
		if(!nonceMap.containsKey(sign)){
			for (Entry<String, Long> entry : nonceMap.entrySet()) {
				   if(entry.getValue()<timeExpired){
					   set.remove(entry.getKey());
					}else{break;}
				  }
			set.add(sign);
			return true;
		}
		return false;
	}


	/**
	 * @param Data
	 * @param publicKey
	 * @param privateKey
	 * @return
	 * @throws IOException 
	 * @throws Exception 
	 * @throws UnsupportedEncodingException 
	 */
	public static HttpServletRequest DecryptData(HttpServletRequest request) throws IOException {
		if(ToolsUtil.isNotEmpty(request.getHeader("sign")) && ToolsUtil.isNotEmpty(request.getHeader("times")) &&
		   ToolsUtil.isNotEmpty(request.getHeader("str"))){
			String publicKey = BaseConfig.PUBLIC_KEY_TWO;
			String privateKey = BaseConfig.PRIVATE_KEY;
			long times = Long.parseLong(request.getHeader("times").toString());
			//时间超过一分钟 请求无效
			if(new Date().getTime()-times>BaseConfig.MAX_TIMESTAMP_COUNT) return null;
			String sign = request.getHeader("sign").toString();
			String str =  request.getHeader("str").toString();
			String rquestData = request.getParameter("data");
			String bodyData = XssShieldUtil.getRequestBody(request);
			Map<String, Object> dataMap = new HashMap<String, Object>();
			//先判断body是否有值
			if(ToolsUtil.isNotEmpty(bodyData)){
				dataMap = verification(sign ,str,times,bodyData,publicKey,privateKey);
				Gson gson = new Gson();
				return new BodyReaderWrapper(request,gson.toJson(dataMap));
			}
			if(ToolsUtil.isNotEmpty(rquestData)){
				dataMap = verification(sign ,str,times,rquestData,publicKey,privateKey);
				for(String key : dataMap.keySet()) {
					request.setAttribute(key,null!=dataMap.get(key)?XssShieldUtil.stripXss(dataMap.get(key).toString()):"");
				  }
			}
		}
		return request;
	} 
	
	
	private static Map<String, Object> verification(String sign ,String str,long times,String data,
									   String publicKey,String privateKey) throws IOException{
		int firstNum = getNumbers(str);
		String datas[] = data.split(",110,");
		StringBuffer decryptData = new StringBuffer();
		for(int i=0;i<datas.length;i++){
			decryptData.append(new String(Encrypt.decryptByPrivateKey(Encrypt.decryptBASE64(datas[i].toString()), privateKey),"utf-8"));
		}
		String unZipData = ZipUtils.gunzip(decryptData.toString().replaceAll("%2B", "+").replaceAll("%2F","/"));
		String unicodeData = Encrypt.unicode2String(unZipData);
		Map<String, Object> map=  ToolsUtil.gsonToMap(XssShieldUtil.stripXss(unicodeData));
		String signStr = data+times+str;
		String signData = signStr.substring(firstNum, signStr.length());
		boolean state = Encrypt.verify(signData.getBytes(),publicKey,sign);
		if(state){
			//验证有效 记录使用过的签名
			addNonceMap(sign);
			return map;
		}
		return null;
	}
	
	/**
	 * 截取字符串中第一个数字 没有则返回0
	 * @param content
	 * @return
	 */
	private static int getNumbers(String content) {  
	       Pattern pattern = Pattern.compile("\\d+");  
	       Matcher matcher = pattern.matcher(content);  
	       while (matcher.find()) {  
	           return Integer.parseInt(matcher.group(0).substring(0,1));  
	       }  
	       return 0;  
	   }  
	/**
	 * 密码生成器
	 * @param 密码长度
	 * @return
	 * @author yinxb
	 * @Date 2014-7-7 下午4:51:07
	 */
	private static String genRandomPass(int pwd_len) {
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
}