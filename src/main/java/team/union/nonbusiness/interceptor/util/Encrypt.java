package team.union.nonbusiness.interceptor.util;

import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import sun.misc.BASE64Decoder;  
import sun.misc.BASE64Encoder;
import team.union.nonbusiness.com.cfg.BaseConfig;
/**
 * Title: Rsa加密
 * Description: 
 * 加密解密两种方式：
 * 1.公钥加密 -- 私钥解密
 * 2.私钥加密 -- 公钥加密
 * 私钥格式 PKCS#1 PKCS#8对前端js解析影响,都需要拼接密钥头和尾
 * PKCS#1 -- "-----BEGIN RSA PRIVATE KEY-----"+privateKey+"-----END RSA PRIVATE KEY-----"
 * PKCS#8 -- "-----BEGIN PRIVATE KEY-----"+privateKey+"-----END PRIVATE KEY-----"
 * @author chens
 * @date 2017年5月21日
 * @version 1.0
 */
public class Encrypt {
	public static enum RSA{
		KEY_RSA("RSA"),
		SIGNATURE_ALGORITHM("MD5withRSA");
		private  RSA(String value){
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

//	static{
//		try {
//			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(RSA.KEY_RSA.getValue());
//			keyPairGen.initialize(1024);
//			KeyPair keyPair = keyPairGen.generateKeyPair();
//			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
//			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
//			RSA.PUBLIC_KEY.setValue(getPublicKey(publicKey));
//			RSA.PRIVATE_KEY.setValue(getPrivateKey(privateKey));
//			
//			keyPair = keyPairGen.generateKeyPair();
//			publicKey = (RSAPublicKey) keyPair.getPublic();
//			privateKey = (RSAPrivateKey) keyPair.getPrivate();
//			RSA.PUBLIC_KEY_TWO.setValue(getPublicKey(publicKey));
//			RSA.PRIVATE_KEY_TWO.setValue(getPrivateKey(privateKey));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//    }
    
	  /** 
     * 用私钥对信息生成数字签名 
     * @param data 加密数据 
     * @param privateKey 私钥 
     * @return 
     */  
    public static String sign(byte[] data, String privateKey) {  
        String str = "";  
        try {  
            // 解密由base64编码的私钥  
            byte[] bytes = decryptBASE64(privateKey);  
            // 构造PKCS8EncodedKeySpec对象  
            PKCS8EncodedKeySpec pkcs = new PKCS8EncodedKeySpec(bytes);  
            // 指定的加密算法  
            KeyFactory factory = KeyFactory.getInstance(RSA.KEY_RSA.getValue());  
            // 取私钥对象  
            PrivateKey key = factory.generatePrivate(pkcs);  
            // 用私钥对信息生成数字签名  
            Signature signature = Signature.getInstance(RSA.SIGNATURE_ALGORITHM.getValue());  
            signature.initSign(key);  
            signature.update(data);  
            str = encryptBASE64(signature.sign());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return str;  
    }  
    
    /** 
     * 校验数字签名 
     * @param data 加密数据 
     * @param publicKey 公钥 
     * @param sign 数字签名 
     * @return 校验成功返回true，失败返回false 
     */  
    public static boolean verify(byte[] data, String publicKey, String sign) {  
        boolean flag = false;  
        try {  
            // 解密由base64编码的公钥  
            byte[] bytes = decryptBASE64(publicKey);  
            // 构造X509EncodedKeySpec对象  
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);  
            // 指定的加密算法  
            KeyFactory factory = KeyFactory.getInstance(RSA.KEY_RSA.getValue());  
            // 取公钥对象  
            PublicKey key = factory.generatePublic(keySpec);  
            // 用公钥验证数字签名  
            Signature signature = Signature.getInstance(RSA.SIGNATURE_ALGORITHM.getValue());  
            signature.initVerify(key);  
            signature.update(data);  
            flag = signature.verify(decryptBASE64(sign));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return flag;  
    }  
	
	public static String getPublicKey(Key key) throws Exception {
         byte[] publicKey = key.getEncoded(); 
         return encryptBASE64(key.getEncoded());
	}

	public static String getPrivateKey(Key key) throws Exception {
         byte[] privateKey =key.getEncoded(); 
         return encryptBASE64(key.getEncoded());
	}  
	/** 
     * BASE64 解密 
     * @param key 需要解密的字符串 
     * @return 字节数组 
	 * @throws IOException 
     * @throws Exception 
     */
    public static byte[] decryptBASE64(String key) throws IOException{               
        return (new BASE64Decoder()).decodeBuffer(key);               
    }                                 
    /** 
     * BASE64 加密 
     * @param key 需要加密的字节数组 
     * @return 字符串 
     * @throws Exception 
     */ 
    public static String encryptBASE64(byte[] key) throws Exception {               
        return (new BASE64Encoder()).encodeBuffer(key);               
    }       
    
    /** 
     * 公钥加密 
     * @param data 待加密数据 
     * @param key 公钥 
     * @return 
     */  
    public static byte[] encryptByPublicKey(byte[] data, String key) {  
        byte[] result = null;  
        try {  
            byte[] bytes = decryptBASE64(key);  
            // 取得公钥  
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);  
            KeyFactory factory = KeyFactory.getInstance(RSA.KEY_RSA.getValue());  
            PublicKey publicKey = factory.generatePublic(keySpec);  
            // 对数据加密  
            Cipher cipher = Cipher.getInstance(factory.getAlgorithm());  
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
            result = cipher.doFinal(data);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
    /** 
     * 私钥解密 
     * @param data 加密数据 
     * @param key 私钥 
     * @return 
     */  
    public static byte[] decryptByPrivateKey(byte[] data, String key) {  
        byte[] result = null;  
        try {  
            // 对私钥解密  
            byte[] bytes = decryptBASE64(key);  
            // 取得私钥  
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);  
            KeyFactory factory = KeyFactory.getInstance(RSA.KEY_RSA.getValue());  
            PrivateKey privateKey = factory.generatePrivate(keySpec);  
            // 对数据解密  
            Cipher cipher = Cipher.getInstance(factory.getAlgorithm());  
            cipher.init(Cipher.DECRYPT_MODE, privateKey);  
            result = cipher.doFinal(data);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
  
    /** 
     * 私钥加密 
     * @param data 待加密数据 
     * @param key 私钥 
     * @return 
     */  
    public static byte[] encryptByPrivateKey(byte[] data, String key) {  
        byte[] result = null;  
        try {  
            byte[] bytes = decryptBASE64(key);  
            // 取得私钥  
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);  
            KeyFactory factory = KeyFactory.getInstance(RSA.KEY_RSA.getValue());  
            PrivateKey privateKey = factory.generatePrivate(keySpec);  
            // 对数据加密  
            Cipher cipher = Cipher.getInstance(factory.getAlgorithm());  
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);  
            result = cipher.doFinal(data);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
    /** 
     * 公钥 解密 
     * @param data 加密数据 
     * @param key 公钥 
     * @return 
     */  
    public static byte[] decryptByPublicKey(byte[] data, String key) {  
        byte[] result = null;  
        try {  
            // 对公钥解密  
            byte[] bytes = decryptBASE64(key);  
            // 取得公钥  
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);  
            KeyFactory factory = KeyFactory.getInstance(RSA.KEY_RSA.getValue());  
            PublicKey publicKey = factory.generatePublic(keySpec);  
            // 对数据解密  
            Cipher cipher = Cipher.getInstance(factory.getAlgorithm());  
            cipher.init(Cipher.DECRYPT_MODE, publicKey);  
            result = cipher.doFinal(data);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
    
    /**
     * 字符串转换unicode
     */
    public static String string2Unicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }
        return unicode.toString();
    }
    
    
    /**
     * unicode 转字符串
     */
    public static String unicode2String(String ori) {
    	char aChar;
        int len = ori.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = ori.charAt(x++);
            if (aChar == '\\') {
                aChar = ori.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = ori.charAt(x++);
                        switch (aChar) {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            value = (value << 4) + aChar - '0';
                            break;
                        case 'a':
                        case 'b':
                        case 'c':
                        case 'd':
                        case 'e':
                        case 'f':
                            value = (value << 4) + 10 + aChar - 'a';
                            break;
                        case 'A':
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'E':
                        case 'F':
                            value = (value << 4) + 10 + aChar - 'A';
                            break;
                        default:
                            throw new IllegalArgumentException(
                                    "Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
 
        }
        return outBuffer.toString();
    }
    public static void main(String[] args) throws Exception{
//    	KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(RSA.KEY_RSA.getValue());
//		keyPairGen.initialize(1024);
//		KeyPair keyPair = keyPairGen.generateKeyPair();
//		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
//		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
//		System.out.println(getPublicKey(publicKey));
//		System.out.println(getPrivateKey(privateKey));
    	String str = "H4sIAAAAAAAAA1WPvQ7CMAyE3yUzkeLiNi4rUwd4ApYkdn+Q+qPQsiDenQQQEp583/l08kMNrA6K1E6tcZOzGyXJy1ZXSEWCSz9PmQBZMu/JdJjuwyrxtPw5kJxOJpaYcQ7H+SphbfirRwm9m4bb+CO9OG7GLikXkEusrQYir9GUrCkAaCHDFn3LaOy7eY3zJ2H3NqBUhaaCWo0goj0BasJaDIIHb3OFi+LS9Xc7zpzfUc8XrXS4i/kAAAA=";
    	System.err.println(str.getBytes().length);
    	System.err.println(new String(encryptByPublicKey(str.substring(0, 116).getBytes(),BaseConfig.PUBLIC_KEY)));
    
    
    }
    
}