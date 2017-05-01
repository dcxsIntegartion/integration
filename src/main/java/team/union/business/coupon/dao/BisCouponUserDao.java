package team.union.business.coupon.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.union.business.com.interf.IDao;
import team.union.business.coupon.model.BisCouponUser;

public interface BisCouponUserDao extends IDao<BisCouponUser> {

	public List<HashMap<String, Object>> selMap(Map<String, Object> parm);
	
	public List<HashMap<String, Object>> selUseMap(Map<String, Object> parm);
}
