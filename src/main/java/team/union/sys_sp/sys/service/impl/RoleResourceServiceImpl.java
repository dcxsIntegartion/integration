/**
 * 
 */
package team.union.sys_sp.sys.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import team.union.sys_sp.sys.controller.domain.RoleResourcesDomain;
import team.union.sys_sp.sys.dao.RoleResourcesPopedomDao;
import team.union.sys_sp.sys.model.RoleResourcesPopedom;
import team.union.sys_sp.sys.model.RoleResourcesPopedomCriteria;
import team.union.sys_sp.sys.service.RoleResourceService;
import team.union.sys_sp.sys.utils.Tree;
import team.union.sys_sp.sys.utils.TreeResourcesWrapper;



/**
 * @author Jack Zhang
 *
 */
@Repository
@Transactional(rollbackFor = Exception.class)
public class RoleResourceServiceImpl implements RoleResourceService {

	@Autowired
	private RoleResourcesPopedomDao roleResourcesPopedomDao;

	
	public Tree findResorucesByRoleId(long roleId) {
		List<HashMap<String, Object>> rows = roleResourcesPopedomDao.getResourcesByRoleId(roleId);
		if (rows.size() == 0)
			return new Tree(1l, "模块视图", 0);
		HashMap<String, Object> rootMap = rows.get(0);
		TreeResourcesWrapper treeResourcesWrapper = new TreeResourcesWrapper() {
			
			public Tree wrapper(HashMap<String, Object> row) {
				int childsize = (Integer) row.get("resources_child_size");
				int rca = ((BigDecimal) row.get("rca")).intValue();
				String state = Tree.TREE_NODE_STATE_DEFAULT;
				if (childsize > 0 && rca == 0) {
					state = Tree.TREE_NODE_STATE_CLOSED;
				}
				return new Tree(String.valueOf(row.get("resources_id")), String.valueOf(row.get("resources_full_name")),
						state);
			}
		};
		Tree root = treeResourcesWrapper.wrapper(rootMap);
		treeResourcesWrapper.initTree(root, rows, "resources_parent_id");
		return root;
	}
	
	/**
	 * 更新角色的权限信息
	 * 
	 * 包括删除角色所对应的资源
	 * 以及新增角色所对应的资源
	 * */
	public void updateRoleResources(RoleResourcesDomain roleResourcesDomain) {
		Long roleId = roleResourcesDomain.getRoleId();
		List<Long> resources = roleResourcesDomain.getResourcesIds();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("role_id", roleId);
		params.put("resource_ids", resources);
		//客户端无参数提交上来,则表示肯定不权限的新增操作
		if(resources!=null&&resources.size()>0)
			addRoleResources(params);
		deleteRoleResources(params);
	}
	
	private void addRoleResources(Map<String, Object> params)
	{
		List<Long> resources =roleResourcesPopedomDao.selectInsertRoleResources(params);
		Long roleId = (Long)params.get("role_id");
		for(int i=0;i<resources.size();i++)
		{
			RoleResourcesPopedom rrp = new RoleResourcesPopedom(roleId, resources.get(i));
			roleResourcesPopedomDao.insert(rrp);
		}
	}
	
	
	private void deleteRoleResources(Map<String, Object> params)
	{
		List<Long> delete =roleResourcesPopedomDao.selectDeleteRoleResources(params);
		if(delete.size()==0) return;
		RoleResourcesPopedomCriteria roleResourcesPopedomCriteria =new RoleResourcesPopedomCriteria();
		roleResourcesPopedomCriteria.createCriteria()
		.andRoleIdEqualTo((Long)params.get("role_id"))
		.andResourcesIdIn(delete);
		List<RoleResourcesPopedom> list = roleResourcesPopedomDao.selectByExample(roleResourcesPopedomCriteria);
		
		if(list!=null&&list.size()>0)
		{
			for(int i=0;i<list.size();i++)
				roleResourcesPopedomDao.deleteByPrimaryKey(list.get(i).getRoleResourcesId());
		}
		
	}

	public Tree loadResourcesByRoleId(long roleId) {
		List<HashMap<String, Object>> rows = roleResourcesPopedomDao.loadResourcesByRoleId(roleId);
		TreeResourcesWrapper treeResourcesWrapper = new TreeResourcesWrapper() {

			
			public void initTree(Tree root, List<HashMap<String, Object>> rows, String parentKey) {
				for (int i = 0; i < rows.size(); i++) {
					HashMap<String, Object> map = rows.get(i);
					if (null == map.get("resources_parent_id")) {
						continue;
					}
					if (String.valueOf(map.get(parentKey)).equals(root.getId())) {
						Tree node = wrapper(map);
						root.getChildren().add(node);
						initTree(node, rows, parentKey);
					}
				}
			}

			
			public Tree wrapper(HashMap<String, Object> row) {
				int childsize = (Integer) row.get("resources_child_size");
				boolean checked = false;
				int _rca = 0;
				Map<String, Object> attributes = new HashMap<String, Object>();
				Object rca = row.get("rca");
				if (rca != null) {
					_rca = ((BigDecimal) row.get("rca")).intValue();
					if (_rca == 0)
						checked = true;
				}
				String from = (String) row.get("from");
				if (from != null) {
					if (from.equals("parent") && _rca == 0) {
						attributes.put("check_state", 0);
					}
				}
				String state = Tree.TREE_NODE_STATE_DEFAULT;
				if (childsize > 0 && _rca == 0) {
					state = Tree.TREE_NODE_STATE_CLOSED;
				}
				return new Tree(String.valueOf(row.get("resources_id")), String.valueOf(row.get("resources_name")),
						state, checked, attributes);
			}
		};
		HashMap<String, Object> temp = null;
		for (HashMap<String, Object> hashMap : rows) {
			if (null == hashMap.get("resources_parent_id")) {
				temp = hashMap;
				break;
			}
		}

		Tree root = treeResourcesWrapper.wrapper(temp);
		root.setState("open");
		treeResourcesWrapper.initTree(root, rows, "resources_parent_id");
		return root;
	}

}
