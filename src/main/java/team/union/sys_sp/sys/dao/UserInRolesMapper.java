package team.union.sys_sp.sys.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import team.union.sys_sp.sys.model.UserInRoles;
import team.union.sys_sp.sys.model.UserInRolesCriteria;

public interface UserInRolesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_in_roles
     *
     * @mbggenerated Thu Dec 31 17:36:57 CST 2015
     */
    int countByExample(UserInRolesCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_in_roles
     *
     * @mbggenerated Thu Dec 31 17:36:57 CST 2015
     */
    int deleteByExample(UserInRolesCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_in_roles
     *
     * @mbggenerated Thu Dec 31 17:36:57 CST 2015
     */
    int deleteByPrimaryKey(Long userInRoleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_in_roles
     *
     * @mbggenerated Thu Dec 31 17:36:57 CST 2015
     */
    int insert(UserInRoles record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_in_roles
     *
     * @mbggenerated Thu Dec 31 17:36:57 CST 2015
     */
    int insertSelective(UserInRoles record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_in_roles
     *
     * @mbggenerated Thu Dec 31 17:36:57 CST 2015
     */
    List<UserInRoles> selectByExample(UserInRolesCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_in_roles
     *
     * @mbggenerated Thu Dec 31 17:36:57 CST 2015
     */
    UserInRoles selectByPrimaryKey(Long userInRoleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_in_roles
     *
     * @mbggenerated Thu Dec 31 17:36:57 CST 2015
     */
    int updateByExampleSelective(@Param("record") UserInRoles record, @Param("example") UserInRolesCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_in_roles
     *
     * @mbggenerated Thu Dec 31 17:36:57 CST 2015
     */
    int updateByExample(@Param("record") UserInRoles record, @Param("example") UserInRolesCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_in_roles
     *
     * @mbggenerated Thu Dec 31 17:36:57 CST 2015
     */
    int updateByPrimaryKeySelective(UserInRoles record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_in_roles
     *
     * @mbggenerated Thu Dec 31 17:36:57 CST 2015
     */
    int updateByPrimaryKey(UserInRoles record);
}