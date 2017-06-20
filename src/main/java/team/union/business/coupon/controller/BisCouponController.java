package team.union.business.coupon.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import team.union.basic_data.com.cfg.BasicDataConfig;
import team.union.basic_data.com.cfg.BasicDataConfig.RESULT_STATE;
import team.union.basic_data.com.rs.BsgridVo;
import team.union.basic_data.com.rs.Result;
import team.union.business.coupon.model.BisCoupon;
import team.union.business.coupon.service.IBisCouponService;
import team.union.business.coupon.service.IBisCouponUserService;
import team.union.nonbusiness.util.ToolsUtil;

/**
 * 
* <p>标题: BisCouponController.java</p>
* <p>业务描述:</p>
* <p>类描述:优惠券管理</p>
* @author zh
* @date 2017年4月25日 
* @version V1.0
 */
@Controller
@RequestMapping("/bis/coupon")
public class BisCouponController {
	
	@Autowired
	private IBisCouponService couponService;
	
	@Autowired
	private IBisCouponUserService couponUserService;

	/**
	 * 
	* 方法名:  queryCoupon
	* 方法功能描述:    根据名称状态分页查询优惠券
	* @param curPage
	* @param pageSize
	* @param req
	* @return
	* @return BsgridVo<HashMap<String,Object>>    
	* @Author:   zh
	* @Create Date:   2017年4月27日
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/queryAll")
	@ResponseBody
	public BsgridVo<HashMap<String, Object>> queryCoupon(
			HttpServletRequest req){
		int curPage = 1;
		int pageSize = 10;
		if(null!=req.getAttribute("curPage") && null!=req.getAttribute("pageSize")){
			curPage = (int) Double.parseDouble(req.getAttribute("curPage").toString());
			pageSize = (int) Double.parseDouble(req.getAttribute("pageSize").toString());
		}
		String couponName = req.getParameter("couponName");
		String couponStatus = req.getParameter("couponStatus");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("couponName", couponName);
		if (ToolsUtil.isNotEmpty(couponStatus)) {
			map.put("couponStatus", Integer.parseInt(couponStatus));
		}
		return couponService.paging(map, curPage, pageSize);
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	@ResponseBody
	public Result view(@RequestParam Long id, HttpServletRequest req) {
		return couponService.selById(id);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Result update(@RequestBody BisCoupon vo, HttpServletRequest req) {
		return couponService.update(vo);
	}
	//逻辑删除优惠券
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Result delete(HttpServletRequest req) {
		String id = req.getParameter("couponId");
		return couponService.delById(Long.parseLong(id));
	}
	
	@RequestMapping(value = "/findBan", method = RequestMethod.POST)
	@ResponseBody
	public Result findBan(HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		BsgridVo<HashMap<String, Object>> vo = couponService.paging(map, 1, 1);
		List<HashMap<String, Object>> pageData = vo.getData();
		BisCoupon coupon = new BisCoupon();
		Result result = new Result();
		if (pageData != null && pageData.size() > 0) {
			Integer ban = (Integer) pageData.get(0).get("coupon_isBan");
			coupon.setCouponIsBan(ban);
			result.setState(RESULT_STATE.SUCCESS.getNumber().toString());
			result.setMsg(RESULT_STATE.SUCCESS.getMsg());
			result.setData(coupon);
		}else{
			result.setState(RESULT_STATE.FAIL.getNumber().toString());
			result.setMsg(RESULT_STATE.FAIL.getMsg());
		}
		return result;
	}
	
	//改变所有优惠券是否可使用
	@RequestMapping(value = "/isBan", method = RequestMethod.POST)
	@ResponseBody
	public Result isEnable(HttpServletRequest req) {
		String couponIsBan = req.getParameter("couponIsBan");
		return couponService.batchBan(Integer.parseInt(couponIsBan));
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result add(@RequestBody BisCoupon vo, HttpServletRequest req) {
		Date date = vo.getCouponValidityStart();
		Date now = new Date();
		if (now.getTime() > date.getTime()) {
			vo.setCouponStatus(BasicDataConfig.COUPON_STATE.online.getValue());
		}else{
			vo.setCouponStatus(BasicDataConfig.COUPON_STATE.ineffective.getValue());
		}
		return couponService.add(vo);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/queryReceiveRecord")
	@ResponseBody
	public BsgridVo<HashMap<String, Object>> queryCouponUserReceive(
			HttpServletRequest req) throws Exception{
		int curPage = 1;
		int pageSize = 10;
		if(null!=req.getAttribute("curPage") && null!=req.getAttribute("pageSize")){
			curPage = (int) Double.parseDouble(req.getAttribute("curPage").toString());
			pageSize = (int) Double.parseDouble(req.getAttribute("pageSize").toString());
		}
		String startDate = req.getParameter("startDate");
		String endDate = req.getParameter("endDate");
		String couponName = req.getParameter("couponName");
		String couponStatus = req.getParameter("couponStatus");
		DateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> map = new HashMap<String, Object>();
		if (ToolsUtil.isNotEmpty(startDate)) {
			map.put("startDate", ft.parse(startDate+":00"));
		}
		if (ToolsUtil.isNotEmpty(endDate)) {
			map.put("endDate", ft.parse(endDate+":00"));
		}
		if (ToolsUtil.isNotEmpty(couponStatus)) {
			map.put("couponStatus", Integer.parseInt(couponStatus));
		}
		map.put("couponName", couponName);
		return couponUserService.paging(map, curPage, pageSize);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/queryUseRecord")
	@ResponseBody
	public BsgridVo<HashMap<String, Object>> queryUseRecord(
			HttpServletRequest req) throws Exception{
		int curPage = 1;
		int pageSize = 10;
		if(null!=req.getAttribute("curPage") && null!=req.getAttribute("pageSize")){
			curPage = (int) Double.parseDouble(req.getAttribute("curPage").toString());
			pageSize = (int) Double.parseDouble(req.getAttribute("pageSize").toString());
		}
		String startDate = req.getParameter("startDate");
		String endDate = req.getParameter("endDate");
		String couponName = req.getParameter("couponName");
		String couponStatus = req.getParameter("couponStatus");
		DateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> map = new HashMap<String, Object>();
		if (ToolsUtil.isNotEmpty(startDate)) {
			map.put("startDate", ft.parse(startDate+":00"));
		}
		if (ToolsUtil.isNotEmpty(endDate)) {
			map.put("endDate", ft.parse(endDate+":00"));
		}
		if (ToolsUtil.isNotEmpty(couponStatus)) {
			map.put("couponStatus", Integer.parseInt(couponStatus));
		}
		map.put("couponName", couponName);
		return couponUserService.usePaging(map, curPage, pageSize);
	}
	
	@RequestMapping(value = "/img/{path}",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String showImg(@PathVariable("path")String path,HttpServletRequest req,
			HttpServletResponse response) {
		if (!ToolsUtil.isNotEmpty(path)) {
			return "false";
		}
		 /*FileInputStream fis = null;  
	     OutputStream os = null;
	     String basePath = "";  //图片储存 文件夹
	     try {  
	    	 	path = couponService.getImgUrl(path);
	    	 	if (ToolsUtil.isNotEmpty(path)) {
	    	 		fis = new FileInputStream(basePath+path);  
		            os = response.getOutputStream();  
		            int count = 0;  
		            byte[] buffer = new byte[1024 * 8];  
		            while ((count = fis.read(buffer)) != -1) {  
		                os.write(buffer, 0, count);  
		                os.flush();  
		            }  
				}
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        try {  
	            fis.close();  
	            os.close();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  */
	        return "ok";  
	}
}
