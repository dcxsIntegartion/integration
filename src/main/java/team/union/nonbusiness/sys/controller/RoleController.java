/**
 * 
 */
package team.union.nonbusiness.sys.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import team.union.nonbusiness.com.excp.BusinessException;
import team.union.nonbusiness.com.rs.BsgridVo;
import team.union.nonbusiness.com.rs.ResultVo;
import team.union.nonbusiness.sys.model.Roles;
import team.union.nonbusiness.sys.model.RolesCriteria;
import team.union.nonbusiness.sys.service.RoleService;


/**
 * @author Jack Zhang
 *
 */
@Controller
@RequestMapping("/sys/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/list")
	public BsgridVo<Roles> list(
			HttpServletRequest req) {
		int curPage = 1;
		int pageSize = 10;
		if(null!=req.getAttribute("curPage") && null!=req.getAttribute("pageSize")){
			curPage = (int) Double.parseDouble(req.getAttribute("curPage").toString());
			pageSize = (int) Double.parseDouble(req.getAttribute("pageSize").toString());
		}
		String roleName = null!=req.getAttribute("roleName")?req.getAttribute("roleName").toString():"";
		RolesCriteria rolesCriteria = new RolesCriteria();
		RolesCriteria.Criteria criteria = rolesCriteria.createCriteria();
		criteria.andRoleIdNotEqualTo(1l);
		if (roleName.trim().length() != 0) {
			criteria.andRoleNameLike("%"+roleName+"%");
		}
		return roleService.queryForlist(rolesCriteria, curPage, pageSize);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public ResultVo save(@RequestBody Roles roles) {
		ResultVo resultVo = new ResultVo();
		try {
			roleService.save(roles);
			resultVo.setStatus(1);
		} catch (BusinessException e) {
			resultVo.setStatus(0);
			resultVo.setInfo(e.getMessage());
		}
		return resultVo;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseBody
	public ResultVo update(@RequestBody Roles roles) {
		ResultVo resultVo = new ResultVo();
		try {
			roleService.update(roles);
			resultVo.setStatus(1);
		} catch (BusinessException e) {
			resultVo.setStatus(0);
			resultVo.setInfo(e.getMessage());
		}
		return resultVo;
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResultVo delete(@PathVariable Long id) {
		ResultVo resultVo = new ResultVo();
		try {
			roleService.delete(id);
			resultVo.setStatus(1);
		} catch (BusinessException e) {
			resultVo.setStatus(0);
			resultVo.setInfo(e.getMessage());
		}
		return resultVo;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateView(ModelMap map,@RequestParam Long roleId) {
		try {
			map.put("role", roleService.findById(roleId));
			map.put("error_msg","");
		} catch (BusinessException e) {
			map.put("error_msg", e.getMessage());
		}
		return "nonbusiness/sys/role/roleEdit.html";
	}
	
	@RequestMapping(value = "/update_state", method = RequestMethod.PUT)
	@ResponseBody
	public ResultVo delete(@RequestParam Long roleId,@RequestParam byte state) {
		ResultVo resultVo = new ResultVo();
		try {
			Roles roles = new Roles();
			roles.setRoleId(roleId);
			roles.setRoleIsUse(state);
			roleService.updateState(roles);
			resultVo.setStatus(1);
		} catch (BusinessException e) {
			resultVo.setStatus(0);
			resultVo.setInfo(e.getMessage());
		}
		return resultVo;
	}
	
}
