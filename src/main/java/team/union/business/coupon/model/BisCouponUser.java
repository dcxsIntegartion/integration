package team.union.business.coupon.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
* <p>标题: BisCouponUser.java</p>
* <p>业务描述:</p>
* <p>类描述:优惠券领取使用实体</p>
* @author zh
* @date 2017年4月25日 
* @version V1.0
 */
public class BisCouponUser implements Serializable{

	/**  
	* 字段:      字段名称
	* @Fields serialVersionUID : TODO 
	*/
	private static final long serialVersionUID = 1L;
	private Long ponUserId;
	private Long userId;		//用户ID
	private Long couponId;		//优惠券ID
	private Date receiveTime;	//领取时间
	private Date useTime;		//使用时间
	private Long orderId;   	//使用的订单id
	private Long usePersonId;	//使用者id
	
	
	public Long getPonUserId() {
		return ponUserId;
	}
	public void setPonUserId(Long ponUserId) {
		this.ponUserId = ponUserId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getCouponId() {
		return couponId;
	}
	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}
	public Date getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}
	public Date getUseTime() {
		return useTime;
	}
	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getUserPersonId() {
		return usePersonId;
	}
	public void setUserPersonId(Long usePersonId) {
		this.usePersonId = usePersonId;
	}
}
