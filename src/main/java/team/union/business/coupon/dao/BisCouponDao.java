package team.union.business.coupon.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.union.business.com.interf.IDao;
import team.union.business.coupon.model.BisCoupon;

public interface BisCouponDao extends IDao<BisCoupon> {

	/**
	 * 
	* 方法名:  selVo
	* 方法功能描述:    分页查询优惠券
	* @param parm
	* @return
	* @Author:   zh
	* @Create Date:   2017年4月27日
	 */
	public List<HashMap<String, Object>> selMap(Map<String, Object> parm);
}
