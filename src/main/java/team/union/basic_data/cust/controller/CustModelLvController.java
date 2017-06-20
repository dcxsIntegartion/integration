package team.union.basic_data.cust.controller;

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
import team.union.basic_data.cust.model.CustModelLv;
import team.union.basic_data.cust.service.ICustModelLvService;
@Controller
@RequestMapping("/cust/modelLv")
public class CustModelLvController implements IController<CustModelLv>{

	@Autowired
	private ICustModelLvService custModelLvService;
	
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
		Map<String, Object> parm = new HashMap<String,Object>();
		return custModelLvService.paging(parm, curPage, pageSize);
	}

	@RequestMapping(value = "/view", method = RequestMethod.POST)
	@ResponseBody
	public Result viwe(@RequestParam Long id, HttpServletRequest req) {
		return custModelLvService.selById(id);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result save(@RequestBody CustModelLv vo,HttpServletRequest req) {
		return custModelLvService.add(vo);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Result update(@RequestBody CustModelLv vo,HttpServletRequest req) {
		return custModelLvService.update(vo);
	}

	@RequestMapping(value = "/del", method = RequestMethod.POST)
	@ResponseBody
	public Result delete(@RequestParam Long id, HttpServletRequest req) {
		return custModelLvService.delById(id);
	}
}
