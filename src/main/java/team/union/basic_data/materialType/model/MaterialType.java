package team.union.basic_data.materialType.model;

public class MaterialType {
    private Long materialTypeId;

    private Integer materialType;

    private String materialNum;

    private String materialName;

    private Integer materialState;

    private Long superiorMaterialId;

    private Long workProcedureId;

    private Integer level;

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

    public void setMaterialNum(String materialNum) {
        this.materialNum = materialNum == null ? null : materialNum.trim();
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName == null ? null : materialName.trim();
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
}