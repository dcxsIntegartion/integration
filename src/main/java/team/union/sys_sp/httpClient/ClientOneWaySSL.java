package team.union.sys_sp.httpClient;
import java.io.File;   
import java.io.FileInputStream;   
import java.security.KeyStore;   
  
import org.apache.http.HttpEntity;   
import org.apache.http.HttpResponse;   
import org.apache.http.client.methods.HttpGet;   
import org.apache.http.conn.scheme.Scheme;   
import org.apache.http.conn.ssl.SSLSocketFactory;   
import org.apache.http.impl.client.DefaultHttpClient;   
  
/**  
 *   
 * @author kevin  
 *  
 */  
public class ClientOneWaySSL {   
  
    public final static void main(String[] args) throws Exception {   
        DefaultHttpClient httpclient = new DefaultHttpClient();   
  
        KeyStore trustStore  = KeyStore.getInstance(KeyStore.getDefaultType());           
        FileInputStream instream = new FileInputStream(new File("com/ssl/http/clientTruststore.jks"));    
        try {   
            trustStore.load(instream, "123456".toCharArray());   
        } finally {   
            instream.close();   
        }   
           
        SSLSocketFactory socketFactory = new SSLSocketFactory(trustStore);   
        Scheme sch = new Scheme("https", socketFactory, 8443);   
        httpclient.getConnectionManager().getSchemeRegistry().register(sch);   
  
        HttpGet httpget = new HttpGet("https://w03gca01a/");   
  
        System.out.println("executing request" + httpget.getRequestLine());   
           
        HttpResponse response = httpclient.execute(httpget);   
        HttpEntity entity = response.getEntity();   
  
        System.out.println("----------------------------------------");   
        System.out.println(response.getStatusLine());   
        if (entity != null) {   
            System.out.println("Response content length: " + entity.getContentLength());   
        }   
        if (entity != null) {   
            entity.consumeContent();   
        }   
  
        // When HttpClient instance is no longer needed,    
        // shut down the connection manager to ensure   
        // immediate deallocation of all system resources   
        httpclient.getConnectionManager().shutdown();           
    }   
  
}  