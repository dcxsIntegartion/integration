package team.union.business.activity.service;

import team.union.basic_data.com.rs.Result;
import team.union.business.activity.model.BisActivityDiscount;
import team.union.business.activity.vo.BisActivityDiscountVo;
import team.union.business.com.interf.IService;

/**
 * 折扣活动业务层接口
 * @author Shuqianli
 * @date 2017年4月30日
 * Describe:
 */
public interface IBisActivityDiscountService extends IService<BisActivityDiscountVo>{

	/***修改活动状态***/
	Result updateStatus(BisActivityDiscount vo);

}
