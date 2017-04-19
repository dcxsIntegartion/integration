package team.union.business.mach.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.union.business.com.interf.IDao;
import team.union.business.mach.model.BisMach;

public interface BisMachDao extends IDao<BisMach>{
	public List<BisMach> selVo(Map<String, Object> parm);
	public List<HashMap<String, Object>> selMap(Map<String, Object> parm);
}