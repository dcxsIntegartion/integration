package team.union.sys_sp.com.interf;

import java.util.HashMap;
import java.util.List;
/**
 * 
 * * Author: Zhang Qi <br>
 * Date: 2013-6-26 <br>
 * Version: v2.0
 * 
 * 
 * K LinkedHashMap
 */
public interface Mapper<E> {

	public int countByExample(BaseCriteria baseCriteria);

	public int deleteByPrimaryKey(Long id);

	public int deleteByPrimaryKey(String id);
	
	public int deleteByExample(BaseCriteria baseCriteria);

	public int insert(E record);

	public List<E> selectByExample(BaseCriteria arg0);

	public E selectByPrimaryKey(Long arg0);

	public E selectByPrimaryKey(String arg0);
	
	public int updateByPrimaryKey(E arg0);

	public int updateByPrimaryKeySelective(E arg0);

	public int updateByExampleSelective(E arg0, BaseCriteria arg1);

	public int updateByExample(E arg0, BaseCriteria arg1);
	
	public int unRecomenCustomer(HashMap<String, Object> map);
}
