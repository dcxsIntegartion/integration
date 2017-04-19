/**
 * 
 */
package team.union.nonbusiness.sys.service;

import team.union.nonbusiness.sys.controller.domain.RoleResourcesDomain;
import team.union.nonbusiness.sys.utils.Tree;



/**
 * @author Jack Zhang
 *
 */
public interface RoleResourceService {
	
	public Tree findResorucesByRoleId(long roleId);
	
	public Tree loadResourcesByRoleId(long roleId);
	
	public void updateRoleResources(RoleResourcesDomain roleResourcesDomain);
}
