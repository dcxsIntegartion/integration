/**
 * 
 */
package team.union.nonbusiness.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import team.union.nonbusiness.com.excp.BusinessException;
import team.union.nonbusiness.com.rs.BsgridVo;
import team.union.nonbusiness.sys.dao.RolesDao;
import team.union.nonbusiness.sys.dao.UserInRolesDao;
import team.union.nonbusiness.sys.model.Roles;
import team.union.nonbusiness.sys.model.RolesCriteria;
import team.union.nonbusiness.sys.model.UserInRolesCriteria;
import team.union.nonbusiness.sys.service.RoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * @author Jack Zhang
 *
 */
@Repository
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RolesDao rolesDao;
	
	@Autowired
	private UserInRolesDao userInRolesDao;

	/**
	 * 修改角色信息
	 * @throws BusinessException 
	 */
	public void update(Roles roles) throws BusinessException {
		if (roleIsExist(roles.getRoleName(), roles.getRoleId())) {
			throw new BusinessException("角色名称重复!");
		}
		rolesDao.updateByPrimaryKeySelective(roles);
	}
	
	
	public void updateState(Roles roles) throws BusinessException {
		rolesDao.updateByPrimaryKeySelective(roles);
	}
	

	/**
	 * 保存角色信息
	 * 
	 * @throws BusinessException
	 */
	public void save(Roles roles) throws BusinessException {

		if (roleIsExist(roles.getRoleName(), null)) {
			throw new BusinessException("角色名称重复!");
		}
		roles.setRoleIsUse((byte)1);
		Roles parent = rolesDao.selectByPrimaryKey(roles.getRoleParentId());
		parent.setRoleChildSize(parent.getRoleChildSize() + 1);
		rolesDao.updateByPrimaryKeySelective(parent);
		rolesDao.insert(roles);

		roles.setRoleFullId(parent.getRoleFullId() + roles.getRoleId() + ".");
		roles.setRoleFullName(parent.getRoleFullName() + roles.getRoleName() + ".");
		roles.setRoleChildSize(0);
		rolesDao.updateByPrimaryKeySelective(roles);
	}

	private boolean roleIsExist(String roleName, Long roleId) {
		RolesCriteria rolesCriteria = new RolesCriteria();
		RolesCriteria.Criteria criteria = rolesCriteria.createCriteria();
		criteria.andRoleNameEqualTo(roleName);
		if (roleId != null) {
			criteria.andRoleIdNotEqualTo(roleId);
		}
		int cnt = rolesDao.countByExample(rolesCriteria);
		return cnt > 0;
	}

	/**
	 * 删除角色信息
	 */
	public void delete(Long roleId) throws BusinessException{
		Roles curretNode = rolesDao.selectByPrimaryKey(roleId);
		if(curretNode==null)
			throw new BusinessException("该角色信息已删除");
		
		//判断角色信息是否被引用
		UserInRolesCriteria userInRolesCriteria = new UserInRolesCriteria();
		userInRolesCriteria.createCriteria().andRoleIdEqualTo(roleId);
		userInRolesDao.deleteByExample(userInRolesCriteria);
		// 查找所有子级角色
		RolesCriteria rolesCriteria = new RolesCriteria();
		rolesCriteria.setOrderByClause("ROLE_ID DESC");// 从最深层子节点开始删除
		rolesCriteria.createCriteria().andRoleFullIdLike(curretNode.getRoleFullId() + "%");

		List<Roles> roles = rolesDao.selectByExample(rolesCriteria);
		if (roles != null && roles.size() > 0) {
			for (int i = 0, size = roles.size(); i < size; i++) {
				Roles role = roles.get(i);
				rolesDao.deleteByPrimaryKey(role.getRoleId());
			}
		}
		// 设置父级节点child_size 减1

		Roles parent = rolesDao.selectByPrimaryKey(curretNode.getRoleParentId());
		parent.setRoleChildSize(parent.getRoleChildSize() - 1);
		rolesDao.updateByPrimaryKeySelective(parent);

	}
	
	

	public Roles findById(Long roleId) throws BusinessException{
		Roles roles = rolesDao.selectByPrimaryKey(roleId);
		if(roles ==null)
			throw new BusinessException("该角色信息已删除");
		return roles;
	}

	/**
	 * @param String
	 *            name 角色名称
	 * 
	 *            根据角色名称查询角色,并返回
	 */
	public Roles findByName(String name) {
		RolesCriteria rolesCriteria = new RolesCriteria();
		rolesCriteria.createCriteria().andRoleNameLike("%" + name + "%");
		List<Roles> roles = rolesDao.selectByExample(rolesCriteria);
		if (roles != null && roles.size() > 0)
			return roles.get(0);
		return null;

	}

	
	public BsgridVo<Roles> queryForlist(RolesCriteria rolesCriteria, int curPage, int pageSize) {
		PageHelper.startPage(curPage, pageSize);
		List<Roles> content = rolesDao.selectByExample(rolesCriteria);
		Page<Roles> result = (Page<Roles>) content;

		BsgridVo<Roles> bsgridVo = new BsgridVo<Roles>();
		bsgridVo.setCurPage(curPage);
		bsgridVo.setData(result);
		bsgridVo.setSuccess(true);
		bsgridVo.setTotalRows(result.getTotal());

		return bsgridVo;
	}


	
	public List<Roles> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
