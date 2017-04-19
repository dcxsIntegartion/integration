package team.union.nonbusiness.area.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NonbisCityCodeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NonbisCityCodeExample() {
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

        public Criteria andCityIdIsNull() {
            addCriterion("CITY_ID is null");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNotNull() {
            addCriterion("CITY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCityIdEqualTo(Long value) {
            addCriterion("CITY_ID =", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotEqualTo(Long value) {
            addCriterion("CITY_ID <>", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThan(Long value) {
            addCriterion("CITY_ID >", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThanOrEqualTo(Long value) {
            addCriterion("CITY_ID >=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThan(Long value) {
            addCriterion("CITY_ID <", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThanOrEqualTo(Long value) {
            addCriterion("CITY_ID <=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdIn(List<Long> values) {
            addCriterion("CITY_ID in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotIn(List<Long> values) {
            addCriterion("CITY_ID not in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdBetween(Long value1, Long value2) {
            addCriterion("CITY_ID between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotBetween(Long value1, Long value2) {
            addCriterion("CITY_ID not between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityCodeIsNull() {
            addCriterion("CITY_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCityCodeIsNotNull() {
            addCriterion("CITY_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCityCodeEqualTo(String value) {
            addCriterion("CITY_CODE =", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotEqualTo(String value) {
            addCriterion("CITY_CODE <>", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeGreaterThan(String value) {
            addCriterion("CITY_CODE >", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CITY_CODE >=", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeLessThan(String value) {
            addCriterion("CITY_CODE <", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeLessThanOrEqualTo(String value) {
            addCriterion("CITY_CODE <=", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeLike(String value) {
            addCriterion("CITY_CODE like", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotLike(String value) {
            addCriterion("CITY_CODE not like", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeIn(List<String> values) {
            addCriterion("CITY_CODE in", values, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotIn(List<String> values) {
            addCriterion("CITY_CODE not in", values, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeBetween(String value1, String value2) {
            addCriterion("CITY_CODE between", value1, value2, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotBetween(String value1, String value2) {
            addCriterion("CITY_CODE not between", value1, value2, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityNameIsNull() {
            addCriterion("CITY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCityNameIsNotNull() {
            addCriterion("CITY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCityNameEqualTo(String value) {
            addCriterion("CITY_NAME =", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotEqualTo(String value) {
            addCriterion("CITY_NAME <>", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameGreaterThan(String value) {
            addCriterion("CITY_NAME >", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameGreaterThanOrEqualTo(String value) {
            addCriterion("CITY_NAME >=", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLessThan(String value) {
            addCriterion("CITY_NAME <", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLessThanOrEqualTo(String value) {
            addCriterion("CITY_NAME <=", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLike(String value) {
            addCriterion("CITY_NAME like", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotLike(String value) {
            addCriterion("CITY_NAME not like", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameIn(List<String> values) {
            addCriterion("CITY_NAME in", values, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotIn(List<String> values) {
            addCriterion("CITY_NAME not in", values, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameBetween(String value1, String value2) {
            addCriterion("CITY_NAME between", value1, value2, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotBetween(String value1, String value2) {
            addCriterion("CITY_NAME not between", value1, value2, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityFullNameIsNull() {
            addCriterion("CITY_FULL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCityFullNameIsNotNull() {
            addCriterion("CITY_FULL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCityFullNameEqualTo(String value) {
            addCriterion("CITY_FULL_NAME =", value, "cityFullName");
            return (Criteria) this;
        }

        public Criteria andCityFullNameNotEqualTo(String value) {
            addCriterion("CITY_FULL_NAME <>", value, "cityFullName");
            return (Criteria) this;
        }

        public Criteria andCityFullNameGreaterThan(String value) {
            addCriterion("CITY_FULL_NAME >", value, "cityFullName");
            return (Criteria) this;
        }

        public Criteria andCityFullNameGreaterThanOrEqualTo(String value) {
            addCriterion("CITY_FULL_NAME >=", value, "cityFullName");
            return (Criteria) this;
        }

        public Criteria andCityFullNameLessThan(String value) {
            addCriterion("CITY_FULL_NAME <", value, "cityFullName");
            return (Criteria) this;
        }

        public Criteria andCityFullNameLessThanOrEqualTo(String value) {
            addCriterion("CITY_FULL_NAME <=", value, "cityFullName");
            return (Criteria) this;
        }

        public Criteria andCityFullNameLike(String value) {
            addCriterion("CITY_FULL_NAME like", value, "cityFullName");
            return (Criteria) this;
        }

        public Criteria andCityFullNameNotLike(String value) {
            addCriterion("CITY_FULL_NAME not like", value, "cityFullName");
            return (Criteria) this;
        }

        public Criteria andCityFullNameIn(List<String> values) {
            addCriterion("CITY_FULL_NAME in", values, "cityFullName");
            return (Criteria) this;
        }

        public Criteria andCityFullNameNotIn(List<String> values) {
            addCriterion("CITY_FULL_NAME not in", values, "cityFullName");
            return (Criteria) this;
        }

        public Criteria andCityFullNameBetween(String value1, String value2) {
            addCriterion("CITY_FULL_NAME between", value1, value2, "cityFullName");
            return (Criteria) this;
        }

        public Criteria andCityFullNameNotBetween(String value1, String value2) {
            addCriterion("CITY_FULL_NAME not between", value1, value2, "cityFullName");
            return (Criteria) this;
        }

        public Criteria andCityFullCodeIsNull() {
            addCriterion("CITY_FULL_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCityFullCodeIsNotNull() {
            addCriterion("CITY_FULL_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCityFullCodeEqualTo(String value) {
            addCriterion("CITY_FULL_CODE =", value, "cityFullCode");
            return (Criteria) this;
        }

        public Criteria andCityFullCodeNotEqualTo(String value) {
            addCriterion("CITY_FULL_CODE <>", value, "cityFullCode");
            return (Criteria) this;
        }

        public Criteria andCityFullCodeGreaterThan(String value) {
            addCriterion("CITY_FULL_CODE >", value, "cityFullCode");
            return (Criteria) this;
        }

        public Criteria andCityFullCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CITY_FULL_CODE >=", value, "cityFullCode");
            return (Criteria) this;
        }

        public Criteria andCityFullCodeLessThan(String value) {
            addCriterion("CITY_FULL_CODE <", value, "cityFullCode");
            return (Criteria) this;
        }

        public Criteria andCityFullCodeLessThanOrEqualTo(String value) {
            addCriterion("CITY_FULL_CODE <=", value, "cityFullCode");
            return (Criteria) this;
        }

        public Criteria andCityFullCodeLike(String value) {
            addCriterion("CITY_FULL_CODE like", value, "cityFullCode");
            return (Criteria) this;
        }

        public Criteria andCityFullCodeNotLike(String value) {
            addCriterion("CITY_FULL_CODE not like", value, "cityFullCode");
            return (Criteria) this;
        }

        public Criteria andCityFullCodeIn(List<String> values) {
            addCriterion("CITY_FULL_CODE in", values, "cityFullCode");
            return (Criteria) this;
        }

        public Criteria andCityFullCodeNotIn(List<String> values) {
            addCriterion("CITY_FULL_CODE not in", values, "cityFullCode");
            return (Criteria) this;
        }

        public Criteria andCityFullCodeBetween(String value1, String value2) {
            addCriterion("CITY_FULL_CODE between", value1, value2, "cityFullCode");
            return (Criteria) this;
        }

        public Criteria andCityFullCodeNotBetween(String value1, String value2) {
            addCriterion("CITY_FULL_CODE not between", value1, value2, "cityFullCode");
            return (Criteria) this;
        }

        public Criteria andCityParentCodeIsNull() {
            addCriterion("CITY_PARENT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCityParentCodeIsNotNull() {
            addCriterion("CITY_PARENT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCityParentCodeEqualTo(String value) {
            addCriterion("CITY_PARENT_CODE =", value, "cityParentCode");
            return (Criteria) this;
        }

        public Criteria andCityParentCodeNotEqualTo(String value) {
            addCriterion("CITY_PARENT_CODE <>", value, "cityParentCode");
            return (Criteria) this;
        }

        public Criteria andCityParentCodeGreaterThan(String value) {
            addCriterion("CITY_PARENT_CODE >", value, "cityParentCode");
            return (Criteria) this;
        }

        public Criteria andCityParentCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CITY_PARENT_CODE >=", value, "cityParentCode");
            return (Criteria) this;
        }

        public Criteria andCityParentCodeLessThan(String value) {
            addCriterion("CITY_PARENT_CODE <", value, "cityParentCode");
            return (Criteria) this;
        }

        public Criteria andCityParentCodeLessThanOrEqualTo(String value) {
            addCriterion("CITY_PARENT_CODE <=", value, "cityParentCode");
            return (Criteria) this;
        }

        public Criteria andCityParentCodeLike(String value) {
            addCriterion("CITY_PARENT_CODE like", value, "cityParentCode");
            return (Criteria) this;
        }

        public Criteria andCityParentCodeNotLike(String value) {
            addCriterion("CITY_PARENT_CODE not like", value, "cityParentCode");
            return (Criteria) this;
        }

        public Criteria andCityParentCodeIn(List<String> values) {
            addCriterion("CITY_PARENT_CODE in", values, "cityParentCode");
            return (Criteria) this;
        }

        public Criteria andCityParentCodeNotIn(List<String> values) {
            addCriterion("CITY_PARENT_CODE not in", values, "cityParentCode");
            return (Criteria) this;
        }

        public Criteria andCityParentCodeBetween(String value1, String value2) {
            addCriterion("CITY_PARENT_CODE between", value1, value2, "cityParentCode");
            return (Criteria) this;
        }

        public Criteria andCityParentCodeNotBetween(String value1, String value2) {
            addCriterion("CITY_PARENT_CODE not between", value1, value2, "cityParentCode");
            return (Criteria) this;
        }

        public Criteria andSortIdIsNull() {
            addCriterion("SORT_ID is null");
            return (Criteria) this;
        }

        public Criteria andSortIdIsNotNull() {
            addCriterion("SORT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSortIdEqualTo(Integer value) {
            addCriterion("SORT_ID =", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdNotEqualTo(Integer value) {
            addCriterion("SORT_ID <>", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdGreaterThan(Integer value) {
            addCriterion("SORT_ID >", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("SORT_ID >=", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdLessThan(Integer value) {
            addCriterion("SORT_ID <", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdLessThanOrEqualTo(Integer value) {
            addCriterion("SORT_ID <=", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdIn(List<Integer> values) {
            addCriterion("SORT_ID in", values, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdNotIn(List<Integer> values) {
            addCriterion("SORT_ID not in", values, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdBetween(Integer value1, Integer value2) {
            addCriterion("SORT_ID between", value1, value2, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdNotBetween(Integer value1, Integer value2) {
            addCriterion("SORT_ID not between", value1, value2, "sortId");
            return (Criteria) this;
        }

        public Criteria andAllSpellCodeIsNull() {
            addCriterion("ALL_SPELL_CODE is null");
            return (Criteria) this;
        }

        public Criteria andAllSpellCodeIsNotNull() {
            addCriterion("ALL_SPELL_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andAllSpellCodeEqualTo(String value) {
            addCriterion("ALL_SPELL_CODE =", value, "allSpellCode");
            return (Criteria) this;
        }

        public Criteria andAllSpellCodeNotEqualTo(String value) {
            addCriterion("ALL_SPELL_CODE <>", value, "allSpellCode");
            return (Criteria) this;
        }

        public Criteria andAllSpellCodeGreaterThan(String value) {
            addCriterion("ALL_SPELL_CODE >", value, "allSpellCode");
            return (Criteria) this;
        }

        public Criteria andAllSpellCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ALL_SPELL_CODE >=", value, "allSpellCode");
            return (Criteria) this;
        }

        public Criteria andAllSpellCodeLessThan(String value) {
            addCriterion("ALL_SPELL_CODE <", value, "allSpellCode");
            return (Criteria) this;
        }

        public Criteria andAllSpellCodeLessThanOrEqualTo(String value) {
            addCriterion("ALL_SPELL_CODE <=", value, "allSpellCode");
            return (Criteria) this;
        }

        public Criteria andAllSpellCodeLike(String value) {
            addCriterion("ALL_SPELL_CODE like", value, "allSpellCode");
            return (Criteria) this;
        }

        public Criteria andAllSpellCodeNotLike(String value) {
            addCriterion("ALL_SPELL_CODE not like", value, "allSpellCode");
            return (Criteria) this;
        }

        public Criteria andAllSpellCodeIn(List<String> values) {
            addCriterion("ALL_SPELL_CODE in", values, "allSpellCode");
            return (Criteria) this;
        }

        public Criteria andAllSpellCodeNotIn(List<String> values) {
            addCriterion("ALL_SPELL_CODE not in", values, "allSpellCode");
            return (Criteria) this;
        }

        public Criteria andAllSpellCodeBetween(String value1, String value2) {
            addCriterion("ALL_SPELL_CODE between", value1, value2, "allSpellCode");
            return (Criteria) this;
        }

        public Criteria andAllSpellCodeNotBetween(String value1, String value2) {
            addCriterion("ALL_SPELL_CODE not between", value1, value2, "allSpellCode");
            return (Criteria) this;
        }

        public Criteria andSimpleSpellCodeIsNull() {
            addCriterion("SIMPLE_SPELL_CODE is null");
            return (Criteria) this;
        }

        public Criteria andSimpleSpellCodeIsNotNull() {
            addCriterion("SIMPLE_SPELL_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andSimpleSpellCodeEqualTo(String value) {
            addCriterion("SIMPLE_SPELL_CODE =", value, "simpleSpellCode");
            return (Criteria) this;
        }

        public Criteria andSimpleSpellCodeNotEqualTo(String value) {
            addCriterion("SIMPLE_SPELL_CODE <>", value, "simpleSpellCode");
            return (Criteria) this;
        }

        public Criteria andSimpleSpellCodeGreaterThan(String value) {
            addCriterion("SIMPLE_SPELL_CODE >", value, "simpleSpellCode");
            return (Criteria) this;
        }

        public Criteria andSimpleSpellCodeGreaterThanOrEqualTo(String value) {
            addCriterion("SIMPLE_SPELL_CODE >=", value, "simpleSpellCode");
            return (Criteria) this;
        }

        public Criteria andSimpleSpellCodeLessThan(String value) {
            addCriterion("SIMPLE_SPELL_CODE <", value, "simpleSpellCode");
            return (Criteria) this;
        }

        public Criteria andSimpleSpellCodeLessThanOrEqualTo(String value) {
            addCriterion("SIMPLE_SPELL_CODE <=", value, "simpleSpellCode");
            return (Criteria) this;
        }

        public Criteria andSimpleSpellCodeLike(String value) {
            addCriterion("SIMPLE_SPELL_CODE like", value, "simpleSpellCode");
            return (Criteria) this;
        }

        public Criteria andSimpleSpellCodeNotLike(String value) {
            addCriterion("SIMPLE_SPELL_CODE not like", value, "simpleSpellCode");
            return (Criteria) this;
        }

        public Criteria andSimpleSpellCodeIn(List<String> values) {
            addCriterion("SIMPLE_SPELL_CODE in", values, "simpleSpellCode");
            return (Criteria) this;
        }

        public Criteria andSimpleSpellCodeNotIn(List<String> values) {
            addCriterion("SIMPLE_SPELL_CODE not in", values, "simpleSpellCode");
            return (Criteria) this;
        }

        public Criteria andSimpleSpellCodeBetween(String value1, String value2) {
            addCriterion("SIMPLE_SPELL_CODE between", value1, value2, "simpleSpellCode");
            return (Criteria) this;
        }

        public Criteria andSimpleSpellCodeNotBetween(String value1, String value2) {
            addCriterion("SIMPLE_SPELL_CODE not between", value1, value2, "simpleSpellCode");
            return (Criteria) this;
        }

        public Criteria andLevelNumberIsNull() {
            addCriterion("LEVEL_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andLevelNumberIsNotNull() {
            addCriterion("LEVEL_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andLevelNumberEqualTo(Integer value) {
            addCriterion("LEVEL_NUMBER =", value, "levelNumber");
            return (Criteria) this;
        }

        public Criteria andLevelNumberNotEqualTo(Integer value) {
            addCriterion("LEVEL_NUMBER <>", value, "levelNumber");
            return (Criteria) this;
        }

        public Criteria andLevelNumberGreaterThan(Integer value) {
            addCriterion("LEVEL_NUMBER >", value, "levelNumber");
            return (Criteria) this;
        }

        public Criteria andLevelNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("LEVEL_NUMBER >=", value, "levelNumber");
            return (Criteria) this;
        }

        public Criteria andLevelNumberLessThan(Integer value) {
            addCriterion("LEVEL_NUMBER <", value, "levelNumber");
            return (Criteria) this;
        }

        public Criteria andLevelNumberLessThanOrEqualTo(Integer value) {
            addCriterion("LEVEL_NUMBER <=", value, "levelNumber");
            return (Criteria) this;
        }

        public Criteria andLevelNumberIn(List<Integer> values) {
            addCriterion("LEVEL_NUMBER in", values, "levelNumber");
            return (Criteria) this;
        }

        public Criteria andLevelNumberNotIn(List<Integer> values) {
            addCriterion("LEVEL_NUMBER not in", values, "levelNumber");
            return (Criteria) this;
        }

        public Criteria andLevelNumberBetween(Integer value1, Integer value2) {
            addCriterion("LEVEL_NUMBER between", value1, value2, "levelNumber");
            return (Criteria) this;
        }

        public Criteria andLevelNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("LEVEL_NUMBER not between", value1, value2, "levelNumber");
            return (Criteria) this;
        }

        public Criteria andIsUseIsNull() {
            addCriterion("IS_USE is null");
            return (Criteria) this;
        }

        public Criteria andIsUseIsNotNull() {
            addCriterion("IS_USE is not null");
            return (Criteria) this;
        }

        public Criteria andIsUseEqualTo(Byte value) {
            addCriterion("IS_USE =", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseNotEqualTo(Byte value) {
            addCriterion("IS_USE <>", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseGreaterThan(Byte value) {
            addCriterion("IS_USE >", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseGreaterThanOrEqualTo(Byte value) {
            addCriterion("IS_USE >=", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseLessThan(Byte value) {
            addCriterion("IS_USE <", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseLessThanOrEqualTo(Byte value) {
            addCriterion("IS_USE <=", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseIn(List<Byte> values) {
            addCriterion("IS_USE in", values, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseNotIn(List<Byte> values) {
            addCriterion("IS_USE not in", values, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseBetween(Byte value1, Byte value2) {
            addCriterion("IS_USE between", value1, value2, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseNotBetween(Byte value1, Byte value2) {
            addCriterion("IS_USE not between", value1, value2, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsReadOnlyIsNull() {
            addCriterion("IS_READ_ONLY is null");
            return (Criteria) this;
        }

        public Criteria andIsReadOnlyIsNotNull() {
            addCriterion("IS_READ_ONLY is not null");
            return (Criteria) this;
        }

        public Criteria andIsReadOnlyEqualTo(Byte value) {
            addCriterion("IS_READ_ONLY =", value, "isReadOnly");
            return (Criteria) this;
        }

        public Criteria andIsReadOnlyNotEqualTo(Byte value) {
            addCriterion("IS_READ_ONLY <>", value, "isReadOnly");
            return (Criteria) this;
        }

        public Criteria andIsReadOnlyGreaterThan(Byte value) {
            addCriterion("IS_READ_ONLY >", value, "isReadOnly");
            return (Criteria) this;
        }

        public Criteria andIsReadOnlyGreaterThanOrEqualTo(Byte value) {
            addCriterion("IS_READ_ONLY >=", value, "isReadOnly");
            return (Criteria) this;
        }

        public Criteria andIsReadOnlyLessThan(Byte value) {
            addCriterion("IS_READ_ONLY <", value, "isReadOnly");
            return (Criteria) this;
        }

        public Criteria andIsReadOnlyLessThanOrEqualTo(Byte value) {
            addCriterion("IS_READ_ONLY <=", value, "isReadOnly");
            return (Criteria) this;
        }

        public Criteria andIsReadOnlyIn(List<Byte> values) {
            addCriterion("IS_READ_ONLY in", values, "isReadOnly");
            return (Criteria) this;
        }

        public Criteria andIsReadOnlyNotIn(List<Byte> values) {
            addCriterion("IS_READ_ONLY not in", values, "isReadOnly");
            return (Criteria) this;
        }

        public Criteria andIsReadOnlyBetween(Byte value1, Byte value2) {
            addCriterion("IS_READ_ONLY between", value1, value2, "isReadOnly");
            return (Criteria) this;
        }

        public Criteria andIsReadOnlyNotBetween(Byte value1, Byte value2) {
            addCriterion("IS_READ_ONLY not between", value1, value2, "isReadOnly");
            return (Criteria) this;
        }

        public Criteria andChildSizeIsNull() {
            addCriterion("CHILD_SIZE is null");
            return (Criteria) this;
        }

        public Criteria andChildSizeIsNotNull() {
            addCriterion("CHILD_SIZE is not null");
            return (Criteria) this;
        }

        public Criteria andChildSizeEqualTo(Integer value) {
            addCriterion("CHILD_SIZE =", value, "childSize");
            return (Criteria) this;
        }

        public Criteria andChildSizeNotEqualTo(Integer value) {
            addCriterion("CHILD_SIZE <>", value, "childSize");
            return (Criteria) this;
        }

        public Criteria andChildSizeGreaterThan(Integer value) {
            addCriterion("CHILD_SIZE >", value, "childSize");
            return (Criteria) this;
        }

        public Criteria andChildSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("CHILD_SIZE >=", value, "childSize");
            return (Criteria) this;
        }

        public Criteria andChildSizeLessThan(Integer value) {
            addCriterion("CHILD_SIZE <", value, "childSize");
            return (Criteria) this;
        }

        public Criteria andChildSizeLessThanOrEqualTo(Integer value) {
            addCriterion("CHILD_SIZE <=", value, "childSize");
            return (Criteria) this;
        }

        public Criteria andChildSizeIn(List<Integer> values) {
            addCriterion("CHILD_SIZE in", values, "childSize");
            return (Criteria) this;
        }

        public Criteria andChildSizeNotIn(List<Integer> values) {
            addCriterion("CHILD_SIZE not in", values, "childSize");
            return (Criteria) this;
        }

        public Criteria andChildSizeBetween(Integer value1, Integer value2) {
            addCriterion("CHILD_SIZE between", value1, value2, "childSize");
            return (Criteria) this;
        }

        public Criteria andChildSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("CHILD_SIZE not between", value1, value2, "childSize");
            return (Criteria) this;
        }

        public Criteria andOpenTimeIsNull() {
            addCriterion("OPEN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andOpenTimeIsNotNull() {
            addCriterion("OPEN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andOpenTimeEqualTo(Date value) {
            addCriterion("OPEN_TIME =", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeNotEqualTo(Date value) {
            addCriterion("OPEN_TIME <>", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeGreaterThan(Date value) {
            addCriterion("OPEN_TIME >", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("OPEN_TIME >=", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeLessThan(Date value) {
            addCriterion("OPEN_TIME <", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeLessThanOrEqualTo(Date value) {
            addCriterion("OPEN_TIME <=", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeIn(List<Date> values) {
            addCriterion("OPEN_TIME in", values, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeNotIn(List<Date> values) {
            addCriterion("OPEN_TIME not in", values, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeBetween(Date value1, Date value2) {
            addCriterion("OPEN_TIME between", value1, value2, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeNotBetween(Date value1, Date value2) {
            addCriterion("OPEN_TIME not between", value1, value2, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenStutasIsNull() {
            addCriterion("OPEN_STUTAS is null");
            return (Criteria) this;
        }

        public Criteria andOpenStutasIsNotNull() {
            addCriterion("OPEN_STUTAS is not null");
            return (Criteria) this;
        }

        public Criteria andOpenStutasEqualTo(Integer value) {
            addCriterion("OPEN_STUTAS =", value, "openStutas");
            return (Criteria) this;
        }

        public Criteria andOpenStutasNotEqualTo(Integer value) {
            addCriterion("OPEN_STUTAS <>", value, "openStutas");
            return (Criteria) this;
        }

        public Criteria andOpenStutasGreaterThan(Integer value) {
            addCriterion("OPEN_STUTAS >", value, "openStutas");
            return (Criteria) this;
        }

        public Criteria andOpenStutasGreaterThanOrEqualTo(Integer value) {
            addCriterion("OPEN_STUTAS >=", value, "openStutas");
            return (Criteria) this;
        }

        public Criteria andOpenStutasLessThan(Integer value) {
            addCriterion("OPEN_STUTAS <", value, "openStutas");
            return (Criteria) this;
        }

        public Criteria andOpenStutasLessThanOrEqualTo(Integer value) {
            addCriterion("OPEN_STUTAS <=", value, "openStutas");
            return (Criteria) this;
        }

        public Criteria andOpenStutasIn(List<Integer> values) {
            addCriterion("OPEN_STUTAS in", values, "openStutas");
            return (Criteria) this;
        }

        public Criteria andOpenStutasNotIn(List<Integer> values) {
            addCriterion("OPEN_STUTAS not in", values, "openStutas");
            return (Criteria) this;
        }

        public Criteria andOpenStutasBetween(Integer value1, Integer value2) {
            addCriterion("OPEN_STUTAS between", value1, value2, "openStutas");
            return (Criteria) this;
        }

        public Criteria andOpenStutasNotBetween(Integer value1, Integer value2) {
            addCriterion("OPEN_STUTAS not between", value1, value2, "openStutas");
            return (Criteria) this;
        }

        public Criteria andCloseTimeIsNull() {
            addCriterion("CLOSE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCloseTimeIsNotNull() {
            addCriterion("CLOSE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCloseTimeEqualTo(Date value) {
            addCriterion("CLOSE_TIME =", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeNotEqualTo(Date value) {
            addCriterion("CLOSE_TIME <>", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeGreaterThan(Date value) {
            addCriterion("CLOSE_TIME >", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CLOSE_TIME >=", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeLessThan(Date value) {
            addCriterion("CLOSE_TIME <", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeLessThanOrEqualTo(Date value) {
            addCriterion("CLOSE_TIME <=", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeIn(List<Date> values) {
            addCriterion("CLOSE_TIME in", values, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeNotIn(List<Date> values) {
            addCriterion("CLOSE_TIME not in", values, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeBetween(Date value1, Date value2) {
            addCriterion("CLOSE_TIME between", value1, value2, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeNotBetween(Date value1, Date value2) {
            addCriterion("CLOSE_TIME not between", value1, value2, "closeTime");
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