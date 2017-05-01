package team.union.business.activity.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BisActivityBargainExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BisActivityBargainExample() {
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

        public Criteria andActivityNameIsNull() {
            addCriterion("activity_name is null");
            return (Criteria) this;
        }

        public Criteria andActivityNameIsNotNull() {
            addCriterion("activity_name is not null");
            return (Criteria) this;
        }

        public Criteria andActivityNameEqualTo(String value) {
            addCriterion("activity_name =", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotEqualTo(String value) {
            addCriterion("activity_name <>", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameGreaterThan(String value) {
            addCriterion("activity_name >", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameGreaterThanOrEqualTo(String value) {
            addCriterion("activity_name >=", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLessThan(String value) {
            addCriterion("activity_name <", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLessThanOrEqualTo(String value) {
            addCriterion("activity_name <=", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLike(String value) {
            addCriterion("activity_name like", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotLike(String value) {
            addCriterion("activity_name not like", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameIn(List<String> values) {
            addCriterion("activity_name in", values, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotIn(List<String> values) {
            addCriterion("activity_name not in", values, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameBetween(String value1, String value2) {
            addCriterion("activity_name between", value1, value2, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotBetween(String value1, String value2) {
            addCriterion("activity_name not between", value1, value2, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityStartTimeIsNull() {
            addCriterion("activity_start_time is null");
            return (Criteria) this;
        }

        public Criteria andActivityStartTimeIsNotNull() {
            addCriterion("activity_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andActivityStartTimeEqualTo(Date value) {
            addCriterion("activity_start_time =", value, "activityStartTime");
            return (Criteria) this;
        }

        public Criteria andActivityStartTimeNotEqualTo(Date value) {
            addCriterion("activity_start_time <>", value, "activityStartTime");
            return (Criteria) this;
        }

        public Criteria andActivityStartTimeGreaterThan(Date value) {
            addCriterion("activity_start_time >", value, "activityStartTime");
            return (Criteria) this;
        }

        public Criteria andActivityStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("activity_start_time >=", value, "activityStartTime");
            return (Criteria) this;
        }

        public Criteria andActivityStartTimeLessThan(Date value) {
            addCriterion("activity_start_time <", value, "activityStartTime");
            return (Criteria) this;
        }

        public Criteria andActivityStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("activity_start_time <=", value, "activityStartTime");
            return (Criteria) this;
        }

        public Criteria andActivityStartTimeIn(List<Date> values) {
            addCriterion("activity_start_time in", values, "activityStartTime");
            return (Criteria) this;
        }

        public Criteria andActivityStartTimeNotIn(List<Date> values) {
            addCriterion("activity_start_time not in", values, "activityStartTime");
            return (Criteria) this;
        }

        public Criteria andActivityStartTimeBetween(Date value1, Date value2) {
            addCriterion("activity_start_time between", value1, value2, "activityStartTime");
            return (Criteria) this;
        }

        public Criteria andActivityStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("activity_start_time not between", value1, value2, "activityStartTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeIsNull() {
            addCriterion("activity_end_time is null");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeIsNotNull() {
            addCriterion("activity_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeEqualTo(Date value) {
            addCriterion("activity_end_time =", value, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeNotEqualTo(Date value) {
            addCriterion("activity_end_time <>", value, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeGreaterThan(Date value) {
            addCriterion("activity_end_time >", value, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("activity_end_time >=", value, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeLessThan(Date value) {
            addCriterion("activity_end_time <", value, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("activity_end_time <=", value, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeIn(List<Date> values) {
            addCriterion("activity_end_time in", values, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeNotIn(List<Date> values) {
            addCriterion("activity_end_time not in", values, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeBetween(Date value1, Date value2) {
            addCriterion("activity_end_time between", value1, value2, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("activity_end_time not between", value1, value2, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityStoreIdIsNull() {
            addCriterion("activity_store_id is null");
            return (Criteria) this;
        }

        public Criteria andActivityStoreIdIsNotNull() {
            addCriterion("activity_store_id is not null");
            return (Criteria) this;
        }

        public Criteria andActivityStoreIdEqualTo(Long value) {
            addCriterion("activity_store_id =", value, "activityStoreId");
            return (Criteria) this;
        }

        public Criteria andActivityStoreIdNotEqualTo(Long value) {
            addCriterion("activity_store_id <>", value, "activityStoreId");
            return (Criteria) this;
        }

        public Criteria andActivityStoreIdGreaterThan(Long value) {
            addCriterion("activity_store_id >", value, "activityStoreId");
            return (Criteria) this;
        }

        public Criteria andActivityStoreIdGreaterThanOrEqualTo(Long value) {
            addCriterion("activity_store_id >=", value, "activityStoreId");
            return (Criteria) this;
        }

        public Criteria andActivityStoreIdLessThan(Long value) {
            addCriterion("activity_store_id <", value, "activityStoreId");
            return (Criteria) this;
        }

        public Criteria andActivityStoreIdLessThanOrEqualTo(Long value) {
            addCriterion("activity_store_id <=", value, "activityStoreId");
            return (Criteria) this;
        }

        public Criteria andActivityStoreIdIn(List<Long> values) {
            addCriterion("activity_store_id in", values, "activityStoreId");
            return (Criteria) this;
        }

        public Criteria andActivityStoreIdNotIn(List<Long> values) {
            addCriterion("activity_store_id not in", values, "activityStoreId");
            return (Criteria) this;
        }

        public Criteria andActivityStoreIdBetween(Long value1, Long value2) {
            addCriterion("activity_store_id between", value1, value2, "activityStoreId");
            return (Criteria) this;
        }

        public Criteria andActivityStoreIdNotBetween(Long value1, Long value2) {
            addCriterion("activity_store_id not between", value1, value2, "activityStoreId");
            return (Criteria) this;
        }

        public Criteria andActivityPicIsNull() {
            addCriterion("activity_pic is null");
            return (Criteria) this;
        }

        public Criteria andActivityPicIsNotNull() {
            addCriterion("activity_pic is not null");
            return (Criteria) this;
        }

        public Criteria andActivityPicEqualTo(Long value) {
            addCriterion("activity_pic =", value, "activityPic");
            return (Criteria) this;
        }

        public Criteria andActivityPicNotEqualTo(Long value) {
            addCriterion("activity_pic <>", value, "activityPic");
            return (Criteria) this;
        }

        public Criteria andActivityPicGreaterThan(Long value) {
            addCriterion("activity_pic >", value, "activityPic");
            return (Criteria) this;
        }

        public Criteria andActivityPicGreaterThanOrEqualTo(Long value) {
            addCriterion("activity_pic >=", value, "activityPic");
            return (Criteria) this;
        }

        public Criteria andActivityPicLessThan(Long value) {
            addCriterion("activity_pic <", value, "activityPic");
            return (Criteria) this;
        }

        public Criteria andActivityPicLessThanOrEqualTo(Long value) {
            addCriterion("activity_pic <=", value, "activityPic");
            return (Criteria) this;
        }

        public Criteria andActivityPicIn(List<Long> values) {
            addCriterion("activity_pic in", values, "activityPic");
            return (Criteria) this;
        }

        public Criteria andActivityPicNotIn(List<Long> values) {
            addCriterion("activity_pic not in", values, "activityPic");
            return (Criteria) this;
        }

        public Criteria andActivityPicBetween(Long value1, Long value2) {
            addCriterion("activity_pic between", value1, value2, "activityPic");
            return (Criteria) this;
        }

        public Criteria andActivityPicNotBetween(Long value1, Long value2) {
            addCriterion("activity_pic not between", value1, value2, "activityPic");
            return (Criteria) this;
        }

        public Criteria andActivityExplainIsNull() {
            addCriterion("activity_explain is null");
            return (Criteria) this;
        }

        public Criteria andActivityExplainIsNotNull() {
            addCriterion("activity_explain is not null");
            return (Criteria) this;
        }

        public Criteria andActivityExplainEqualTo(String value) {
            addCriterion("activity_explain =", value, "activityExplain");
            return (Criteria) this;
        }

        public Criteria andActivityExplainNotEqualTo(String value) {
            addCriterion("activity_explain <>", value, "activityExplain");
            return (Criteria) this;
        }

        public Criteria andActivityExplainGreaterThan(String value) {
            addCriterion("activity_explain >", value, "activityExplain");
            return (Criteria) this;
        }

        public Criteria andActivityExplainGreaterThanOrEqualTo(String value) {
            addCriterion("activity_explain >=", value, "activityExplain");
            return (Criteria) this;
        }

        public Criteria andActivityExplainLessThan(String value) {
            addCriterion("activity_explain <", value, "activityExplain");
            return (Criteria) this;
        }

        public Criteria andActivityExplainLessThanOrEqualTo(String value) {
            addCriterion("activity_explain <=", value, "activityExplain");
            return (Criteria) this;
        }

        public Criteria andActivityExplainLike(String value) {
            addCriterion("activity_explain like", value, "activityExplain");
            return (Criteria) this;
        }

        public Criteria andActivityExplainNotLike(String value) {
            addCriterion("activity_explain not like", value, "activityExplain");
            return (Criteria) this;
        }

        public Criteria andActivityExplainIn(List<String> values) {
            addCriterion("activity_explain in", values, "activityExplain");
            return (Criteria) this;
        }

        public Criteria andActivityExplainNotIn(List<String> values) {
            addCriterion("activity_explain not in", values, "activityExplain");
            return (Criteria) this;
        }

        public Criteria andActivityExplainBetween(String value1, String value2) {
            addCriterion("activity_explain between", value1, value2, "activityExplain");
            return (Criteria) this;
        }

        public Criteria andActivityExplainNotBetween(String value1, String value2) {
            addCriterion("activity_explain not between", value1, value2, "activityExplain");
            return (Criteria) this;
        }

        public Criteria andActivityStatusIsNull() {
            addCriterion("activity_status is null");
            return (Criteria) this;
        }

        public Criteria andActivityStatusIsNotNull() {
            addCriterion("activity_status is not null");
            return (Criteria) this;
        }

        public Criteria andActivityStatusEqualTo(Byte value) {
            addCriterion("activity_status =", value, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusNotEqualTo(Byte value) {
            addCriterion("activity_status <>", value, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusGreaterThan(Byte value) {
            addCriterion("activity_status >", value, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("activity_status >=", value, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusLessThan(Byte value) {
            addCriterion("activity_status <", value, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusLessThanOrEqualTo(Byte value) {
            addCriterion("activity_status <=", value, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusIn(List<Byte> values) {
            addCriterion("activity_status in", values, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusNotIn(List<Byte> values) {
            addCriterion("activity_status not in", values, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusBetween(Byte value1, Byte value2) {
            addCriterion("activity_status between", value1, value2, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("activity_status not between", value1, value2, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andShareTitleIsNull() {
            addCriterion("share_title is null");
            return (Criteria) this;
        }

        public Criteria andShareTitleIsNotNull() {
            addCriterion("share_title is not null");
            return (Criteria) this;
        }

        public Criteria andShareTitleEqualTo(String value) {
            addCriterion("share_title =", value, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleNotEqualTo(String value) {
            addCriterion("share_title <>", value, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleGreaterThan(String value) {
            addCriterion("share_title >", value, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleGreaterThanOrEqualTo(String value) {
            addCriterion("share_title >=", value, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleLessThan(String value) {
            addCriterion("share_title <", value, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleLessThanOrEqualTo(String value) {
            addCriterion("share_title <=", value, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleLike(String value) {
            addCriterion("share_title like", value, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleNotLike(String value) {
            addCriterion("share_title not like", value, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleIn(List<String> values) {
            addCriterion("share_title in", values, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleNotIn(List<String> values) {
            addCriterion("share_title not in", values, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleBetween(String value1, String value2) {
            addCriterion("share_title between", value1, value2, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleNotBetween(String value1, String value2) {
            addCriterion("share_title not between", value1, value2, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareDescribeIsNull() {
            addCriterion("share_describe is null");
            return (Criteria) this;
        }

        public Criteria andShareDescribeIsNotNull() {
            addCriterion("share_describe is not null");
            return (Criteria) this;
        }

        public Criteria andShareDescribeEqualTo(String value) {
            addCriterion("share_describe =", value, "shareDescribe");
            return (Criteria) this;
        }

        public Criteria andShareDescribeNotEqualTo(String value) {
            addCriterion("share_describe <>", value, "shareDescribe");
            return (Criteria) this;
        }

        public Criteria andShareDescribeGreaterThan(String value) {
            addCriterion("share_describe >", value, "shareDescribe");
            return (Criteria) this;
        }

        public Criteria andShareDescribeGreaterThanOrEqualTo(String value) {
            addCriterion("share_describe >=", value, "shareDescribe");
            return (Criteria) this;
        }

        public Criteria andShareDescribeLessThan(String value) {
            addCriterion("share_describe <", value, "shareDescribe");
            return (Criteria) this;
        }

        public Criteria andShareDescribeLessThanOrEqualTo(String value) {
            addCriterion("share_describe <=", value, "shareDescribe");
            return (Criteria) this;
        }

        public Criteria andShareDescribeLike(String value) {
            addCriterion("share_describe like", value, "shareDescribe");
            return (Criteria) this;
        }

        public Criteria andShareDescribeNotLike(String value) {
            addCriterion("share_describe not like", value, "shareDescribe");
            return (Criteria) this;
        }

        public Criteria andShareDescribeIn(List<String> values) {
            addCriterion("share_describe in", values, "shareDescribe");
            return (Criteria) this;
        }

        public Criteria andShareDescribeNotIn(List<String> values) {
            addCriterion("share_describe not in", values, "shareDescribe");
            return (Criteria) this;
        }

        public Criteria andShareDescribeBetween(String value1, String value2) {
            addCriterion("share_describe between", value1, value2, "shareDescribe");
            return (Criteria) this;
        }

        public Criteria andShareDescribeNotBetween(String value1, String value2) {
            addCriterion("share_describe not between", value1, value2, "shareDescribe");
            return (Criteria) this;
        }

        public Criteria andSharePicIsNull() {
            addCriterion("share_pic is null");
            return (Criteria) this;
        }

        public Criteria andSharePicIsNotNull() {
            addCriterion("share_pic is not null");
            return (Criteria) this;
        }

        public Criteria andSharePicEqualTo(String value) {
            addCriterion("share_pic =", value, "sharePic");
            return (Criteria) this;
        }

        public Criteria andSharePicNotEqualTo(String value) {
            addCriterion("share_pic <>", value, "sharePic");
            return (Criteria) this;
        }

        public Criteria andSharePicGreaterThan(String value) {
            addCriterion("share_pic >", value, "sharePic");
            return (Criteria) this;
        }

        public Criteria andSharePicGreaterThanOrEqualTo(String value) {
            addCriterion("share_pic >=", value, "sharePic");
            return (Criteria) this;
        }

        public Criteria andSharePicLessThan(String value) {
            addCriterion("share_pic <", value, "sharePic");
            return (Criteria) this;
        }

        public Criteria andSharePicLessThanOrEqualTo(String value) {
            addCriterion("share_pic <=", value, "sharePic");
            return (Criteria) this;
        }

        public Criteria andSharePicLike(String value) {
            addCriterion("share_pic like", value, "sharePic");
            return (Criteria) this;
        }

        public Criteria andSharePicNotLike(String value) {
            addCriterion("share_pic not like", value, "sharePic");
            return (Criteria) this;
        }

        public Criteria andSharePicIn(List<String> values) {
            addCriterion("share_pic in", values, "sharePic");
            return (Criteria) this;
        }

        public Criteria andSharePicNotIn(List<String> values) {
            addCriterion("share_pic not in", values, "sharePic");
            return (Criteria) this;
        }

        public Criteria andSharePicBetween(String value1, String value2) {
            addCriterion("share_pic between", value1, value2, "sharePic");
            return (Criteria) this;
        }

        public Criteria andSharePicNotBetween(String value1, String value2) {
            addCriterion("share_pic not between", value1, value2, "sharePic");
            return (Criteria) this;
        }

        public Criteria andActivityCreatTimeIsNull() {
            addCriterion("activity_creat_time is null");
            return (Criteria) this;
        }

        public Criteria andActivityCreatTimeIsNotNull() {
            addCriterion("activity_creat_time is not null");
            return (Criteria) this;
        }

        public Criteria andActivityCreatTimeEqualTo(Date value) {
            addCriterion("activity_creat_time =", value, "activityCreatTime");
            return (Criteria) this;
        }

        public Criteria andActivityCreatTimeNotEqualTo(Date value) {
            addCriterion("activity_creat_time <>", value, "activityCreatTime");
            return (Criteria) this;
        }

        public Criteria andActivityCreatTimeGreaterThan(Date value) {
            addCriterion("activity_creat_time >", value, "activityCreatTime");
            return (Criteria) this;
        }

        public Criteria andActivityCreatTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("activity_creat_time >=", value, "activityCreatTime");
            return (Criteria) this;
        }

        public Criteria andActivityCreatTimeLessThan(Date value) {
            addCriterion("activity_creat_time <", value, "activityCreatTime");
            return (Criteria) this;
        }

        public Criteria andActivityCreatTimeLessThanOrEqualTo(Date value) {
            addCriterion("activity_creat_time <=", value, "activityCreatTime");
            return (Criteria) this;
        }

        public Criteria andActivityCreatTimeIn(List<Date> values) {
            addCriterion("activity_creat_time in", values, "activityCreatTime");
            return (Criteria) this;
        }

        public Criteria andActivityCreatTimeNotIn(List<Date> values) {
            addCriterion("activity_creat_time not in", values, "activityCreatTime");
            return (Criteria) this;
        }

        public Criteria andActivityCreatTimeBetween(Date value1, Date value2) {
            addCriterion("activity_creat_time between", value1, value2, "activityCreatTime");
            return (Criteria) this;
        }

        public Criteria andActivityCreatTimeNotBetween(Date value1, Date value2) {
            addCriterion("activity_creat_time not between", value1, value2, "activityCreatTime");
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