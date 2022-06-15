package com.mx.odms_be.service;

import com.mx.odms_be.entity.Manager;

public interface ManagerService {
    Manager login(String username, String password);
}
