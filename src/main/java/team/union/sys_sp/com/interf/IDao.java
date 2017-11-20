package team.union.sys_sp.com.interf;

/**
 * * Author: Zhang Qi <br>
 * Date: 2013-6-26 <br>
 * Version: v2.0
 * K LinkedHashMap
 */
public interface IDao<E> {

	public int deleteByPrimaryKey(Long id);
	public int deleteByPrimaryKey(String id);

	public int insert(E record);

	public E selectByPrimaryKey(Long arg0);
	public E selectByPrimaryKey(String arg0);
	/** 修改全部 **/
	public int updateByPrimaryKey(E arg0);
	/** 修改非空部分 **/
	public int updateByPrimaryKeySelective(E arg0);
}
