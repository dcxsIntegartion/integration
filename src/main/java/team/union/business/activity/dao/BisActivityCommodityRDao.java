package team.union.business.activity.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import team.union.business.activity.model.BisActivityCommodityR;
import team.union.business.activity.model.BisActivityCommodityRExample;

/**
 * 活动-商品关联表
 * @author Shuqianli
 * @date 2017年4月30日
 * Describe:
 */
public interface BisActivityCommodityRDao {
    int countByExample(BisActivityCommodityRExample example);

    int deleteByExample(BisActivityCommodityRExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BisActivityCommodityR record);

    int insertSelective(BisActivityCommodityR record);

    List<BisActivityCommodityR> selectByExample(BisActivityCommodityRExample example);

    BisActivityCommodityR selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BisActivityCommodityR record, @Param("example") BisActivityCommodityRExample example);

    int updateByExample(@Param("record") BisActivityCommodityR record, @Param("example") BisActivityCommodityRExample example);

    int updateByPrimaryKeySelective(BisActivityCommodityR record);

    int updateByPrimaryKey(BisActivityCommodityR record);
}