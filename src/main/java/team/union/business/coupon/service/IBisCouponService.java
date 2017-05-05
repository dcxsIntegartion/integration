package team.union.business.coupon.service;

import team.union.basic_data.com.interf.IService;
import team.union.basic_data.com.rs.Result;
import team.union.business.coupon.model.BisCoupon;

public interface IBisCouponService extends IService<BisCoupon> {

	public String getImgUrl(String objId);
	
	public Result batchBan(Integer couponIsBan);
	
	/**
	 * 
	* 方法名:  refreshCoupon
	* 方法功能描述:    将达到 上线 或 过期条件的  优惠券状态改变
	* @return void    
	* @Author:   zh
	* @Create Date:   2017年5月3日
	 */
	public void refreshCoupon();
}
