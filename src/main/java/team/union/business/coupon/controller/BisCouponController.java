package team.union.business.coupon.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import team.union.basic_data.com.rs.BsgridVo;

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

	@RequestMapping("/queryAll")
	@ResponseBody
	public BsgridVo<HashMap<String, Object>> queryCoupon(@RequestParam(defaultValue = "1") int curPage,
			@RequestParam(defaultValue = "10") int pageSize,HttpServletRequest req){
		
		return null;
	}
}
