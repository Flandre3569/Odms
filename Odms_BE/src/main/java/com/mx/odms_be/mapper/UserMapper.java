package com.mx.odms_be.mapper;

import com.mx.odms_be.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from users")
    List<User> findAll();
    @Select("select * from `users` where id = #{id}")
    User findUserById(int id);
    @Insert({"insert into `users`(username, password) value (#{username}, #{password})"})
    int addUser(User user);
    @Select({"select * from `users` where username = #{username}"})
    User findUserByUsername(String username);
    @Update({"update `users` set userStatus = 1 where username = #{username}"})
    int deleteUser(String username);
    @Update({"update `users` set userStatus = 1 where id = #{id}"})
    int deleteUserById(int id);
    @Update({"<script>",
            "update users",
            "<set>",
            "<if test = 'username != null'> username = #{username},</if>",
            "<if test = 'password != null'> password = #{password},</if>",
            "<if test = 'userStatus != null'> userStatus = #{userStatus},</if>",
            "</set>",
            "where id = #{id}",
            "</script>"})
    int updateUser(User user);
}
