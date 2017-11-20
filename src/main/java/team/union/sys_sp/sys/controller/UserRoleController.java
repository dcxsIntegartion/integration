package team.union.sys_sp.sys.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import team.union.sys_sp.sys.controller.domain.UserRolesDomain;
import team.union.sys_sp.sys.service.UserRoleService;


/**
 * @author Jack Zhang
 *
 */
@Controller
@RequestMapping("/sys/user_role")
public class UserRoleController {
	
	@Autowired
	private UserRoleService userRoleService;
	
	@RequestMapping(value = "/update_view", method = RequestMethod.GET)
	public String roleResourceView(ModelMap map,@RequestParam Long userId) {
		map.put("userId", userId);
		map.put("roles", userRoleService.selectRoleSelected(userId));
		return "sys_sp/sys/user_role/index.html";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public UserRolesDomain save(@RequestBody  UserRolesDomain userRolesDomain,HttpServletRequest req) {
		userRoleService.save(userRolesDomain);
		return userRolesDomain;
	}
	
}
