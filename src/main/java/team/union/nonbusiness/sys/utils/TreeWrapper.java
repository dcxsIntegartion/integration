/**
 * 
 */
package team.union.nonbusiness.sys.utils;

import java.util.List;

/**
 * @author Jack Zhang
 *
 */
public abstract class TreeWrapper<T> {
	public abstract Tree wrapper(T r);
	public abstract void initTree(Tree root,List<T> rows,String pa);
	
}
