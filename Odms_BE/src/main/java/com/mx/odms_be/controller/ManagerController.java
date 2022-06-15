package com.mx.odms_be.controller;

import com.mx.odms_be.entity.Manager;
import com.mx.odms_be.entity.R;
import com.mx.odms_be.service.ManagerService;
import com.mx.odms_be.utils.CreateJwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Resource
    private ManagerService managerService;
    @PostMapping("/login")
    public R login(@RequestBody Manager manager) {
        Manager result = managerService.login(manager.getUsername(), manager.getPassword());
        if (result != null) {
            String token = CreateJwt.getoken(result);
            return R.userSuccess(200, "登录成功", result, token);
        }
        return R.failure(400, "登录失败");
    }
}
