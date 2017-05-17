package team.union.business.commodity.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import team.union.business.commodity.model.BisCommodityType;

public interface BisCommodityTypeDao {

	/**
	 * 
	* 方法名:          queryAll
	* 方法功能描述:    查询所有
	* @return  
	* @return:        List<BisCommodityType>
	* @Author:        zh
	* @Create Date:   2017年5月10日
	 */
	List<BisCommodityType> queryAll();
	
	/**
	 * 
	* 方法名:          queryByParam
	* 方法功能描述:    条件查询 
	* 				parTypeId
	* 				level
	* 				typeState
	* @param map
	* @return  
	* @return:        List<BisCommodityType>
	* @Author:        zh
	* @Create Date:   2017年5月10日
	 */
	List<BisCommodityType> queryByParam(Map<String, Object> map);
	
	BisCommodityType findById(BigDecimal typeId);
	
	void insert(BisCommodityType type);
	
	void delete(BigDecimal id);
	
	void deleteByIds(List<BigDecimal> list);
	
	void update(BisCommodityType type);
	
	List<BisCommodityType> querySelect();
}
