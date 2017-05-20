package team.union.business.activity.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.union.business.activity.model.BisActivitySeckil;
import team.union.business.com.interf.IDao;

/**
 * 秒杀持久层
 * @author Shuqianli
 * @date 2017年4月29日
 * Describe:
 */
public interface BisActivitySeckilDao extends IDao<BisActivitySeckil>{
	
	/**
	 * 列表查询
	 * @param map
	 * @return
	 */
	public List<HashMap<String, Object>> selectPage(Map<String, Object> map);
}