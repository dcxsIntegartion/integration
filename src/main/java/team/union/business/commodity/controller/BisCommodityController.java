package team.union.business.commodity.controller;

import java.util.ArrayList;
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

import com.github.pagehelper.Page;
import com.google.gson.Gson;

import team.union.basic_data.com.rs.BsgridVo;
import team.union.basic_data.com.rs.Result;
import team.union.business.commodity.model.BisCommodity;
import team.union.business.commodity.service.IBisCommodityService;
import team.union.nonbusiness.util.ToolsUtil;

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
		String commodityName = req.getParameter("commodityName");
		String commodityType = req.getParameter("commodityType");
		String commodityStatus = req.getParameter("commodityStatus");
		map.put("commodityStatus", commodityStatus);
		map.put("commodityTypeId", commodityType);
		map.put("commodityName", commodityName);
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
	public Result findOne(@RequestParam Long id,HttpServletRequest req){
		return commodityService.selById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/dele")
	@ResponseBody
	public Result delete(@RequestParam Long[] ids,HttpServletRequest req){
		List<Long> list = Arrays.asList(ids);
		return commodityService.batchDel(list);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/batchOn")
	@ResponseBody
	public Result batchOn(@RequestParam Long[] ids,HttpServletRequest req){
		List<Long> list = Arrays.asList(ids);
		return commodityService.batchOnSale(list);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/batchOff")
	@ResponseBody
	public Result batchOff(@RequestParam Long[] ids,HttpServletRequest req){
		List<Long> list = Arrays.asList(ids);
		return commodityService.batchOffSale(list);
	}
	
	/*******************活动商品接口**************************/
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.POST, value = "/getavtivityCommodity")
	@ResponseBody
	public BsgridVo<HashMap<String, Object>> getavtivityCommodity(
			@RequestParam(defaultValue = "1") int curPage,
			@RequestParam(defaultValue = "10") int pageSize,
			HttpServletRequest req){
		//初始化列表对象
		BsgridVo<HashMap<String, Object>> bsgridVo = new BsgridVo<>();
//		List<HashMap<String, Object>> data = (List)new ArrayList<>();
		Page<HashMap<String, Object>> page = new Page<>(curPage, pageSize);//(Page<HashMap<String, Object>>) data;
		bsgridVo.setCurPage(1);
		bsgridVo.setData(page);
		bsgridVo.setTotalRows(page.getTotal());
		bsgridVo.setSuccess(true);
		//参数封装
		Gson gson = new Gson();
		HashMap<String, Object> param = new HashMap<>();
		String selectedCommodities = req.getParameter("selectedCommodities");
		String storeId = req.getParameter("storeId");
		String activityId = req.getParameter("activityId");//活动id
		String activityType = req.getParameter("activityType");//活动类型
		String selected = req.getParameter("selected");//查询类型ture:获取选中的商品详情，false:获取未选择的商品详情
		List<Long> selectedCommoditiesList = new ArrayList<>();
		if (ToolsUtil.isNotEmpty(storeId) && ToolsUtil.isNotEmpty(selected)) {//店铺id、查询类型
			param.put("storeId", Long.parseLong(storeId));
			param.put("selected", Boolean.parseBoolean(selected));
		}else{
			return bsgridVo;
		}
		if (ToolsUtil.isNotEmpty(activityId)) {//活动id
			param.put("activityId", Long.parseLong(activityId));
			
		}
		if (ToolsUtil.isNotEmpty(activityType)) {//活动类型
			param.put("activityType", Byte.parseByte(activityType));
			
		}
		if (ToolsUtil.isNotEmpty(selectedCommodities) && !"[]".equals(selectedCommodities)){
			//商品id
			selectedCommoditiesList =  gson.fromJson(selectedCommodities, ArrayList.class);
			param.put("idList", selectedCommoditiesList);
		}else if (Boolean.parseBoolean(selected)) {
			return bsgridVo;
		}
		if (Boolean.parseBoolean(selected)) {
			//插件bug
			pageSize++;
		}
		//数据查询
		bsgridVo = commodityService.getavtivityCommodity(param, curPage, pageSize);
		return bsgridVo;
	}
}
