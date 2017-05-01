package team.union.business.commodity.model;

import java.math.BigDecimal;
import java.util.Date;

public class BisCommodity {
    private Long id;

    private String commodityName;

    private BigDecimal commodityPrice;

    private BigDecimal commodityOldPrice;

    private Long commodityStoreId;

    private Integer commodityNum;

    private BigDecimal commodityQuality;

    private String commodityPic;

    private Integer commoditySortNum;

    private String commodityIntroduction;

    private Integer commodityStatus;

    private Integer homepageShow;

    private Date timingOff;

    private Date timingBegain;

    private Integer isTiming;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName == null ? null : commodityName.trim();
    }

    public BigDecimal getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(BigDecimal commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public BigDecimal getCommodityOldPrice() {
        return commodityOldPrice;
    }

    public void setCommodityOldPrice(BigDecimal commodityOldPrice) {
        this.commodityOldPrice = commodityOldPrice;
    }

    public Long getCommodityStoreId() {
        return commodityStoreId;
    }

    public void setCommodityStoreId(Long commodityStoreId) {
        this.commodityStoreId = commodityStoreId;
    }

    public Integer getCommodityNum() {
        return commodityNum;
    }

    public void setCommodityNum(Integer commodityNum) {
        this.commodityNum = commodityNum;
    }

    public BigDecimal getCommodityQuality() {
        return commodityQuality;
    }

    public void setCommodityQuality(BigDecimal commodityQuality) {
        this.commodityQuality = commodityQuality;
    }

    public String getCommodityPic() {
        return commodityPic;
    }

    public void setCommodityPic(String commodityPic) {
        this.commodityPic = commodityPic == null ? null : commodityPic.trim();
    }

    public Integer getCommoditySortNum() {
        return commoditySortNum;
    }

    public void setCommoditySortNum(Integer commoditySortNum) {
        this.commoditySortNum = commoditySortNum;
    }

    public String getCommodityIntroduction() {
        return commodityIntroduction;
    }

    public void setCommodityIntroduction(String commodityIntroduction) {
        this.commodityIntroduction = commodityIntroduction == null ? null : commodityIntroduction.trim();
    }

    public Integer getCommodityStatus() {
        return commodityStatus;
    }

    public void setCommodityStatus(Integer commodityStatus) {
        this.commodityStatus = commodityStatus;
    }

    public Integer getHomepageShow() {
        return homepageShow;
    }

    public void setHomepageShow(Integer homepageShow) {
        this.homepageShow = homepageShow;
    }

    public Date getTimingOff() {
        return timingOff;
    }

    public void setTimingOff(Date timingOff) {
        this.timingOff = timingOff;
    }

    public Date getTimingBegain() {
        return timingBegain;
    }

    public void setTimingBegain(Date timingBegain) {
        this.timingBegain = timingBegain;
    }

    public Integer getIsTiming() {
        return isTiming;
    }

    public void setIsTiming(Integer isTiming) {
        this.isTiming = isTiming;
    }
}