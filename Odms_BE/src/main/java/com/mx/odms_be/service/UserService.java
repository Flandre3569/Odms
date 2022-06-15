package com.mx.odms_be.service;

import com.mx.odms_be.entity.User;

import java.util.List;

public interface UserService {
//    查找所有用户
    List<User> findAll();
//    添加用户
    int addUser(User user);
//    登录
    User login(String username, String password);
//    删除用户
    int deleteUser(String username);
//    更新用户账户信息
    int updateUser(User user);
//    根据用户名查找用户
    User findUserByUsername(String username);
//    根据id查找用户
    User findUserById(int id);
}

