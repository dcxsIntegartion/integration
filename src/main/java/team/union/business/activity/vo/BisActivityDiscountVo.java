package team.union.business.activity.vo;

import java.util.List;

import team.union.business.activity.model.BisActivityDiscount;
import team.union.business.activity.model.BisActivityCommodityR;

/**
 * 折扣活动参数接收vo
 * @author Shuqianli
 * @date 2017年4月30日
 * Describe:
 */
public class BisActivityDiscountVo {
	
	private BisActivityDiscount bisActivityDiscount;
	
	private List<BisActivityCommodityR> bisActivityCommodityRList;

	public List<BisActivityCommodityR> getBisActivityCommodityRList() {
		return bisActivityCommodityRList;
	}

	public void setBisActivityCommodityRList(List<BisActivityCommodityR> bisActivityCommodityRList) {
		this.bisActivityCommodityRList = bisActivityCommodityRList;
	}

	public BisActivityDiscount getBisActivityDiscount() {
		return bisActivityDiscount;
	}

	public void setBisActivityDiscount(BisActivityDiscount bisActivityDiscount) {
		this.bisActivityDiscount = bisActivityDiscount;
	}
}
