package team.union.basic_data.materialType.vo;

import java.util.List;

import team.union.sys_sp.sys.model.Users;


public class MaterialTypeVo {
	private Long materialTypeId;

	private Integer materialType;

	private String materialNum;

	private String materialName;

	private Integer materialState;

	private Long superiorMaterialId;

	private Long workProcedureId;

	private Integer level;
	
	private List<MaterialTypeVo> children;

	private boolean isParent;
	
	private Users users;
	
	private String ip;
	
	public MaterialTypeVo() {
		super();
	}

	public MaterialTypeVo(Long materialTypeId, Integer materialType, String materialNum, String materialName,
			Integer materialState, Long superiorMaterialId, Long workProcedureId, Integer level,
			List<MaterialTypeVo> children, boolean isParent) {
		super();
		this.materialTypeId = materialTypeId;
		this.materialType = materialType;
		this.materialNum = materialNum;
		this.materialName = materialName;
		this.materialState = materialState;
		this.superiorMaterialId = superiorMaterialId;
		this.workProcedureId = workProcedureId;
		this.level = level;
		this.children = children;
		this.isParent = isParent;
	}


	public Long getMaterialTypeId() {
		return materialTypeId;
	}

	public void setMaterialTypeId(Long materialTypeId) {
		this.materialTypeId = materialTypeId;
	}

	public Integer getMaterialType() {
		return materialType;
	}

	public void setMaterialType(Integer materialType) {
		this.materialType = materialType;
	}

	public String getMaterialNum() {
		return materialNum;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setMaterialNum(String materialNum) {
		this.materialNum = materialNum;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public Integer getMaterialState() {
		return materialState;
	}

	public void setMaterialState(Integer materialState) {
		this.materialState = materialState;
	}

	public Long getSuperiorMaterialId() {
		return superiorMaterialId;
	}

	public void setSuperiorMaterialId(Long superiorMaterialId) {
		this.superiorMaterialId = superiorMaterialId;
	}

	public Long getWorkProcedureId() {
		return workProcedureId;
	}

	public void setWorkProcedureId(Long workProcedureId) {
		this.workProcedureId = workProcedureId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public List<MaterialTypeVo> getChildren() {
		return children;
	}

	public void setChildren(List<MaterialTypeVo> children) {
		this.children = children;
	}


	public boolean isParent() {
		return isParent;
	}


	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}
	
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

}
