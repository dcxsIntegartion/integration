/**
 * 
 */
package team.union.sys_sp.sys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import team.union.sys_sp.com.rs.ResultVo;
import team.union.sys_sp.sys.model.Users;
import team.union.sys_sp.sys.service.ResourceService;
import team.union.sys_sp.sys.utils.Tree;
import team.union.sys_sp.sys.utils.WebUtils;




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
		Users user =WebUtils.getUser(req);
		
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
