/**
 * 
 */
package team.union.sys_sp.sys.service;


import java.util.List;

import team.union.sys_sp.com.excp.BusinessException;
import team.union.sys_sp.com.rs.BsgridVo;
import team.union.sys_sp.sys.model.Roles;
import team.union.sys_sp.sys.model.RolesCriteria;


/**
 * @author Jack Zhang
 *
 */
public interface RoleService {
	
	public void update(Roles roles) throws BusinessException;
	public void updateState(Roles roles) throws BusinessException;
	
	public List<Roles> findAll();

	public void save(Roles roles) throws BusinessException;

	public void delete(Long roleId) throws BusinessException;

	public Roles findById(Long roleId) throws BusinessException;

	public Roles findByName(String name);
	
	public BsgridVo<Roles> queryForlist(RolesCriteria queryArgs,int page,int rows);

}
