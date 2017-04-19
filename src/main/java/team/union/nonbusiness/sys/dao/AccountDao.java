package team.union.nonbusiness.sys.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.union.nonbusiness.com.interf.Mapper;
import team.union.nonbusiness.sys.model.Account;



public interface AccountDao extends Mapper<Account> {
	
	/**
	 * 定义分页查询
	 * @param Map params 参数
	 * @param Pagination 分页对象,继承自RowBounds,传如该参数,将默认进行分页
	 * */
	public List<HashMap<String, Object>> selectAll(Map<String, Object> params);
	
	
	/**
	 * 修改密码
	 * @param params
	 * @author	yinxb
	 * @Date	2016-1-14 下午9:13:36
	 */
	public void updatePassword(Map<String, Object> params);
}