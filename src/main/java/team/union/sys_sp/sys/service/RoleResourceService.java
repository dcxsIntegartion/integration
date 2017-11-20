/**
 * 
 */
package team.union.sys_sp.sys.service;

import team.union.sys_sp.sys.controller.domain.RoleResourcesDomain;
import team.union.sys_sp.sys.utils.Tree;



/**
 * @author Jack Zhang
 *
 */
public interface RoleResourceService {
	
	public Tree findResorucesByRoleId(long roleId);
	
	public Tree loadResourcesByRoleId(long roleId);
	
	public void updateRoleResources(RoleResourcesDomain roleResourcesDomain);
}
