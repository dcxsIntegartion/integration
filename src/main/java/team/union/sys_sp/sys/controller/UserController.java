/**
 * 
 */
package team.union.sys_sp.sys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import team.union.sys_sp.com.cfg.BaseConfig;
import team.union.sys_sp.com.excp.BusinessException;
import team.union.sys_sp.com.rs.BsgridVo;
import team.union.sys_sp.com.rs.ResultVo;
import team.union.sys_sp.sys.controller.domain.UserAccountDomain;
import team.union.sys_sp.sys.controller.domain.UserPasswordDomain;
import team.union.sys_sp.sys.model.Account;
import team.union.sys_sp.sys.model.Users;
import team.union.sys_sp.sys.service.UserService;
import team.union.sys_sp.sys.utils.WebUtils;
import team.union.sys_sp.util.ToolsUtil;


/**
 * @author Jack Zhang <br/>
 *         系统管理，用户管理controller
 */
@Controller
@RequestMapping("/sys/user")
public class UserController {
	
	private static Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/list")
	public BsgridVo<HashMap<String, Object>> list(
			HttpServletRequest req) throws Exception {
		int curPage = 1;
		int pageSize = 10;
		if(null!=req.getAttribute("curPage") && null!=req.getAttribute("pageSize")){
			curPage = (int) Double.parseDouble(req.getAttribute("curPage").toString());
			pageSize = (int) Double.parseDouble(req.getAttribute("pageSize").toString());
		}
		String roleName = null!=req.getAttribute("roleName")?req.getAttribute("roleName").toString():"";
		String accountLoginName = null!=req.getAttribute("accountLoginName")?req.getAttribute("accountLoginName").toString():"";
		String userName = null!=req.getAttribute("userName")?req.getAttribute("userName").toString():"";
		
		Map<String, Object> queryArgs = new HashMap<String,Object>();
		if(ToolsUtil.isNotEmpty(userName)){
			queryArgs.put("user_name","%"+ userName+"%");
		}
		if(ToolsUtil.isNotEmpty(roleName)){
			queryArgs.put("role_name",roleName+"%");
		}
		if(ToolsUtil.isNotEmpty(accountLoginName)){
			queryArgs.put("account_login_name","%"+ accountLoginName+"%");
		}
		return userService.findByPage(queryArgs, curPage, pageSize);
	}
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/roleLeftUserslist")
	public BsgridVo<HashMap<String, Object>> roleLeftUserslist(
			HttpServletRequest req) throws Exception {
		int curPage = 1;
		int pageSize = 10;
		if(null!=req.getAttribute("curPage") && null!=req.getAttribute("pageSize")){
			curPage = (int) Double.parseDouble(req.getAttribute("curPage").toString());
			pageSize = (int) Double.parseDouble(req.getAttribute("pageSize").toString());
		}
		String roleName = null!=req.getAttribute("roleName")?req.getAttribute("roleName").toString():"";
		
		HashMap<String, Object> queryArgs = new HashMap<String,Object>();
		if(ToolsUtil.isNotEmpty(roleName)){
			queryArgs.put("roleName","%"+ roleName+"%");
		}
		return userService.roleLeftUsersList(queryArgs, curPage, pageSize);
	}
	
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public ResultVo save(@RequestBody UserAccountDomain userAccountDomain) {
		ResultVo resultVo = new ResultVo();
		try {
			userService.save(userAccountDomain);
			resultVo.setStatus(BaseConfig.SUCCESS_STATUS);
		} catch (BusinessException e) {
			resultVo.setStatus(BaseConfig.FAILED_STATUS);
			resultVo.setInfo(e.getMessage());
		}
		return resultVo;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResultVo login(@RequestBody Account account,HttpServletRequest req) {
		ResultVo resultVo = new ResultVo();
		try {
			UserAccountDomain userAccountDomain = userService.login(account);
			req.getSession().setAttribute("user", userAccountDomain.getUsers());
			req.getSession().setAttribute("account", userAccountDomain.getAccount());
			WebUtils.newSession(req);
			resultVo.setStatus(1);
		} catch (BusinessException e) {
			resultVo.setStatus(0);
			resultVo.setInfo(e.getMessage());
		}
		return resultVo;
	}

	/**
	 * 退出登录
	 * @param request
	 * @return
	 * @author	yinxb
	 * @Date	2016-1-3 下午12:46:03
	 */
	@ResponseBody
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResultVo logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		ResultVo resultVo = new ResultVo();
		try {
			if (session.getAttribute("user") != null) {
				session.invalidate();
				resultVo.setStatus(1);
				return resultVo;
			} else {
				resultVo.setStatus(0);
				return resultVo;
			}
		} catch (Exception e) {
			resultVo.setStatus(0);
			return resultVo;
		}
	}
	
	/**
	 * 删除用户的信息<br/>
	 * 只删除账号信息
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResultVo delete(@PathVariable Long id) {
		ResultVo resultVo = new ResultVo();
		try {
			userService.delete(id);
			resultVo.setStatus(1);
		} catch (BusinessException e) {
			resultVo.setStatus(0);
			resultVo.setInfo(e.getMessage());
			logger.error(e.getMessage(),e);
		}
		return resultVo;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateView(@RequestParam Long id,ModelMap map) {
		try {
			map.put("users_account", userService.find(id));
			map.put("error_msg","");
		} catch (BusinessException e) {
			logger.error(e.getMessage(),e);
			map.put("error_msg", e.getMessage());
		}
		return "sys_sp/sys/user/userEdit.html";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseBody
	public ResultVo update(@RequestBody Users users) {
		ResultVo resultVo = new ResultVo();
		try {
			userService.updateUser(users);
			resultVo.setStatus(1);
		} catch (BusinessException e) {
			resultVo.setInfo(e.getMessage());
			resultVo.setStatus(0);
			logger.error(e.getMessage(),e);
		}
		return resultVo;
	}
	
	

	
	@RequestMapping(value = "/update_state", method = RequestMethod.PUT)
	@ResponseBody
	public ResultVo delete(@RequestParam Long accountId,@RequestParam int state) {
		ResultVo resultVo = new ResultVo();
		try {
			userService.updateState(accountId, state);
			resultVo.setStatus(1);
		} catch (BusinessException e) {
			resultVo.setStatus(0);
			resultVo.setInfo(e.getMessage());
		}
		return resultVo;
	}
	@RequestMapping(value = "/forUpdatePwd", method = RequestMethod.GET)
	public String forUpdatePwd(HttpServletRequest request,HttpServletResponse response) {
		try {
			Users user = WebUtils.getUser(request);
			request.setAttribute("users_account", userService.find(user.getUserId()));
		} catch (BusinessException e) {
			logger.error(e.getMessage(),e);
		}
		return "sys_sp/sys/user/pwdEdit.html";
	}
	
	@ResponseBody
	@RequestMapping("/updatePwd")
	public Object updatePwd(@ModelAttribute UserPasswordDomain userPasswordDomain,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userPasswordDomain.getUserId());
		map.put("oldPassword", userPasswordDomain.getOldPassword());
		map.put("accountLoginPassword", userPasswordDomain.getAccountLoginPassword());
		ResultVo resultVo =  userService.updatePwd(map);
		return resultVo;
	}
}