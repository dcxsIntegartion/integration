package team.union.business.activity.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.union.business.activity.model.BisActivityTurntable;
import team.union.business.com.interf.IDao;

/**
 * 大转盘持久层
 * @author Shuqianli
 * @date 2017年4月29日
 * Describe:
 */
public interface BisActivityTurntableDao extends IDao<BisActivityTurntable>{
	
	/**
	 * 列表查询
	 * @param map
	 * @return
	 */
	public List<HashMap<String, Object>> selectPage(Map<String, Object> map);
}