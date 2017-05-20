package team.union.business.activity.vo;

import java.util.List;

import team.union.business.activity.model.BisActivityTurntable;
import team.union.business.activity.model.BisActivityCommodityR;

/**
 * 大转盘活动参数接收vo
 * @author Shuqianli
 * @date 2017年4月30日
 * Describe:
 */
public class BisActivityTurntableVo {
	
	private BisActivityTurntable bisActivityTurntable;
	
	private List<BisActivityCommodityR> bisActivityCommodityRList;

	public List<BisActivityCommodityR> getBisActivityCommodityRList() {
		return bisActivityCommodityRList;
	}

	public void setBisActivityCommodityRList(List<BisActivityCommodityR> bisActivityCommodityRList) {
		this.bisActivityCommodityRList = bisActivityCommodityRList;
	}

	public BisActivityTurntable getBisActivityTurntable() {
		return bisActivityTurntable;
	}

	public void setBisActivityTurntable(BisActivityTurntable bisActivityTurntable) {
		this.bisActivityTurntable = bisActivityTurntable;
	}
}
