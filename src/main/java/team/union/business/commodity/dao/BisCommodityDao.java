package team.union.business.commodity.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import team.union.business.commodity.model.BisCommodity;
import team.union.business.commodity.model.BisCommodityExample;

/**
 * 商品持久层
 * @author Shuqianli
 * @date 2017年4月30日
 * Describe:
 */
public interface BisCommodityDao {
    int countByExample(BisCommodityExample example);

    int deleteByExample(BisCommodityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BisCommodity record);

    int insertSelective(BisCommodity record);

    List<BisCommodity> selectByExample(BisCommodityExample example);

    BisCommodity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BisCommodity record, @Param("example") BisCommodityExample example);

    int updateByExample(@Param("record") BisCommodity record, @Param("example") BisCommodityExample example);

    int updateByPrimaryKeySelective(BisCommodity record);

    int updateByPrimaryKey(BisCommodity record);
}