package com.fashion.mjysite.mapper;

import com.fashion.mjysite.entity.UserRole;
import java.util.List;

public interface UserRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_role
     *
     * @mbg.generated Tue Aug 28 12:18:49 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_role
     *
     * @mbg.generated Tue Aug 28 12:18:49 CST 2018
     */
    int insert(UserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_role
     *
     * @mbg.generated Tue Aug 28 12:18:49 CST 2018
     */
    UserRole selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_role
     *
     * @mbg.generated Tue Aug 28 12:18:49 CST 2018
     */
    List<UserRole> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_role
     *
     * @mbg.generated Tue Aug 28 12:18:49 CST 2018
     */
    int updateByPrimaryKey(UserRole record);
}