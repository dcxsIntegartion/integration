package team.union.business.coupon.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import team.union.basic_data.com.rs.BsgridVo;
import team.union.business.coupon.service.IBisCouponService;
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
			@RequestParam(defaultValue = "1") int curPage,
			@RequestParam(defaultValue = "10") int pageSize,
			HttpServletRequest req){
		String couponName = req.getParameter("couponName");
		String couponStatus = req.getParameter("couponStatus");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("couponName", couponName);
		if (ToolsUtil.isNotEmpty(couponStatus)) {
			map.put("couponStatus", Integer.parseInt(couponStatus));
		}
		return couponService.paging(map, curPage, pageSize);
	}
}
