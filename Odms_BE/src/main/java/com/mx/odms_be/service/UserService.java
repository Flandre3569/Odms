package com.mx.odms_be.service;

import com.mx.odms_be.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    int userAdd(User user);
    User findUserByUsername(String username, String password);
}

