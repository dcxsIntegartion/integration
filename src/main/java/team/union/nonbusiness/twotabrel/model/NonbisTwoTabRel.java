package team.union.nonbusiness.twotabrel.model;

public class NonbisTwoTabRel {
    private Long id;

    private Long bisId;

    private Long mainId;

    private Long slaveId;

    private String coler;

    private Integer bisType;

    private Integer state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBisId() {
        return bisId;
    }

    public void setBisId(Long bisId) {
        this.bisId = bisId;
    }

    public Long getMainId() {
        return mainId;
    }

    public void setMainId(Long mainId) {
        this.mainId = mainId;
    }

    public Long getSlaveId() {
        return slaveId;
    }

    public void setSlaveId(Long slaveId) {
        this.slaveId = slaveId;
    }

    public String getColer() {
        return coler;
    }

    public void setColer(String coler) {
        this.coler = coler == null ? null : coler.trim();
    }

    public Integer getBisType() {
        return bisType;
    }

    public void setBisType(Integer bisType) {
        this.bisType = bisType;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}