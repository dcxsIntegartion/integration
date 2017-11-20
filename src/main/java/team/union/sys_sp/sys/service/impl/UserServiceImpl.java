package team.union.sys_sp.sys.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import team.union.sys_sp.com.cfg.BaseConfig;
import team.union.sys_sp.com.excp.BusinessException;
import team.union.sys_sp.com.rs.BsgridVo;
import team.union.sys_sp.com.rs.ResultVo;
import team.union.sys_sp.sys.controller.domain.UserAccountDomain;
import team.union.sys_sp.sys.dao.AccountDao;
import team.union.sys_sp.sys.dao.UsersDao;
import team.union.sys_sp.sys.model.Account;
import team.union.sys_sp.sys.model.AccountCriteria;
import team.union.sys_sp.sys.model.Users;
import team.union.sys_sp.sys.service.UserService;
import team.union.sys_sp.util.MD5Utils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * @author Jack Zhang
 *
 */
@Repository
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

	@Autowired
	private AccountDao accountDao;
	@Autowired
	private UsersDao usersDao;
	
	
	public BsgridVo<HashMap<String, Object>> findByPage(Map<String, Object> args, int curPage, int pageSize) {
		PageHelper.startPage(curPage, pageSize);
		List<HashMap<String, Object>> content = accountDao.selectAll(args);
		Page<HashMap<String, Object>> result = (Page<HashMap<String, Object>>) content;

		BsgridVo<HashMap<String, Object>> bsgridVo = new BsgridVo<HashMap<String, Object>>();
		bsgridVo.setCurPage(curPage);
		bsgridVo.setData(result);
		bsgridVo.setSuccess(true);
		bsgridVo.setTotalRows(result.getTotal());

		return bsgridVo;
	}
	
	public BsgridVo<HashMap<String, Object>> roleLeftUsersList(
			HashMap<String, Object> para, int curPage, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(curPage, pageSize);
		List<HashMap<String, Object>> content = usersDao.roleLeftUsers(para);
		Page<HashMap<String, Object>> result = (Page<HashMap<String, Object>>) content;

		BsgridVo<HashMap<String, Object>> bsgridVo = new BsgridVo<HashMap<String, Object>>();
		bsgridVo.setCurPage(curPage);
		bsgridVo.setData(result);
		bsgridVo.setSuccess(true);
		bsgridVo.setTotalRows(result.getTotal());

		return bsgridVo;
	}
	
	public void save(UserAccountDomain userAccountDomain) throws BusinessException {

		Users users = userAccountDomain.getUsers();
		Account account = userAccountDomain.getAccount();

		// 判断账号是否存在
		if (accountIsExist(account.getAccountLoginName(), null)) {
			throw new BusinessException("登陆账号已存在");
		}

		// 保存用户基本信息
		usersDao.insert(users);
		
		if (account != null) {
			account.setUserId(users.getUserId());
			account.setAccountLoginPassword(MD5Utils.compute(account.getAccountLoginPassword()));
			account.setAccuontCreateTime(new Date());
			account.setAccountIsUse(Byte.parseByte("1"));
			accountDao.insert(account);
		}
	}

	
	/**
	 * 
	 * @param accountName
	 *            账号名称
	 * @param accountId
	 *            账号编号
	 * @return 返回查询到的账号数量
	 */
	private boolean accountIsExist(String accountName, Long accountId) {
		AccountCriteria accountCriteria = new AccountCriteria();
		AccountCriteria.Criteria criteria = accountCriteria.createCriteria();
		criteria.andAccountLoginNameEqualTo(accountName);
		if (accountId != null) {
			criteria.andAccountIdNotEqualTo(accountId);
		}
		int cnt = accountDao.countByExample(accountCriteria);
		return cnt > 0;
	}

	
	public void delete(Long userId) throws BusinessException {

		Users users = usersDao.selectByPrimaryKey(userId);
		if (users == null) {
			throw new BusinessException("用户信息已删除");
		}

		Account account = findByUserId(userId);
		if (account == null) {
			throw new BusinessException("用户信息已删除");
		}

		accountDao.deleteByPrimaryKey(account.getAccountId());
		try {
			// TODO 删除关联数据
			usersDao.deleteByPrimaryKey(userId);
		} catch (Exception e) {
			throw new BusinessException("删除失败");
		}

	}

	private Account findByUserId(Long userId) {
		AccountCriteria accountCriteria = new AccountCriteria();
		accountCriteria.createCriteria().andUserIdEqualTo(userId);
		List<Account> account = accountDao.selectByExample(accountCriteria);
		if (account.size() > 0) {
			return account.get(0);
		}
		return null;
	}

	
	public void updateState(Long accountId, int state) throws BusinessException {
		Account account = accountDao.selectByPrimaryKey(accountId);
		if (account == null)
			throw new BusinessException("用户账户信息已删除");
		account.setAccountIsUse((byte) state);
		accountDao.updateByPrimaryKey(account);
	}

	
	public UserAccountDomain find(Long userId) throws BusinessException {
		Account account = findByUserId(userId);
		if (account == null)
			throw new BusinessException("用户账户信息已删除");
		
		Users users = usersDao.selectByPrimaryKey(userId);
		if (users == null)
			throw new BusinessException("用户账户信息已删除");
		
		UserAccountDomain userAccountDomain = new UserAccountDomain();
		userAccountDomain.setAccount(account);
		userAccountDomain.setUsers(users);
		return userAccountDomain;
	}

	
	public void updateUser(Users users) throws BusinessException {
		
		Users u = usersDao.selectByPrimaryKey(users.getUserId());
		if(u==null)
			throw new BusinessException("用户账户信息已删除");
		usersDao.updateByPrimaryKeySelective(users);
	}

	

	
	public UserAccountDomain login(Account _account) throws BusinessException {
		String accountLoginName = _account.getAccountLoginName();
		String accountPassword = _account.getAccountLoginPassword();
		Account account = findByLoginName(accountLoginName);
		if(account==null) throw new BusinessException("账号或密码错误");
		
		if(!account.getAccountLoginPassword().equals(MD5Utils.compute(accountPassword)))
			throw new BusinessException("账号或密码错误");
		if(account.getAccountIsUse()==0)
			throw new BusinessException("账号已被锁定,登陆失败");
		
		UserAccountDomain userAccountDomain = new UserAccountDomain();
		userAccountDomain.setAccount(account);
		userAccountDomain.setUsers(usersDao.selectByPrimaryKey(account.getUserId()));
		return userAccountDomain;
	}
	public Account findByLoginName(String loginName){
		AccountCriteria accountCriteria  = new AccountCriteria();
		accountCriteria.createCriteria().andAccountLoginNameEqualTo(loginName);
		List<Account> accounts = accountDao.selectByExample(accountCriteria);
		if(accounts!=null&&accounts.size()>0){
			return accounts.get(0);
		}
		return null;
	}

	
	public ResultVo updatePwd(HashMap<String, Object> map) {
		AccountCriteria accountCriteria  = new AccountCriteria();
		Long userId =  0l;
		String oldPwd = "";
		ResultVo resultVo = new ResultVo();
		try {
			if(map.get("userId") != null){
			 String	userIds =  map.get("userId").toString();
			 userId = Long.valueOf(userIds);
			}
			accountCriteria.createCriteria().andUserIdEqualTo(userId);
			List<Account> accounts = accountDao.selectByExample(accountCriteria);
			if(accounts != null && accounts.size()>0){
				Account account = accounts.get(0);
				oldPwd = account.getAccountLoginPassword();
			}
			if(map.get("oldPassword") != null){
				String old_password_request = MD5Utils.compute(map.get("oldPassword").toString());
				if(!oldPwd.equals(old_password_request)){
					resultVo.setInfo("旧密码错误");
					resultVo.setStatus(BaseConfig.FAILED_STATUS);
				}else{
					if(map.get("accountLoginPassword") != null){//加密新密码
						map.put("accountLoginPassword", MD5Utils.compute(map.get("accountLoginPassword").toString()));
					}
					accountDao.updatePassword(map);
					resultVo.setInfo("修改成功");
					resultVo.setStatus(BaseConfig.SUCCESS_STATUS);
				}
			}
		} catch (Exception e) {
			resultVo.setInfo("修改失败");
			resultVo.setStatus(BaseConfig.FAILED_STATUS);
			e.printStackTrace();
		}
		return resultVo;
	}
	public ResultVo updatePwdApp(HashMap<String, Object> map) {
		AccountCriteria accountCriteria  = new AccountCriteria();
		Long userId =  0l;
		String oldPwd = "";
		ResultVo resultVo = new ResultVo();
		try {
			if(map.get("userId") != null){
			 String	userIds =  map.get("userId").toString();
			 userId = Long.valueOf(userIds);
			}
			accountCriteria.createCriteria().andUserIdEqualTo(userId);
			List<Account> accounts = accountDao.selectByExample(accountCriteria);
			if(accounts != null && accounts.size()>0){
				Account account = accounts.get(0);
				oldPwd = account.getAccountLoginPassword();
			}
			if(map.get("oldPassword") != null){
				String old_password_request = map.get("oldPassword").toString();
				if(!oldPwd.equals(old_password_request)){
					resultVo.setInfo("旧密码错误");
					resultVo.setStatus(BaseConfig.FAILED_STATUS);
				}else{
					if(map.get("accountLoginPassword") != null){//加密新密码
						map.put("accountLoginPassword", MD5Utils.compute(map.get("accountLoginPassword").toString()));
					}
					accountDao.updatePassword(map);
					resultVo.setInfo("修改成功");
					resultVo.setStatus(BaseConfig.SUCCESS_STATUS);
				}
			}
		} catch (Exception e) {
			resultVo.setInfo("修改失败");
			resultVo.setStatus(BaseConfig.FAILED_STATUS);
			e.printStackTrace();
		}
		return resultVo;
	}
}
