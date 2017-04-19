package team.union.nonbusiness.sys.controller.domain;

import java.util.List;

public class UserRolesDomain {
	private Long userId;
	private List<Long> rolesId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<Long> getRolesId() {
		return rolesId;
	}

	public void setRolesId(List<Long> rolesId) {
		this.rolesId = rolesId;
	}
}
