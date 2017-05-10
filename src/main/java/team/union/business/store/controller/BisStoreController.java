package team.union.business.store.controller;

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

import team.union.business.com.rs.BsgridVo;
import team.union.business.com.rs.Result;
import team.union.business.store.model.BisStore;
import team.union.business.store.service.IBisStoreService;
import team.union.nonbusiness.util.ToolsUtil;

/**
 * 
 *描述：店铺controller层
 * @author Shuqianli
 * 2017年5月6日
 */
@Controller
@RequestMapping("/bis/store")
public class BisStoreController {
	
	@Autowired
	private IBisStoreService iBisStoreService;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/page")
	public BsgridVo<HashMap<String, Object>> paging(
			@RequestParam(defaultValue = "1") int curPage, 
			@RequestParam(defaultValue = "10")int pageSize, 
			HttpServletRequest req) {
		//查询条件
		String userId = req.getParameter("userId");
		String storeName = req.getParameter("storeName");
		String storeStatus = req.getParameter("storeStatus");
		Map<String, Object> parm= new HashMap<>();
		//非空判断
		if(ToolsUtil.isNotEmpty(userId)){
			parm.put("userId", userId);
		}
		if(ToolsUtil.isNotEmpty(storeName)){
			parm.put("storeName", storeName);
		}
		if(ToolsUtil.isNotEmpty(storeStatus)){
			parm.put("storeStatus", Integer.parseInt(storeStatus));
		}
		return iBisStoreService.page(parm, curPage, pageSize);
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/selectById")
	public Result viwe(@RequestParam Long Id, HttpServletRequest req) {
		return iBisStoreService.selectById(Id);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/insert")
	public Result save(@RequestBody BisStore vo, HttpServletRequest req) {
		return iBisStoreService.saveStore(vo);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/deleteById")
	public Result delete(@RequestParam Long Id, HttpServletRequest req) {
		return null;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/updateById")
	public Result update(@RequestBody BisStore vo, HttpServletRequest req) {
		return iBisStoreService.updateById(vo);
	}

}
