/**
 * 
 */
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

import team.union.sys_sp.sys.controller.domain.RoleResourcesDomain;
import team.union.sys_sp.sys.service.RoleResourceService;
import team.union.sys_sp.sys.utils.Tree;




/**
 * @author Jack Zhang
 *
 */

@Controller
@RequestMapping("/sys/role_resource")
public class RoleResourceController {
	
	@Autowired
	private RoleResourceService roleResourceService;
	
	@RequestMapping(value = "/update_view", method = RequestMethod.GET)
	public String roleResourceView(ModelMap map,@RequestParam Long roleId) {
		map.put("roleId", roleId);
		return "sys_sp/sys/role_resource/index.html";
	}
	

	// 根据角色编号查询角色拥有的资源
	@RequestMapping(value = "/loadResorucesByRoleId", method = RequestMethod.POST)
	@ResponseBody
	public Tree loadResorucesByRoleId(@RequestParam  long roleId,HttpServletRequest req) throws Exception {
		
		return roleResourceService.loadResourcesByRoleId(roleId);
		
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public RoleResourcesDomain update(
			@RequestBody RoleResourcesDomain roleResourcesDomain,HttpServletRequest req) {
		roleResourceService.updateRoleResources(roleResourcesDomain);
		return roleResourcesDomain;
	}
}
