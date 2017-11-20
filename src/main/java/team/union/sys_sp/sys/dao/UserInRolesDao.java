package team.union.sys_sp.sys.dao;


import java.util.HashMap;
import java.util.List;

import team.union.sys_sp.com.interf.Mapper;
import team.union.sys_sp.sys.model.UserInRoles;


public interface UserInRolesDao extends Mapper<UserInRoles> {
	public List<HashMap<String, Object>> selectRoleSelected(Long userId);
}