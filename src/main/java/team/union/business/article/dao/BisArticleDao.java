package team.union.business.article.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.union.business.article.model.BisArticle;

/**
 * 
* 标题: BisArticleDao.java
* 类描述:
* @author zh
* @date 2017年5月17日 
* @version 1.0
 */
public interface BisArticleDao {

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
	public List<HashMap<String, Object>> selMap(Map<String, Object> parm);
	
	BisArticle findById(Long id);
	
	void update(BisArticle article);
	
	void insert(BisArticle article);
	
	void deleteById(Long id);
}
