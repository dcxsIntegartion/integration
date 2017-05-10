package team.union.basic_data.area.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import team.union.basic_data.area.dao.NonbisCityCodeDao;
import team.union.basic_data.area.model.NonbisCityCode;
import team.union.basic_data.area.service.ICoCityService;
import team.union.nonbusiness.com.cfg.BaseConfig;
import team.union.nonbusiness.util.ToolsUtil;


/**
 * @Author yinyao
 * @Version 2015-12-10 上午10:20:35
 */
@Repository
@Transactional(rollbackFor = Exception.class)
public class CoCityServiceImpl implements ICoCityService {
	@Autowired
	private NonbisCityCodeDao coCityDao;
	
	public List<NonbisCityCode> selectAll() {
		HashMap<String, Object> parm = new HashMap<String, Object>();
		return coCityDao.selectCityNodes(parm);
	}	
	
	public List<NonbisCityCode> selectProvince() {
		HashMap<String, Object> parm = new HashMap<String, Object>();
		parm.put("levelNumber", BaseConfig.CITY_PROVINCE);
		return coCityDao.selectCityNodes(parm);
	}
	
	public List<NonbisCityCode> getProvinceAndCity() {
		return coCityDao.selProvincesAndCity();
	}
	
	public List<HashMap<String, Object>> selectCityNodes(HttpServletRequest request) {
		String cityCode = request.getParameter("cityCode");
		int levelNumber = Integer.parseInt(request.getParameter("levelNumber").toString());
		String cityParentCode = request.getParameter("cityParentCode");
		HashMap<String, Object> map = new HashMap<String,Object>();
		if(ToolsUtil.isNotEmpty(cityCode)){
			map.put("cityCode", "%"+cityCode+"%");
			levelNumber = levelNumber+judgeLevelNum(cityCode);
		}
		map.put("levelNumber", levelNumber);
		if(ToolsUtil.isNotEmpty(cityParentCode)){
			map.put("cityParentCode", cityParentCode);
		}
		List<NonbisCityCode> coCitys = coCityDao.selectCityNodes(map);
		List<HashMap<String, Object>> cities = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < coCitys.size(); i++) {
			HashMap<String, Object> cityMap = new HashMap<String, Object>();
			cityMap.put("cityCode", coCitys.get(i).getCityCode());
			cityMap.put("name",coCitys.get(i).getCityName());
			cityMap.put("cityFullCode", coCitys.get(i).getCityFullCode());
			cityMap.put("childSize", coCitys.get(i).getChildSize());
			cityMap.put("levelNumber", coCitys.get(i).getLevelNumber()+1);
			if(coCitys.get(i).getChildSize()!=0&&BaseConfig.CITY_PROVINCE ==coCitys.get(i).getLevelNumber()){
				cityMap.put("isParent", true);
			}
			cities.add(cityMap);
		}
		return cities;
	}
	
	public List<NonbisCityCode> selectByCodeCityNodes(
			HttpServletRequest request) {
		String levelNumber = request.getParameter("levelNumber");
		String cityParentCode = request.getParameter("cityParentCode");
		HashMap<String, Object> map = new HashMap<String,Object>();
		if(ToolsUtil.isDirectly(cityParentCode)){
			map.put("cityFullCode", "%"+cityParentCode+"%");
			map.put("levelNumber", BaseConfig.CITY_AREA);
		}else if(ToolsUtil.isNotEmpty(cityParentCode) && ToolsUtil.isNotEmpty(levelNumber)){
			map.put("cityParentCode", cityParentCode);
			map.put("levelNumber", Integer.parseInt(levelNumber));
		}
		
		List<NonbisCityCode> coCitys = coCityDao.selectCityNodes(map);
		return coCitys;
	}
	
	
	public Page<NonbisCityCode> selectPage(HashMap<String, Object> map) {
		Integer curPage = (Integer) map.get("curPage");
		Integer pageSize = (Integer) map.get("pageSize");		
		PageHelper.startPage(curPage, pageSize);
		List<NonbisCityCode> lst = coCityDao.selectCityNodes(map);
		Page<NonbisCityCode> page = null; 
		if(lst != null && lst.size()>0){
			page = (Page<NonbisCityCode>) lst;
		}else{
			page = new Page<NonbisCityCode>(curPage, pageSize);
		}
		return page;
	}

	/* (non-Javadoc)
	 * @see com.biiway.dcxs.area.service.ICoCityService#updateStatus(java.lang.Integer)
	 */
	
	public int updataStatus(Long cityId,Integer status,String levelNumber ,String cityCode) {
		updata(cityId,status);
		if(!"3".equals(levelNumber)||!ToolsUtil.isDirectly(cityCode)){
			HashMap<String, Object> map = new HashMap<String, Object>();
			Integer levelNum = 0;
			map.put("cityCode", "%"+cityCode+"%");
			levelNum = levelNum+judgeLevelNum(cityCode);
			map.put("levelNumber", levelNum);
			List<NonbisCityCode> coCities = coCityDao.selectCityNodes(map);
			for (NonbisCityCode city : coCities) {
				Long cityIds =  city.getCityId();
				updata(cityIds,status);
			}
		}
		return 1;
	}
	
	private int judgeLevelNum(String cityCode){
		int levelNum = 1;
		if(ToolsUtil.isDirectly(cityCode)){
			levelNum = 2;
		}else if(ToolsUtil.isSAR(cityCode)){
			levelNum = 0;
		}
		return levelNum;
	}
	
	private void updata(Long cityId,Integer status){
		NonbisCityCode coCity = new NonbisCityCode();
		coCity.setCityId(cityId);
		coCity.setOpenStutas(status);
		if(status==BaseConfig.CLOSE){
			coCity.setCloseTime(new Date());
		}
		if(status==BaseConfig.OPEN){
			coCity.setOpenTime(new Date());
		}
		coCityDao.updateByPrimaryKeySelective(coCity);
	}
}
