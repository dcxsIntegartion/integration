/**
 * 
 */
package team.union.sys_sp.sys.service;

import team.union.sys_sp.com.excp.BusinessException;
import team.union.sys_sp.sys.controller.domain.ResourcesUpdateDomain;
import team.union.sys_sp.sys.model.Resources;
import team.union.sys_sp.sys.utils.Tree;


/**
 * @author Jack Zhang
 *
 */
public interface ResourceService {
	public Tree findResoucesTreeById(Long id,int check_state,boolean checked);
	public Resources findById(Long resourcesId);
	public void delete(Long resorucesId) throws BusinessException;
	public Resources save(Resources resources);
	public void update(ResourcesUpdateDomain resourcesUpdateDomain);
	public Resources findParent(Long childId);
	public Tree findMenuByUserId(Long userId);
	
}
