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
import team.union.business.activity.dao.BisActivityTurntableDao;
import team.union.business.activity.dao.BisActivityCommodityRDao;
import team.union.business.activity.model.BisActivityTurntable;
import team.union.business.activity.model.BisActivityCommodityR;
import team.union.business.activity.service.IBisActivityTurntableService;
import team.union.business.activity.vo.BisActivityTurntableVo;
import team.union.business.com.cfg.BisConfig.ACTIVITY_STATUS;
import team.union.business.com.cfg.BisConfig.ACTIVITY_TYPE;
import team.union.business.store.dao.BisStoreDao;
import team.union.business.store.model.BisStore;
import team.union.sys_sp.com.cfg.PromptMsgConfig.PROMPT;

/**
 * 特价活动业务层
 * @author Shuqianli
 * @date 2017年4月30日
 * Describe:
 */
@Repository
@Transactional(rollbackFor = Exception.class)
public class BisActivityTurntableServiceImpl implements IBisActivityTurntableService {

	@Autowired
	private BisActivityTurntableDao BisActivityTurntableDao;
	@Autowired
	private BisActivityCommodityRDao bisActivityCommodityRDao;
	@Autowired
	private BisStoreDao bisStoreDao;
	
	@Override
	public BsgridVo<HashMap<String, Object>> paging(Map<String, Object> parm, int curPage, int pageSize) {
		PageHelper.startPage(curPage, pageSize);
		List<HashMap<String, Object>> data =  BisActivityTurntableDao.selectPage(parm);
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
	public List<BisActivityTurntableVo> selVoLst(Map<String, Object> parm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result selById(Long Id) {
		Result result = new Result();
		HashMap<String, Object> dataMap = new HashMap<>();
		HashMap<String, Object> selectMap = new HashMap<>();
		try {
			BisActivityTurntable bargain = BisActivityTurntableDao.selectByPrimaryKey(Id);
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
			selectMap.put("activityType", ACTIVITY_TYPE.TURNTABLE.getNumber());
			List<HashMap<String, Object>> activityCommodityR =  bisActivityCommodityRDao.baseSelect(selectMap);
			//只取关联商品id
			List<Long> activityCommodityId = new ArrayList<>();
			for (HashMap<String, Object> hashMap : activityCommodityR) {
				Long commodityId = (Long) hashMap.get("commodityId");
				activityCommodityId.add(commodityId);
			}
			dataMap.put("activityCommodityId", activityCommodityId);
			result.setData(dataMap);
			result.setMsg(PROMPT.SUCCESS.getMsg());
			result.setState(PROMPT.SUCCESS.getNo());
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg(PROMPT.FAIL.getMsg());
			result.setState(PROMPT.FAIL.getNo());
		}
		return result;
	}

	@Override
	public Result add(BisActivityTurntableVo vo) {
		Result result = new Result();
		try {
			//活动图片
			BisActivityTurntable BisActivityTurntable = vo.getBisActivityTurntable();
			//活动时间
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			BisActivityTurntable.setActivityStartTime(format.parse(BisActivityTurntable.getActivityStartTimeStr()));
			BisActivityTurntable.setActivityEndTime(format.parse(BisActivityTurntable.getActivityEndTimeStr()));
			BisActivityTurntable.setActivityCreatTime(new Date());
			BisActivityTurntable.setActivityStatus(ACTIVITY_STATUS.CLOSE.getNumber());//活动状态
			//添加活动
			BisActivityTurntableDao.insert(BisActivityTurntable);
			//添加活动-商品关联
			List<BisActivityCommodityR> bisActivityCommodityRList = vo.getBisActivityCommodityRList();
			for (BisActivityCommodityR bisActivityCommodityR : bisActivityCommodityRList) {
				bisActivityCommodityR.setActivityId(BisActivityTurntable.getId());//活动id
				bisActivityCommodityR.setActivityType(ACTIVITY_TYPE.TURNTABLE.getNumber());//活动类型
				bisActivityCommodityRDao.insert(bisActivityCommodityR);
			}
			result.setData(vo.getBisActivityTurntable());
			result.setMsg(PROMPT.SUCCESS.getMsg());
			result.setState(PROMPT.SUCCESS.getNo());
		} catch (Exception e) {
			result.setMsg(PROMPT.FAIL.getMsg());
			result.setState(PROMPT.FAIL.getNo());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result update(BisActivityTurntableVo vo) {
		Result result = new Result();
		try {
			BisActivityTurntable BisActivityTurntable = vo.getBisActivityTurntable();
			BisActivityTurntableDao.updateByPrimaryKeySelective(BisActivityTurntable);
			//删除之前的关联商品
			HashMap<String, Object> deleteMap = new HashMap<>();
			deleteMap.put("avtivityId", BisActivityTurntable.getId());
			deleteMap.put("avtivityType", ACTIVITY_TYPE.TURNTABLE.getNumber());
			bisActivityCommodityRDao.deleteByactivity(deleteMap);
			//添加活动-商品关联
			List<BisActivityCommodityR> bisActivityCommodityRList = vo.getBisActivityCommodityRList();
			for (BisActivityCommodityR bisActivityCommodityR : bisActivityCommodityRList) {
				bisActivityCommodityR.setActivityId(BisActivityTurntable.getId());//活动id
				bisActivityCommodityR.setActivityType(ACTIVITY_TYPE.TURNTABLE.getNumber());//活动类型
				bisActivityCommodityRDao.insert(bisActivityCommodityR);
			}
			result.setData(vo.getBisActivityTurntable());
			result.setMsg(PROMPT.SUCCESS.getMsg());
			result.setState(PROMPT.SUCCESS.getNo());
		} catch (Exception e) {
			result.setMsg(PROMPT.FAIL.getMsg());
			result.setState(PROMPT.FAIL.getNo());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result updateBySelective(BisActivityTurntableVo vo) {
		return null;
	}

	@Override
	public Result delById(Long id) {
		Result result = new Result();
		try {
			BisActivityTurntableDao.deleteByPrimaryKey(id);
			result.setMsg(PROMPT.SUCCESS.getMsg());
			result.setState(PROMPT.SUCCESS.getNo());
		} catch (Exception e) {
			result.setMsg(PROMPT.FAIL.getMsg());
			result.setState(PROMPT.FAIL.getNo());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result updateStatus(BisActivityTurntable vo) {
		Result result = new Result();
		BisActivityTurntableDao.updateByPrimaryKeySelective(vo);
		result.setState(PROMPT.SUCCESS.getNo());
		return result;
	}

}
