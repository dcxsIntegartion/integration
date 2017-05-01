package team.union.business.store.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import team.union.business.store.model.BisStore;
import team.union.business.store.model.BisStoreExample;

/**
 * 店铺持久层
 * @author Shuqianli
 * @date 2017年4月30日
 * Describe:
 */
public interface BisStoreDao {
    int countByExample(BisStoreExample example);

    int deleteByExample(BisStoreExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BisStore record);

    int insertSelective(BisStore record);

    List<BisStore> selectByExample(BisStoreExample example);

    BisStore selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BisStore record, @Param("example") BisStoreExample example);

    int updateByExample(@Param("record") BisStore record, @Param("example") BisStoreExample example);

    int updateByPrimaryKeySelective(BisStore record);

    int updateByPrimaryKey(BisStore record);
}