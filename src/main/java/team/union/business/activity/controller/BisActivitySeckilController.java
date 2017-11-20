package team.union.business.activity.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import team.union.basic_data.com.rs.BsgridVo;
import team.union.basic_data.com.rs.Result;
import team.union.business.activity.model.BisActivitySeckil;
import team.union.business.activity.service.IBisActivitySeckilService;
import team.union.business.activity.vo.BisActivitySeckilVo;
import team.union.business.com.interf.IController;
import team.union.sys_sp.util.ToolsUtil;

/**
 * 特价活动Controller层
 * @author Shuqianli
 * @date 2017年4月29日
 * Describe:
 */
@Controller
@RequestMapping("/bis/activitySeckil")
public class BisActivitySeckilController implements IController<BisActivitySeckilVo> {

	@Autowired
	private IBisActivitySeckilService iBisActivitySeckilService;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/page")
	public BsgridVo<HashMap<String, Object>> paging(
			HttpServletRequest req) {
		int curPage = 1;
		int pageSize = 10;
		if(null!=req.getAttribute("curPage") && null!=req.getAttribute("pageSize")){
			curPage = (int) Double.parseDouble(req.getAttribute("curPage").toString());
			pageSize = (int) Double.parseDouble(req.getAttribute("pageSize").toString());
		}
		String activityName = req.getParameter("activityName");
		String storeName = req.getParameter("storeId");
		String activityStatus = req.getParameter("activityStatus");
		Map<String, Object> parm= new HashMap<>();
		
		if(ToolsUtil.isNotEmpty(activityName)){
			parm.put("activityName", activityName);
		}
		if(ToolsUtil.isNotEmpty(storeName)){
			parm.put("storeName", storeName);
		}
		if(ToolsUtil.isNotEmpty(activityStatus)){
			parm.put("activityStatus", Integer.parseInt(activityStatus));
		}
		return iBisActivitySeckilService.paging(parm, curPage, pageSize);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/selectById")
	public Result viwe(@RequestParam Long Id, HttpServletRequest req) {
		return iBisActivitySeckilService.selById(Id);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/insert")
	public Result save(@RequestBody BisActivitySeckilVo vo, HttpServletRequest req) {
		return iBisActivitySeckilService.add(vo);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/deleteById")
	public Result delete(@RequestParam Long Id, HttpServletRequest req) {
		return iBisActivitySeckilService.delById(Id);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/updateById")
	public Result update(@RequestBody BisActivitySeckilVo vo, HttpServletRequest req) {
		return iBisActivitySeckilService.update(vo);
	}
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/updateStatus")
	public Result updateStatus(@RequestBody BisActivitySeckil vo, HttpServletRequest req) {
		return iBisActivitySeckilService.updateStatus(vo);
	}

}
