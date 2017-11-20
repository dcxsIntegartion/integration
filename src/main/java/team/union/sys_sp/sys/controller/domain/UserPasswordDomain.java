package team.union.sys_sp.sys.controller.domain;

/**
 * 修改密码临时对象
 * @author		yinxb
 * @Description	模块描述
 * @version		1.00 
 * @see			参考类1
 * @Date		2016-1-20 下午2:40:31
 */
public class UserPasswordDomain {

	private Long userId;
	
	private String name;
	
	private String oldPassword;
	
	private String accountLoginPassword;

	private String veriCode;
	
	
	public String getVeriCode() {
		return veriCode;
	}

	public void setVeriCode(String veriCode) {
		this.veriCode = veriCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getAccountLoginPassword() {
		return accountLoginPassword;
	}

	public void setAccountLoginPassword(String accountLoginPassword) {
		this.accountLoginPassword = accountLoginPassword;
	}
	
	
}
