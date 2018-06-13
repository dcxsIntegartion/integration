package team.union.business.store.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.union.business.store.model.BisStore;

/**
 * 店铺持久层
 * @author Shuqianli
 * @date 2017年4月30日
 * Describe:
 */
public interface BisStoreDao {

    int deleteByPrimaryKey(Long id);

    int insert(BisStore record);

    int insertSelective(BisStore record);

    BisStore selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BisStore record);

    int updateByPrimaryKey(BisStore record);

    /**
     * 店铺列表查询
     * @param parm
     * @return
     */
	List<HashMap<String, Object>> selectPage(Map<String, Object> parm);
	/**
	 * @param subDomain 二级域名开头部分
	 * @return
	 */
	public BisStore selBySubDomain(String subDomain);
	
}