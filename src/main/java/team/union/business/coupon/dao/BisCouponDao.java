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
	
	/**
	 * 
	* 方法名:          getImgUrl
	* 方法功能描述:    根据objId获取图片路径
	* @param objId
	* @return  
	* @return:        String
	* @Author:        zh
	* @Create Date:   2017年4月30日
	 */
	public String getImgUrl(String objId);
	
	/**
	 * 
	* 方法名:  batchBan
	* 方法功能描述:    修改全部优惠券是否启用
	* @param couponIsBan
	* @return void    
	* @Author:   zh
	* @Create Date:   2017年5月3日
	 */
	public void batchBan(Integer couponIsBan);
	
	/**
	 * 
	* 方法名:  updateCouponState
	* 方法功能描述:    将过期优惠券状态改变
	* @return void    
	* @Author:   zh
	* @Create Date:   2017年5月3日
	 */
	public void updateCouponState();
	
	/**
	 * 
	* 方法名:  updateCouponOnline
	* 方法功能描述:   将达到上线条件的优惠券改为上线条件
	* @return void    
	* @Author:   zh
	* @Create Date:   2017年5月3日
	 */
	public void updateCouponOnline();
}
