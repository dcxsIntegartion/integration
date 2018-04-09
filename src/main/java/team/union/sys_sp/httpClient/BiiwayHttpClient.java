package team.union.sys_sp.httpClient;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

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
	public String sendGet(String path, Map<String, String> params){
		return DoPostOrGet.doGet(httpClient, path, params);
	}
	public String sendPost(String path, String xmlParam){
		return DoPostOrGet.doPost(httpClient, path, xmlParam);
	}
	
}