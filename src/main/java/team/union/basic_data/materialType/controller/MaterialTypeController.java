package team.union.basic_data.materialType.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import team.union.nonbusiness.com.cfg.BaseConfig;
import team.union.nonbusiness.com.rs.BsgridVo;
import team.union.nonbusiness.com.rs.ResultVo;
import team.union.basic_data.materialType.model.MaterialType;
import team.union.basic_data.materialType.service.IMaterialTypeService;
import team.union.basic_data.materialType.vo.MaterialTypeVo;
import team.union.nonbusiness.sys.model.Users;
import team.union.nonbusiness.sys.utils.WebUtils;
import team.union.nonbusiness.util.IPUtil;

@Controller
@RequestMapping("/nonbis/materialType")
public class MaterialTypeController {
	@Autowired
	private IMaterialTypeService materialTypeService;
	
	@RequestMapping("/findAllTreeNode")
	@ResponseBody
	public String findAllTreeNode() {
		Gson gson = new Gson();
		return gson.toJson(materialTypeService.findAllTreeNode());
	}
	
	@RequestMapping("/findAllImportantAndUsingTreeNode")
	@ResponseBody
	public String findAllImportantAndUsingTreeNode() {
		Gson gson = new Gson();
		return gson.toJson(materialTypeService.findAllImportantAndUsingTreeNode());
	}
	
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public ResultVo saveOrUpdate(@RequestBody MaterialTypeVo vo,HttpServletRequest req) {
		ResultVo resultVo = new ResultVo();
		try {
			Users users = WebUtils.getUser(req);
			vo.setIp(IPUtil.getIp2(req));
			vo.setUsers(users);
			resultVo = materialTypeService.saveOrUpdateMaterialType(vo);
		}
		catch (Exception e) {
			resultVo.setData(e.getMessage());
			resultVo.setStatus(BaseConfig.FAILED_STATUS);
			resultVo.setInfo("请求失败");
			e.printStackTrace();
		}
		return resultVo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/listByWorkProcedureId",method = RequestMethod.POST)
	public BsgridVo<MaterialType> listByWorkProcedureId(
			@RequestParam(defaultValue = "-1") Long workProcedureId,
			@RequestParam(defaultValue = "10") Integer pageSize, 
			@RequestParam(defaultValue = "1") Integer curPage) throws Exception {
		Map<String, Object> queryArgs = new HashMap<String,Object>();
		queryArgs.put("workProcedureId", workProcedureId);

		return materialTypeService.list(queryArgs, curPage, pageSize);
	}
	
	@RequestMapping(value = "/addToGroup", method = RequestMethod.POST)
	@ResponseBody
	public ResultVo addToGroup(@RequestBody Map<String, Object> params,HttpServletRequest req) {
		ResultVo resultVo = new ResultVo();
		try {
			Users users = WebUtils.getUser(req);
			params.put("userId", users.getUserId());
			params.put("ip", IPUtil.getIp2(req));
			resultVo = materialTypeService.addToGroup(params);
		}
		catch (Exception e) {
			resultVo.setData(e.getMessage());
			resultVo.setStatus(BaseConfig.FAILED_STATUS);
			resultVo.setInfo("请求失败");
		}
		return resultVo;
	}
	@RequestMapping(value = "/removeFromGroup", method = RequestMethod.POST)
	@ResponseBody
	public ResultVo removeFromGroup(@RequestBody Map<String, Object> params,HttpServletRequest req) {
		ResultVo resultVo = new ResultVo();
		try {
			Users users = WebUtils.getUser(req);
			params.put("userId", users.getUserId());
			params.put("ip", IPUtil.getIp2(req));
			resultVo = materialTypeService.removeFromGroup(params);
		}
		catch (Exception e) {
			resultVo.setData(e.getMessage());
			resultVo.setStatus(BaseConfig.FAILED_STATUS);
			resultVo.setInfo("请求失败");
		}
		return resultVo;
	}
}
