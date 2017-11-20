package team.union.we_chat.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import team.union.sys_sp.com.rs.ResultVo;
import team.union.sys_sp.httpClient.BiiwayHttpClientFactory;
import team.union.sys_sp.util.ToolsUtil;
import team.union.we_chat.com.cfg.BaseConfig.RESULT_STATE;

/**
 * Title: 获取楼盘展示信息
 * Description: 获取dcxs项目中楼盘数据
 * @author chens
 * @date 2017年6月29日
 * @version 1.0
 */
@Controller
@RequestMapping("/dcxs")
public class DcxsController {
	
	@RequestMapping("bld/selOne")
	@ResponseBody
	public static ResultVo BldInfo(HttpServletRequest req){
		ResultVo rs = new ResultVo();
		if(null!=req.getAttribute("buildId")){
			String path = "http://121.40.18.193:8080/dcxs/partner/build/selOne?buildId="+ (int)Double.parseDouble(req.getAttribute("buildId").toString());
			String result = BiiwayHttpClientFactory.getInstance()
			.getBiiwayHttpClient()
			.sendGet(path, new HashMap<String, String>());
			if(ToolsUtil.isNotEmpty(result)){
				rs.isSuccess();
				rs.setData(result);
			}
		}else{
			rs.isError();
		}
		return rs;
	}
	
	@RequestMapping("/shortMsg/sendShortMessage")
	@ResponseBody
	public static ResultVo sendShortMessage(HttpServletRequest req){
		ResultVo rs = new ResultVo();
		if(null!=req.getAttribute("mobile")){
			String path = "http://121.40.18.193:8080/dcxs/shortMessage/sendShortMessage?"
					 + "mobile="+ req.getAttribute("mobile").toString()
					 + "&codeType=3";
			String result = BiiwayHttpClientFactory.getInstance()
			.getBiiwayHttpClient()
			.sendGet(path, new HashMap<String, String>());
			if(ToolsUtil.isNotEmpty(result) && ToolsUtil.gsonToMap(result).get("status") !=null ){
				int status =  (int)Double.parseDouble(ToolsUtil.gsonToMap(result).get("status").toString());
				if(RESULT_STATE.SUCCESS.getNumber()==status)
				rs.isSuccess();
				rs.setData(result);
			}
		}
		return rs;
	}
	
	@RequestMapping("/shortMsg/checkInviterNumber")
	@ResponseBody
	public static ResultVo checkVaide(HttpServletRequest req){
		ResultVo rs = new ResultVo();
		if(null!=req.getAttribute("validateCode") && null!=req.getAttribute("mobile")){
			String path = "http://121.40.18.193:8080/dcxs/sys/user/checkInviterNumber/App?"
					 + "validateCode="+ req.getAttribute("validateCode").toString()
					 + "&mobile="+req.getAttribute("mobile");
			String result = BiiwayHttpClientFactory.getInstance()
			.getBiiwayHttpClient()
			.sendGet(path, new HashMap<String, String>());
			if(ToolsUtil.isNotEmpty(result) && RESULT_STATE.SUCCESS.getNumber().toString().equals(result)){
				rs.isSuccess();
			}
		}
		return rs;
	}
}
