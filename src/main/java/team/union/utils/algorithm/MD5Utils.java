package team.union.utils.algorithm;

import java.security.MessageDigest;
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
	public static void main(String[] args) {
		
		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
	}
	
}
