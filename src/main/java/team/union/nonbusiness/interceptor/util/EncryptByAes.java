package team.union.nonbusiness.interceptor.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import team.union.nonbusiness.com.cfg.BaseConfig;
import team.union.nonbusiness.util.MD5Utils;
import team.union.nonbusiness.util.ZipUtils;

public class EncryptByAes{
	
	/***
	 * 数字签名
	 * @param random      随机字符串
	 * @param aesKey	     公钥
	 * @param encContent  Gzip
	 * @return
	 */
	private static String signature(String random,String aesKey,String content){
		String encContent = ZipUtils.gzip(content);
		return MD5Utils.compute(random.toString()+aesKey.toString()+encContent.toString());
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
	public static String encryption(String random,String key,String content){
		int firstNum = getNumbers(random);
		String  signature = signature(random.substring(0, firstNum),key.substring(0, firstNum),content);
		return signature(random,key,signature);
	}
	public static void main(String[] args) {
		for(int a =0;a<100;a++){
			System.out.println(encryption(genRandomPass(32),BaseConfig.encryptKey,"123"));
		}
		
	}
}