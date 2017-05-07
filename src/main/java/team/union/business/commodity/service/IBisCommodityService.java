package team.union.business.commodity.service;

import java.util.List;

import team.union.basic_data.com.interf.IService;
import team.union.basic_data.com.rs.Result;
import team.union.business.commodity.model.BisCommodity;

public interface IBisCommodityService extends IService<BisCommodity>{

	void refresh();
	//批量删除
	Result batchDel(List<Long> ids);
	//批量上架
	Result batchOnSale(List<Long> ids);
	//批量下架
	Result batchOffSale(List<Long> ids);
}
