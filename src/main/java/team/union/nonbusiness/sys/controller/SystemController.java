/**
 * 
 */
package team.union.nonbusiness.sys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import team.union.nonbusiness.com.rs.ResultVo;
import team.union.nonbusiness.interceptor.utils.InterceptorUtils;
import team.union.nonbusiness.sys.model.Users;
import team.union.nonbusiness.sys.service.ResourceService;
import team.union.nonbusiness.sys.utils.Tree;




/**
 * @author Jack Zhang
 *
 */
@Controller
public class SystemController {
	
	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(ModelMap map,HttpServletRequest req) {
		Users user = InterceptorUtils.getUser(req);
		Tree tree = resourceService.findMenuByUserId(user.getUserId());
		map.put("menu", tree);
		return  "index.html";
	}
	
	@RequestMapping(value = "/au/fail", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo fail(ModelMap map,HttpServletRequest req,HttpServletResponse rep) {
		rep.setStatus(HttpServletResponse.SC_FORBIDDEN);
		ResultVo re = new ResultVo();
		return re;
	}
}
