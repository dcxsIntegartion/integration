package team.union.business.coupon.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import team.union.basic_data.com.rs.BsgridVo;
import team.union.basic_data.com.rs.Result;
import team.union.business.coupon.dao.BisCouponDao;
import team.union.business.coupon.model.BisCoupon;
import team.union.business.coupon.service.IBisCouponService;

@Repository
@Transactional(rollbackFor = Exception.class)
public class BisCouponServiceImpl implements IBisCouponService {

	@Autowired
	private BisCouponDao couponDao;
	@Override
	public BsgridVo<HashMap<String, Object>> paging(
			Map<String, Object> parm, int curPage, int pageSize) {
		PageHelper.startPage(curPage, pageSize);
		List<HashMap<String, Object>> data =  couponDao.selMap(parm);
		Page<HashMap<String, Object>> pageData = (Page<HashMap<String, Object>>) data;
		BsgridVo<HashMap<String, Object>> bsgridVo = new BsgridVo<HashMap<String, Object>>();
		bsgridVo.setCurPage(curPage);
		bsgridVo.setData(pageData);
		bsgridVo.setSuccess(true);
		bsgridVo.setTotalRows(pageData.getTotal());
		return bsgridVo;
	}

	@Override
	public List<HashMap<String, Object>> selMapLst(Map<String, Object> parm) {
		return null;
	}

	@Override
	public List<BisCoupon> selVoLst(Map<String, Object> parm) {
		return null;
	}

	@Override
	public Result selById(Long Id) {
		return null;
	}

	@Override
	public Result add(BisCoupon vo) {
		return null;
	}

	@Override
	public Result update(BisCoupon vo) {
		return null;
	}

	@Override
	public Result updateBySelective(BisCoupon vo) {
		return null;
	}

	@Override
	public Result delById(Long id) {
		return null;
	}

}
