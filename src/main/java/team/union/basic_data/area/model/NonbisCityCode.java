package team.union.basic_data.area.model;

import java.util.Date;

public class NonbisCityCode {
    private Long cityId;

    private String cityCode;

    private String cityName;

    private String cityFullName;

    private String cityFullCode;

    private String cityParentCode;

    private Integer sortId;

    private String allSpellCode;

    private String simpleSpellCode;

    private Integer levelNumber;

    private Byte isUse;

    private Byte isReadOnly;

    private Integer childSize;

    private Date openTime;

    private Integer openStutas;

    private Date closeTime;

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getCityFullName() {
        return cityFullName;
    }

    public void setCityFullName(String cityFullName) {
        this.cityFullName = cityFullName == null ? null : cityFullName.trim();
    }

    public String getCityFullCode() {
        return cityFullCode;
    }

    public void setCityFullCode(String cityFullCode) {
        this.cityFullCode = cityFullCode == null ? null : cityFullCode.trim();
    }

    public String getCityParentCode() {
        return cityParentCode;
    }

    public void setCityParentCode(String cityParentCode) {
        this.cityParentCode = cityParentCode == null ? null : cityParentCode.trim();
    }

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public String getAllSpellCode() {
        return allSpellCode;
    }

    public void setAllSpellCode(String allSpellCode) {
        this.allSpellCode = allSpellCode == null ? null : allSpellCode.trim();
    }

    public String getSimpleSpellCode() {
        return simpleSpellCode;
    }

    public void setSimpleSpellCode(String simpleSpellCode) {
        this.simpleSpellCode = simpleSpellCode == null ? null : simpleSpellCode.trim();
    }

    public Integer getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(Integer levelNumber) {
        this.levelNumber = levelNumber;
    }

    public Byte getIsUse() {
        return isUse;
    }

    public void setIsUse(Byte isUse) {
        this.isUse = isUse;
    }

    public Byte getIsReadOnly() {
        return isReadOnly;
    }

    public void setIsReadOnly(Byte isReadOnly) {
        this.isReadOnly = isReadOnly;
    }

    public Integer getChildSize() {
        return childSize;
    }

    public void setChildSize(Integer childSize) {
        this.childSize = childSize;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public Integer getOpenStutas() {
        return openStutas;
    }

    public void setOpenStutas(Integer openStutas) {
        this.openStutas = openStutas;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }
}