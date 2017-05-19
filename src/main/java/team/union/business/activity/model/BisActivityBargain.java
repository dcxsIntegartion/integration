package team.union.business.activity.model;

import java.util.Date;
import java.util.List;

import team.union.nonbusiness.upload.model.NonbizUpload;

public class BisActivityBargain {
    private Long id;

    private String activityName;

    private Date activityStartTime;

    private Date activityEndTime;

    private Long activityStoreId;

    private String activityPic;

    private String activityExplain;

    private Integer activityStatus;

    private String shareTitle;

    private String shareDescribe;

    private String sharePic;

    private Date activityCreatTime;
    //活动图片
    private List<NonbizUpload> activityPicList;
    //分享图片
    private List<NonbizUpload> sharePicList;
    //活动开始时间String
    private String activityStartTimeStr;
    //活动结束时间string
    private String activityEndTimeStr;
    //活动店铺名称
    private String activityStoreName;

    public String getActivityStoreName() {
		return activityStoreName;
	}

	public void setActivityStoreName(String activityStoreName) {
		this.activityStoreName = activityStoreName;
	}

	public String getActivityStartTimeStr() {
		return activityStartTimeStr;
	}

	public void setActivityStartTimeStr(String activityStartTimeStr) {
		this.activityStartTimeStr = activityStartTimeStr;
	}

	public String getActivityEndTimeStr() {
		return activityEndTimeStr;
	}

	public void setActivityEndTimeStr(String activityEndTimeStr) {
		this.activityEndTimeStr = activityEndTimeStr;
	}

	public List<NonbizUpload> getActivityPicList() {
		return activityPicList;
	}

	public void setActivityPicList(List<NonbizUpload> activityPicList) {
		this.activityPicList = activityPicList;
	}

	public List<NonbizUpload> getSharePicList() {
		return sharePicList;
	}

	public void setSharePicList(List<NonbizUpload> sharePicList) {
		this.sharePicList = sharePicList;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    public Date getActivityStartTime() {
        return activityStartTime;
    }

    public void setActivityStartTime(Date activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    public Date getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(Date activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    public Long getActivityStoreId() {
        return activityStoreId;
    }

    public void setActivityStoreId(Long activityStoreId) {
        this.activityStoreId = activityStoreId;
    }

    public String getActivityPic() {
        return activityPic;
    }

    public void setActivityPic(String activityPic) {
        this.activityPic = activityPic;
    }

    public String getActivityExplain() {
        return activityExplain;
    }

    public void setActivityExplain(String activityExplain) {
        this.activityExplain = activityExplain == null ? null : activityExplain.trim();
    }

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle == null ? null : shareTitle.trim();
    }

    public String getShareDescribe() {
        return shareDescribe;
    }

    public void setShareDescribe(String shareDescribe) {
        this.shareDescribe = shareDescribe == null ? null : shareDescribe.trim();
    }

    public String getSharePic() {
        return sharePic;
    }

    public void setSharePic(String sharePic) {
        this.sharePic = sharePic == null ? null : sharePic.trim();
    }

    public Date getActivityCreatTime() {
        return activityCreatTime;
    }

    public void setActivityCreatTime(Date activityCreatTime) {
        this.activityCreatTime = activityCreatTime;
    }
}