package team.union.nonbusiness.interceptor.util;

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
import java.util.HashMap;

import java.util.Map;

import javax.crypto.Cipher;

import sun.misc.BASE64Decoder;  

import sun.misc.BASE64Encoder;

public class Encrypt {

	public static enum RSA{
		KEY_RSA("RSA"),
		SIGNATURE_ALGORITHM("MD5withRSA"),
		PUBLIC_KEY("RSAPublicKey"),
		PRIVATE_KEY("RSAPrivateKey");
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

	static{
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(RSA.KEY_RSA.getValue());
			keyPairGen.initialize(1024);
			KeyPair keyPair = keyPairGen.generateKeyPair();
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
			RSA.PUBLIC_KEY.setValue(getPublicKey(publicKey));
			RSA.PRIVATE_KEY.setValue(getPrivateKey(privateKey));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
    }
    
	
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
     * @throws Exception 
     */
    public static byte[] decryptBASE64(String key) throws Exception {               
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
  
    
    public static void main(String[] args) throws Exception {
    	
    	String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCZSDto7+FqOBiwEjyzjydWNw0tjcwDaBlQRMoH2t2nJtRlZltRYS8BLVpEZ2+3opikI4MrKmFbgTGq7oPGqRWvZpEeb0jydAqQ0V0rVeA6twHnnaCW8wCOrL+cjWcwJ6MR63p3uh0aSp/ALDNLVdYxCUhvuOPdRu/LHnM23NNmCwIDAQAB";
    	String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJlIO2jv4Wo4GLASPLOPJ1Y3DS2N"+
							"zANoGVBEygfa3acm1GVmW1FhLwEtWkRnb7eimKQjgysqYVuBMarug8apFa9mkR5vSPJ0CpDRXStV"+
							"4Dq3AeedoJbzAI6sv5yNZzAnoxHrene6HRpKn8AsM0tV1jEJSG+4491G78seczbc02YLAgMBAAEC"+
							"gYBBzwI6vmqP+P+YcGwBR1/DIyWUPkGt4L6leLMohi4Nup0L39Mq8EeHANq1bZdIRxWzus9w8+QS"+
							"rjgNxBNtcmCtXhDx04T3DPDvKcQB39twf0N73Z/0Jj6P6+lwuk187+BjxEzRAT1p0mYBrlK5W4Ur"+
							"nl/BkMUbMhXXtEW+PJmFaQJBAOrhMEYBCievq1DS5yhUWpgJ6hVwUrIuo3aMeNFbrh2qZwQQRCqp"+
							"5b1VHCR761WGny2C/qSp61iqtcnj7razJt0CQQCnELXY73K6+Qr7HIQtR5kqbHdUmkOrSpkA/5wg"+
							"SdtRUUw6vcH+jh4bp6SsUd4jbPTFS4GdcP/Zdiu4ZEdIDk4HAkBCN3IQikJ+pbu6cXrRl9ZifokL"+
							"ujrWGOfkh/2XqC0SF/Qq5RsSnAs0sUXZ00WpOuatQfzgFzdOK/JMFII7543RAkAUVQxMoper+bNE"+
							"bGwJtrUFXL4JJd0mc6W7YWB27YYrl2FXpNB/UnyHkReRgUUvuorvmAjmSGJa7O7VOh8SverxAkEA"+
							"gWBIRZ3r18loGNkp7dEZpA+disVbHWKcMavwM1CNh+BuYboPd4+DayGoCvLv1J/jDIVuT7cMzDjw"+
							"3dDgDtdPWQ==";
    	String privateK2 = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJMKbnWSEoS4OaSYj9F3WMBapjAA6mflMTqZA4X8etYFE3uK2BUHOrS+xQtC+dCMbIlYW9tXDvre0X5BA4RtNVfHqceajGH1YKZHIOSDd9OLclY8NFDh0OIpyYHADTM9YkaQAx3MT89M8GyP9Mp5JpWpX8sxsHQApzC5dymTwOrNAgMBAAECgYATKkLndBiRz+lDeNcV+DZLLAWJMDVdQVQ/TP5Wkmf4SUUWzywG4aBXOp44L4ycEBF9fVTLq/c535zylcq9kfZnvTS7EhANoUOHQEeJil2ag+hmmcivOWpE8qxxH9AmcdJkqOzgVtWbWTFZYAJMF1vBqNEZTGKqxWGzl2x6sWZdAQJBAOdDIQMKo7WbawtE1BfET6gQn/M5/N9mFKEkBBxBqFbyb3eAN/Qcl8R9dxREGeHB/nJhjYH4G3by6oGs/L9ozyECQQCixPyUp+8ABTsepUkBkx8lKto+NdFHe3R+9GBxG7vAWbVPfUkcxaSJzodk7FOEKDOULP1ydp4viqW6J7yRLkItAkBuUnfNG6Y9XIcUOSF0tRHK+yNSxLb9W5U7yhKr6CaGU+EZAPGwYnOEKNZFtLckG4dmyWLYPaPcesWkVwG7ziQBAkEAiJobhELf4LuDHzgF6i1nkRDCsj0GUyB352f+XE6zJj3jXT+/EUeJzCGpHXj8qkf26Z9MYTjNjwakCXP53DnfTQJAdY0w477+RstaupdRL11W/UGfASKdJx34IF+jaUXXjWlsT3k8x8l5daW0JpSRWyFNnLo6OdpPdCOmk06mhN1MXQ==";
    	
    	String str = "jkuxHEYQdlwC3VGNAk2I7ePhPQmbAvuU6V82RRJ9C8PyGR9XC0YXUjNjviZdJfCtBAWWdbBCpll84z5lEJoV7o2FKh+mb2OnIzxzuQdB+Y/Cf3ZnPKxVrG4uexEPICGZvNsXnQFp73BU/MdIkXub/h9M1I3W3bHnjmU1pCUzdaM=";
    	String jia = new String(encryptByPublicKey("123".getBytes("UTF-8"),publicKey));
    	System.out.println(jia);
    	System.out.println(new String(decryptByPrivateKey(decryptBASE64(str),privateKey)));
    	String sig =  sign("1".getBytes(),privateKey); 
    	System.out.println(sig);
    	System.out.println(verify("1".getBytes(),publicKey,sig));
    	
    	
    	
	}
    

}