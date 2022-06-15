package com.mx.odms_be.mapper;

import com.mx.odms_be.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from users")
    List<User> findAll();
    @Insert({"insert into `users`(username, password) value (#{username}, #{password})"})
    int addUser(User user);
    @Select({"select * from `users` where username = #{username}"})
    User findUserByUsername(String username);

}
