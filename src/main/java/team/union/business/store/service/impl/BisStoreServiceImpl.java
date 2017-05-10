package team.union.business.store.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import team.union.business.com.cfg.BisConfig.RESULT_STATE;
import team.union.business.com.rs.BsgridVo;
import team.union.business.com.rs.Result;
import team.union.business.store.dao.BisStoreDao;
import team.union.business.store.model.BisStore;
import team.union.business.store.service.IBisStoreService;
/**
 * 
 *描述：店铺业务层实现
 * @author Shuqianli
 * 2017年5月6日
 */
@Repository
@Transactional(rollbackFor = Exception.class)
public class BisStoreServiceImpl implements IBisStoreService {

	@Autowired
	private BisStoreDao bisStoreDao;
	
	@Override
	public Result saveStore(BisStore bisStore) {
		Result result = new Result();
		try {
			bisStore.setStoreCreatTime(new Date());
			bisStore.setStoreStatus(0);
			bisStoreDao.insert(bisStore);
			result.setData(bisStore);
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
	public BsgridVo<HashMap<String, Object>> page(Map<String, Object> parm, int curPage, int pageSize) {
		PageHelper.startPage(curPage, pageSize);
		List<HashMap<String, Object>> data =  bisStoreDao.selectPage(parm);
		//获取图片
//		for (HashMap<String, Object> hashMap : data) {
//			String activityPic = (String) hashMap.get("activity_pic");
//			List<NonbizUpload> activityPicList = iUploadService.selVoLst(activityPic);
//			hashMap.put("activityPicList", activityPicList);
//		}
		Page<HashMap<String, Object>> pageData = (Page<HashMap<String, Object>>) data;
		BsgridVo<HashMap<String, Object>> bsgridVo = new BsgridVo<HashMap<String, Object>>();
		bsgridVo.setCurPage(curPage);
		bsgridVo.setData(pageData);
		bsgridVo.setSuccess(true);
		bsgridVo.setTotalRows(pageData.getTotal());
		return bsgridVo;
	}

	@Override
	public Result updateById(BisStore bisStore) {
		Result result = new Result();
		try {
			bisStoreDao.updateByPrimaryKey(bisStore);
			result.setData(bisStore);
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
	public Result selectById(Long id) {
		Result result = new Result();
		BisStore bisStore = bisStoreDao.selectByPrimaryKey(id);
		if (bisStore != null) {
			result.setData(bisStore);
			result.setMsg(RESULT_STATE.SUCCESS.getMsg());
			result.setState(RESULT_STATE.SUCCESS.getNumber().toString());
		}else{
			result.setMsg(RESULT_STATE.FAIL.getMsg());
			result.setState(RESULT_STATE.FAIL.getNumber().toString());
		}
		return result;
	}

}
