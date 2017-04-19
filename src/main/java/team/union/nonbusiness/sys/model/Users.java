package team.union.nonbusiness.sys.model;

import java.util.Date;

public class Users {
    private Long userId;

    private String userName;

    private String userMobilephone;

    private String iosToken;

    private String androidToken;

    private Date iosEffectiveTime;

    private Date androidEffectiveTime;

    private Integer userType;

    private Integer phoneType;
    
    private String imgId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserMobilephone() {
        return userMobilephone;
    }

    public void setUserMobilephone(String userMobilephone) {
        this.userMobilephone = userMobilephone == null ? null : userMobilephone.trim();
    }

    public String getIosToken() {
        return iosToken;
    }

    public void setIosToken(String iosToken) {
        this.iosToken = iosToken == null ? null : iosToken.trim();
    }

    public String getAndroidToken() {
        return androidToken;
    }

    public void setAndroidToken(String androidToken) {
        this.androidToken = androidToken == null ? null : androidToken.trim();
    }

    public Date getIosEffectiveTime() {
        return iosEffectiveTime;
    }

    public void setIosEffectiveTime(Date iosEffectiveTime) {
        this.iosEffectiveTime = iosEffectiveTime;
    }

    public Date getAndroidEffectiveTime() {
        return androidEffectiveTime;
    }

    public void setAndroidEffectiveTime(Date androidEffectiveTime) {
        this.androidEffectiveTime = androidEffectiveTime;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId == null ? null : imgId.trim();
    }

	public Integer getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(Integer phoneType) {
		this.phoneType = phoneType;
	}

	
    
}