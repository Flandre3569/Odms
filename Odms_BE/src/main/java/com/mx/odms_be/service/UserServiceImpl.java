package com.mx.odms_be.service;

import com.mx.odms_be.entity.User;
import com.mx.odms_be.mapper.UserMapper;
import com.mx.odms_be.utils.MD5Util;
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
    public int addUser(User user) {
        String newPwd = MD5Util.getMD5(user.getPassword());
        user.setPassword(newPwd);
        return userMapper.addUser(user);
    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.findUserByUsername(username);
        String newPwd = MD5Util.getMD5(password);
        if (user.getPassword().equals(newPwd)) {
            return user;
        }
        return null;
    }

    @Override
    public int deleteUser(String username) {
        return userMapper.deleteUser(username);
    }

    @Override
    public int updateUser(User user) {
        String newPwd = MD5Util.getMD5(user.getPassword());
        user.setPassword(newPwd);
        return userMapper.updateUser(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);

    }

    @Override
    public User findUserById(int id) {
        return userMapper.findUserById(id);
    }
}
