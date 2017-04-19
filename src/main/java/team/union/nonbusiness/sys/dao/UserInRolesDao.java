package team.union.nonbusiness.sys.dao;


import java.util.HashMap;
import java.util.List;

import team.union.nonbusiness.com.interf.Mapper;
import team.union.nonbusiness.sys.model.UserInRoles;


public interface UserInRolesDao extends Mapper<UserInRoles> {
	public List<HashMap<String, Object>> selectRoleSelected(Long userId);
}