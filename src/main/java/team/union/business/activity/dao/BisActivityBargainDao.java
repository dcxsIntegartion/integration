package team.union.business.activity.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.union.business.activity.model.BisActivityBargain;
import team.union.business.com.interf.IDao;

/**
 * 特价持久层
 * @author Shuqianli
 * @date 2017年4月29日
 * Describe:
 */
public interface BisActivityBargainDao extends IDao<BisActivityBargain>{
	
	/**
	 * 列表查询
	 * @param map
	 * @return
	 */
	public List<HashMap<String, Object>> selectPage(Map<String, Object> map);
}