package team.union.sys_sp.sys.controller.domain;

import team.union.sys_sp.sys.model.Account;
import team.union.sys_sp.sys.model.Users;


/**
 * @author Jack Zhang 用户账户对象，用于封装 post的用户数据
 */
public class UserAccountDomain {
	
	// 用户信息
	private Users users;
	// 账户信息
	private Account account;
	//用户类型
	private int userType;
	//区域Code
	private String areaCode;
	//验证码
	private String validateCode; 
	//邀请人code（邀请人电话号码代替）
	private String invitationCode;
	
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}
	
	
}
