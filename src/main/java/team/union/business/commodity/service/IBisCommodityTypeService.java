package team.union.business.commodity.service;

import java.math.BigDecimal;
import java.util.List;

import team.union.basic_data.com.rs.Result;
import team.union.business.commodity.model.BisCommodityType;

public interface IBisCommodityTypeService {

	public Result typeTree();
	
	public Result listAll();
	
	public Result findOne(BigDecimal id);
	
	public Result update(BisCommodityType type);
	
	public Result insert(BisCommodityType type);
	
	public Result delete(List<BigDecimal> ids);
	
	public Result listSelect();
}
