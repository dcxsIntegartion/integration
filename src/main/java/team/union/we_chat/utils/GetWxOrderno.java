package team.union.we_chat.utils;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import team.union.sys_sp.httpClient.BiiwayHttpClientFactory;


public class GetWxOrderno{
  /**
   *description:获取预支付id
   *@param url
   *@param xmlParam
   *@return
   * @author ex_yangxiaoyi
   * @see
   */
  public static String getPayNo(String url,String xmlParam){
	 
    String prepay_id = "";
    String result = BiiwayHttpClientFactory.getInstance()
	      .getBiiwayHttpClient().sendPost(url, xmlParam);
  
	if(result.indexOf("FAIL")!=-1){
    	return prepay_id;
    }
    Map map = doXMLParse(result);
    prepay_id  = (String) map.get("prepay_id");
	return prepay_id;
  }
	
  /**
   *description:获取扫码支付连接
   *@param url
   *@param xmlParam
   *@return
   * @author ex_yangxiaoyi
   * @see
   */
  public static String getCodeUrl(String url,String xmlParam){
	  String code_url = "";
	  String result = BiiwayHttpClientFactory.getInstance()
		      .getBiiwayHttpClient().sendPost(url, xmlParam);
	  if(result.indexOf("FAIL")!=-1){
	    	return code_url;
	    }
	    Map map = doXMLParse(result);
	    code_url  = (String) map.get("code_url");
	  return code_url;
  }
  
  
  /**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static Map doXMLParse(String strxml){
		if(null == strxml || "".equals(strxml)) {
			return null;
		}
		
		Map m = new HashMap();
		InputStream in = String2Inputstream(strxml);
		SAXBuilder builder = new SAXBuilder();
		Document doc = null;
		try {
			doc = builder.build(in);
		} catch (JDOMException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while(it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if(children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = getChildrenText(children);
			}
			
			m.put(k, v);
		}
		
		//关闭流
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return m;
	}
	/**
	 * 获取子结点的xml
	 * @param children
	 * @return String
	 */
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if(!children.isEmpty()) {
			Iterator it = children.iterator();
			while(it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if(!list.isEmpty()) {
					sb.append(getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}
		
		return sb.toString();
	}
  public static InputStream String2Inputstream(String str) {
		return new ByteArrayInputStream(str.getBytes());
	}
  
}