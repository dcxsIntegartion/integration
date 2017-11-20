/**
 * 
 */
package team.union.sys_sp.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import team.union.sys_sp.com.excp.BusinessException;
import team.union.sys_sp.sys.controller.domain.ResourcesUpdateDomain;
import team.union.sys_sp.sys.dao.ResourcesDao;
import team.union.sys_sp.sys.dao.RoleResourcesPopedomDao;
import team.union.sys_sp.sys.model.Resources;
import team.union.sys_sp.sys.model.ResourcesCriteria;
import team.union.sys_sp.sys.model.RoleResourcesPopedomCriteria;
import team.union.sys_sp.sys.service.ResourceService;
import team.union.sys_sp.sys.utils.Tree;
import team.union.sys_sp.sys.utils.TreeResourcesWrapper;


/**
 * @author Jack Zhang
 *
 */
@Repository
@Transactional(rollbackFor = Exception.class)
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourcesDao resourcesDao;
	
	@Autowired
	private RoleResourcesPopedomDao roleResourcesPopedomDao;

	
	public Tree findResoucesTreeById(Long id, int check_state, boolean checked) {
		Resources resources = resourcesDao.selectByPrimaryKey(id);
		ResourcesCriteria resourcesCriteria = new ResourcesCriteria();
		resourcesCriteria.setOrderByClause("RESOURCES_LEVEL_NUMBER,RESOURCES_SORT_ID");
		resourcesCriteria.createCriteria().andResourcesParentIdEqualTo(id);
		List<Resources> child = resourcesDao.selectByExample(resourcesCriteria);
		Tree tree = wrapper(resources, resources.getResourcesChildSize(), check_state, checked);
		if (child != null && child.size() > 0) {
			for (Resources r : child) {
				tree.getChildren().add(wrapper(r, r.getResourcesChildSize(), check_state, checked));
			}
		}
		return tree;
	}

	private Tree wrapper(Resources r, int childSize, int check_state, boolean checked) {
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("resourcesSortId", r.getResourcesSortId());
		attributes.put("check_state", check_state);
		return new Tree(r.getResourcesId(), r.getResourcesName(), childSize, attributes, checked);
	}
	
	public Resources findById(Long resourcesId) {
		return resourcesDao.selectByPrimaryKey(resourcesId);
	}
	
	public void delete(Long resorucesId) throws BusinessException
	{
		
		
		//倒序查询,避免删除出现外键依赖
		ResourcesCriteria resourcesCriteria = new ResourcesCriteria();
		resourcesCriteria.createCriteria().andResourcesFullIdLike("%."+resorucesId+".%");
		resourcesCriteria.setOrderByClause("RESOURCES_ID DESC");
		List<Resources> resources = resourcesDao.selectByExample(resourcesCriteria);
		if(resources==null||resources.size()==0)
			throw new BusinessException("该资源信息不存在!");
		
		//如果存在子级资源
		if(resources.size()>0){
			List<Long> resroucesIds = new ArrayList<Long>();
			for(int i=0,j=resources.size();i<j;i++)
			{
				resroucesIds.add(resources.get(i).getResourcesId());
			}
			
			//判断资源是否已分配到具体的角色
			//查询该资源以及子资源是否被使用,判断该资源没分配角色信息
			RoleResourcesPopedomCriteria roleResourcesPopedomCriteria = new RoleResourcesPopedomCriteria();
			roleResourcesPopedomCriteria.createCriteria().andResourcesIdIn(resroucesIds);
			int count = roleResourcesPopedomDao.countByExample(roleResourcesPopedomCriteria);
			if(count>0)
				throw new BusinessException("该资源或其子资源已被使用分配到角色,不能删除!");
		}
		
		
		for(int i=0;i<resources.size();i++)
		{
			Resources rr =resources.get(i);
			if(rr.getResourcesId()==resorucesId)
			{
				//更新父节点 的childsize 个数
				Resources parent = resourcesDao.selectByPrimaryKey(rr.getResourcesParentId());
				parent.setResourcesChildSize(parent.getResourcesChildSize()-1);
				resourcesDao.updateByPrimaryKeySelective(parent);
			}
			resourcesDao.deleteByPrimaryKey(rr.getResourcesId());
		}
		
		
	}
	
	public Resources save(Resources resources) {
		
		resources.setResourcesIsPopedoms((byte)0);
		resources.setResourcesIsVisable((byte)1);
		resources.setResourcesCreateDate(new Date());
		//插入当前点
		Resources parent = resourcesDao.selectByPrimaryKey(resources.getResourcesParentId());
		resources.setResourcesLevelNumber(parent.getResourcesLevelNumber() + 1);
		resources.setResourcesChildSize(0);
		resources.setResourcesSortId(parent.getResourcesChildSize()+1);
		resourcesDao.insert(resources);
		
		
		//插入后修改full_id ,full_name
		resources.setResourcesFullId(parent.getResourcesFullId()+ resources.getResourcesId() + ".");
		resources.setResourcesFullName(parent.getResourcesFullName()+ resources.getResourcesName() + ".");
		resourcesDao.updateByPrimaryKeySelective(resources);
		
		//更新parent信息
		parent.setResourcesChildSize(parent.getResourcesChildSize()+1);
		resourcesDao.updateByPrimaryKeySelective(parent);
		return resources;
	}
	
	
	public void update(ResourcesUpdateDomain resourcesUpdateDomain)
	{
		Resources resources = resourcesUpdateDomain.getResources();
		resourcesDao.updateByPrimaryKeySelective(resources);
		List<Resources> sortable =resourcesUpdateDomain.getResourcesSortable();
		//更新排序信息
		if(sortable!=null&&sortable.size()>0)
		{
			for(int i=0,length=sortable.size();i<length;i++)
			{
				resourcesDao.updateByPrimaryKeySelective(sortable.get(i));
			}
		}
		
	}

	
	public Resources findParent(Long childId) {
		Resources resources = resourcesDao.selectByPrimaryKey(childId);
		Resources parent = resourcesDao.selectByPrimaryKey(resources.getResourcesParentId());
		return parent;
	}

	
