package team.union.business.commodity.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BisCommodityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BisCommodityExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCommodityNameIsNull() {
            addCriterion("commodity_name is null");
            return (Criteria) this;
        }

        public Criteria andCommodityNameIsNotNull() {
            addCriterion("commodity_name is not null");
            return (Criteria) this;
        }

        public Criteria andCommodityNameEqualTo(String value) {
            addCriterion("commodity_name =", value, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityNameNotEqualTo(String value) {
            addCriterion("commodity_name <>", value, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityNameGreaterThan(String value) {
            addCriterion("commodity_name >", value, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityNameGreaterThanOrEqualTo(String value) {
            addCriterion("commodity_name >=", value, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityNameLessThan(String value) {
            addCriterion("commodity_name <", value, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityNameLessThanOrEqualTo(String value) {
            addCriterion("commodity_name <=", value, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityNameLike(String value) {
            addCriterion("commodity_name like", value, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityNameNotLike(String value) {
            addCriterion("commodity_name not like", value, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityNameIn(List<String> values) {
            addCriterion("commodity_name in", values, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityNameNotIn(List<String> values) {
            addCriterion("commodity_name not in", values, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityNameBetween(String value1, String value2) {
            addCriterion("commodity_name between", value1, value2, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityNameNotBetween(String value1, String value2) {
            addCriterion("commodity_name not between", value1, value2, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityPriceIsNull() {
            addCriterion("commodity_price is null");
            return (Criteria) this;
        }

        public Criteria andCommodityPriceIsNotNull() {
            addCriterion("commodity_price is not null");
            return (Criteria) this;
        }

        public Criteria andCommodityPriceEqualTo(BigDecimal value) {
            addCriterion("commodity_price =", value, "commodityPrice");
            return (Criteria) this;
        }

        public Criteria andCommodityPriceNotEqualTo(BigDecimal value) {
            addCriterion("commodity_price <>", value, "commodityPrice");
            return (Criteria) this;
        }

        public Criteria andCommodityPriceGreaterThan(BigDecimal value) {
            addCriterion("commodity_price >", value, "commodityPrice");
            return (Criteria) this;
        }

        public Criteria andCommodityPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("commodity_price >=", value, "commodityPrice");
            return (Criteria) this;
        }

        public Criteria andCommodityPriceLessThan(BigDecimal value) {
            addCriterion("commodity_price <", value, "commodityPrice");
            return (Criteria) this;
        }

        public Criteria andCommodityPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("commodity_price <=", value, "commodityPrice");
            return (Criteria) this;
        }

        public Criteria andCommodityPriceIn(List<BigDecimal> values) {
            addCriterion("commodity_price in", values, "commodityPrice");
            return (Criteria) this;
        }

        public Criteria andCommodityPriceNotIn(List<BigDecimal> values) {
            addCriterion("commodity_price not in", values, "commodityPrice");
            return (Criteria) this;
        }

        public Criteria andCommodityPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("commodity_price between", value1, value2, "commodityPrice");
            return (Criteria) this;
        }

        public Criteria andCommodityPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("commodity_price not between", value1, value2, "commodityPrice");
            return (Criteria) this;
        }

        public Criteria andCommodityOldPriceIsNull() {
            addCriterion("commodity_old_price is null");
            return (Criteria) this;
        }

        public Criteria andCommodityOldPriceIsNotNull() {
            addCriterion("commodity_old_price is not null");
            return (Criteria) this;
        }

        public Criteria andCommodityOldPriceEqualTo(BigDecimal value) {
            addCriterion("commodity_old_price =", value, "commodityOldPrice");
            return (Criteria) this;
        }

        public Criteria andCommodityOldPriceNotEqualTo(BigDecimal value) {
            addCriterion("commodity_old_price <>", value, "commodityOldPrice");
            return (Criteria) this;
        }

        public Criteria andCommodityOldPriceGreaterThan(BigDecimal value) {
            addCriterion("commodity_old_price >", value, "commodityOldPrice");
            return (Criteria) this;
        }

        public Criteria andCommodityOldPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("commodity_old_price >=", value, "commodityOldPrice");
            return (Criteria) this;
        }

        public Criteria andCommodityOldPriceLessThan(BigDecimal value) {
            addCriterion("commodity_old_price <", value, "commodityOldPrice");
            return (Criteria) this;
        }

        public Criteria andCommodityOldPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("commodity_old_price <=", value, "commodityOldPrice");
            return (Criteria) this;
        }

        public Criteria andCommodityOldPriceIn(List<BigDecimal> values) {
            addCriterion("commodity_old_price in", values, "commodityOldPrice");
            return (Criteria) this;
        }

        public Criteria andCommodityOldPriceNotIn(List<BigDecimal> values) {
            addCriterion("commodity_old_price not in", values, "commodityOldPrice");
            return (Criteria) this;
        }

        public Criteria andCommodityOldPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("commodity_old_price between", value1, value2, "commodityOldPrice");
            return (Criteria) this;
        }

        public Criteria andCommodityOldPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("commodity_old_price not between", value1, value2, "commodityOldPrice");
            return (Criteria) this;
        }

        public Criteria andCommodityStoreIdIsNull() {
            addCriterion("commodity_store_id is null");
            return (Criteria) this;
        }

        public Criteria andCommodityStoreIdIsNotNull() {
            addCriterion("commodity_store_id is not null");
            return (Criteria) this;
        }

        public Criteria andCommodityStoreIdEqualTo(Long value) {
            addCriterion("commodity_store_id =", value, "commodityStoreId");
            return (Criteria) this;
        }

        public Criteria andCommodityStoreIdNotEqualTo(Long value) {
            addCriterion("commodity_store_id <>", value, "commodityStoreId");
            return (Criteria) this;
        }

        public Criteria andCommodityStoreIdGreaterThan(Long value) {
            addCriterion("commodity_store_id >", value, "commodityStoreId");
            return (Criteria) this;
        }

        public Criteria andCommodityStoreIdGreaterThanOrEqualTo(Long value) {
            addCriterion("commodity_store_id >=", value, "commodityStoreId");
            return (Criteria) this;
        }

        public Criteria andCommodityStoreIdLessThan(Long value) {
            addCriterion("commodity_store_id <", value, "commodityStoreId");
            return (Criteria) this;
        }

        public Criteria andCommodityStoreIdLessThanOrEqualTo(Long value) {
            addCriterion("commodity_store_id <=", value, "commodityStoreId");
            return (Criteria) this;
        }

        public Criteria andCommodityStoreIdIn(List<Long> values) {
            addCriterion("commodity_store_id in", values, "commodityStoreId");
            return (Criteria) this;
        }

        public Criteria andCommodityStoreIdNotIn(List<Long> values) {
            addCriterion("commodity_store_id not in", values, "commodityStoreId");
            return (Criteria) this;
        }

        public Criteria andCommodityStoreIdBetween(Long value1, Long value2) {
            addCriterion("commodity_store_id between", value1, value2, "commodityStoreId");
            return (Criteria) this;
        }

        public Criteria andCommodityStoreIdNotBetween(Long value1, Long value2) {
            addCriterion("commodity_store_id not between", value1, value2, "commodityStoreId");
            return (Criteria) this;
        }

        public Criteria andCommodityNumIsNull() {
            addCriterion("commodity_num is null");
            return (Criteria) this;
        }

        public Criteria andCommodityNumIsNotNull() {
            addCriterion("commodity_num is not null");
            return (Criteria) this;
        }

        public Criteria andCommodityNumEqualTo(Integer value) {
            addCriterion("commodity_num =", value, "commodityNum");
            return (Criteria) this;
        }

        public Criteria andCommodityNumNotEqualTo(Integer value) {
            addCriterion("commodity_num <>", value, "commodityNum");
            return (Criteria) this;
        }

        public Criteria andCommodityNumGreaterThan(Integer value) {
            addCriterion("commodity_num >", value, "commodityNum");
            return (Criteria) this;
        }

        public Criteria andCommodityNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("commodity_num >=", value, "commodityNum");
            return (Criteria) this;
        }

        public Criteria andCommodityNumLessThan(Integer value) {
            addCriterion("commodity_num <", value, "commodityNum");
            return (Criteria) this;
        }

        public Criteria andCommodityNumLessThanOrEqualTo(Integer value) {
            addCriterion("commodity_num <=", value, "commodityNum");
            return (Criteria) this;
        }

        public Criteria andCommodityNumIn(List<Integer> values) {
            addCriterion("commodity_num in", values, "commodityNum");
            return (Criteria) this;
        }

        public Criteria andCommodityNumNotIn(List<Integer> values) {
            addCriterion("commodity_num not in", values, "commodityNum");
            return (Criteria) this;
        }

        public Criteria andCommodityNumBetween(Integer value1, Integer value2) {
            addCriterion("commodity_num between", value1, value2, "commodityNum");
            return (Criteria) this;
        }

        public Criteria andCommodityNumNotBetween(Integer value1, Integer value2) {
            addCriterion("commodity_num not between", value1, value2, "commodityNum");
            return (Criteria) this;
        }

        public Criteria andCommodityQualityIsNull() {
            addCriterion("commodity_quality is null");
            return (Criteria) this;
        }

        public Criteria andCommodityQualityIsNotNull() {
            addCriterion("commodity_quality is not null");
            return (Criteria) this;
        }

        public Criteria andCommodityQualityEqualTo(BigDecimal value) {
            addCriterion("commodity_quality =", value, "commodityQuality");
            return (Criteria) this;
        }

        public Criteria andCommodityQualityNotEqualTo(BigDecimal value) {
            addCriterion("commodity_quality <>", value, "commodityQuality");
            return (Criteria) this;
        }

        public Criteria andCommodityQualityGreaterThan(BigDecimal value) {
            addCriterion("commodity_quality >", value, "commodityQuality");
            return (Criteria) this;
        }

        public Criteria andCommodityQualityGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("commodity_quality >=", value, "commodityQuality");
            return (Criteria) this;
        }

        public Criteria andCommodityQualityLessThan(BigDecimal value) {
            addCriterion("commodity_quality <", value, "commodityQuality");
            return (Criteria) this;
        }

        public Criteria andCommodityQualityLessThanOrEqualTo(BigDecimal value) {
            addCriterion("commodity_quality <=", value, "commodityQuality");
            return (Criteria) this;
        }

        public Criteria andCommodityQualityIn(List<BigDecimal> values) {
            addCriterion("commodity_quality in", values, "commodityQuality");
            return (Criteria) this;
        }

        public Criteria andCommodityQualityNotIn(List<BigDecimal> values) {
            addCriterion("commodity_quality not in", values, "commodityQuality");
            return (Criteria) this;
        }

        public Criteria andCommodityQualityBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("commodity_quality between", value1, value2, "commodityQuality");
            return (Criteria) this;
        }

        public Criteria andCommodityQualityNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("commodity_quality not between", value1, value2, "commodityQuality");
            return (Criteria) this;
        }

        public Criteria andCommodityPicIsNull() {
            addCriterion("commodity_pic is null");
            return (Criteria) this;
        }

        public Criteria andCommodityPicIsNotNull() {
            addCriterion("commodity_pic is not null");
            return (Criteria) this;
        }

        public Criteria andCommodityPicEqualTo(String value) {
            addCriterion("commodity_pic =", value, "commodityPic");
            return (Criteria) this;
        }

        public Criteria andCommodityPicNotEqualTo(String value) {
            addCriterion("commodity_pic <>", value, "commodityPic");
            return (Criteria) this;
        }

        public Criteria andCommodityPicGreaterThan(String value) {
            addCriterion("commodity_pic >", value, "commodityPic");
            return (Criteria) this;
        }

        public Criteria andCommodityPicGreaterThanOrEqualTo(String value) {
            addCriterion("commodity_pic >=", value, "commodityPic");
            return (Criteria) this;
        }

        public Criteria andCommodityPicLessThan(String value) {
            addCriterion("commodity_pic <", value, "commodityPic");
            return (Criteria) this;
        }

        public Criteria andCommodityPicLessThanOrEqualTo(String value) {
            addCriterion("commodity_pic <=", value, "commodityPic");
            return (Criteria) this;
        }

        public Criteria andCommodityPicLike(String value) {
            addCriterion("commodity_pic like", value, "commodityPic");
            return (Criteria) this;
        }

        public Criteria andCommodityPicNotLike(String value) {
            addCriterion("commodity_pic not like", value, "commodityPic");
            return (Criteria) this;
        }

        public Criteria andCommodityPicIn(List<String> values) {
            addCriterion("commodity_pic in", values, "commodityPic");
            return (Criteria) this;
        }

        public Criteria andCommodityPicNotIn(List<String> values) {
            addCriterion("commodity_pic not in", values, "commodityPic");
            return (Criteria) this;
        }

        public Criteria andCommodityPicBetween(String value1, String value2) {
            addCriterion("commodity_pic between", value1, value2, "commodityPic");
            return (Criteria) this;
        }

        public Criteria andCommodityPicNotBetween(String value1, String value2) {
            addCriterion("commodity_pic not between", value1, value2, "commodityPic");
            return (Criteria) this;
        }

        public Criteria andCommoditySortNumIsNull() {
            addCriterion("commodity_sort_num is null");
            return (Criteria) this;
        }

        public Criteria andCommoditySortNumIsNotNull() {
            addCriterion("commodity_sort_num is not null");
            return (Criteria) this;
        }

        public Criteria andCommoditySortNumEqualTo(Integer value) {
            addCriterion("commodity_sort_num =", value, "commoditySortNum");
            return (Criteria) this;
        }

        public Criteria andCommoditySortNumNotEqualTo(Integer value) {
            addCriterion("commodity_sort_num <>", value, "commoditySortNum");
            return (Criteria) this;
        }

        public Criteria andCommoditySortNumGreaterThan(Integer value) {
            addCriterion("commodity_sort_num >", value, "commoditySortNum");
            return (Criteria) this;
        }

        public Criteria andCommoditySortNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("commodity_sort_num >=", value, "commoditySortNum");
            return (Criteria) this;
        }

        public Criteria andCommoditySortNumLessThan(Integer value) {
            addCriterion("commodity_sort_num <", value, "commoditySortNum");
            return (Criteria) this;
        }

        public Criteria andCommoditySortNumLessThanOrEqualTo(Integer value) {
            addCriterion("commodity_sort_num <=", value, "commoditySortNum");
            return (Criteria) this;
        }

        public Criteria andCommoditySortNumIn(List<Integer> values) {
            addCriterion("commodity_sort_num in", values, "commoditySortNum");
            return (Criteria) this;
        }

        public Criteria andCommoditySortNumNotIn(List<Integer> values) {
            addCriterion("commodity_sort_num not in", values, "commoditySortNum");
            return (Criteria) this;
        }

        public Criteria andCommoditySortNumBetween(Integer value1, Integer value2) {
            addCriterion("commodity_sort_num between", value1, value2, "commoditySortNum");
            return (Criteria) this;
        }

        public Criteria andCommoditySortNumNotBetween(Integer value1, Integer value2) {
            addCriterion("commodity_sort_num not between", value1, value2, "commoditySortNum");
            return (Criteria) this;
        }

        public Criteria andCommodityIntroductionIsNull() {
            addCriterion("commodity_introduction is null");
            return (Criteria) this;
        }

        public Criteria andCommodityIntroductionIsNotNull() {
            addCriterion("commodity_introduction is not null");
            return (Criteria) this;
        }

        public Criteria andCommodityIntroductionEqualTo(String value) {
            addCriterion("commodity_introduction =", value, "commodityIntroduction");
            return (Criteria) this;
        }

        public Criteria andCommodityIntroductionNotEqualTo(String value) {
            addCriterion("commodity_introduction <>", value, "commodityIntroduction");
            return (Criteria) this;
        }

        public Criteria andCommodityIntroductionGreaterThan(String value) {
            addCriterion("commodity_introduction >", value, "commodityIntroduction");
            return (Criteria) this;
        }

        public Criteria andCommodityIntroductionGreaterThanOrEqualTo(String value) {
            addCriterion("commodity_introduction >=", value, "commodityIntroduction");
            return (Criteria) this;
        }

        public Criteria andCommodityIntroductionLessThan(String value) {
            addCriterion("commodity_introduction <", value, "commodityIntroduction");
            return (Criteria) this;
        }

        public Criteria andCommodityIntroductionLessThanOrEqualTo(String value) {
            addCriterion("commodity_introduction <=", value, "commodityIntroduction");
            return (Criteria) this;
        }

        public Criteria andCommodityIntroductionLike(String value) {
            addCriterion("commodity_introduction like", value, "commodityIntroduction");
            return (Criteria) this;
        }

        public Criteria andCommodityIntroductionNotLike(String value) {
            addCriterion("commodity_introduction not like", value, "commodityIntroduction");
            return (Criteria) this;
        }

        public Criteria andCommodityIntroductionIn(List<String> values) {
            addCriterion("commodity_introduction in", values, "commodityIntroduction");
            return (Criteria) this;
        }

        public Criteria andCommodityIntroductionNotIn(List<String> values) {
            addCriterion("commodity_introduction not in", values, "commodityIntroduction");
            return (Criteria) this;
        }

        public Criteria andCommodityIntroductionBetween(String value1, String value2) {
            addCriterion("commodity_introduction between", value1, value2, "commodityIntroduction");
            return (Criteria) this;
        }

        public Criteria andCommodityIntroductionNotBetween(String value1, String value2) {
            addCriterion("commodity_introduction not between", value1, value2, "commodityIntroduction");
            return (Criteria) this;
        }

        public Criteria andCommodityStatusIsNull() {
            addCriterion("commodity_status is null");
            return (Criteria) this;
        }

        public Criteria andCommodityStatusIsNotNull() {
            addCriterion("commodity_status is not null");
            return (Criteria) this;
        }

        public Criteria andCommodityStatusEqualTo(Integer value) {
            addCriterion("commodity_status =", value, "commodityStatus");
            return (Criteria) this;
        }

        public Criteria andCommodityStatusNotEqualTo(Integer value) {
            addCriterion("commodity_status <>", value, "commodityStatus");
            return (Criteria) this;
        }

        public Criteria andCommodityStatusGreaterThan(Integer value) {
            addCriterion("commodity_status >", value, "commodityStatus");
            return (Criteria) this;
        }

        public Criteria andCommodityStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("commodity_status >=", value, "commodityStatus");
            return (Criteria) this;
        }

        public Criteria andCommodityStatusLessThan(Integer value) {
            addCriterion("commodity_status <", value, "commodityStatus");
            return (Criteria) this;
        }

        public Criteria andCommodityStatusLessThanOrEqualTo(Integer value) {
            addCriterion("commodity_status <=", value, "commodityStatus");
            return (Criteria) this;
        }

        public Criteria andCommodityStatusIn(List<Integer> values) {
            addCriterion("commodity_status in", values, "commodityStatus");
            return (Criteria) this;
        }

        public Criteria andCommodityStatusNotIn(List<Integer> values) {
            addCriterion("commodity_status not in", values, "commodityStatus");
            return (Criteria) this;
        }

        public Criteria andCommodityStatusBetween(Integer value1, Integer value2) {
            addCriterion("commodity_status between", value1, value2, "commodityStatus");
            return (Criteria) this;
        }

        public Criteria andCommodityStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("commodity_status not between", value1, value2, "commodityStatus");
            return (Criteria) this;
        }

        public Criteria andHomepageShowIsNull() {
            addCriterion("homepage_show is null");
            return (Criteria) this;
        }

        public Criteria andHomepageShowIsNotNull() {
            addCriterion("homepage_show is not null");
            return (Criteria) this;
        }

        public Criteria andHomepageShowEqualTo(Integer value) {
            addCriterion("homepage_show =", value, "homepageShow");
            return (Criteria) this;
        }

        public Criteria andHomepageShowNotEqualTo(Integer value) {
            addCriterion("homepage_show <>", value, "homepageShow");
            return (Criteria) this;
        }

        public Criteria andHomepageShowGreaterThan(Integer value) {
            addCriterion("homepage_show >", value, "homepageShow");
            return (Criteria) this;
        }

        public Criteria andHomepageShowGreaterThanOrEqualTo(Integer value) {
            addCriterion("homepage_show >=", value, "homepageShow");
            return (Criteria) this;
        }

        public Criteria andHomepageShowLessThan(Integer value) {
            addCriterion("homepage_show <", value, "homepageShow");
            return (Criteria) this;
        }

        public Criteria andHomepageShowLessThanOrEqualTo(Integer value) {
            addCriterion("homepage_show <=", value, "homepageShow");
            return (Criteria) this;
        }

        public Criteria andHomepageShowIn(List<Integer> values) {
            addCriterion("homepage_show in", values, "homepageShow");
            return (Criteria) this;
        }

        public Criteria andHomepageShowNotIn(List<Integer> values) {
            addCriterion("homepage_show not in", values, "homepageShow");
            return (Criteria) this;
        }

        public Criteria andHomepageShowBetween(Integer value1, Integer value2) {
            addCriterion("homepage_show between", value1, value2, "homepageShow");
            return (Criteria) this;
        }

        public Criteria andHomepageShowNotBetween(Integer value1, Integer value2) {
            addCriterion("homepage_show not between", value1, value2, "homepageShow");
            return (Criteria) this;
        }

        public Criteria andTimingOffIsNull() {
            addCriterion("timing_off is null");
            return (Criteria) this;
        }

        public Criteria andTimingOffIsNotNull() {
            addCriterion("timing_off is not null");
            return (Criteria) this;
        }

        public Criteria andTimingOffEqualTo(Date value) {
            addCriterion("timing_off =", value, "timingOff");
            return (Criteria) this;
        }

        public Criteria andTimingOffNotEqualTo(Date value) {
            addCriterion("timing_off <>", value, "timingOff");
            return (Criteria) this;
        }

        public Criteria andTimingOffGreaterThan(Date value) {
            addCriterion("timing_off >", value, "timingOff");
            return (Criteria) this;
        }

        public Criteria andTimingOffGreaterThanOrEqualTo(Date value) {
            addCriterion("timing_off >=", value, "timingOff");
            return (Criteria) this;
        }

        public Criteria andTimingOffLessThan(Date value) {
            addCriterion("timing_off <", value, "timingOff");
            return (Criteria) this;
        }

        public Criteria andTimingOffLessThanOrEqualTo(Date value) {
            addCriterion("timing_off <=", value, "timingOff");
            return (Criteria) this;
        }

        public Criteria andTimingOffIn(List<Date> values) {
            addCriterion("timing_off in", values, "timingOff");
            return (Criteria) this;
        }

        public Criteria andTimingOffNotIn(List<Date> values) {
            addCriterion("timing_off not in", values, "timingOff");
            return (Criteria) this;
        }

        public Criteria andTimingOffBetween(Date value1, Date value2) {
            addCriterion("timing_off between", value1, value2, "timingOff");
            return (Criteria) this;
        }

        public Criteria andTimingOffNotBetween(Date value1, Date value2) {
            addCriterion("timing_off not between", value1, value2, "timingOff");
            return (Criteria) this;
        }

        public Criteria andTimingBegainIsNull() {
            addCriterion("timing_begain is null");
            return (Criteria) this;
        }

        public Criteria andTimingBegainIsNotNull() {
            addCriterion("timing_begain is not null");
            return (Criteria) this;
        }

        public Criteria andTimingBegainEqualTo(Date value) {
            addCriterion("timing_begain =", value, "timingBegain");
            return (Criteria) this;
        }

        public Criteria andTimingBegainNotEqualTo(Date value) {
            addCriterion("timing_begain <>", value, "timingBegain");
            return (Criteria) this;
        }

        public Criteria andTimingBegainGreaterThan(Date value) {
            addCriterion("timing_begain >", value, "timingBegain");
            return (Criteria) this;
        }

        public Criteria andTimingBegainGreaterThanOrEqualTo(Date value) {
            addCriterion("timing_begain >=", value, "timingBegain");
            return (Criteria) this;
        }

        public Criteria andTimingBegainLessThan(Date value) {
            addCriterion("timing_begain <", value, "timingBegain");
            return (Criteria) this;
        }

        public Criteria andTimingBegainLessThanOrEqualTo(Date value) {
            addCriterion("timing_begain <=", value, "timingBegain");
            return (Criteria) this;
        }

        public Criteria andTimingBegainIn(List<Date> values) {
            addCriterion("timing_begain in", values, "timingBegain");
            return (Criteria) this;
        }

        public Criteria andTimingBegainNotIn(List<Date> values) {
            addCriterion("timing_begain not in", values, "timingBegain");
            return (Criteria) this;
        }

        public Criteria andTimingBegainBetween(Date value1, Date value2) {
            addCriterion("timing_begain between", value1, value2, "timingBegain");
            return (Criteria) this;
        }

        public Criteria andTimingBegainNotBetween(Date value1, Date value2) {
            addCriterion("timing_begain not between", value1, value2, "timingBegain");
            return (Criteria) this;
        }

        public Criteria andIsTimingIsNull() {
            addCriterion("is_timing is null");
            return (Criteria) this;
        }

        public Criteria andIsTimingIsNotNull() {
            addCriterion("is_timing is not null");
            return (Criteria) this;
        }

        public Criteria andIsTimingEqualTo(Integer value) {
            addCriterion("is_timing =", value, "isTiming");
            return (Criteria) this;
        }

        public Criteria andIsTimingNotEqualTo(Integer value) {
            addCriterion("is_timing <>", value, "isTiming");
            return (Criteria) this;
        }

        public Criteria andIsTimingGreaterThan(Integer value) {
            addCriterion("is_timing >", value, "isTiming");
            return (Criteria) this;
        }

        public Criteria andIsTimingGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_timing >=", value, "isTiming");
            return (Criteria) this;
        }

        public Criteria andIsTimingLessThan(Integer value) {
            addCriterion("is_timing <", value, "isTiming");
            return (Criteria) this;
        }

        public Criteria andIsTimingLessThanOrEqualTo(Integer value) {
            addCriterion("is_timing <=", value, "isTiming");
            return (Criteria) this;
        }

        public Criteria andIsTimingIn(List<Integer> values) {
            addCriterion("is_timing in", values, "isTiming");
            return (Criteria) this;
        }

        public Criteria andIsTimingNotIn(List<Integer> values) {
            addCriterion("is_timing not in", values, "isTiming");
            return (Criteria) this;
        }

        public Criteria andIsTimingBetween(Integer value1, Integer value2) {
            addCriterion("is_timing between", value1, value2, "isTiming");
            return (Criteria) this;
        }

        public Criteria andIsTimingNotBetween(Integer value1, Integer value2) {
            addCriterion("is_timing not between", value1, value2, "isTiming");
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