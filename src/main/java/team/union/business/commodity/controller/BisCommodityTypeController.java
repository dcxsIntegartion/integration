package team.union.business.commodity.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import team.union.basic_data.com.rs.Result;
import team.union.business.commodity.model.BisCommodityType;
import team.union.business.commodity.service.IBisCommodityTypeService;

/**
 * 
* 标题: BisCommodityTypeController.java
* 业务描述: 商品分类
* 类描述:
* @author zh
* @date 2017年5月10日 
* @version V1.0
 */
@Controller
@RequestMapping("/bis/commodityType")
public class BisCommodityTypeController {

	@Autowired
	private IBisCommodityTypeService typeService;
	
	/**
	 * 
	* 方法名:          queryAll
	* 方法功能描述:    获取所有分类，非树结构
	* @return  
	* @return:        Result
	* @Author:        zh
	* @Create Date:   2017年5月10日
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/listAll")
	@ResponseBody
	public Result queryAll(){
		return typeService.listAll();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/listSelect")
	@ResponseBody
	public Result queryCommoSelect(){
		return typeService.listSelect();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/tree")
	@ResponseBody
	public Result getTree(){
		return typeService.typeTree();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/findOne")
	@ResponseBody
	public Result findDetail(@RequestParam BigDecimal id){
		return typeService.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/update")
	@ResponseBody
	public Result update(@RequestBody BisCommodityType type){
		return typeService.update(type);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	@ResponseBody
	public Result create(@RequestBody BisCommodityType type){
		return typeService.insert(type);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/addPage")
	public String addPage(@RequestParam BigDecimal parId,ModelMap map){
		BisCommodityType type = new BisCommodityType();
		type = (BisCommodityType) typeService.findOne(parId).getData();
		type.setTypeLevel(type.getTypeLevel()+1);
		map.put("parent", type);
		return "business/commodity/addType.html";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/delete/{id}")
	@ResponseBody
	public Result deleteByIds(@PathVariable BigDecimal id){
		List<BigDecimal> list = new ArrayList<>();
		list.add(id);
		return typeService.delete(list);
	}
}
