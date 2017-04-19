package team.union.business.com.interf;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import team.union.basic_data.com.rs.BsgridVo;
import team.union.basic_data.com.rs.Result;

/**
 * Title: 控制层接口
 * @author chenS
 * @date 2017-3-25
 * @version 1.0
 */
public interface IController<E>{

	/** 分页 **/
	public BsgridVo<HashMap<String, Object>> paging(
			@RequestParam(defaultValue = "1") int curPage,
			@RequestParam(defaultValue = "10") int pageSize,
			HttpServletRequest req);
	/** 页面详情 **/
	public Result viwe(@RequestParam Long Id, HttpServletRequest req);
	/** 保存数据 **/
	public Result save(@RequestBody E vo,HttpServletRequest req);
	/** 修改数据 **/
	public Result update(@RequestBody E vo,HttpServletRequest req);
	/** 删除数据 **/
	public Result delete(@RequestParam Long Id, HttpServletRequest req);
}
