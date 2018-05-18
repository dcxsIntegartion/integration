package team.union.utils.httpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class DoPostOrGet {
	public static String doPost(HttpClient httpClient,String path, String xmlParam){
		String result = new String();
		HttpPost httpPost = new HttpPost(path);
		CloseableHttpResponse response = null;
		try {
			httpPost.setEntity(new StringEntity(xmlParam, "UTF-8"));
			response = (CloseableHttpResponse) httpClient.execute(httpPost);
			if(response.getStatusLine().getStatusCode() == 200){
				/* 将参数转化成 json ***/
				HttpEntity entity = response.getEntity();
				Object obj =  EntityUtils.toString(entity, "utf-8");
				String objStr = obj.toString().replaceAll("%", "%25").replaceAll("null", "false");
				result = objStr;
			}
		} catch(Exception e) {
			e.printStackTrace();
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
	public static String doPost(HttpClient httpClient,String path, Map<String, String> params){
		String result = new String();
		HttpPost httpPost = new HttpPost(path);
		CloseableHttpResponse response = null;
		try {
			List<NameValuePair> nvps = parseMapToParamPair(params);
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			response = (CloseableHttpResponse) httpClient.execute(httpPost);
			if(response.getStatusLine().getStatusCode() == 200){
				/* 将参数转化成 json ***/
				HttpEntity entity = response.getEntity();
				Object obj =  EntityUtils.toString(entity, "utf-8");
				String objStr = obj.toString().replaceAll("%", "%25").replaceAll("null", "false");
				result = objStr;
			}
		} catch(Exception e) {
			e.printStackTrace();
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
	public static String doGet(HttpClient httpClient,String path, Map<String, String> params){
		String result = new String();
		String url = path;
		List<NameValuePair> nvps = parseMapToParamPair(params);
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
			response = (CloseableHttpResponse)httpClient.execute(httpGet);
			if(response.getStatusLine().getStatusCode() == 200){
				/* 将参数转化成 json ***/
				HttpEntity entity = response.getEntity();
				result =  EntityUtils.toString(entity, "utf-8");
			}
		} catch(Exception e) {
			e.printStackTrace();
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
	private static List<NameValuePair> parseMapToParamPair(Map<String, String> params){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for(String key : params.keySet()){
			nvps.add(new BasicNameValuePair(key, String.valueOf(params.get(key))));
		}
		return nvps;
	}
}
