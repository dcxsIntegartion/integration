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
import team.union.business.activity.service.IBisActivityBargainService;
import team.union.business.activity.vo.BisActivityBargainVo;
import team.union.business.com.interf.IController;
import team.union.nonbusiness.util.ToolsUtil;

/**
 * 特价活动Controller层
 * @author Shuqianli
 * @date 2017年4月29日
 * Describe:
 */
@Controller
@RequestMapping("/bis/activityBargain")
public class BisActivityBargainController implements IController<BisActivityBargainVo> {

	@Autowired
	private IBisActivityBargainService iBisActivityBargainService;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/page")
	public BsgridVo<HashMap<String, Object>> paging(
			@RequestParam(defaultValue = "1") int curPage, 
			@RequestParam(defaultValue = "10")int pageSize, 
			HttpServletRequest req) {
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
		return iBisActivityBargainService.paging(parm, curPage, pageSize);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/selectById")
	public Result viwe(@RequestParam Long Id, HttpServletRequest req) {
		return iBisActivityBargainService.selById(Id);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/insert")
	public Result save(@RequestBody BisActivityBargainVo vo, HttpServletRequest req) {
		return iBisActivityBargainService.add(vo);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/deleteById")
	public Result delete(@RequestParam Long Id, HttpServletRequest req) {
		return iBisActivityBargainService.delById(Id);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/updateById")
	public Result update(@RequestBody BisActivityBargainVo vo, HttpServletRequest req) {
		return iBisActivityBargainService.update(vo);
	}

}
