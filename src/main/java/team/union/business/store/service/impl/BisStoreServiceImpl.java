package team.union.business.store.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import team.union.business.com.rs.BsgridVo;
import team.union.business.com.rs.Result;
import team.union.business.store.dao.BisStoreDao;
import team.union.business.store.model.BisStore;
import team.union.business.store.service.IBisStoreService;
import team.union.sys_sp.com.cfg.PromptMsgConfig.PROMPT;
import team.union.utils.upload.service.IUploadService;
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
	
	@Autowired
	private IUploadService uploadService;
	
	@Override
	public Result saveStore(BisStore bisStore) {
		try {
			bisStore.setStoreCreatTime(new Date());
			bisStore.setStoreStatus(0);
			bisStoreDao.insert(bisStore);
			List<String> lst = new ArrayList<String>();
			lst.get(1);
			return Result.success(bisStore);
		} catch (Exception e) {
			return Result.error(e.getMessage());
		}
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
	public Result selectById(Long id) {
		Result result = new Result();
		BisStore bisStore = bisStoreDao.selectByPrimaryKey(id);
		if (bisStore != null) {
			result.setData(bisStore);
			result.setMsg(PROMPT.SUCCESS.getMsg());
			result.setState(PROMPT.SUCCESS.getNo());
		}else{
			result.setMsg(PROMPT.FAIL.getMsg());
			result.setState(PROMPT.FAIL.getNo());
		}
		return result;
	}

	public Result selByServiceName(String subDomain){
		if(null!=subDomain && !"".equals(subDomain) && subDomain.split("\\.").length>0){
			String subDomainS[] = subDomain.split("\\.");
			BisStore bs= bisStoreDao.selBySubDomain(subDomainS[0]);
			if(null!=bs){//图片获取封装
				if(StringUtils.isNotEmpty(bs.getStorePic())){
					bs.setStorePicUrls(uploadService.selUrlLst(bs.getStorePic()));
				}
			}
			return Result.success(bs);
		}
		return Result.error();
	}
	
	
}
