package team.union.we_chat.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import team.union.nonbusiness.com.rs.ResultVo;
import team.union.nonbusiness.httpClient.BiiwayHttpClientFactory;
import team.union.nonbusiness.util.ToolsUtil;

/**
 * Title: 获取楼盘展示信息
 * Description: 获取dcxs项目中楼盘数据
 * @author chens
 * @date 2017年6月29日
 * @version 1.0
 */
@Controller
@RequestMapping("/bld")
public class BldController {
	
	@RequestMapping("selOne")
	@ResponseBody
	public static ResultVo BldInfo(HttpServletRequest req){
		ResultVo rs = new ResultVo();
		if(null!=req.getAttribute("buildId")){
			String path = "http://121.40.18.193/dcxs/partner/build/selOne?buildId="+ (int)Double.parseDouble(req.getAttribute("buildId").toString());
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

}
