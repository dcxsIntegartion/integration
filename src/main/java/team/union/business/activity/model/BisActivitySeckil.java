package team.union.business.activity.model;

import java.util.Date;
import java.util.List;

import team.union.sys_sp.upload.model.NonbizUpload;

public class BisActivitySeckil {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bis_activity_seckil.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bis_activity_seckil.activity_name
     *
     * @mbggenerated
     */
    private String activityName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bis_activity_seckil.activity_day_time
     *
     * @mbggenerated
     */
    private Date activityDayTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bis_activity_seckil.activity_point_time
     *
     * @mbggenerated
     */
    private Byte activityPointTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bis_activity_seckil.activity_store_id
     *
     * @mbggenerated
     */
    private Long activityStoreId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bis_activity_seckil.activity_pic
     *
     * @mbggenerated
     */
    private String activityPic;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bis_activity_seckil.activity_explain
     *
     * @mbggenerated
     */
    private String activityExplain;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bis_activity_seckil.activity_status
     *
     * @mbggenerated
     */
    private Integer activityStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bis_activity_seckil.share_title
     *
     * @mbggenerated
     */
    private String shareTitle;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bis_activity_seckil.share_describe
     *
     * @mbggenerated
     */
    private String shareDescribe;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bis_activity_seckil.share_pic
     *
     * @mbggenerated
     */
    private String sharePic;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bis_activity_seckil.activity_creat_time
     *
     * @mbggenerated
     */
    private Date activityCreatTime;
    
    //活动图片
    private List<NonbizUpload> activityPicList;
    //分享图片
    private List<NonbizUpload> sharePicList;
    //活动日期
    private String activityDayTimeStr;
    //活动店铺名称
    private String activityStoreName;

    public String getActivityDayTimeStr() {
		return activityDayTimeStr;
	}

	public void setActivityDayTimeStr(String activityDayTimeStr) {
		this.activityDayTimeStr = activityDayTimeStr;
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

	public String getActivityStoreName() {
		return activityStoreName;
	}

	public void setActivityStoreName(String activityStoreName) {
		this.activityStoreName = activityStoreName;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bis_activity_seckil.id
     *
     * @return the value of bis_activity_seckil.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bis_activity_seckil.id
     *
     * @param id the value for bis_activity_seckil.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bis_activity_seckil.activity_name
     *
     * @return the value of bis_activity_seckil.activity_name
     *
     * @mbggenerated
     */
    public String getActivityName() {
        return activityName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bis_activity_seckil.activity_name
     *
     * @param activityName the value for bis_activity_seckil.activity_name
     *
     * @mbggenerated
     */
    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bis_activity_seckil.activity_day_time
     *
     * @return the value of bis_activity_seckil.activity_day_time
     *
     * @mbggenerated
     */
    public Date getActivityDayTime() {
        return activityDayTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bis_activity_seckil.activity_day_time
     *
     * @param activityDayTime the value for bis_activity_seckil.activity_day_time
     *
     * @mbggenerated
     */
    public void setActivityDayTime(Date activityDayTime) {
        this.activityDayTime = activityDayTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bis_activity_seckil.activity_point_time
     *
     * @return the value of bis_activity_seckil.activity_point_time
     *
     * @mbggenerated
     */
    public Byte getActivityPointTime() {
        return activityPointTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bis_activity_seckil.activity_point_time
     *
     * @param activityPointTime the value for bis_activity_seckil.activity_point_time
     *
     * @mbggenerated
     */
    public void setActivityPointTime(Byte activityPointTime) {
        this.activityPointTime = activityPointTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bis_activity_seckil.activity_store_id
     *
     * @return the value of bis_activity_seckil.activity_store_id
     *
     * @mbggenerated
     */
    public Long getActivityStoreId() {
        return activityStoreId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bis_activity_seckil.activity_store_id
     *
     * @param activityStoreId the value for bis_activity_seckil.activity_store_id
     *
     * @mbggenerated
     */
    public void setActivityStoreId(Long activityStoreId) {
        this.activityStoreId = activityStoreId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bis_activity_seckil.activity_pic
     *
     * @return the value of bis_activity_seckil.activity_pic
     *
     * @mbggenerated
     */
    public String getActivityPic() {
        return activityPic;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bis_activity_seckil.activity_pic
     *
     * @param activityPic the value for bis_activity_seckil.activity_pic
     *
     * @mbggenerated
     */
    public void setActivityPic(String activityPic) {
        this.activityPic = activityPic == null ? null : activityPic.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bis_activity_seckil.activity_explain
     *
     * @return the value of bis_activity_seckil.activity_explain
     *
     * @mbggenerated
     */
    public String getActivityExplain() {
        return activityExplain;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bis_activity_seckil.activity_explain
     *
     * @param activityExplain the value for bis_activity_seckil.activity_explain
     *
     * @mbggenerated
     */
    public void setActivityExplain(String activityExplain) {
        this.activityExplain = activityExplain == null ? null : activityExplain.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bis_activity_seckil.activity_status
     *
     * @return the value of bis_activity_seckil.activity_status
     *
     * @mbggenerated
     */
    public Integer getActivityStatus() {
        return activityStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bis_activity_seckil.activity_status
     *
     * @param activityStatus the value for bis_activity_seckil.activity_status
     *
     * @mbggenerated
     */
    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bis_activity_seckil.share_title
     *
     * @return the value of bis_activity_seckil.share_title
     *
     * @mbggenerated
     */
    public String getShareTitle() {
        return shareTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bis_activity_seckil.share_title
     *
     * @param shareTitle the value for bis_activity_seckil.share_title
     *
     * @mbggenerated
     */
    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle == null ? null : shareTitle.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bis_activity_seckil.share_describe
     *
     * @return the value of bis_activity_seckil.share_describe
     *
     * @mbggenerated
     */
    public String getShareDescribe() {
        return shareDescribe;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bis_activity_seckil.share_describe
     *
     * @param shareDescribe the value for bis_activity_seckil.share_describe
     *
     * @mbggenerated
     */
    public void setShareDescribe(String shareDescribe) {
        this.shareDescribe = shareDescribe == null ? null : shareDescribe.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bis_activity_seckil.share_pic
     *
     * @return the value of bis_activity_seckil.share_pic
     *
     * @mbggenerated
     */
    public String getSharePic() {
        return sharePic;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bis_activity_seckil.share_pic
     *
     * @param sharePic the value for bis_activity_seckil.share_pic
     *
     * @mbggenerated
     */
    public void setSharePic(String sharePic) {
        this.sharePic = sharePic == null ? null : sharePic.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bis_activity_seckil.activity_creat_time
     *
     * @return the value of bis_activity_seckil.activity_creat_time
     *
     * @mbggenerated
     */
    public Date getActivityCreatTime() {
        return activityCreatTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bis_activity_seckil.activity_creat_time
     *
     * @param activityCreatTime the value for bis_activity_seckil.activity_creat_time
     *
     * @mbggenerated
     */
    public void setActivityCreatTime(Date activityCreatTime) {
        this.activityCreatTime = activityCreatTime;
    }
}