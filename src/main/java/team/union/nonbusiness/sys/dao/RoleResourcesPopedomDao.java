package team.union.nonbusiness.sys.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.union.nonbusiness.com.interf.Mapper;
import team.union.nonbusiness.sys.model.RoleResourcesPopedom;

public interface RoleResourcesPopedomDao extends Mapper<RoleResourcesPopedom> {
	public List<HashMap<String, Object>> getResourcesByRoleId(Long roleId);
	public List<HashMap<String, Object>> loadResourcesByRoleId(Long roleId);
	public List<Long> selectInsertRoleResources(Map<String, Object> maps);
	public List<Long> selectDeleteRoleResources(Map<String, Object> maps);
}