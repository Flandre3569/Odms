package com.mx.odms_be.mapper;

import com.mx.odms_be.entity.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ManagerMapper {
    @Select({"select * from `manager` where username = #{username}"})
    Manager findManager(String username);
}
