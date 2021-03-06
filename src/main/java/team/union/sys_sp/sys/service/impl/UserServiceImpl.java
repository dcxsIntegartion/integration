package team.union.sys_sp.sys.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import team.union.business.com.rs.Result;
import team.union.business.store.dao.BisStoreDao;
import team.union.business.store.model.BisStore;
import team.union.business.store.model.MidUsersStore;
import team.union.business.store.service.IMidUsersStoreService;
import team.union.sys_sp.com.cfg.BaseConfig;
import team.union.sys_sp.com.cfg.PromptMsgConfig.PROMPT;
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
import team.union.utils.algorithm.MD5Utils;
import team.union.utils.qr.QrcodeUtils;
import team.union.we_chat.com.cfg.BaseConfig.WX_USER_TYPE;
import team.union.we_chat.com.rs.WeChatRS;
import team.union.we_chat.oauth2.WXUserAuth;
import team.union.we_chat.oauth2.WXUserInfo;

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
	@Autowired
	private BisStoreDao bisStoreDao;
	@Autowired
	private IMidUsersStoreService midUsersStoreService;
	
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
	
//	/********************** 微信相关查询 *********************/
	
	public WeChatRS selByWXopenid(WXUserAuth userAuth){
		WXUserInfo user = null;
		if(null != userAuth.getOpenid() && !"".equals(userAuth.getOpenid())){
			user = usersDao.selByWXopenid(userAuth.getOpenid());
		}
		if(null==user){
			return WeChatRS.error(PROMPT.ACCT_NO_EXISTED.getMsg());
		}
		return WeChatRS.success();
	}
	public Users saveWXuser(WXUserInfo userInfo){
		WXUserInfo wxUserInfo = null;
		Users user = new Users();
		if(null!=userInfo && null!=userInfo.getOpenid() && !"".equals(userInfo.getOpenid())){
			wxUserInfo = usersDao.selByWXopenid(userInfo.getOpenid());
		}
		// 保存用户基本信息
		user.setOpenid(userInfo.getOpenid());
		user.setSubscribeTime(userInfo.getSubscribeTime());
		user.setUserName(userInfo.getNickname());
		user.setNickname(userInfo.getNickname());
		user.setSex(userInfo.getSex());
		user.setCountry(userInfo.getCountry());
		user.setProvince(userInfo.getProvince());
		user.setCity(userInfo.getCity());
		user.setLanguage(userInfo.getLanguage());
		user.setHeadimgurl(userInfo.getHeadimgurl());
		user.setRemark(userInfo.getRemark());
		user.setGroupid(userInfo.getGroupid());
		user.setTagidlist(userInfo.getTagidList());
		user.setUnionid(userInfo.getUnionid());
		// 判断账号是否存在
		if (null != wxUserInfo && null != wxUserInfo.getUserId()) {
			user.setUserId(wxUserInfo.getUserId());
			usersDao.updateByPrimaryKeySelective(user);
		}else{
			usersDao.insert(user);
		}
		return  user;
	}
	/**
	 * 1.调用用户保存（修改）
	 * 2.生成游客扫码url（包含公众号-店铺-员工信息）
	 * 3.建立商店与员工中间关系
	 * @param userInfo
	 * @param subDomain
	 * @return imgUrl
	 */
	public Result staffScavenging(WXUserInfo userInfo,String subDomain){
		Users wxUser = saveWXuser(userInfo);
		BisStore bs = bisStoreDao.selBySubDomain(subDomain);
		if(null!=wxUser && wxUser.getUserId()>0 &&
				null!=bs && bs.getId()>0){
			String content = "https://open.weixin.qq.com/connect/oauth2/authorize?"
			   		+ "appid="+bs.getAppid()
			   		+ "&redirect_uri="+subDomain
			   		+ "%2fwe_chat%2f/touristScavenging"
			   		+ ".&response_type=code&scope=snsapi_userinfo&"
			   		+ "state="+wxUser.getUserId()+"#wechat_redirect";
			String imgUrl = QrcodeUtils.staffQr(content);
		    if(imgUrl!=null){
			   MidUsersStore midUS = new MidUsersStore();
			   midUS.setIsTourist(WX_USER_TYPE.staff.getNo());
			   midUS.setStoreId(bs.getId());
			   midUS.setUserId(wxUser.getUserId());
			   midUS.setStoreQrImg(imgUrl);
			   Result result= midUsersStoreService.saveMidUsersStore(midUS);
			   if(result.isSuccess()){
				   return Result.success(imgUrl);
			   }
				
		    }
		}
		return Result.error();
	}
	/**
	 * @param userAuth    游客微信信息
	 * @param subDomain	   二级域名
	 * @param staffId	   引导游客扫码的店员id
	 * @return
	 */
	public Result touristScavenging(WXUserAuth userAuth,String subDomain,Long staffId){
		BisStore bs = bisStoreDao.selBySubDomain(subDomain);
		if(null!=userAuth && StringUtils.isNotEmpty(userAuth.getOpenid()) &&
				null!=bs && bs.getId()>0){
			MidUsersStore midUS = new MidUsersStore();
			   midUS.setIsTourist(WX_USER_TYPE.tourist.getNo());
			   midUS.setStoreId(bs.getId());
			   midUS.setUserId(staffId);
			   midUS.setTouristOpenid(userAuth.getOpenid());
			   Result result= midUsersStoreService.saveMidUsersStore(midUS);
			   return result;
		}
		return Result.error();
	}
	
}