package team.union.business.store.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BisStoreExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BisStoreExample() {
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

        public Criteria andStoreNameIsNull() {
            addCriterion("store_name is null");
            return (Criteria) this;
        }

        public Criteria andStoreNameIsNotNull() {
            addCriterion("store_name is not null");
            return (Criteria) this;
        }

        public Criteria andStoreNameEqualTo(String value) {
            addCriterion("store_name =", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotEqualTo(String value) {
            addCriterion("store_name <>", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameGreaterThan(String value) {
            addCriterion("store_name >", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameGreaterThanOrEqualTo(String value) {
            addCriterion("store_name >=", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameLessThan(String value) {
            addCriterion("store_name <", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameLessThanOrEqualTo(String value) {
            addCriterion("store_name <=", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameLike(String value) {
            addCriterion("store_name like", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotLike(String value) {
            addCriterion("store_name not like", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameIn(List<String> values) {
            addCriterion("store_name in", values, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotIn(List<String> values) {
            addCriterion("store_name not in", values, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameBetween(String value1, String value2) {
            addCriterion("store_name between", value1, value2, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotBetween(String value1, String value2) {
            addCriterion("store_name not between", value1, value2, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreAddressIsNull() {
            addCriterion("store_address is null");
            return (Criteria) this;
        }

        public Criteria andStoreAddressIsNotNull() {
            addCriterion("store_address is not null");
            return (Criteria) this;
        }

        public Criteria andStoreAddressEqualTo(String value) {
            addCriterion("store_address =", value, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressNotEqualTo(String value) {
            addCriterion("store_address <>", value, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressGreaterThan(String value) {
            addCriterion("store_address >", value, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressGreaterThanOrEqualTo(String value) {
            addCriterion("store_address >=", value, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressLessThan(String value) {
            addCriterion("store_address <", value, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressLessThanOrEqualTo(String value) {
            addCriterion("store_address <=", value, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressLike(String value) {
            addCriterion("store_address like", value, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressNotLike(String value) {
            addCriterion("store_address not like", value, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressIn(List<String> values) {
            addCriterion("store_address in", values, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressNotIn(List<String> values) {
            addCriterion("store_address not in", values, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressBetween(String value1, String value2) {
            addCriterion("store_address between", value1, value2, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressNotBetween(String value1, String value2) {
            addCriterion("store_address not between", value1, value2, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStorePhoneIsNull() {
            addCriterion("store_phone is null");
            return (Criteria) this;
        }

        public Criteria andStorePhoneIsNotNull() {
            addCriterion("store_phone is not null");
            return (Criteria) this;
        }

        public Criteria andStorePhoneEqualTo(String value) {
            addCriterion("store_phone =", value, "storePhone");
            return (Criteria) this;
        }

        public Criteria andStorePhoneNotEqualTo(String value) {
            addCriterion("store_phone <>", value, "storePhone");
            return (Criteria) this;
        }

        public Criteria andStorePhoneGreaterThan(String value) {
            addCriterion("store_phone >", value, "storePhone");
            return (Criteria) this;
        }

        public Criteria andStorePhoneGreaterThanOrEqualTo(String value) {
            addCriterion("store_phone >=", value, "storePhone");
            return (Criteria) this;
        }

        public Criteria andStorePhoneLessThan(String value) {
            addCriterion("store_phone <", value, "storePhone");
            return (Criteria) this;
        }

        public Criteria andStorePhoneLessThanOrEqualTo(String value) {
            addCriterion("store_phone <=", value, "storePhone");
            return (Criteria) this;
        }

        public Criteria andStorePhoneLike(String value) {
            addCriterion("store_phone like", value, "storePhone");
            return (Criteria) this;
        }

        public Criteria andStorePhoneNotLike(String value) {
            addCriterion("store_phone not like", value, "storePhone");
            return (Criteria) this;
        }

        public Criteria andStorePhoneIn(List<String> values) {
            addCriterion("store_phone in", values, "storePhone");
            return (Criteria) this;
        }

        public Criteria andStorePhoneNotIn(List<String> values) {
            addCriterion("store_phone not in", values, "storePhone");
            return (Criteria) this;
        }

        public Criteria andStorePhoneBetween(String value1, String value2) {
            addCriterion("store_phone between", value1, value2, "storePhone");
            return (Criteria) this;
        }

        public Criteria andStorePhoneNotBetween(String value1, String value2) {
            addCriterion("store_phone not between", value1, value2, "storePhone");
            return (Criteria) this;
        }

        public Criteria andStorePicIsNull() {
            addCriterion("store_pic is null");
            return (Criteria) this;
        }

        public Criteria andStorePicIsNotNull() {
            addCriterion("store_pic is not null");
            return (Criteria) this;
        }

        public Criteria andStorePicEqualTo(String value) {
            addCriterion("store_pic =", value, "storePic");
            return (Criteria) this;
        }

        public Criteria andStorePicNotEqualTo(String value) {
            addCriterion("store_pic <>", value, "storePic");
            return (Criteria) this;
        }

        public Criteria andStorePicGreaterThan(String value) {
            addCriterion("store_pic >", value, "storePic");
            return (Criteria) this;
        }

        public Criteria andStorePicGreaterThanOrEqualTo(String value) {
            addCriterion("store_pic >=", value, "storePic");
            return (Criteria) this;
        }

        public Criteria andStorePicLessThan(String value) {
            addCriterion("store_pic <", value, "storePic");
            return (Criteria) this;
        }

        public Criteria andStorePicLessThanOrEqualTo(String value) {
            addCriterion("store_pic <=", value, "storePic");
            return (Criteria) this;
        }

        public Criteria andStorePicLike(String value) {
            addCriterion("store_pic like", value, "storePic");
            return (Criteria) this;
        }

        public Criteria andStorePicNotLike(String value) {
            addCriterion("store_pic not like", value, "storePic");
            return (Criteria) this;
        }

        public Criteria andStorePicIn(List<String> values) {
            addCriterion("store_pic in", values, "storePic");
            return (Criteria) this;
        }

        public Criteria andStorePicNotIn(List<String> values) {
            addCriterion("store_pic not in", values, "storePic");
            return (Criteria) this;
        }

        public Criteria andStorePicBetween(String value1, String value2) {
            addCriterion("store_pic between", value1, value2, "storePic");
            return (Criteria) this;
        }

        public Criteria andStorePicNotBetween(String value1, String value2) {
            addCriterion("store_pic not between", value1, value2, "storePic");
            return (Criteria) this;
        }

        public Criteria andStoreIntroductionIsNull() {
            addCriterion("store_introduction is null");
            return (Criteria) this;
        }

        public Criteria andStoreIntroductionIsNotNull() {
            addCriterion("store_introduction is not null");
            return (Criteria) this;
        }

        public Criteria andStoreIntroductionEqualTo(String value) {
            addCriterion("store_introduction =", value, "storeIntroduction");
            return (Criteria) this;
        }

        public Criteria andStoreIntroductionNotEqualTo(String value) {
            addCriterion("store_introduction <>", value, "storeIntroduction");
            return (Criteria) this;
        }

        public Criteria andStoreIntroductionGreaterThan(String value) {
            addCriterion("store_introduction >", value, "storeIntroduction");
            return (Criteria) this;
        }

        public Criteria andStoreIntroductionGreaterThanOrEqualTo(String value) {
            addCriterion("store_introduction >=", value, "storeIntroduction");
            return (Criteria) this;
        }

        public Criteria andStoreIntroductionLessThan(String value) {
            addCriterion("store_introduction <", value, "storeIntroduction");
            return (Criteria) this;
        }

        public Criteria andStoreIntroductionLessThanOrEqualTo(String value) {
            addCriterion("store_introduction <=", value, "storeIntroduction");
            return (Criteria) this;
        }

        public Criteria andStoreIntroductionLike(String value) {
            addCriterion("store_introduction like", value, "storeIntroduction");
            return (Criteria) this;
        }

        public Criteria andStoreIntroductionNotLike(String value) {
            addCriterion("store_introduction not like", value, "storeIntroduction");
            return (Criteria) this;
        }

        public Criteria andStoreIntroductionIn(List<String> values) {
            addCriterion("store_introduction in", values, "storeIntroduction");
            return (Criteria) this;
        }

        public Criteria andStoreIntroductionNotIn(List<String> values) {
            addCriterion("store_introduction not in", values, "storeIntroduction");
            return (Criteria) this;
        }

        public Criteria andStoreIntroductionBetween(String value1, String value2) {
            addCriterion("store_introduction between", value1, value2, "storeIntroduction");
            return (Criteria) this;
        }

        public Criteria andStoreIntroductionNotBetween(String value1, String value2) {
            addCriterion("store_introduction not between", value1, value2, "storeIntroduction");
            return (Criteria) this;
        }

        public Criteria andStoreLongitudeIsNull() {
            addCriterion("store_longitude is null");
            return (Criteria) this;
        }

        public Criteria andStoreLongitudeIsNotNull() {
            addCriterion("store_longitude is not null");
            return (Criteria) this;
        }

        public Criteria andStoreLongitudeEqualTo(String value) {
            addCriterion("store_longitude =", value, "storeLongitude");
            return (Criteria) this;
        }

        public Criteria andStoreLongitudeNotEqualTo(String value) {
            addCriterion("store_longitude <>", value, "storeLongitude");
            return (Criteria) this;
        }

        public Criteria andStoreLongitudeGreaterThan(String value) {
            addCriterion("store_longitude >", value, "storeLongitude");
            return (Criteria) this;
        }

        public Criteria andStoreLongitudeGreaterThanOrEqualTo(String value) {
            addCriterion("store_longitude >=", value, "storeLongitude");
            return (Criteria) this;
        }

        public Criteria andStoreLongitudeLessThan(String value) {
            addCriterion("store_longitude <", value, "storeLongitude");
            return (Criteria) this;
        }

        public Criteria andStoreLongitudeLessThanOrEqualTo(String value) {
            addCriterion("store_longitude <=", value, "storeLongitude");
            return (Criteria) this;
        }

        public Criteria andStoreLongitudeLike(String value) {
            addCriterion("store_longitude like", value, "storeLongitude");
            return (Criteria) this;
        }

        public Criteria andStoreLongitudeNotLike(String value) {
            addCriterion("store_longitude not like", value, "storeLongitude");
            return (Criteria) this;
        }

        public Criteria andStoreLongitudeIn(List<String> values) {
            addCriterion("store_longitude in", values, "storeLongitude");
            return (Criteria) this;
        }

        public Criteria andStoreLongitudeNotIn(List<String> values) {
            addCriterion("store_longitude not in", values, "storeLongitude");
            return (Criteria) this;
        }

        public Criteria andStoreLongitudeBetween(String value1, String value2) {
            addCriterion("store_longitude between", value1, value2, "storeLongitude");
            return (Criteria) this;
        }

        public Criteria andStoreLongitudeNotBetween(String value1, String value2) {
            addCriterion("store_longitude not between", value1, value2, "storeLongitude");
            return (Criteria) this;
        }

        public Criteria andStoreLatitudeIsNull() {
            addCriterion("store_latitude is null");
            return (Criteria) this;
        }

        public Criteria andStoreLatitudeIsNotNull() {
            addCriterion("store_latitude is not null");
            return (Criteria) this;
        }

        public Criteria andStoreLatitudeEqualTo(String value) {
            addCriterion("store_latitude =", value, "storeLatitude");
            return (Criteria) this;
        }

        public Criteria andStoreLatitudeNotEqualTo(String value) {
            addCriterion("store_latitude <>", value, "storeLatitude");
            return (Criteria) this;
        }

        public Criteria andStoreLatitudeGreaterThan(String value) {
            addCriterion("store_latitude >", value, "storeLatitude");
            return (Criteria) this;
        }

        public Criteria andStoreLatitudeGreaterThanOrEqualTo(String value) {
            addCriterion("store_latitude >=", value, "storeLatitude");
            return (Criteria) this;
        }

        public Criteria andStoreLatitudeLessThan(String value) {
            addCriterion("store_latitude <", value, "storeLatitude");
            return (Criteria) this;
        }

        public Criteria andStoreLatitudeLessThanOrEqualTo(String value) {
            addCriterion("store_latitude <=", value, "storeLatitude");
            return (Criteria) this;
        }

        public Criteria andStoreLatitudeLike(String value) {
            addCriterion("store_latitude like", value, "storeLatitude");
            return (Criteria) this;
        }

        public Criteria andStoreLatitudeNotLike(String value) {
            addCriterion("store_latitude not like", value, "storeLatitude");
            return (Criteria) this;
        }

        public Criteria andStoreLatitudeIn(List<String> values) {
            addCriterion("store_latitude in", values, "storeLatitude");
            return (Criteria) this;
        }

        public Criteria andStoreLatitudeNotIn(List<String> values) {
            addCriterion("store_latitude not in", values, "storeLatitude");
            return (Criteria) this;
        }

        public Criteria andStoreLatitudeBetween(String value1, String value2) {
            addCriterion("store_latitude between", value1, value2, "storeLatitude");
            return (Criteria) this;
        }

        public Criteria andStoreLatitudeNotBetween(String value1, String value2) {
            addCriterion("store_latitude not between", value1, value2, "storeLatitude");
            return (Criteria) this;
        }

        public Criteria andStoreUserIdIsNull() {
            addCriterion("store_user_id is null");
            return (Criteria) this;
        }

        public Criteria andStoreUserIdIsNotNull() {
            addCriterion("store_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andStoreUserIdEqualTo(Long value) {
            addCriterion("store_user_id =", value, "storeUserId");
            return (Criteria) this;
        }

        public Criteria andStoreUserIdNotEqualTo(Long value) {
            addCriterion("store_user_id <>", value, "storeUserId");
            return (Criteria) this;
        }

        public Criteria andStoreUserIdGreaterThan(Long value) {
            addCriterion("store_user_id >", value, "storeUserId");
            return (Criteria) this;
        }

        public Criteria andStoreUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("store_user_id >=", value, "storeUserId");
            return (Criteria) this;
        }

        public Criteria andStoreUserIdLessThan(Long value) {
            addCriterion("store_user_id <", value, "storeUserId");
            return (Criteria) this;
        }

        public Criteria andStoreUserIdLessThanOrEqualTo(Long value) {
            addCriterion("store_user_id <=", value, "storeUserId");
            return (Criteria) this;
        }

        public Criteria andStoreUserIdIn(List<Long> values) {
            addCriterion("store_user_id in", values, "storeUserId");
            return (Criteria) this;
        }

        public Criteria andStoreUserIdNotIn(List<Long> values) {
            addCriterion("store_user_id not in", values, "storeUserId");
            return (Criteria) this;
        }

        public Criteria andStoreUserIdBetween(Long value1, Long value2) {
            addCriterion("store_user_id between", value1, value2, "storeUserId");
            return (Criteria) this;
        }

        public Criteria andStoreUserIdNotBetween(Long value1, Long value2) {
            addCriterion("store_user_id not between", value1, value2, "storeUserId");
            return (Criteria) this;
        }

        public Criteria andStoreSortNumIsNull() {
            addCriterion("store_sort_num is null");
            return (Criteria) this;
        }

        public Criteria andStoreSortNumIsNotNull() {
            addCriterion("store_sort_num is not null");
            return (Criteria) this;
        }

        public Criteria andStoreSortNumEqualTo(Long value) {
            addCriterion("store_sort_num =", value, "storeSortNum");
            return (Criteria) this;
        }

        public Criteria andStoreSortNumNotEqualTo(Long value) {
            addCriterion("store_sort_num <>", value, "storeSortNum");
            return (Criteria) this;
        }

        public Criteria andStoreSortNumGreaterThan(Long value) {
            addCriterion("store_sort_num >", value, "storeSortNum");
            return (Criteria) this;
        }

        public Criteria andStoreSortNumGreaterThanOrEqualTo(Long value) {
            addCriterion("store_sort_num >=", value, "storeSortNum");
            return (Criteria) this;
        }

        public Criteria andStoreSortNumLessThan(Long value) {
            addCriterion("store_sort_num <", value, "storeSortNum");
            return (Criteria) this;
        }

        public Criteria andStoreSortNumLessThanOrEqualTo(Long value) {
            addCriterion("store_sort_num <=", value, "storeSortNum");
            return (Criteria) this;
        }

        public Criteria andStoreSortNumIn(List<Long> values) {
            addCriterion("store_sort_num in", values, "storeSortNum");
            return (Criteria) this;
        }

        public Criteria andStoreSortNumNotIn(List<Long> values) {
            addCriterion("store_sort_num not in", values, "storeSortNum");
            return (Criteria) this;
        }

        public Criteria andStoreSortNumBetween(Long value1, Long value2) {
            addCriterion("store_sort_num between", value1, value2, "storeSortNum");
            return (Criteria) this;
        }

        public Criteria andStoreSortNumNotBetween(Long value1, Long value2) {
            addCriterion("store_sort_num not between", value1, value2, "storeSortNum");
            return (Criteria) this;
        }

        public Criteria andStoreCreatTimeIsNull() {
            addCriterion("store_creat_time is null");
            return (Criteria) this;
        }

        public Criteria andStoreCreatTimeIsNotNull() {
            addCriterion("store_creat_time is not null");
            return (Criteria) this;
        }

        public Criteria andStoreCreatTimeEqualTo(Date value) {
            addCriterion("store_creat_time =", value, "storeCreatTime");
            return (Criteria) this;
        }

        public Criteria andStoreCreatTimeNotEqualTo(Date value) {
            addCriterion("store_creat_time <>", value, "storeCreatTime");
            return (Criteria) this;
        }

        public Criteria andStoreCreatTimeGreaterThan(Date value) {
            addCriterion("store_creat_time >", value, "storeCreatTime");
            return (Criteria) this;
        }

        public Criteria andStoreCreatTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("store_creat_time >=", value, "storeCreatTime");
            return (Criteria) this;
        }

        public Criteria andStoreCreatTimeLessThan(Date value) {
            addCriterion("store_creat_time <", value, "storeCreatTime");
            return (Criteria) this;
        }

        public Criteria andStoreCreatTimeLessThanOrEqualTo(Date value) {
            addCriterion("store_creat_time <=", value, "storeCreatTime");
            return (Criteria) this;
        }

        public Criteria andStoreCreatTimeIn(List<Date> values) {
            addCriterion("store_creat_time in", values, "storeCreatTime");
            return (Criteria) this;
        }

        public Criteria andStoreCreatTimeNotIn(List<Date> values) {
            addCriterion("store_creat_time not in", values, "storeCreatTime");
            return (Criteria) this;
        }

        public Criteria andStoreCreatTimeBetween(Date value1, Date value2) {
            addCriterion("store_creat_time between", value1, value2, "storeCreatTime");
            return (Criteria) this;
        }

        public Criteria andStoreCreatTimeNotBetween(Date value1, Date value2) {
            addCriterion("store_creat_time not between", value1, value2, "storeCreatTime");
            return (Criteria) this;
        }

        public Criteria andStoreStatusIsNull() {
            addCriterion("store_status is null");
            return (Criteria) this;
        }

        public Criteria andStoreStatusIsNotNull() {
            addCriterion("store_status is not null");
            return (Criteria) this;
        }

        public Criteria andStoreStatusEqualTo(Integer value) {
            addCriterion("store_status =", value, "storeStatus");
            return (Criteria) this;
        }

        public Criteria andStoreStatusNotEqualTo(Integer value) {
            addCriterion("store_status <>", value, "storeStatus");
            return (Criteria) this;
        }

        public Criteria andStoreStatusGreaterThan(Integer value) {
            addCriterion("store_status >", value, "storeStatus");
            return (Criteria) this;
        }

        public Criteria andStoreStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("store_status >=", value, "storeStatus");
            return (Criteria) this;
        }

        public Criteria andStoreStatusLessThan(Integer value) {
            addCriterion("store_status <", value, "storeStatus");
            return (Criteria) this;
        }

        public Criteria andStoreStatusLessThanOrEqualTo(Integer value) {
            addCriterion("store_status <=", value, "storeStatus");
            return (Criteria) this;
        }

        public Criteria andStoreStatusIn(List<Integer> values) {
            addCriterion("store_status in", values, "storeStatus");
            return (Criteria) this;
        }

        public Criteria andStoreStatusNotIn(List<Integer> values) {
            addCriterion("store_status not in", values, "storeStatus");
            return (Criteria) this;
        }

        public Criteria andStoreStatusBetween(Integer value1, Integer value2) {
            addCriterion("store_status between", value1, value2, "storeStatus");
            return (Criteria) this;
        }

        public Criteria andStoreStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("store_status not between", value1, value2, "storeStatus");
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