package com.mx.odms_be.service;

import com.mx.odms_be.entity.Manager;
import com.mx.odms_be.mapper.ManagerMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Resource
    private ManagerMapper managerMapper;
    @Override
    public Manager login(String username, String password) {
        Manager manager = managerMapper.findManager(username);
        if (manager.getPassword().equals(password)) {
            return manager;
        }
        return null;
    }
}
