package team.union.business.activity.vo;

import java.util.List;

import team.union.business.activity.model.BisActivityBargain;
import team.union.business.activity.model.BisActivityCommodityR;

/**
 * 特价活动参数接收vo
 * @author Shuqianli
 * @date 2017年4月30日
 * Describe:
 */
public class BisActivityBargainVo {
	
	private BisActivityBargain bisActivityBargain;
	
	private List<BisActivityCommodityR> bisActivityCommodityRList;

	public List<BisActivityCommodityR> getBisActivityCommodityRList() {
		return bisActivityCommodityRList;
	}

	public void setBisActivityCommodityRList(List<BisActivityCommodityR> bisActivityCommodityRList) {
		this.bisActivityCommodityRList = bisActivityCommodityRList;
	}

	public BisActivityBargain getBisActivityBargain() {
		return bisActivityBargain;
	}

	public void setBisActivityBargain(BisActivityBargain bisActivityBargain) {
		this.bisActivityBargain = bisActivityBargain;
	}
}
