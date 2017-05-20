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
import team.union.business.activity.dao.BisActivitySeckilDao;
import team.union.business.activity.dao.BisActivityCommodityRDao;
import team.union.business.activity.model.BisActivitySeckil;
import team.union.business.activity.model.BisActivityCommodityR;
import team.union.business.activity.service.IBisActivitySeckilService;
import team.union.business.activity.vo.BisActivitySeckilVo;
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
public class BisActivitySeckilServiceImpl implements IBisActivitySeckilService {

	@Autowired
	private BisActivitySeckilDao BisActivitySeckilDao;
	@Autowired
	private BisActivityCommodityRDao bisActivityCommodityRDao;
	@Autowired
	private BisStoreDao bisStoreDao;
	
	@Override
	public BsgridVo<HashMap<String, Object>> paging(Map<String, Object> parm, int curPage, int pageSize) {
		PageHelper.startPage(curPage, pageSize);
		List<HashMap<String, Object>> data =  BisActivitySeckilDao.selectPage(parm);
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
	public List<BisActivitySeckilVo> selVoLst(Map<String, Object> parm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result selById(Long Id) {
		Result result = new Result();
		HashMap<String, Object> dataMap = new HashMap<>();
		HashMap<String, Object> selectMap = new HashMap<>();
		try {
			BisActivitySeckil bargain = BisActivitySeckilDao.selectByPrimaryKey(Id);
			Long storeId = bargain.getActivityStoreId();
			//活动时间
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			bargain.setActivityDayTimeStr(format.format(bargain.getActivityDayTime()));
//			bargain.setActivityEndTimeStr(format.format(bargain.getActivityEndTime()));
			//活动店铺
			BisStore bisStore = bisStoreDao.selectByPrimaryKey(storeId);
			bargain.setActivityStoreName(bisStore.getStoreName());
			dataMap.put("activity", bargain);
			//关联商品
			selectMap.put("activityId", Id);
			selectMap.put("activityType", ACTIVITY_TYPE.SECKILL.getNumber());
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
	public Result add(BisActivitySeckilVo vo) {
		Result result = new Result();
		try {
			//活动图片
			BisActivitySeckil BisActivitySeckil = vo.getBisActivitySeckil();
			//活动时间
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			BisActivitySeckil.setActivityDayTime(format.parse(BisActivitySeckil.getActivityDayTimeStr()));
			BisActivitySeckil.setActivityCreatTime(new Date());
			BisActivitySeckil.setActivityStatus(ACTIVITY_STATUS.CLOSE.getNumber());//活动状态
			//添加活动
			BisActivitySeckilDao.insert(BisActivitySeckil);
			//添加活动-商品关联
			List<BisActivityCommodityR> bisActivityCommodityRList = vo.getBisActivityCommodityRList();
			for (BisActivityCommodityR bisActivityCommodityR : bisActivityCommodityRList) {
				bisActivityCommodityR.setActivityId(BisActivitySeckil.getId());//活动id
				bisActivityCommodityR.setActivityType(ACTIVITY_TYPE.SECKILL.getNumber());//活动类型
				bisActivityCommodityRDao.insert(bisActivityCommodityR);
			}
			result.setData(vo.getBisActivitySeckil());
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
	public Result update(BisActivitySeckilVo vo) {
		Result result = new Result();
		try {
			BisActivitySeckil BisActivitySeckil = vo.getBisActivitySeckil();
			BisActivitySeckilDao.updateByPrimaryKeySelective(BisActivitySeckil);
			//删除之前的关联商品
			HashMap<String, Object> deleteMap = new HashMap<>();
			deleteMap.put("avtivityId", BisActivitySeckil.getId());
			deleteMap.put("avtivityType", ACTIVITY_TYPE.SECKILL.getNumber());
			bisActivityCommodityRDao.deleteByactivity(deleteMap);
			//添加活动-商品关联
			List<BisActivityCommodityR> bisActivityCommodityRList = vo.getBisActivityCommodityRList();
			for (BisActivityCommodityR bisActivityCommodityR : bisActivityCommodityRList) {
				bisActivityCommodityR.setActivityId(BisActivitySeckil.getId());//活动id
				bisActivityCommodityR.setActivityType(ACTIVITY_TYPE.SECKILL.getNumber());//活动类型
				bisActivityCommodityRDao.insert(bisActivityCommodityR);
			}
			result.setData(vo.getBisActivitySeckil());
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
	public Result updateBySelective(BisActivitySeckilVo vo) {
		return null;
	}

	@Override
	public Result delById(Long id) {
		Result result = new Result();
		try {
			BisActivitySeckilDao.deleteByPrimaryKey(id);
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
	public Result updateStatus(BisActivitySeckil vo) {
		Result result = new Result();
		BisActivitySeckilDao.updateByPrimaryKeySelective(vo);
		result.setState(RESULT_STATE.SUCCESS.getNumber().toString());
		return result;
	}

}
