package team.union.business.activity.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
import team.union.business.com.cfg.BisConfig.ACTIVITY_TYPE;
import team.union.business.com.cfg.BisConfig.RESULT_STATE;
import team.union.nonbusiness.upload.model.NonbizUpload;
import team.union.nonbusiness.upload.service.IUploadService;

/**
 * 特价活动业务层
 * @author Shuqianli
 * @date 2017年4月30日
 * Describe:
 */
@Repository
public class BisActivityBargainServiceImpl implements IBisActivityBargainService {

	@Autowired
	private BisActivityBargainDao bisActivityBargainDao;
	@Autowired
	private BisActivityCommodityRDao bisActivityCommodityRDao;
	@Autowired
	private IUploadService iUploadService;
	
	@Override
	public BsgridVo<HashMap<String, Object>> paging(Map<String, Object> parm, int curPage, int pageSize) {
		PageHelper.startPage(curPage, pageSize);
		List<HashMap<String, Object>> data =  bisActivityBargainDao.selectPage(parm);
		//获取图片
//		for (HashMap<String, Object> hashMap : data) {
//			//活动图片
//			String activityPic = (String) hashMap.get("activity_pic");
//			List<NonbizUpload> activityPicList = iUploadService.selVoLst(activityPic);
//			hashMap.put("activityPicList", activityPicList);
//			//分享图片
//			String sharePic = (String) hashMap.get("share_pic");
//			List<NonbizUpload> sharePicList = iUploadService.selVoLst(sharePic);
//			hashMap.put("sharePicList", sharePicList);
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
	public List<HashMap<String, Object>> selMapLst(Map<String, Object> parm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BisActivityBargainVo> selVoLst(Map<String, Object> parm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result selById(Long Id) {
		Result result = new Result();
		BisActivityBargain bargain = bisActivityBargainDao.selectByPrimaryKey(Id);
		if (bargain != null) {
			//活动图片
			String activityPic = bargain.getActivityPic();
			List<NonbizUpload> activityPicList = iUploadService.selVoLst(activityPic);
			bargain.setActivityPicList(activityPicList);
			//分享图片
			String sharePic = bargain.getSharePic();
			List<NonbizUpload> sharePicList = iUploadService.selVoLst(sharePic);
			bargain.setSharePicList(sharePicList);
			
			result.setData(bargain);
			result.setMsg(RESULT_STATE.SUCCESS.getMsg());
			result.setState(RESULT_STATE.SUCCESS.getNumber().toString());
		}else{
			result.setMsg(RESULT_STATE.FAIL.getMsg());
			result.setState(RESULT_STATE.FAIL.getNumber().toString());
		}
		return result;
	}

	@Override
	public Result add(BisActivityBargainVo vo) {
		Result result = new Result();
		try {
			//活动图片
			BisActivityBargain bisActivityBargain = vo.getBisActivityBargain();
//			List<NonbizUpload> activityPicList = bisActivityBargain.getActivityPicList();
//			String activityPic = iUploadService.banding(activityPicList);
//			bisActivityBargain.setActivityPic(activityPic);
//			//分享图片
//			List<NonbizUpload> sharePicList = bisActivityBargain.getSharePicList();
//			String sharePic = iUploadService.banding(sharePicList);
//			bisActivityBargain.setSharePic(sharePic);
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
	public Result update(BisActivityBargainVo vo) {
		Result result = new Result();
		try {
			bisActivityBargainDao.updateByPrimaryKey(vo.getBisActivityBargain());
			result.setData(vo.getBisActivityBargain());
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
	public Result updateBySelective(BisActivityBargainVo vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result delById(Long id) {
		Result result = new Result();
		try {
			bisActivityBargainDao.deleteByPrimaryKey(id);
			result.setMsg(RESULT_STATE.SUCCESS.getMsg());
			result.setState(RESULT_STATE.SUCCESS.getNumber().toString());
		} catch (Exception e) {
			result.setMsg(RESULT_STATE.FAIL.getMsg());
			result.setState(RESULT_STATE.FAIL.getNumber().toString());
			e.printStackTrace();
		}
		return result;
	}

}
