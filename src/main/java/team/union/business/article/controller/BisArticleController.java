package team.union.business.article.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import team.union.basic_data.com.rs.BsgridVo;
import team.union.basic_data.com.rs.Result;
import team.union.business.article.model.BisArticle;
import team.union.business.article.service.IBisArticleService;

/**
 * 
* 标题: BisArticleController.java
* 类描述: 文章类
* @author zh
* @date 2017年5月17日 
* @version 1.0
 */
@Controller
@RequestMapping("/bis/article")
public class BisArticleController {

	@Autowired
	private IBisArticleService articleService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/paging")
	@ResponseBody
	public BsgridVo<HashMap<String, Object>> queryCoupon(
			HttpServletRequest req){
		int curPage = 1;
		int pageSize = 10;
		if(null!=req.getAttribute("curPage") && null!=req.getAttribute("pageSize")){
			curPage = (int) Double.parseDouble(req.getAttribute("curPage").toString());
			pageSize = (int) Double.parseDouble(req.getAttribute("pageSize").toString());
		}
		Map<String, Object> map = new  HashMap<>();
		String artTitle = req.getParameter("artTitle");
		String artPlace = req.getParameter("artPlace");
		String artTop = req.getParameter("artTop");
		Integer top = null;
		if (StringUtils.isNotBlank(artTop)) {
			top = Integer.parseInt(artTop);
		}
		String startDate = req.getParameter("startDate");
		String endDate = req.getParameter("endDate");
		if (!StringUtils.isEmpty(startDate)) {
			startDate += ":00";
		}
		if (!StringUtils.isEmpty(endDate)) {
			endDate += ":00";
		}
		map.put("artTitle", artTitle);
		map.put("artPlace", artPlace);
		map.put("artTop", top);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return articleService.selMap(map, curPage, pageSize);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/findById")
	@ResponseBody
	public Result findById(@RequestParam Long id,HttpServletRequest req){
		return articleService.findById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/update")
	@ResponseBody
	public Result update(@RequestBody BisArticle article,HttpServletRequest req){
		return articleService.update(article);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	@ResponseBody
	public Result create(@RequestBody BisArticle article,HttpServletRequest req){
		return articleService.insert(article);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/deleteById")
	@ResponseBody
	public Result deleteById(@RequestParam Long id,HttpServletRequest req){
		return articleService.deleteById(id);
	}
}
