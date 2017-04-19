package team.union.nonbusiness.twotabrel.controller;

import java.util.HashMap;

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
import team.union.nonbusiness.com.interf.IController;
import team.union.nonbusiness.twotabrel.Service.INonbisTwoTabRelService;
import team.union.nonbusiness.twotabrel.model.NonbisTwoTabRel;

@Controller
@RequestMapping("/bis/machRel")
public class NonbisTwoTabRelController implements IController<NonbisTwoTabRel>{

	@Autowired
	private INonbisTwoTabRelService nonbisTwoTabRelService;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/page")
	public BsgridVo<HashMap<String, Object>> paging(
			@RequestParam(defaultValue = "1") int curPage,
			@RequestParam(defaultValue = "10") int pageSize,
			HttpServletRequest req) {
		return null;
	}

	@RequestMapping(value = "/view", method = RequestMethod.POST)
	@ResponseBody
	public Result viwe(@RequestParam Long id, HttpServletRequest req) {
		return nonbisTwoTabRelService.selById(id);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result save(@RequestBody NonbisTwoTabRel vo,HttpServletRequest req) {
		return nonbisTwoTabRelService.add(vo);
		
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Result update(@RequestBody NonbisTwoTabRel vo,HttpServletRequest req) {
		return nonbisTwoTabRelService.update(vo);
	}

	@RequestMapping(value = "/del", method = RequestMethod.POST)
	@ResponseBody
	public Result delete(@RequestParam Long id, HttpServletRequest req) {
		return nonbisTwoTabRelService.delById(id);
	}

}