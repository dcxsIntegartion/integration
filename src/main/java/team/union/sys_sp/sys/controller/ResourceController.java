/**
 * 
 */
package team.union.sys_sp.sys.controller;

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

import team.union.sys_sp.com.excp.BusinessException;
import team.union.sys_sp.com.rs.ResultVo;
import team.union.sys_sp.sys.controller.domain.ResourcesUpdateDomain;
import team.union.sys_sp.sys.model.Resources;
import team.union.sys_sp.sys.service.ResourceService;
import team.union.sys_sp.sys.utils.Tree;


/**
 * @author Jack Zhang
 *
 */
@Controller
@RequestMapping("/sys/resource")
public class ResourceController {

	@Autowired
	private ResourceService resourceService;

	/**
	 * 查询资源的树形结构<br/>
	 * 只显示一级，剩余部分动态加载
	 */
	@RequestMapping(value = "/tree", method = RequestMethod.POST)
	@ResponseBody
	public Tree findResourcesTree(@RequestParam(defaultValue = "1") long id,
			@RequestParam(defaultValue = "1") int check_state, @RequestParam(defaultValue = "false") boolean checked)
					throws Exception {
		return resourceService.findResoucesTreeById(id, check_state, checked);
	}

	@RequestMapping(value = "/find", method = RequestMethod.GET)
	@ResponseBody
	public Resources findById(@RequestParam Long id) {
		Resources resources = resourceService.findById(id);
		return resources;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Resources save(@RequestBody Resources resources, HttpServletRequest req) {
		return resourceService.save(resources);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public String saveView(@RequestParam long resourceId,ModelMap map) {
		 map.put("parent", resourceService.findById(resourceId));
		 return "sys_sp/sys/resource/addResource.html";
	}

	@RequestMapping(value = "/delete/{resourcesId}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResultVo delete(@PathVariable Long resourcesId, HttpServletRequest req) {

		ResultVo vo = new ResultVo();
		try {
			resourceService.delete(resourcesId);
			vo.setStatus(1);

		} catch (BusinessException ex) {
			vo.setStatus(0);
			vo.setInfo(ex.getMessage());
		}
		return vo;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseBody
	public Resources update(@RequestBody ResourcesUpdateDomain resources, HttpServletRequest request) {
		resourceService.update(resources);
		return resources.getResources();
	}
}
