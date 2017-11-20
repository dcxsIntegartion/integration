package team.union.sys_sp.sys.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.union.sys_sp.com.interf.Mapper;
import team.union.sys_sp.sys.model.RoleResourcesPopedom;

public interface RoleResourcesPopedomDao extends Mapper<RoleResourcesPopedom> {
	public List<HashMap<String, Object>> getResourcesByRoleId(Long roleId);
	public List<HashMap<String, Object>> loadResourcesByRoleId(Long roleId);
	public List<Long> selectInsertRoleResources(Map<String, Object> maps);
	public List<Long> selectDeleteRoleResources(Map<String, Object> maps);
}