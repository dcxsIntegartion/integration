package team.union.nonbusiness.sys.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.union.nonbusiness.com.interf.Mapper;
import team.union.nonbusiness.sys.model.Resources;


public interface ResourcesDao extends Mapper<Resources>{
	
	public List<HashMap<String, Object>> findByUserId(Long userId);
	public List<Long> selectInsertRole(Map<String, Object> maps);
	public List<Long> selectDeleteRole(Map<String, Object> maps);
}