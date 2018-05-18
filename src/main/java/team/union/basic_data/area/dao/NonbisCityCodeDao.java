package team.union.basic_data.area.dao;

import java.util.HashMap;
import java.util.List;

import team.union.basic_data.area.model.NbisCityCode;


public interface NonbisCityCodeDao {

    int deleteByPrimaryKey(Long cityId);

    int insert(NbisCityCode record);

    int insertSelective(NbisCityCode record);

    NbisCityCode selectByPrimaryKey(Long cityId);

    int updateByPrimaryKeySelective(NbisCityCode record);

    int updateByPrimaryKey(NbisCityCode record);
    
    /**
     * 根据城市code查询，它下面的所有城市
     * @param map
     * @return
     * @author	yinyao
     * @Date	2016-1-4 下午6:19:25
     */
    List<NbisCityCode> selectCityNodes(HashMap<String, Object> map);
    
    /**selProvincesAndCity
     * 获取省市城市数据
     * @return
     * @author	yinxb
     * @Date	2016-1-4 下午6:15:02
     */
   public  List<NbisCityCode> selProvincesAndCity();
   
}