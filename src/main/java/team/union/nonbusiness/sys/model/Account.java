package team.union.nonbusiness.sys.model;

import java.util.Date;

public class Account {

	private Long accountId;

	private Long userId;

	private String accountLoginName;

	private String accountLoginPassword;

	private Date accuontCreateTime;

	private Byte accountIsUse;

	private int phonetype;
	
	private String token;
	
	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAccountLoginName() {
		return accountLoginName;
	}

	public void setAccountLoginName(String accountLoginName) {
		this.accountLoginName = accountLoginName == null ? null
				: accountLoginName.trim();
	}

	public String getAccountLoginPassword() {
		return accountLoginPassword;
	}

	public void setAccountLoginPassword(String accountLoginPassword) {
		this.accountLoginPassword = accountLoginPassword == null ? null
				: accountLoginPassword.trim();
	}

	public Date getAccuontCreateTime() {
		return accuontCreateTime;
	}

	public void setAccuontCreateTime(Date accuontCreateTime) {
		this.accuontCreateTime = accuontCreateTime;
	}

	public Byte getAccountIsUse() {
		return accountIsUse;
	}

	public void setAccountIsUse(Byte accountIsUse) {
		this.accountIsUse = accountIsUse;
	}

	public int getPhonetype() {
		return phonetype;
	}

	public void setPhonetype(int phonetype) {
		this.phonetype = phonetype;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}