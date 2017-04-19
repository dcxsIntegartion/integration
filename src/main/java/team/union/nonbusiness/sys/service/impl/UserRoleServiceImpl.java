/**
 * 
 */
package team.union.nonbusiness.sys.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import team.union.nonbusiness.sys.controller.domain.UserRolesDomain;
import team.union.nonbusiness.sys.dao.UserInRolesDao;
import team.union.nonbusiness.sys.model.UserInRoles;
import team.union.nonbusiness.sys.model.UserInRolesCriteria;
import team.union.nonbusiness.sys.service.UserRoleService;


/**
 * @author Jack Zhang
 *
 */
@Repository
@Transactional(rollbackFor = Exception.class)
public class UserRoleServiceImpl implements UserRoleService{

	@Autowired
	private UserInRolesDao userInRolesDao;
	
	
	public void save(UserRolesDomain userRolesDomain) {
		Long userId = userRolesDomain.getUserId();
		deleteByUserId(userId);
		
		List<Long> role_ids = userRolesDomain.getRolesId();
		if(role_ids!=null&&role_ids.size()>0){
			for(int i=0;i<role_ids.size();i++){
				UserInRoles userInRoles = new UserInRoles();
				userInRoles.setOpDate(new Date());
				userInRoles.setRoleId(role_ids.get(i));
				userInRoles.setUserId(userId);
				userInRolesDao.insert(userInRoles);
			}
		}
		
	}
	
	private void deleteByUserId(Long userId){
		UserInRolesCriteria userInRolesCriteria = new UserInRolesCriteria();
		userInRolesCriteria.createCriteria().andUserIdEqualTo(userId);
		userInRolesDao.deleteByExample(userInRolesCriteria);
	}

	
	public List<HashMap<String, Object>> selectRoleSelected(Long userId) {
		return userInRolesDao.selectRoleSelected(userId);
	}

}
