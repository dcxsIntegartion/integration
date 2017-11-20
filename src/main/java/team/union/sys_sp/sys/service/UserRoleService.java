/**
 * 
 */
package team.union.sys_sp.sys.service;

import java.util.HashMap;
import java.util.List;

import team.union.sys_sp.sys.controller.domain.UserRolesDomain;


/**
 * @author Jack Zhang
 *
 */
public interface UserRoleService {
	public void save(UserRolesDomain userRolesDomain);
	public List<HashMap<String, Object>> selectRoleSelected(Long userId);
}
