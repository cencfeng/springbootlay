package com.fashion.mjysite.mapper;

import com.fashion.mjysite.entity.Sign;
import java.util.List;

public interface SignMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sign
     *
     * @mbg.generated Tue Aug 28 12:18:49 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sign
     *
     * @mbg.generated Tue Aug 28 12:18:49 CST 2018
     */
    int insert(Sign record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sign
     *
     * @mbg.generated Tue Aug 28 12:18:49 CST 2018
     */
    Sign selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sign
     *
     * @mbg.generated Tue Aug 28 12:18:49 CST 2018
     */
    List<Sign> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sign
     *
     * @mbg.generated Tue Aug 28 12:18:49 CST 2018
     */
    int updateByPrimaryKey(Sign record);
}