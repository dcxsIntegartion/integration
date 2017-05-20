package team.union.business.article.service;

import java.util.HashMap;
import java.util.Map;

import team.union.basic_data.com.rs.BsgridVo;
import team.union.basic_data.com.rs.Result;
import team.union.business.article.model.BisArticle;

/**
 * 
* 标题: IBisArticleService.java
* 类描述: 文章类  service
* @author zh
* @date 2017年5月17日 
* @version 1.0
 */
public interface IBisArticleService {

	/**
	 * 
	* 方法名:          selMap
	* 方法功能描述:    分页查询
	* @param parm
	* @return  
	* @return:        List<HashMap<String,Object>>
	* @Author:        zh
	* @Create Date:   2017年5月17日
	 */
	public BsgridVo<HashMap<String, Object>> selMap(Map<String, Object> parm, int curPage, int pageSize);
	
	Result findById(Long id);
	
	Result update(BisArticle article);
	
	Result insert(BisArticle article);
	
	Result deleteById(Long id);
}
