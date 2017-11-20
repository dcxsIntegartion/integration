/**
 * 
 */
package team.union.sys_sp.sys.utils;

import java.util.HashMap;
import java.util.List;

/**
 * @author Jack Zhang
 *
 */
public abstract class TreeResourcesWrapper extends TreeWrapper<HashMap<String, Object>>{

	@Override
	public void initTree(Tree root, List<HashMap<String, Object>> rows,String parentKey) {
		for (int i = 1; i < rows.size(); i++) {
			HashMap<String, Object> map = rows.get(i);
			if (String.valueOf(map.get(parentKey)).equals(root.getId())) {
				Tree node = wrapper(map);
				root.getChildren().add(node);
				initTree(node, rows,parentKey);
			}
		}
	}

}