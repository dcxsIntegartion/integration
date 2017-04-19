/**
 * 
 */
package team.union.nonbusiness.sys.controller.domain;

import java.util.List;

import team.union.nonbusiness.sys.model.Resources;


/**
 * @author Jack Zhang
 *
 */
public class ResourcesUpdateDomain {
	//要修改的资源对象
	private Resources resources;
	//要修改的资源排序对象
	private List<Resources> resourcesSortable;

	public Resources getResources() {
		return resources;
	}

	public void setResources(Resources resources) {
		this.resources = resources;
	}

	public List<Resources> getResourcesSortable() {
		return resourcesSortable;
	}

	public void setResourcesSortable(List<Resources> resourcesSortable) {
		this.resourcesSortable = resourcesSortable;
	}
}
