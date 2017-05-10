package team.union.basic_data.area.controller;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;

import team.union.basic_data.area.model.NonbisCityCode;
import team.union.basic_data.area.service.ICoCityService;
import team.union.nonbusiness.com.rs.BsgridVo;
import team.union.nonbusiness.com.rs.ResultVo;
import team.union.nonbusiness.util.ToolsUtil;


/**
 * @Author yinyao
 * @Version 2015-12-10 上午10:30:44
 */
@Controller
@RequestMapping("nonbis/cityCode")
public class CityCodeController {
	@Autowired
	private ICoCityService coCityService;
	
	/**
	 * 查询出省
	 * @return
	 * @author	yinyao
	 * @Date	2015-12-18 下午1:01:47
	 */
	@RequestMapping("/selectProvince")
	@ResponseBody
	public List<NonbisCityCode> selectProvince(){
		return coCityService.selectProvince();
	}
	
	/**
	 * 得到省和市
	 * @return
	 * @author	yinyao
	 * @Date	2015-12-18 下午1:00:27
	 */
	@ResponseBody
	@RequestMapping(value = "/getProvinceAndCity",method=RequestMethod.GET)
	public List<NonbisCityCode> getProvinceAndCity(){
		return coCityService.getProvinceAndCity();
	}
	/**
	 * 查询所有
	 * @return
	 * @author	yinyao
	 * @Date	2015-12-18 下午3:58:08
	 */
	@RequestMapping("/selectAll")
	@ResponseBody
	public List<NonbisCityCode> selectAll(){
		return coCityService.selectAll();
	}
	
	
	/**
	 * 根据code查询出下级并封装
	 * @param request
	 * @return
	 * @author	yinyao
	 * @Date	2015-12-18 下午1:02:53
	 */
	@RequestMapping("/selectCityNodes")
	@ResponseBody
	public List<HashMap<String, Object>> selectCityNodes(HttpServletRequest request){		
		return coCityService.selectCityNodes(request);
	}
	
	/**
	 * 根据父类code查询出下级地区
	 * @param request
	 * @return
	 * @author	yinyao
	 * @Date	2015-12-18 下午1:02:53
	 */
	@RequestMapping("/selectByCodeCityNodes")
	@ResponseBody
	public List<NonbisCityCode> selectByCodeCityNodes(HttpServletRequest request){		
		return coCityService.selectByCodeCityNodes(request);
	}
	
	/**
	 * 分页
	 * @param pageSize
	 * @param curPage
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author	yinyao
	 * @Date	2016-3-10 上午11:40:08
	 */
	@ResponseBody
	@RequestMapping(value = "/page")
	public Object selectPage(
			@RequestParam(defaultValue = "10") Integer pageSize, 
			@RequestParam(defaultValue = "1") Integer curPage,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String openStart = request.getParameter("openStart");
		String openEnd = request.getParameter("openEnd");
		String closeStart = request.getParameter("closeStart");
		String closeEnd = request.getParameter("closeEnd");
		String status = request.getParameter("status");
		String cityCode = request.getParameter("cityCode");
		String levelNumber = request.getParameter("levelNumber");
		Integer levelNum = 0;
		if(ToolsUtil.isNotEmpty(openStart)){
			map.put("openStart", openStart);
		}
		if(ToolsUtil.isNotEmpty(openEnd)){
			map.put("openEnd", openEnd);
		}
		if(ToolsUtil.isNotEmpty(closeStart)){
			map.put("closeStart", closeStart);
		}
		if(ToolsUtil.isNotEmpty(closeEnd)){
			map.put("closeEnd", closeEnd);
		}
		if(ToolsUtil.isNotEmpty(status)){
			map.put("status", status);
		}
		if(ToolsUtil.isNotEmpty(cityCode)){
			map.put("cityFullCode", "%"+cityCode+"%");
			if(ToolsUtil.isDirectly(cityCode)){
				levelNum = Integer.parseInt(levelNumber)+2;
			}else if(ToolsUtil.isSAR(cityCode)){
				levelNum = Integer.parseInt(levelNumber);
			}else{
				levelNum = Integer.parseInt(levelNumber)+1;
			}
		}
		if(ToolsUtil.isNotEmpty(levelNumber)){			
			map.put("levelNumber", levelNum);
		}
		map.put("curPage", curPage);
		map.put("pageSize", pageSize);		
		
		Page<NonbisCityCode> page =  coCityService.selectPage(map);
		BsgridVo<NonbisCityCode>  bsgridVo= new BsgridVo<NonbisCityCode>();  
		if(page != null){
			if(page.getTotal()==0){
				bsgridVo.setCurPage(0);
			}else{
				bsgridVo.setCurPage(curPage);
			}
			bsgridVo.setTotalRows(page.getTotal());
			bsgridVo.setData(page);
			bsgridVo.setSuccess(true);
		}else{
			bsgridVo.setSuccess(false);
		}
		return bsgridVo;		
	}
	
	/**
	 * 修改状态
	 * @param cityId
	 * @param status
	 * @return
	 * @author	yinyao
	 * @Date	2016-3-10 下午1:41:33
	 */
	@ResponseBody
	@RequestMapping("/updataStatus")
	public ResultVo updataStatus(@RequestParam Long cityId,@RequestParam Integer status,
			@RequestParam String levelNumber,@RequestParam String cityCode){
		ResultVo resultVo = new ResultVo();
		resultVo.setStatus(coCityService.updataStatus(cityId, status,levelNumber,cityCode));
		return resultVo;
	}
}
