package team.union.business.commodity.service.Impl;

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
import team.union.business.activity.dao.BisActivityCommodityRDao;
import team.union.business.activity.model.BisActivityCommodityR;
import team.union.business.activity.model.BisActivityCommodityRExample;
import team.union.business.commodity.dao.BisCommodityDao;
import team.union.business.commodity.model.BisCommodity;
import team.union.business.commodity.service.IBisCommodityService;

@Repository
@Transactional(rollbackFor = Exception.class)
public class BisCommodityServiceImpl implements IBisCommodityService {

	@Autowired
	private BisCommodityDao commodityDao;
	@Autowired
	private BisActivityCommodityRDao bisActivityCommodityRDao;
	
	@Override
	public BsgridVo<HashMap<String, Object>> paging(Map<String, Object> parm, int curPage, int pageSize) {
		this.refresh();
		PageHelper.startPage(curPage, pageSize);
		List<HashMap<String, Object>> data =  commodityDao.selMap(parm);
		Page<HashMap<String, Object>> pageData = (Page<HashMap<String, Object>>) data;
		BsgridVo<HashMap<String, Object>> bsgridVo = new BsgridVo<HashMap<String, Object>>();
		bsgridVo.setCurPage(curPage);
		bsgridVo.setData(pageData);
		bsgridVo.setSuccess(true);
		bsgridVo.setTotalRows(pageData.getTotal());
		return bsgridVo;
	}
	@Override
	public Result selById(Long Id) {
		this.refresh();
		Result result = new Result();
		BisCommodity vo = commodityDao.selectByPrimaryKey(Id);
		if(vo!=null){
			result.setState(RESULT_STATE.SUCCESS.getNumber().toString());
			result.setMsg(RESULT_STATE.SUCCESS.getMsg());
			result.setData(vo);
		}else{
			result.setState(RESULT_STATE.FAIL.getNumber().toString());
			result.setMsg(RESULT_STATE.FAIL.getMsg());
		}
		return result;
	}
	@Override
	public Result add(BisCommodity vo) {
		Result result = new Result();
		if(null!=vo && null == vo.getId()){
			commodityDao.insert(vo);
			result.setState(RESULT_STATE.SUCCESS.getNumber().toString());
			result.setMsg(RESULT_STATE.SUCCESS.getMsg());
		}else{
			result.setState(RESULT_STATE.FAIL.getNumber().toString());
			result.setMsg(RESULT_STATE.FAIL.getMsg());
		}
		return result;
	}
	@Override
	public Result update(BisCommodity vo) {
		Result result = new Result();
		if(null!=vo && null != commodityDao.selectByPrimaryKey(vo.getId())){
			commodityDao.updateOne(vo);
			result.setState(RESULT_STATE.SUCCESS.getNumber().toString());
			result.setMsg(RESULT_STATE.SUCCESS.getMsg());
		}else{
			result.setState(RESULT_STATE.FAIL.getNumber().toString());
			result.setMsg(RESULT_STATE.FAIL.getMsg());
		}
		return result;
	}
	//批量删除
	@Override
	public Result batchDel(List<Long> ids) {
		Result result = new Result();
		if (null != ids && ids.size() > 0) {
			Map<String, Object> map = new HashMap<>();
			map.put("del", "1");
			map.put("data", ids);
			commodityDao.batchOpearate(map);
			result.setState(RESULT_STATE.SUCCESS.getNumber().toString());
			result.setMsg(RESULT_STATE.SUCCESS.getMsg());
		}else{
			result.setState(RESULT_STATE.FAIL.getNumber().toString());
			result.setMsg(RESULT_STATE.FAIL.getMsg());
		}
		return result;
	}
	@Override
	public Result batchOnSale(List<Long> ids) {
		this.refresh();
		Result result = new Result();
		if (null != ids && ids.size() > 0) {
			Map<String, Object> map = new HashMap<>();
			map.put("onSale", "1");
			map.put("data", ids);
			commodityDao.batchOpearate(map);
			result.setState(RESULT_STATE.SUCCESS.getNumber().toString());
			result.setMsg(RESULT_STATE.SUCCESS.getMsg());
		}else{
			result.setState(RESULT_STATE.FAIL.getNumber().toString());
			result.setMsg(RESULT_STATE.FAIL.getMsg());
		}
		return result;
	}
	@Override
	public Result batchOffSale(List<Long> ids) {
		this.refresh();
		Result result = new Result();
		if (null != ids && ids.size() > 0) {
			Map<String, Object> map = new HashMap<>();
			map.put("offSale", "1");
			map.put("data", ids);
			commodityDao.batchOpearate(map);
			result.setState(RESULT_STATE.SUCCESS.getNumber().toString());
			result.setMsg(RESULT_STATE.SUCCESS.getMsg());
		}else{
			result.setState(RESULT_STATE.FAIL.getNumber().toString());
			result.setMsg(RESULT_STATE.FAIL.getMsg());
		}
		return result;
	}
	
	@Override
	public void refresh() {
		commodityDao.onSaleAuto();
		commodityDao.offSaleAuto();
	}

	
	@Override
	public List<HashMap<String, Object>> selMapLst(Map<String, Object> parm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BisCommodity> selVoLst(Map<String, Object> parm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result updateBySelective(BisCommodity vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result delById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public BsgridVo<HashMap<String, Object>> getavtivityCommodity(HashMap<String, Object> param,
			int curPage, int pageSize) {
		//获取商品的类型
		List<HashMap<String, Object>> data;
		Page<HashMap<String, Object>> pageData;
		BsgridVo<HashMap<String, Object>> bsgridVo = new BsgridVo<HashMap<String, Object>>();
		if (param.get("activityType") != null && param.get("selected") != null 
				&& param.get("activityId") != null) {//已选择商品
			data =  commodityDao.getavtivityCommodity(param);
			//查询条件
			for (HashMap<String, Object> hashMap : data) {
				Long commodityId = (Long) hashMap.get("id");
				//是否为关联商品
				BisActivityCommodityRExample example = new BisActivityCommodityRExample();
				BisActivityCommodityRExample.Criteria  criteria= example.createCriteria();
				criteria.andActivityTypeEqualTo((Byte)param.get("activityType"));
				criteria.andActivityIdEqualTo((Long)param.get("activityId"));
				criteria.andCommodityIdEqualTo(commodityId);
				List<BisActivityCommodityR> rCommodityRs = bisActivityCommodityRDao.selectByExample(example);
				if (rCommodityRs != null && rCommodityRs.size() == 1) {
					hashMap.put("activityPrice", rCommodityRs.get(0).getActivityPrice());
					hashMap.put("activityNum", rCommodityRs.get(0).getCommodityNum());
				}
			}
			bsgridVo.setCurPage(1);
			bsgridVo.setData(data);
			bsgridVo.setTotalRows((long)data.size());
		}else{//未选中的商品
			PageHelper.startPage(curPage, pageSize);
			data =  commodityDao.getavtivityCommodity(param);
			pageData = (Page<HashMap<String, Object>>) data;
			bsgridVo.setCurPage(curPage);
			bsgridVo.setData(pageData);
			bsgridVo.setTotalRows(pageData.getTotal());
		}
		bsgridVo.setSuccess(true);
		return bsgridVo;
	}
}
