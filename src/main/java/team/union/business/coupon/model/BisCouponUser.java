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
	private String ponUserId;
	private long userId;		//用户ID
	private long couponId;		//优惠券ID
	private Date receiveTime;	//领取时间
	private Date useTime;		//使用时间
	private long orderId;   	//使用的订单id
	
	
	public String getPonUserId() {
		return ponUserId;
	}
	public void setPonUserId(String ponUserId) {
		this.ponUserId = ponUserId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getCouponId() {
		return couponId;
	}
	public void setCouponId(long couponId) {
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
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
}
