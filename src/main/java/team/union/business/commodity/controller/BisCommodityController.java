package team.union.business.commodity.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
import team.union.business.commodity.model.BisCommodity;
import team.union.business.commodity.service.IBisCommodityService;

/**
 * 
* <p>标题: BisCommodityController.java</p>
* <p>业务描述:商品</p>
* <p>类描述:</p>
* @author zh
* @date 2017年5月7日 
* @version V1.0
 */
@Controller
@RequestMapping("/bis/commodity")
public class BisCommodityController {

	@Autowired
	private IBisCommodityService commodityService;
	
	/**
	 * 
	* 方法名:   queryCoupon
	* 方法功能描述:    商品分页 查询
	* @param curPage
	* @param pageSize
	* @param req
	* @return  
	* @return:        BsgridVo<HashMap<String,Object>>
	* @Author:        zh
	* @Create Date:   2017年5月7日
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/allPaging")
	@ResponseBody
	public BsgridVo<HashMap<String, Object>> queryCoupon(
			@RequestParam(defaultValue = "1") int curPage,
			@RequestParam(defaultValue = "10") int pageSize,
			HttpServletRequest req){
		Map<String, Object> map = new  HashMap<>();
		return commodityService.paging(map, curPage, pageSize);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	@ResponseBody
	public Result add(@RequestBody BisCommodity commodity,HttpServletRequest req){
		return commodityService.add(commodity);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/update")
	@ResponseBody
	public Result update(@RequestBody BisCommodity commodity,HttpServletRequest req){
		return commodityService.update(commodity);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/findOne")
	@ResponseBody
	public Result findOne(@RequestBody Long id,HttpServletRequest req){
		return commodityService.selById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/dele")
	@ResponseBody
	public Result delete(@RequestBody Long[] ids,HttpServletRequest req){
		List<Long> list = Arrays.asList(ids);
		return commodityService.batchDel(list);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/batchOn")
	@ResponseBody
	public Result batchOn(@RequestBody Long[] ids,HttpServletRequest req){
		List<Long> list = Arrays.asList(ids);
		return commodityService.batchOnSale(list);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/batchOff")
	@ResponseBody
	public Result batchOff(@RequestBody Long[] ids,HttpServletRequest req){
		List<Long> list = Arrays.asList(ids);
		return commodityService.batchOffSale(list);
	}
}
