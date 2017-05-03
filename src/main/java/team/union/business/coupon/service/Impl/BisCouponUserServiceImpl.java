package team.union.business.coupon.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import team.union.basic_data.com.cfg.BasicDataConfig.RESULT_STATE;
import team.union.basic_data.com.rs.BsgridVo;
import team.union.basic_data.com.rs.Result;
import team.union.business.coupon.dao.BisCouponUserDao;
import team.union.business.coupon.model.BisCouponUser;
import team.union.business.coupon.service.IBisCouponService;
import team.union.business.coupon.service.IBisCouponUserService;

@Repository
@Transactional(rollbackFor = Exception.class)
public class BisCouponUserServiceImpl implements IBisCouponUserService {

	@Autowired
	private BisCouponUserDao couponUserDao;
	@Autowired
	private IBisCouponService couponService;
	@Override
	public BsgridVo<HashMap<String, Object>> paging(Map<String, Object> parm,
			int curPage, int pageSize) {
		couponService.refreshCoupon();
		PageHelper.startPage(curPage, pageSize);
		List<HashMap<String, Object>> data =  couponUserDao.selMap(parm);
		Page<HashMap<String, Object>> pageData = (Page<HashMap<String, Object>>) data;
		BsgridVo<HashMap<String, Object>> bsgridVo = new BsgridVo<HashMap<String, Object>>();
		bsgridVo.setCurPage(curPage);
		bsgridVo.setData(pageData);
		bsgridVo.setSuccess(true);
		bsgridVo.setTotalRows(pageData.getTotal());
		return bsgridVo;
	}
	@Override
	public BsgridVo<HashMap<String, Object>> usePaging(Map<String, Object> parm, int curPage, int pageSize) {
		couponService.refreshCoupon();
		PageHelper.startPage(curPage, pageSize);
		List<HashMap<String, Object>> data =  couponUserDao.selUseMap(parm);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BisCouponUser> selVoLst(Map<String, Object> parm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result selById(Long Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result add(BisCouponUser vo) {
		Result result = new Result();
		if(null!=vo ){
			couponUserDao.insert(vo);
			result.setState(RESULT_STATE.SUCCESS.getNumber().toString());
			result.setMsg(RESULT_STATE.SUCCESS.getMsg());
		}else{
			result.setState(RESULT_STATE.FAIL.getNumber().toString());
			result.setMsg(RESULT_STATE.FAIL.getMsg());
		}
		return result;
	}

	@Override
	public Result update(BisCouponUser vo) {
		Result result = new Result();
		if(null!=vo &&
			null != couponUserDao.selectByPrimaryKey(vo.getPonUserId())){
			couponUserDao.updateByPrimaryKeySelective(vo);
			result.setState(RESULT_STATE.SUCCESS.getNumber().toString());
			result.setMsg(RESULT_STATE.SUCCESS.getMsg());
		}else{
			result.setState(RESULT_STATE.FAIL.getNumber().toString());
			result.setMsg(RESULT_STATE.FAIL.getMsg());
		}
		return result;
	}

	@Override
	public Result updateBySelective(BisCouponUser vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result delById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
