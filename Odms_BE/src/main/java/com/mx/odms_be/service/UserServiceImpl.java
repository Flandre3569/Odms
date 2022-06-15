package com.mx.odms_be.service;

import com.mx.odms_be.entity.User;
import com.mx.odms_be.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public int userAdd(User user) {

        int result = userMapper.addUser(user);
        return result;
    }

    @Override
    public User findUserByUsername(String username, String password) {
        User user = userMapper.findUserByUsername(username);
        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
