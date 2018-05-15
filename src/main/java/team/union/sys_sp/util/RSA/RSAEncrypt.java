package team.union.sys_sp.util.RSA;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import sun.misc.BASE64Decoder;  
import sun.misc.BASE64Encoder;
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
@SuppressWarnings("restriction")
public class RSAEncrypt {
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
         return encryptBASE64(publicKey);
	}

	public static String getPrivateKey(Key key) throws Exception {
         byte[] privateKey =key.getEncoded(); 
         return encryptBASE64(privateKey);
	}  
	/** 
     * BASE64 解密 
     * @param key 需要解密的字符串 
     * @return 字节数组 
	 * @throws IOException 
     * @throws Exception 
     */
	public static byte[] decryptBASE64(String key) throws IOException{    
    	BASE64Decoder decoder = new BASE64Decoder();
        return decoder.decodeBuffer(key);               
    }                                 
    /** 
     * BASE64 加密 
     * @param key 需要加密的字节数组 
     * @return 字符串 
     * @throws Exception 
     */ 
	public static String encryptBASE64(byte[] key) throws Exception {  
    	BASE64Encoder encode = new BASE64Encoder();
        return encode.encodeBuffer(key);               
    }       
    
    /** 
     * 公钥加密 
     * @param data 待加密数据 
     * @param key 公钥 
     * @return 
     * @throws IOException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeySpecException 
     * @throws NoSuchPaddingException 
     * @throws InvalidKeyException 
     * @throws BadPaddingException 
     * @throws IllegalBlockSizeException 
     */  
    public static byte[] encryptByPublicKey(byte[] data, String key) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {  
        byte[] bytes = decryptBASE64(key);  
        // 取得公钥  
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);  
        KeyFactory factory = KeyFactory.getInstance(RSA.KEY_RSA.getValue());  
        PublicKey publicKey = factory.generatePublic(keySpec);  
        // 对数据加密  
        Cipher cipher = Cipher.getInstance(factory.getAlgorithm());  
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
        byte[] result = cipher.doFinal(data);  
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
    
    
}