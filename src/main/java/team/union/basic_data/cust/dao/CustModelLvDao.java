package team.union.basic_data.cust.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.union.basic_data.com.interf.IDao;
import team.union.basic_data.cust.model.CustModelLv;

public interface CustModelLvDao extends IDao<CustModelLv>{

	public List<CustModelLv> selVo(Map<String, Object> parm);
	public List<HashMap<String, Object>> selMap(Map<String, Object> parm);
}