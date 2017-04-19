/**
 * 
 */
package team.union.nonbusiness.sys.service;

import java.util.HashMap;
import java.util.List;

import team.union.nonbusiness.sys.controller.domain.UserRolesDomain;


/**
 * @author Jack Zhang
 *
 */
public interface UserRoleService {
	public void save(UserRolesDomain userRolesDomain);
	public List<HashMap<String, Object>> selectRoleSelected(Long userId);
}
