package team.union.sys_sp.twotabrel.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.union.business.com.interf.IDao;
import team.union.sys_sp.twotabrel.model.NonbisTwoTabRel;

public interface NonbisTwoTabRelDao extends IDao<NonbisTwoTabRel> {
	public List<NonbisTwoTabRel> selVo(Map<String, Object> parm);
	public List<HashMap<String, Object>> selMap(Map<String, Object> parm);
	/** 查询颜色和主表业务颜色 **/
	public List<NonbisTwoTabRel> selColer(Map<String, Object> parm);
	
}