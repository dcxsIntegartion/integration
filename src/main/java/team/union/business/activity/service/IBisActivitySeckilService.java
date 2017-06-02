package team.union.business.activity.service;

import team.union.basic_data.com.rs.Result;
import team.union.business.activity.model.BisActivitySeckil;
import team.union.business.activity.vo.BisActivitySeckilVo;
import team.union.business.com.interf.IService;

/**
 * 秒杀活动业务层接口
 * @author Shuqianli
 * @date 2017年4月30日
 * Describe:
 */
public interface IBisActivitySeckilService extends IService<BisActivitySeckilVo>{

	/***修改活动状态***/
	Result updateStatus(BisActivitySeckil vo);

}
