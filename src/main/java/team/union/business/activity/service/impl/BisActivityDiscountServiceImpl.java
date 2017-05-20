package team.union.business.activity.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import team.union.business.activity.dao.BisActivityDiscountDao;
import team.union.business.activity.dao.BisActivityCommodityRDao;
import team.union.business.activity.model.BisActivityDiscount;
import team.union.business.activity.model.BisActivityCommodityR;
import team.union.business.activity.service.IBisActivityDiscountService;
import team.union.business.activity.vo.BisActivityDiscountVo;
import team.union.business.com.cfg.BisConfig.ACTIVITY_STATUS;
import team.union.business.com.cfg.BisConfig.ACTIVITY_TYPE;
import team.union.business.com.cfg.BisConfig.RESULT_STATE;
import team.union.business.store.dao.BisStoreDao;
import team.union.business.store.model.BisStore;

/**
 * 特价活动业务层
 * @author Shuqianli
 * @date 2017年4月30日
 * Describe:
 */
@Repository
@Transactional(rollbackFor = Exception.class)
public class BisActivityDiscountServiceImpl implements IBisActivityDiscountService {

	@Autowired
	private BisActivityDiscountDao BisActivityDiscountDao;
	@Autowired
	private BisActivityCommodityRDao bisActivityCommodityRDao;
	@Autowired
	private BisStoreDao bisStoreDao;
	
	@Override
	public BsgridVo<HashMap<String, Object>> paging(Map<String, Object> parm, int curPage, int pageSize) {
		PageHelper.startPage(curPage, pageSize);
		List<HashMap<String, Object>> data =  BisActivityDiscountDao.selectPage(parm);
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
	public List<BisActivityDiscountVo> selVoLst(Map<String, Object> parm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result selById(Long Id) {
		Result result = new Result();
		HashMap<String, Object> dataMap = new HashMap<>();
		HashMap<String, Object> selectMap = new HashMap<>();
		try {
			BisActivityDiscount bargain = BisActivityDiscountDao.selectByPrimaryKey(Id);
			Long storeId = bargain.getActivityStoreId();
			//活动时间
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			bargain.setActivityStartTimeStr(format.format(bargain.getActivityStartTime()));
			bargain.setActivityEndTimeStr(format.format(bargain.getActivityEndTime()));
			//活动店铺
			BisStore bisStore = bisStoreDao.selectByPrimaryKey(storeId);
			bargain.setActivityStoreName(bisStore.getStoreName());
			dataMap.put("activity", bargain);
			//关联商品
			selectMap.put("activityId", Id);
			selectMap.put("activityType", ACTIVITY_TYPE.DISCOUNT.getNumber());
			List<HashMap<String, Object>> activityCommodityR =  bisActivityCommodityRDao.baseSelect(selectMap);
			//只取关联商品id
			List<Long> activityCommodityId = new ArrayList<>();
			for (HashMap<String, Object> hashMap : activityCommodityR) {
				Long commodityId = (Long) hashMap.get("commodityId");
				activityCommodityId.add(commodityId);
			}
			dataMap.put("activityCommodityId", activityCommodityId);
			result.setData(dataMap);
			result.setMsg(RESULT_STATE.SUCCESS.getMsg());
			result.setState(RESULT_STATE.SUCCESS.getNumber().toString());
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg(RESULT_STATE.FAIL.getMsg());
			result.setState(RESULT_STATE.FAIL.getNumber().toString());
		}
		return result;
	}

	@Override
	public Result add(BisActivityDiscountVo vo) {
		Result result = new Result();
		try {
			//活动图片
			BisActivityDiscount bisActivityDiscount = vo.getBisActivityDiscount();
			//活动时间
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			bisActivityDiscount.setActivityStartTime(format.parse(bisActivityDiscount.getActivityStartTimeStr()));
			bisActivityDiscount.setActivityEndTime(format.parse(bisActivityDiscount.getActivityEndTimeStr()));
			bisActivityDiscount.setActivityCreatTime(new Date());
			bisActivityDiscount.setActivityStatus(ACTIVITY_STATUS.CLOSE.getNumber());//活动状态
			//添加活动
			BisActivityDiscountDao.insert(bisActivityDiscount);
			//添加活动-商品关联
			List<BisActivityCommodityR> bisActivityCommodityRList = vo.getBisActivityCommodityRList();
			for (BisActivityCommodityR bisActivityCommodityR : bisActivityCommodityRList) {
				bisActivityCommodityR.setActivityId(bisActivityDiscount.getId());//活动id
				bisActivityCommodityR.setActivityType(ACTIVITY_TYPE.DISCOUNT.getNumber());//活动类型
				bisActivityCommodityRDao.insert(bisActivityCommodityR);
			}
			result.setData(vo.getBisActivityDiscount());
			result.setMsg(RESULT_STATE.SUCCESS.getMsg());
			result.setState(RESULT_STATE.SUCCESS.getNumber().toString());
		} catch (Exception e) {
			result.setMsg(RESULT_STATE.FAIL.getMsg());
			result.setState(RESULT_STATE.FAIL.getNumber().toString());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result update(BisActivityDiscountVo vo) {
		Result result = new Result();
		try {
			BisActivityDiscount bisActivityDiscount = vo.getBisActivityDiscount();
			BisActivityDiscountDao.updateByPrimaryKeySelective(bisActivityDiscount);
			//删除之前的关联商品
			HashMap<String, Object> deleteMap = new HashMap<>();
			deleteMap.put("avtivityId", bisActivityDiscount.getId());
			deleteMap.put("avtivityType", ACTIVITY_TYPE.DISCOUNT.getNumber());
			bisActivityCommodityRDao.deleteByactivity(deleteMap);
			//添加活动-商品关联
			List<BisActivityCommodityR> bisActivityCommodityRList = vo.getBisActivityCommodityRList();
			for (BisActivityCommodityR bisActivityCommodityR : bisActivityCommodityRList) {
				bisActivityCommodityR.setActivityId(bisActivityDiscount.getId());//活动id
				bisActivityCommodityR.setActivityType(ACTIVITY_TYPE.DISCOUNT.getNumber());//活动类型
				bisActivityCommodityRDao.insert(bisActivityCommodityR);
			}
			result.setData(vo.getBisActivityDiscount());
			result.setMsg(RESULT_STATE.SUCCESS.getMsg());
			result.setState(RESULT_STATE.SUCCESS.getNumber().toString());
		} catch (Exception e) {
			result.setMsg(RESULT_STATE.FAIL.getMsg());
			result.setState(RESULT_STATE.FAIL.getNumber().toString());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result updateBySelective(BisActivityDiscountVo vo) {
		return null;
	}

	@Override
	public Result delById(Long id) {
		Result result = new Result();
		try {
			BisActivityDiscountDao.deleteByPrimaryKey(id);
			result.setMsg(RESULT_STATE.SUCCESS.getMsg());
			result.setState(RESULT_STATE.SUCCESS.getNumber().toString());
		} catch (Exception e) {
			result.setMsg(RESULT_STATE.FAIL.getMsg());
			result.setState(RESULT_STATE.FAIL.getNumber().toString());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result updateStatus(BisActivityDiscount vo) {
		Result result = new Result();
		BisActivityDiscountDao.updateByPrimaryKeySelective(vo);
		result.setState(RESULT_STATE.SUCCESS.getNumber().toString());
		return result;
	}

}