/*	public Tree findMenuByUserId(Long userId) {
		List<HashMap<String, Object>> rows =resourcesDao.findByUserId(userId);
		if(rows==null||rows.size()==0) return null;
		HashMap<String, Object> rootMap = rows.get(0);
		TreeResourcesWrapper tree= new TreeResourcesWrapper() {
			
			public Tree wrapper(HashMap<String, Object> row) {
				return new Tree(String.valueOf(row.get("resources_id")),String.valueOf(row.get("resources_name")), Tree.TREE_NODE_STATE_DEFAULT,row);
			}
		};
		Tree root =tree.wrapper(rootMap);
		tree.initTree(root, rows, "resources_parent_id");
		return root;
	}*/
	
	public Tree findMenuByUserId(Long userId)
	{
		List<HashMap<String, Object>> rows =resourcesDao.findByUserId(userId);
		if(rows==null||rows.size()==0) return null;
		HashMap<String, Object> rootMap = rows.get(0);
		if(!String.valueOf(rootMap.get("resources_id")).equals("1")){
			HashMap<String, Object> top= new HashMap<String, Object>();
			top.put("resources_id", 1);
			top.put("resources_name", "资源视图");
			top.put("resources_parent_id", null);
			
			rows.add(0, top);
		}
		HashMap<String, Object> rootMapNew = rows.get(0);
		TreeResourcesWrapper tree= new TreeResourcesWrapper() {
			
			public Tree wrapper(HashMap<String, Object> row) {
				return new Tree(String.valueOf(row.get("resources_id")),String.valueOf(row.get("resources_name")), Tree.TREE_NODE_STATE_DEFAULT,row);
			}
		};
		Tree root =tree.wrapper(rootMapNew);
		tree.initTree(root, rows, "resources_parent_id");
		return root;
	}

}
