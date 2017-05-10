package team.union.basic_data.area.dao;

import java.util.HashMap;
import java.util.List;

import team.union.basic_data.area.model.NonbisCityCode;

public interface NonbisCityCodeDao {

    int deleteByPrimaryKey(Long cityId);

    int insert(NonbisCityCode record);

    int insertSelective(NonbisCityCode record);

    NonbisCityCode selectByPrimaryKey(Long cityId);

    int updateByPrimaryKeySelective(NonbisCityCode record);

    int updateByPrimaryKey(NonbisCityCode record);
    
    /**
     * 根据城市code查询，它下面的所有城市
     * @param map
     * @return
     * @author	yinyao
     * @Date	2016-1-4 下午6:19:25
     */
    List<NonbisCityCode> selectCityNodes(HashMap<String, Object> map);
    
    /**selProvincesAndCity
     * 获取省市城市数据
     * @return
     * @author	yinxb
     * @Date	2016-1-4 下午6:15:02
     */
   public  List<NonbisCityCode> selProvincesAndCity();
   
}