package team.union.nonbusiness.com.interf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.union.basic_data.com.rs.BsgridVo;
import team.union.basic_data.com.rs.Result;


/**
 * Title: 业务接口
 * @author chenS
 * @date 2017-3-25
 * @version 1.0
 */
public interface IService<E> {
	/** 分页数据   **/
	public BsgridVo<HashMap<String, Object>> paging(
			Map<String, Object> parm, int curPage, int pageSize);
	/** 查询多条或者单条数据   **/
	public List<HashMap<String, Object>> selMapLst(Map<String, Object> parm);
	public List<E> selVoLst(Map<String, Object> parm);
	/** 通过ID查询数据   **/
	public Result selById(Long Id);
	/** 新增数据   **/
	public Result add(E vo);
	/** 修改全部数据   **/
	public Result update(E vo);
	/** 修改非空数据   **/
	public Result updateBySelective(E vo);
	/** 删除数据   **/
	public Result delById(Long id);
	
}
