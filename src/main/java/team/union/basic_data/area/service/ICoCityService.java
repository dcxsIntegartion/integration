package team.union.basic_data.area.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.Page;

import team.union.basic_data.area.model.NonbisCityCode;

/**
 * @Author yinyao
 * @Version 2015-12-10 上午9:55:42
 */
public interface ICoCityService {
	
	/**
	 * 查询出所有
	 * @return
	 * @author	yinyao
	 * @Date	2015-12-18 下午3:56:28
	 */
	public List<NonbisCityCode> selectAll();
	
	/**
	 * 查询出所有的省
	 * @return
	 * @author	yinyao
	 * @Date	2015-12-18 上午11:25:32
	 */
	public List<NonbisCityCode> selectProvince();
	
	/**
	 * 得到所有的省和市
	 * @return
	 */
	public List<NonbisCityCode> getProvinceAndCity();
	
	/**
	 * 根据城市code查询，它下面的所有城市
	 * @param map
	 * @return
	 * @author	yinyao
	 * @Date	2015-12-18 上午11:04:28
	 */
	public List<HashMap<String, Object>> selectCityNodes(HttpServletRequest request);
	
	/**
	 * 根据城市code查询，它下面的所有城市
	 * @param map
	 * @return
	 * @author	yinyao
	 * @Date	2015-12-18 上午11:04:28
	 */
	public List<NonbisCityCode> selectByCodeCityNodes(HttpServletRequest request);
	
	/**
	 * 分页查询
	 * @param map
	 * @return
	 * @author	yinyao
	 * @Date	2016-3-10 上午11:28:47
	 */
	public Page<NonbisCityCode> selectPage(HashMap<String, Object> map);
	
	/**
	 * 修改
	 * @param status,cityId
	 * @return
	 * @author	yinyao
	 * @Date	2016-3-10 上午11:33:02
	 */
	public int updataStatus(Long cityId,Integer status,String levelNumber ,String cityCode);
}
