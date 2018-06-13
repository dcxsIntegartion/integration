/**
 * 
 */
package team.union.sys_sp.sys.service;

import java.util.HashMap;
import java.util.Map;

import team.union.business.com.rs.Result;
import team.union.sys_sp.com.excp.BusinessException;
import team.union.sys_sp.com.rs.BsgridVo;
import team.union.sys_sp.com.rs.ResultVo;
import team.union.sys_sp.sys.controller.domain.UserAccountDomain;
import team.union.sys_sp.sys.model.Account;
import team.union.sys_sp.sys.model.Users;
import team.union.we_chat.com.rs.WeChatRS;
import team.union.we_chat.oauth2.WXUserAuth;
import team.union.we_chat.oauth2.WXUserInfo;



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
	
	
	/********************** 以下微信相关查询 *********************/
	public WeChatRS selByWXopenid(WXUserAuth userAuth);
	public Users saveWXuser(WXUserInfo userInfo);
	public Result staffScavenging(WXUserInfo userInfo,String subDomain);
	public Result touristScavenging(WXUserAuth userAuth,String subDomain,Long staffId);
}
