/**
 * 
 */
package team.union.nonbusiness.sys.service;

import java.util.HashMap;
import java.util.Map;

import team.union.nonbusiness.com.excp.BusinessException;
import team.union.nonbusiness.com.rs.BsgridVo;
import team.union.nonbusiness.com.rs.ResultVo;
import team.union.nonbusiness.sys.controller.domain.UserAccountDomain;
import team.union.nonbusiness.sys.model.Account;
import team.union.nonbusiness.sys.model.Users;



/**
 * @author Jack Zhang
 *
 */
public interface UserService {

	/**
	 * 查询用户信息
	 */
	public BsgridVo<HashMap<String, Object>> findByPage(Map<String, Object> args, int page, int rows);

	/**
	 * 新增用户信息
	 */
	public void save(UserAccountDomain userAccountDomain) throws BusinessException;
	
	public UserAccountDomain find(Long userId) throws BusinessException;

	/**
	 * 删除用户的信息
	 */
	public void delete(Long userId) throws BusinessException;
	
	/**
	 * 修改用户账户的启用状态
	 * */
	public void updateState(Long accountId,int state) throws BusinessException;
	
	/**
	 * 修改用户的基本信息
	 * */
	public void updateUser(Users users) throws BusinessException;
	
	public UserAccountDomain login(Account account) throws BusinessException;

	/**
	 * 修改登录用户密码
	 * @param map
	 * @return
	 * @author	yinxb
	 * @Date	2016-1-14 下午8:57:43
	 */
	public ResultVo updatePwd(HashMap<String, Object> map);
	public ResultVo updatePwdApp(HashMap<String, Object> map);
	
	/*****用户关联角色查询*******/
	public BsgridVo<HashMap<String, Object>> roleLeftUsersList(HashMap<String, Object> para, int curPage, int pageSize);
	
}
