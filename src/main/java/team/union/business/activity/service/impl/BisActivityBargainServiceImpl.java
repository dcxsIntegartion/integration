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
import team.union.business.activity.dao.BisActivityBargainDao;
import team.union.business.activity.dao.BisActivityCommodityRDao;
import team.union.business.activity.model.BisActivityBargain;
import team.union.business.activity.model.BisActivityCommodityR;
import team.union.business.activity.service.IBisActivityBargainService;
import team.union.business.activity.vo.BisActivityBargainVo;
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
public class BisActivityBargainServiceImpl implements IBisActivityBargainService {

	@Autowired
	private BisActivityBargainDao bisActivityBargainDao;
	@Autowired
	private BisActivityCommodityRDao bisActivityCommodityRDao;
	@Autowired
	private BisStoreDao bisStoreDao;
	
	
	public BsgridVo<HashMap<String, Object>> paging(Map<String, Object> parm, int curPage, int pageSize) {
		PageHelper.startPage(curPage, pageSize);
		List<HashMap<String, Object>> data =  bisActivityBargainDao.selectPage(parm);
		Page<HashMap<String, Object>> pageData = (Page<HashMap<String, Object>>) data;
		BsgridVo<HashMap<String, Object>> bsgridVo = new BsgridVo<HashMap<String, Object>>();
		bsgridVo.setCurPage(curPage);
		bsgridVo.setData(pageData);
		bsgridVo.setSuccess(true);
		bsgridVo.setTotalRows(pageData.getTotal());
		return bsgridVo;
	}

	
	public List<HashMap<String, Object>> selMapLst(Map<String, Object> parm) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<BisActivityBargainVo> selVoLst(Map<String, Object> parm) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Result selById(Long Id) {
		Result result = new Result();
		HashMap<String, Object> dataMap = new HashMap<>();
		HashMap<String, Object> selectMap = new HashMap<>();
		try {
			BisActivityBargain bargain = bisActivityBargainDao.selectByPrimaryKey(Id);
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
			selectMap.put("activityType", ACTIVITY_TYPE.BARGAIN.getNumber());
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

	
	public Result add(BisActivityBargainVo vo) {
		Result result = new Result();
		try {
			//活动图片
			BisActivityBargain bisActivityBargain = vo.getBisActivityBargain();
			//活动时间
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			bisActivityBargain.setActivityStartTime(format.parse(bisActivityBargain.getActivityStartTimeStr()));
			bisActivityBargain.setActivityEndTime(format.parse(bisActivityBargain.getActivityEndTimeStr()));
			bisActivityBargain.setActivityCreatTime(new Date());
			bisActivityBargain.setActivityStatus(ACTIVITY_STATUS.CLOSE.getNumber());//活动状态
			//添加活动
			bisActivityBargainDao.insert(bisActivityBargain);
			//添加活动-商品关联
			List<BisActivityCommodityR> bisActivityCommodityRList = vo.getBisActivityCommodityRList();
			for (BisActivityCommodityR bisActivityCommodityR : bisActivityCommodityRList) {
				bisActivityCommodityR.setActivityId(bisActivityBargain.getId());//活动id
				bisActivityCommodityR.setActivityType(ACTIVITY_TYPE.BARGAIN.getNumber());//活动类型
				bisActivityCommodityRDao.insert(bisActivityCommodityR);
			}
			result.setData(vo.getBisActivityBargain());
			result.setMsg(PROMPT.SUCCESS.getMsg());
			result.setState(PROMPT.SUCCESS.getNo());
		} catch (Exception e) {
			result.setMsg(PROMPT.FAIL.getMsg());
			result.setState(PROMPT.FAIL.getNo());
			e.printStackTrace();
		}
		return result;
	}

	
	public Result update(BisActivityBargainVo vo) {
		Result result = new Result();
		try {
			BisActivityBargain bisActivityBargain = vo.getBisActivityBargain();
			bisActivityBargainDao.updateByPrimaryKeySelective(bisActivityBargain);
			//删除之前的关联商品
			HashMap<String, Object> deleteMap = new HashMap<>();
			deleteMap.put("avtivityId", bisActivityBargain.getId());
			deleteMap.put("avtivityType", ACTIVITY_TYPE.BARGAIN.getNumber());
			bisActivityCommodityRDao.deleteByactivity(deleteMap);
			//添加活动-商品关联
			List<BisActivityCommodityR> bisActivityCommodityRList = vo.getBisActivityCommodityRList();
			for (BisActivityCommodityR bisActivityCommodityR : bisActivityCommodityRList) {
				bisActivityCommodityR.setActivityId(bisActivityBargain.getId());//活动id
				bisActivityCommodityR.setActivityType(ACTIVITY_TYPE.BARGAIN.getNumber());//活动类型
				bisActivityCommodityRDao.insert(bisActivityCommodityR);
			}
			result.setData(vo.getBisActivityBargain());
			result.setMsg(PROMPT.SUCCESS.getMsg());
			result.setState(PROMPT.SUCCESS.getNo());
		} catch (Exception e) {
			result.setMsg(PROMPT.FAIL.getMsg());
			result.setState(PROMPT.FAIL.getNo());
			e.printStackTrace();
		}
		return result;
	}

	
	public Result updateBySelective(BisActivityBargainVo vo) {
		return null;
	}

	
	public Result delById(Long id) {
		Result result = new Result();
		try {
			bisActivityBargainDao.deleteByPrimaryKey(id);
			result.setMsg(PROMPT.SUCCESS.getMsg());
			result.setState(PROMPT.SUCCESS.getNo());
		} catch (Exception e) {
			result.setMsg(PROMPT.FAIL.getMsg());
			result.setState(PROMPT.FAIL.getNo());
			e.printStackTrace();
		}
		return result;
	}

	
	public Result updateStatus(BisActivityBargain vo) {
		Result result = new Result();
		bisActivityBargainDao.updateByPrimaryKeySelective(vo);
		result.setState(PROMPT.SUCCESS.getNo());
		return result;
	}

}
