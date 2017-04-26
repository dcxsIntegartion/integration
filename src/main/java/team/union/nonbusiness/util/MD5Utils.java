package team.union.nonbusiness.util;

import java.security.MessageDigest;
import java.util.Random;
import java.util.UUID;

/**
 * @author Jack Zhang MD5 加密类<br/>
 *         用户密码等加密
 */
public class MD5Utils {
	
	
	private static MessageDigest md5;

	static {
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param inStr 需要加密的字符串
	 * 
	 * @return 加密后的MD5字符串
	 * 
	 * */
	public static String compute(String inStr) {

		char[] charArray = inStr.toCharArray();

		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];

		byte[] md5Bytes = md5.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();

		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}

		return hexValue.toString();
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
	
}
