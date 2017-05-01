package team.union.business.coupon.service;

import java.util.HashMap;
import java.util.Map;

import team.union.basic_data.com.interf.IService;
import team.union.basic_data.com.rs.BsgridVo;
import team.union.business.coupon.model.BisCouponUser;

public interface IBisCouponUserService extends IService<BisCouponUser> {

	public BsgridVo<HashMap<String, Object>> usePaging(Map<String, Object> parm,
			int curPage, int pageSize);
}
