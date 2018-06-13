package team.union.business.store.model;

import java.util.Date;
import java.util.List;

public class BisStore {
    private Long id;

    private String storeName;

    private String storeAddress;

    private String storePhone;

    private String storePic;
    
    private List<String> storePicUrls;
    
    private String storeWxQr;

    private List<String> storeWxQrUrls;
    
    private String storeIntroduction;

    private String storeLongitude;

    private String storeLatitude;

    private Long storeUserId;

    private Long storeSortNum;

    private Date storeCreatTime;

    private Integer storeStatus;

    private String appid;
    
    private String subDomain;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress == null ? null : storeAddress.trim();
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone == null ? null : storePhone.trim();
    }

    public String getStorePic() {
        return storePic;
    }

    public void setStorePic(String storePic) {
        this.storePic = storePic == null ? null : storePic.trim();
    }

    public String getStoreIntroduction() {
        return storeIntroduction;
    }

    public void setStoreIntroduction(String storeIntroduction) {
        this.storeIntroduction = storeIntroduction == null ? null : storeIntroduction.trim();
    }

    public String getStoreLongitude() {
        return storeLongitude;
    }

    public void setStoreLongitude(String storeLongitude) {
        this.storeLongitude = storeLongitude == null ? null : storeLongitude.trim();
    }

    public String getStoreLatitude() {
        return storeLatitude;
    }

    public void setStoreLatitude(String storeLatitude) {
        this.storeLatitude = storeLatitude == null ? null : storeLatitude.trim();
    }

    public Long getStoreUserId() {
        return storeUserId;
    }

    public void setStoreUserId(Long storeUserId) {
        this.storeUserId = storeUserId;
    }

    public Long getStoreSortNum() {
        return storeSortNum;
    }

    public void setStoreSortNum(Long storeSortNum) {
        this.storeSortNum = storeSortNum;
    }

    public Date getStoreCreatTime() {
        return storeCreatTime;
    }

    public void setStoreCreatTime(Date storeCreatTime) {
        this.storeCreatTime = storeCreatTime;
    }

    public Integer getStoreStatus() {
        return storeStatus;
    }

    public void setStoreStatus(Integer storeStatus) {
        this.storeStatus = storeStatus;
    }

	public String getStoreWxQr() {
		return storeWxQr;
	}

	public void setStoreWxQr(String storeWxQr) {
		this.storeWxQr = storeWxQr;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public List<String> getStorePicUrls() {
		return storePicUrls;
	}

	public void setStorePicUrls(List<String> storePicUrls) {
		this.storePicUrls = storePicUrls;
	}

	public List<String> getStoreWxQrUrls() {
		return storeWxQrUrls;
	}

	public void setStoreWxQrUrls(List<String> storeWxQrUrls) {
		this.storeWxQrUrls = storeWxQrUrls;
	}

	public String getSubDomain() {
		return subDomain;
	}

	public void setSubDomain(String subDomain) {
		this.subDomain = subDomain;
	}
	
}