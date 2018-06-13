package team.union.business.store.service;
/**
 * 
 *描述：店铺业务层接口
 * @author Shuqianli
 * 2017年5月6日
 */

import java.util.HashMap;
import java.util.Map;

import team.union.business.com.rs.BsgridVo;
import team.union.business.com.rs.Result;
import team.union.business.store.model.BisStore;

public interface IBisStoreService {
	
	/**
	 * 店铺列表查询
	 * @param parm 查询条件
	 * @param curPage 页数
	 * @param pageSize 行数
	 * @return
	 */
	public BsgridVo<HashMap<String, Object>> page(
			Map<String, Object> parm, int curPage, int pageSize);
	

	/**
	 * 添加店铺
	 * @param bisStore
	 * @return
	 */
	public Result saveStore(BisStore bisStore);
	
	/**
	 * 根据id更新店铺
	 * @param bisStore
	 * @return
	 */
	public Result updateById(BisStore bisStore);
	
	/**
	 * 根据查询
	 * @param bisStore
	 * @return
	 */
	public Result selectById(Long id);
	
	public Result selByServiceName(String appid);
}
