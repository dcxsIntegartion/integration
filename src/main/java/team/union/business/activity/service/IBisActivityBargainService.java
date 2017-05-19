package team.union.business.activity.service;

import team.union.basic_data.com.rs.Result;
import team.union.business.activity.model.BisActivityBargain;
import team.union.business.activity.vo.BisActivityBargainVo;
import team.union.business.com.interf.IService;

/**
 * 特价活动业务层接口
 * @author Shuqianli
 * @date 2017年4月30日
 * Describe:
 */
public interface IBisActivityBargainService extends IService<BisActivityBargainVo>{

	/***修改活动状态***/
	Result updateStatus(BisActivityBargain vo);

}
