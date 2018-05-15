package team.union.sys_sp.util.httpClient;

import java.util.Hashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @ClassName: BiiwayHttpClientFactory 
 * @Description: 
 * @author zhubin
 * @date Apr 21, 2016 3:05:01 PM 
 *
 */
public class BiiwayHttpClientFactory {
	private static Log log = LogFactory.getLog(BiiwayHttpClientFactory.class);
	private static BiiwayHttpClientFactory httpClientFactory = new BiiwayHttpClientFactory();

	private Hashtable<String, BiiwayHttpClient> httpClientCache;

	private BiiwayHttpClientFactory() {

		httpClientCache = new Hashtable<String, BiiwayHttpClient>();
	}

	public static BiiwayHttpClientFactory getInstance() {
		return httpClientFactory;
	}

	public BiiwayHttpClient getBiiwayHttpClient() {
		BiiwayHttpClient httpClient = null;

		if (httpClientCache.containsKey("BACKEND-SERVER")) {
			if(log.isDebugEnabled())
				log.debug("HttpClient in cache");
			httpClient = httpClientCache.get("BACKEND-SERVER");
		} else {
			httpClient = new BiiwayHttpClient();
			if(log.isDebugEnabled())
				log.debug("HttpClient not in cache, Creating new httpClient");
			httpClientCache.put("BACKEND-SERVER", httpClient);
		}
		return httpClient;
	}

}
