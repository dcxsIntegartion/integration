package team.union.business.coupon.service;

import team.union.basic_data.com.interf.IService;
import team.union.basic_data.com.rs.Result;
import team.union.business.coupon.model.BisCoupon;

public interface IBisCouponService extends IService<BisCoupon> {

	public String getImgUrl(String objId);
	
	public Result batchBan(Integer couponIsBan);
}
