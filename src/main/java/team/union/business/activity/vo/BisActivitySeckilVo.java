package team.union.business.activity.vo;

import java.util.List;

import team.union.business.activity.model.BisActivitySeckil;
import team.union.business.activity.model.BisActivityCommodityR;

/**
 * 秒杀活动参数接收vo
 * @author Shuqianli
 * @date 2017年4月30日
 * Describe:
 */
public class BisActivitySeckilVo {
	
	private BisActivitySeckil bisActivitySeckil;
	
	private List<BisActivityCommodityR> bisActivityCommodityRList;

	public List<BisActivityCommodityR> getBisActivityCommodityRList() {
		return bisActivityCommodityRList;
	}

	public void setBisActivityCommodityRList(List<BisActivityCommodityR> bisActivityCommodityRList) {
		this.bisActivityCommodityRList = bisActivityCommodityRList;
	}

	public BisActivitySeckil getBisActivitySeckil() {
		return bisActivitySeckil;
	}

	public void setBisActivitySeckil(BisActivitySeckil bisActivitySeckil) {
		this.bisActivitySeckil = bisActivitySeckil;
	}
}
