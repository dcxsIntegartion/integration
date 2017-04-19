package team.union.nonbusiness.sys.dao;

import team.union.nonbusiness.com.interf.Mapper;
import team.union.nonbusiness.sys.model.Users;
import java.util.HashMap;
import java.util.List;

public interface UsersDao extends Mapper<Users> {

	List<Users> findAllUsers();
	List<Users> findByToken(HashMap<String, Object> param);
	/**
	 * 角色管理用户查询
	 * @param para
	 * @return
	 */
	public List<HashMap<String, Object>> roleLeftUsers(HashMap<String, Object> para);
	
	/*
	 * 根据电话号码查询用户
	 */
	public Users selectByPhone(String agentPhone);
}