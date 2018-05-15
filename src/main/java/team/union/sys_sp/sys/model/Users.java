package team.union.sys_sp.sys.model;

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
    
    private Integer state;

    private String openid;

    private Integer subscribe;

    private Integer subscribeTime;

    private String nickname;

    private Integer sex;

    private String country;

    private String province;

    private String city;

    private String language;

    private String headimgurl;

    private String unionid;

    private String privilege;

    private String remark;

    private String groupid;

    private String tagidlist;

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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public Integer getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}

	public Integer getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(Integer subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getTagidlist() {
		return tagidlist;
	}

	public void setTagidlist(String tagidlist) {
		this.tagidlist = tagidlist;
	}
    
}