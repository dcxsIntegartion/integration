/**
 * @(#)RoleResourcesDomain.java
 * Copyright 2013 成都百维科技有限公司, Inc. All rights reserved.
 */
package team.union.sys_sp.sys.controller.domain;

import java.util.List;

/**
 * @author Jack Zhang
 *
 */
public class RoleResourcesDomain {
	
	private Long roleId;
	private List<Long> resourcesIds;
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public List<Long> getResourcesIds() {
		return resourcesIds;
	}
	public void setResourcesIds(List<Long> resourcesIds) {
		this.resourcesIds = resourcesIds;
	}
	
}
