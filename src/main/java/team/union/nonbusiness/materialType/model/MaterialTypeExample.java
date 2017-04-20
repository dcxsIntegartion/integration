package team.union.nonbusiness.materialType.model;

import java.util.ArrayList;
import java.util.List;

public class MaterialTypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MaterialTypeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andMaterialTypeIdIsNull() {
            addCriterion("material_type_id is null");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeIdIsNotNull() {
            addCriterion("material_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeIdEqualTo(Long value) {
            addCriterion("material_type_id =", value, "materialTypeId");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeIdNotEqualTo(Long value) {
            addCriterion("material_type_id <>", value, "materialTypeId");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeIdGreaterThan(Long value) {
            addCriterion("material_type_id >", value, "materialTypeId");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("material_type_id >=", value, "materialTypeId");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeIdLessThan(Long value) {
            addCriterion("material_type_id <", value, "materialTypeId");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeIdLessThanOrEqualTo(Long value) {
            addCriterion("material_type_id <=", value, "materialTypeId");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeIdIn(List<Long> values) {
            addCriterion("material_type_id in", values, "materialTypeId");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeIdNotIn(List<Long> values) {
            addCriterion("material_type_id not in", values, "materialTypeId");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeIdBetween(Long value1, Long value2) {
            addCriterion("material_type_id between", value1, value2, "materialTypeId");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeIdNotBetween(Long value1, Long value2) {
            addCriterion("material_type_id not between", value1, value2, "materialTypeId");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeIsNull() {
            addCriterion("material_type is null");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeIsNotNull() {
            addCriterion("material_type is not null");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeEqualTo(Integer value) {
            addCriterion("material_type =", value, "materialType");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeNotEqualTo(Integer value) {
            addCriterion("material_type <>", value, "materialType");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeGreaterThan(Integer value) {
            addCriterion("material_type >", value, "materialType");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("material_type >=", value, "materialType");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeLessThan(Integer value) {
            addCriterion("material_type <", value, "materialType");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeLessThanOrEqualTo(Integer value) {
            addCriterion("material_type <=", value, "materialType");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeIn(List<Integer> values) {
            addCriterion("material_type in", values, "materialType");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeNotIn(List<Integer> values) {
            addCriterion("material_type not in", values, "materialType");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeBetween(Integer value1, Integer value2) {
            addCriterion("material_type between", value1, value2, "materialType");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("material_type not between", value1, value2, "materialType");
            return (Criteria) this;
        }

        public Criteria andMaterialNumIsNull() {
            addCriterion("material_num is null");
            return (Criteria) this;
        }

        public Criteria andMaterialNumIsNotNull() {
            addCriterion("material_num is not null");
            return (Criteria) this;
        }

        public Criteria andMaterialNumEqualTo(String value) {
            addCriterion("material_num =", value, "materialNum");
            return (Criteria) this;
        }

        public Criteria andMaterialNumNotEqualTo(String value) {
            addCriterion("material_num <>", value, "materialNum");
            return (Criteria) this;
        }

        public Criteria andMaterialNumGreaterThan(String value) {
            addCriterion("material_num >", value, "materialNum");
            return (Criteria) this;
        }

        public Criteria andMaterialNumGreaterThanOrEqualTo(String value) {
            addCriterion("material_num >=", value, "materialNum");
            return (Criteria) this;
        }

        public Criteria andMaterialNumLessThan(String value) {
            addCriterion("material_num <", value, "materialNum");
            return (Criteria) this;
        }

        public Criteria andMaterialNumLessThanOrEqualTo(String value) {
            addCriterion("material_num <=", value, "materialNum");
            return (Criteria) this;
        }

        public Criteria andMaterialNumLike(String value) {
            addCriterion("material_num like", value, "materialNum");
            return (Criteria) this;
        }

        public Criteria andMaterialNumNotLike(String value) {
            addCriterion("material_num not like", value, "materialNum");
            return (Criteria) this;
        }

        public Criteria andMaterialNumIn(List<String> values) {
            addCriterion("material_num in", values, "materialNum");
            return (Criteria) this;
        }

        public Criteria andMaterialNumNotIn(List<String> values) {
            addCriterion("material_num not in", values, "materialNum");
            return (Criteria) this;
        }

        public Criteria andMaterialNumBetween(String value1, String value2) {
            addCriterion("material_num between", value1, value2, "materialNum");
            return (Criteria) this;
        }

        public Criteria andMaterialNumNotBetween(String value1, String value2) {
            addCriterion("material_num not between", value1, value2, "materialNum");
            return (Criteria) this;
        }

        public Criteria andMaterialNameIsNull() {
            addCriterion("material_name is null");
            return (Criteria) this;
        }

        public Criteria andMaterialNameIsNotNull() {
            addCriterion("material_name is not null");
            return (Criteria) this;
        }

        public Criteria andMaterialNameEqualTo(String value) {
            addCriterion("material_name =", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameNotEqualTo(String value) {
            addCriterion("material_name <>", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameGreaterThan(String value) {
            addCriterion("material_name >", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameGreaterThanOrEqualTo(String value) {
            addCriterion("material_name >=", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameLessThan(String value) {
            addCriterion("material_name <", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameLessThanOrEqualTo(String value) {
            addCriterion("material_name <=", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameLike(String value) {
            addCriterion("material_name like", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameNotLike(String value) {
            addCriterion("material_name not like", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameIn(List<String> values) {
            addCriterion("material_name in", values, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameNotIn(List<String> values) {
            addCriterion("material_name not in", values, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameBetween(String value1, String value2) {
            addCriterion("material_name between", value1, value2, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameNotBetween(String value1, String value2) {
            addCriterion("material_name not between", value1, value2, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialStateIsNull() {
            addCriterion("material_state is null");
            return (Criteria) this;
        }

        public Criteria andMaterialStateIsNotNull() {
            addCriterion("material_state is not null");
            return (Criteria) this;
        }

        public Criteria andMaterialStateEqualTo(Integer value) {
            addCriterion("material_state =", value, "materialState");
            return (Criteria) this;
        }

        public Criteria andMaterialStateNotEqualTo(Integer value) {
            addCriterion("material_state <>", value, "materialState");
            return (Criteria) this;
        }

        public Criteria andMaterialStateGreaterThan(Integer value) {
            addCriterion("material_state >", value, "materialState");
            return (Criteria) this;
        }

        public Criteria andMaterialStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("material_state >=", value, "materialState");
            return (Criteria) this;
        }

        public Criteria andMaterialStateLessThan(Integer value) {
            addCriterion("material_state <", value, "materialState");
            return (Criteria) this;
        }

        public Criteria andMaterialStateLessThanOrEqualTo(Integer value) {
            addCriterion("material_state <=", value, "materialState");
            return (Criteria) this;
        }

        public Criteria andMaterialStateIn(List<Integer> values) {
            addCriterion("material_state in", values, "materialState");
            return (Criteria) this;
        }

        public Criteria andMaterialStateNotIn(List<Integer> values) {
            addCriterion("material_state not in", values, "materialState");
            return (Criteria) this;
        }

        public Criteria andMaterialStateBetween(Integer value1, Integer value2) {
            addCriterion("material_state between", value1, value2, "materialState");
            return (Criteria) this;
        }

        public Criteria andMaterialStateNotBetween(Integer value1, Integer value2) {
            addCriterion("material_state not between", value1, value2, "materialState");
            return (Criteria) this;
        }

        public Criteria andSuperiorMaterialIdIsNull() {
            addCriterion("superior_material_id is null");
            return (Criteria) this;
        }

        public Criteria andSuperiorMaterialIdIsNotNull() {
            addCriterion("superior_material_id is not null");
            return (Criteria) this;
        }

        public Criteria andSuperiorMaterialIdEqualTo(Long value) {
            addCriterion("superior_material_id =", value, "superiorMaterialId");
            return (Criteria) this;
        }

        public Criteria andSuperiorMaterialIdNotEqualTo(Long value) {
            addCriterion("superior_material_id <>", value, "superiorMaterialId");
            return (Criteria) this;
        }

        public Criteria andSuperiorMaterialIdGreaterThan(Long value) {
            addCriterion("superior_material_id >", value, "superiorMaterialId");
            return (Criteria) this;
        }

        public Criteria andSuperiorMaterialIdGreaterThanOrEqualTo(Long value) {
            addCriterion("superior_material_id >=", value, "superiorMaterialId");
            return (Criteria) this;
        }

        public Criteria andSuperiorMaterialIdLessThan(Long value) {
            addCriterion("superior_material_id <", value, "superiorMaterialId");
            return (Criteria) this;
        }

        public Criteria andSuperiorMaterialIdLessThanOrEqualTo(Long value) {
            addCriterion("superior_material_id <=", value, "superiorMaterialId");
            return (Criteria) this;
        }

        public Criteria andSuperiorMaterialIdIn(List<Long> values) {
            addCriterion("superior_material_id in", values, "superiorMaterialId");
            return (Criteria) this;
        }

        public Criteria andSuperiorMaterialIdNotIn(List<Long> values) {
            addCriterion("superior_material_id not in", values, "superiorMaterialId");
            return (Criteria) this;
        }

        public Criteria andSuperiorMaterialIdBetween(Long value1, Long value2) {
            addCriterion("superior_material_id between", value1, value2, "superiorMaterialId");
            return (Criteria) this;
        }

        public Criteria andSuperiorMaterialIdNotBetween(Long value1, Long value2) {
            addCriterion("superior_material_id not between", value1, value2, "superiorMaterialId");
            return (Criteria) this;
        }

        public Criteria andWorkProcedureIdIsNull() {
            addCriterion("work_procedure_id is null");
            return (Criteria) this;
        }

        public Criteria andWorkProcedureIdIsNotNull() {
            addCriterion("work_procedure_id is not null");
            return (Criteria) this;
        }

        public Criteria andWorkProcedureIdEqualTo(Long value) {
            addCriterion("work_procedure_id =", value, "workProcedureId");
            return (Criteria) this;
        }

        public Criteria andWorkProcedureIdNotEqualTo(Long value) {
            addCriterion("work_procedure_id <>", value, "workProcedureId");
            return (Criteria) this;
        }

        public Criteria andWorkProcedureIdGreaterThan(Long value) {
            addCriterion("work_procedure_id >", value, "workProcedureId");
            return (Criteria) this;
        }

        public Criteria andWorkProcedureIdGreaterThanOrEqualTo(Long value) {
            addCriterion("work_procedure_id >=", value, "workProcedureId");
            return (Criteria) this;
        }

        public Criteria andWorkProcedureIdLessThan(Long value) {
            addCriterion("work_procedure_id <", value, "workProcedureId");
            return (Criteria) this;
        }

        public Criteria andWorkProcedureIdLessThanOrEqualTo(Long value) {
            addCriterion("work_procedure_id <=", value, "workProcedureId");
            return (Criteria) this;
        }

        public Criteria andWorkProcedureIdIn(List<Long> values) {
            addCriterion("work_procedure_id in", values, "workProcedureId");
            return (Criteria) this;
        }

        public Criteria andWorkProcedureIdNotIn(List<Long> values) {
            addCriterion("work_procedure_id not in", values, "workProcedureId");
            return (Criteria) this;
        }

        public Criteria andWorkProcedureIdBetween(Long value1, Long value2) {
            addCriterion("work_procedure_id between", value1, value2, "workProcedureId");
            return (Criteria) this;
        }

        public Criteria andWorkProcedureIdNotBetween(Long value1, Long value2) {
            addCriterion("work_procedure_id not between", value1, value2, "workProcedureId");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(Integer value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(Integer value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(Integer value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(Integer value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(Integer value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<Integer> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<Integer> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(Integer value1, Integer value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("level not between", value1, value2, "level");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}