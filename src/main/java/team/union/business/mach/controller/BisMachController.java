package team.union.business.mach.controller;

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

import team.union.basic_data.com.interf.IController;
import team.union.basic_data.com.rs.BsgridVo;
import team.union.basic_data.com.rs.Result;
import team.union.business.mach.model.BisMach;
import team.union.business.mach.servie.IBisMachService;
import team.union.nonbusiness.util.ToolsUtil;

@Controller
@RequestMapping("/bis/mach")
public class BisMachController implements IController<BisMach>{

	@Autowired
	private IBisMachService bisMachService;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/page")
	public BsgridVo<HashMap<String, Object>> paging(
			@RequestParam(defaultValue = "1") int curPage,
			@RequestParam(defaultValue = "10") int pageSize,
			HttpServletRequest req) {
		String areaCode = req.getParameter("areaCode");
		Map<String, Object> parm = new HashMap<String,Object>();
		if(ToolsUtil.isNotEmpty(areaCode)){
			parm.put("areaCode", areaCode);
		}
		return bisMachService.paging(parm, curPage, pageSize);
	}

	@RequestMapping(value = "/view", method = RequestMethod.POST)
	@ResponseBody
	public Result viwe(@RequestParam Long id, HttpServletRequest req) {
		return bisMachService.selById(id);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result save(@RequestBody BisMach vo,HttpServletRequest req) {
		return bisMachService.add(vo);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Result update(@RequestBody BisMach vo,HttpServletRequest req) {
		return bisMachService.update(vo);
	}

	@RequestMapping(value = "/del", method = RequestMethod.POST)
	@ResponseBody
	public Result delete(@RequestParam Long id, HttpServletRequest req) {
		return bisMachService.delById(id);
	}

}