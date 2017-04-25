package team.union.business.coupon.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
* <p>标题: BisCoupon.java</p>
* <p>业务描述:</p>
* <p>类描述:优惠券实体类</p>
* @author zh
* @date 2017年4月25日 
* @version V1.0
 */
public class BisCoupon implements Serializable{

	/**  
	* 字段:      字段名称
	* @Fields serialVersionUID : TODO 
	*/
	private static final long serialVersionUID = 1L;
	private long couponId;
	private float couponMoney;			//优惠券金额
	private Integer couponBaseLine;		//最低消费金额
	private Date couponValidityStart;	//有效开始时间
	private Date couponValidityEnd;		//有效结束时间
	private Date couponReceiveEnd;		//领取截止时间
	private Integer couponGrantNum;     //发放数量
	private Integer couponReceiveTimes;	//没人可领取次数
	private Integer couponEveryNum;		//每次可领取数量
	private Integer couponSort;			//排序
	private String couponDes;			//优惠券描述
	private Integer couponType;			//类型
	private String couponShareDes;		//分享描述
	private String couponShareTitle;	//分享标题
	private String couponShareImg;		//分享图片
	private String couponName;			//优惠券名称
	private Integer couponStatus;		//状态
	private Integer couponRemainNum;	//剩余数量
	
	
	public long getCouponId() {
		return couponId;
	}
	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}
	public float getCouponMoney() {
		return couponMoney;
	}
	public void setCouponMoney(float couponMoney) {
		this.couponMoney = couponMoney;
	}
	public Integer getCouponBaseLine() {
		return couponBaseLine;
	}
	public void setCouponBaseLine(Integer couponBaseLine) {
		this.couponBaseLine = couponBaseLine;
	}
	public Date getCouponValidityStart() {
		return couponValidityStart;
	}
	public void setCouponValidityStart(Date couponValidityStart) {
		this.couponValidityStart = couponValidityStart;
	}
	public Date getCouponValidityEnd() {
		return couponValidityEnd;
	}
	public void setCouponValidityEnd(Date couponValidityEnd) {
		this.couponValidityEnd = couponValidityEnd;
	}
	public Date getCouponReceiveEnd() {
		return couponReceiveEnd;
	}
	public void setCouponReceiveEnd(Date couponReceiveEnd) {
		this.couponReceiveEnd = couponReceiveEnd;
	}
	public Integer getCouponGrantNum() {
		return couponGrantNum;
	}
	public void setCouponGrantNum(Integer couponGrantNum) {
		this.couponGrantNum = couponGrantNum;
	}
	public Integer getCouponReceiveTimes() {
		return couponReceiveTimes;
	}
	public void setCouponReceiveTimes(Integer couponReceiveTimes) {
		this.couponReceiveTimes = couponReceiveTimes;
	}
	public Integer getCouponEveryNum() {
		return couponEveryNum;
	}
	public void setCouponEveryNum(Integer couponEveryNum) {
		this.couponEveryNum = couponEveryNum;
	}
	public Integer getCouponSort() {
		return couponSort;
	}
	public void setCouponSort(Integer couponSort) {
		this.couponSort = couponSort;
	}
	public String getCouponDes() {
		return couponDes;
	}
	public void setCouponDes(String couponDes) {
		this.couponDes = couponDes;
	}
	public Integer getCouponType() {
		return couponType;
	}
	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}
	public String getCouponShareDes() {
		return couponShareDes;
	}
	public void setCouponShareDes(String couponShareDes) {
		this.couponShareDes = couponShareDes;
	}
	public String getCouponShareTitle() {
		return couponShareTitle;
	}
	public void setCouponShareTitle(String couponShareTitle) {
		this.couponShareTitle = couponShareTitle;
	}
	public String getCouponShareImg() {
		return couponShareImg;
	}
	public void setCouponShareImg(String couponShareImg) {
		this.couponShareImg = couponShareImg;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public Integer getCouponStatus() {
		return couponStatus;
	}
	public void setCouponStatus(Integer couponStatus) {
		this.couponStatus = couponStatus;
	}
	public Integer getCouponRemainNum() {
		return couponRemainNum;
	}
	public void setCouponRemainNum(Integer couponRemainNum) {
		this.couponRemainNum = couponRemainNum;
	} 
	
}
