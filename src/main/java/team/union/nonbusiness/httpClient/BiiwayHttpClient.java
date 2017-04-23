package team.union.nonbusiness.httpClient;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;


/**
 * @ClassName: BiiwayHttpClient 
 * @Description: HttpClient请求实例 
 * @author zhubin
 * @date Apr 21, 2016 3:03:09 PM 
 *
 */
public class BiiwayHttpClient {
	private static Log log = LogFactory.getLog(BiiwayHttpClient.class);
	
	private HttpClient httpClient = null;
	
	private static final String SERVER = "";//BaseConfig.shortMessageService;//Config.getValue(Key.SERVER);
	private static final String SSL_CONTEXT_PROTOCOL = "SSL";//Config.getValue(Key.SSL_CONTEXT_PROTOCOL);
	private static int THREAD_MAX_TOTAL = 1000;// Integer.valueOf(Config.getValue(Key.HTTP_CLIENT_THREAD_NUM));

	public BiiwayHttpClient() {
		if (log.isInfoEnabled())
			log.info("Creating new instance of HttpClient with Multi-Thread Support");
		
		ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
		LayeredConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(createSSLContext());
		Registry<ConnectionSocketFactory> r = RegistryBuilder.<ConnectionSocketFactory> create()
				.register("http", plainsf).register("https", sslsf).build();

		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(r);
		cm.setMaxTotal(THREAD_MAX_TOTAL);

		httpClient = HttpClients.custom().setConnectionManager(cm).build();
	}
	
	private SSLContext createSSLContext() {
		SSLContext ctx = null;
		try {
			ctx = SSLContext.getInstance(SSL_CONTEXT_PROTOCOL);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		X509TrustManager tm = new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
			}
			public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
			}
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};
		try {
			ctx.init(null, new TrustManager[] { tm }, null);
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
		return ctx;
	}
	
	public HttpClient getNativeHttpClient(){
		return this.httpClient;
	}
	
	private List<NameValuePair> parseMapToParamPair(Map<String, String> params){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for(String key : params.keySet()){
			nvps.add(new BasicNameValuePair(key, String.valueOf(params.get(key))));
		}
		return nvps;
	}
	
	private Object doPost(String path, Map<String, String> params){
		Object result = new Object();
		HttpPost httpPost = new HttpPost(SERVER + path);
		CloseableHttpResponse response = null;
		try {
			List<NameValuePair> nvps = this.parseMapToParamPair(params);
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			response = (CloseableHttpResponse) this.httpClient.execute(httpPost);
			if(response.getStatusLine().getStatusCode() == 200){
				/* 将参数转化成 json ***/
				HttpEntity entity = response.getEntity();
				Object obj =  EntityUtils.toString(entity, "utf-8");
				String objStr = obj.toString().replaceAll("%", "%25").replaceAll("null", "false");
				result = objStr;
			}
		} catch(Exception e) {
			e.printStackTrace();
			result = ResMapUtils.buildUnknownErrorMsg();
		} finally{
			if(response != null){
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	public Object sendGet(String path, Map<String, String> params){
		return this.doGet(path, params);
	}
	private Object doGet(String path, Map<String, String> params){
		Object result = new Object();
		String url = path;
		List<NameValuePair> nvps = this.parseMapToParamPair(params);
		if(nvps.size()>0){
			for(int i=0;i<nvps.size();i++){
				if(i==0){
					url += "?"+nvps.get(i).getName()+"="+nvps.get(i).getValue();
				}else{
					url += "&"+nvps.get(i).getName()+"="+nvps.get(i).getValue();
				}
			}
		}
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = null;
		try {
			response = (CloseableHttpResponse) this.httpClient.execute(httpGet);
			if(response.getStatusLine().getStatusCode() == 200){
				/* 将参数转化成 json ***/
				HttpEntity entity = response.getEntity();
				Object obj =  EntityUtils.toString(entity, "utf-8");
				result = obj;
			}
		} catch(Exception e) {
			e.printStackTrace();
			result = ResMapUtils.buildUnknownErrorMsg();
		} finally{
			if(response != null){
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}