package team.union.business.commodity.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BisCommodity implements Serializable{
    /**  
	* 字段:      字段名称
	* @Fields serialVersionUID : TODO 
	*/
	private static final long serialVersionUID = 1L;

	private Long id;							//

    private String commodityName;				//商品名称/标题

    private BigDecimal commodityPrice;			//商品现价

    private BigDecimal commodityOldPrice;		//商品原价

    private Long commodityStoreId;				//商品所属店铺

    private Integer commodityNum;				//商品库存

    private BigDecimal commodityQuality;		//商品重量

    private String commodityPic;				//商品图片：封面
    
    private String commodityPic2;				//商品图片：介绍图片1
    
    private String commodityPic3;				//商品图片：介绍图片2

    private Integer commoditySortNum;			//商品排序，越大越靠前

    private String commodityIntroduction;		//商品描述

    private Integer commodityStatus;			//商品状态 1：上架，2：下架

    private Integer homepageShow;				//是否首页显示，若已满则按照排序大小

    private Date timingOff;						//定时下架 时间

    private Date timingBegain;					//定时上架时间

    private Integer isTiming;					//是否定时上下架	1:是  0：否

    private Date commodityCreatTime;			//创建时间
    
    private Long commodityTypeId;			//所属分类
    
    private Integer commodityVolume;			//商品容积  ml
    
    private String commodityNumber;				//商品货号
    
    private Integer commodityNumDecrease;		//库存计数方式 0：拍下减库存（默认） 1：付款减库存
    
    private Integer commoditySaleNum;			//实际销量
    
    private Integer commoditySaleAdd;			//基础销量  （虚构的额外销量）
    
    private Integer commodityDel;				//是否已删除 0：正常 1：已删除

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
		this.commodityName = commodityName;
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
		this.commodityPic = commodityPic;
	}
	
	public String getCommodityPic2() {
		return commodityPic2;
	}

	public void setCommodityPic2(String commodityPic2) {
		this.commodityPic2 = commodityPic2;
	}

	public String getCommodityPic3() {
		return commodityPic3;
	}

	public void setCommodityPic3(String commodityPic3) {
		this.commodityPic3 = commodityPic3;
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
		this.commodityIntroduction = commodityIntroduction;
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

	public Date getCommodityCreatTime() {
		return commodityCreatTime;
	}

	public void setCommodityCreatTime(Date commodityCreatTime) {
		this.commodityCreatTime = commodityCreatTime;
	}

	public Long getCommodityTypeId() {
		return commodityTypeId;
	}

	public void setCommodityTypeId(Long commodityTypeId) {
		this.commodityTypeId = commodityTypeId;
	}

	public Integer getCommodityVolume() {
		return commodityVolume;
	}

	public void setCommodityVolume(Integer commodityVolume) {
		this.commodityVolume = commodityVolume;
	}

	public String getCommodityNumber() {
		return commodityNumber;
	}

	public void setCommodityNumber(String commodityNumber) {
		this.commodityNumber = commodityNumber;
	}

	public Integer getCommodityNumDecrease() {
		return commodityNumDecrease;
	}

	public void setCommodityNumDecrease(Integer commodityNumDecrease) {
		this.commodityNumDecrease = commodityNumDecrease;
	}

	public Integer getCommoditySaleNum() {
		return commoditySaleNum;
	}

	public void setCommoditySaleNum(Integer commoditySaleNum) {
		this.commoditySaleNum = commoditySaleNum;
	}

	public Integer getCommoditySaleAdd() {
		return commoditySaleAdd;
	}

	public void setCommoditySaleAdd(Integer commoditySaleAdd) {
		this.commoditySaleAdd = commoditySaleAdd;
	}

	public Integer getCommodityDel() {
		return commodityDel;
	}

	public void setCommodityDel(Integer commodityDel) {
		this.commodityDel = commodityDel;
	}
    
}