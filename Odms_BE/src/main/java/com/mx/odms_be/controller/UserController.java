package com.mx.odms_be.controller;

import com.mx.odms_be.entity.R;
import com.mx.odms_be.entity.User;
import com.mx.odms_be.service.UserService;
import com.mx.odms_be.utils.CreateJwt;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

        @GetMapping("/findAllUser")
    public List<User> findAllUser() {
        return userService.findAll();
    }

    @PostMapping("/addUser")
    public R addUser(@RequestBody User user) {
        int result = userService.userAdd(user);
        if (result > 0) {
            return R.success(200, "注册成功");
        }
        return R.failure(400, "添加失败");
    }
    @PostMapping("/login")
    public R login(@RequestBody User user) {
        User result = userService.findUserByUsername(user.getUsername(), user.getPassword());
        String token = CreateJwt.getoken(result);
        if (result != null) {
            return R.userSuccess(200, "登录成功", result.getId(), token);
        }
        return R.failure(400, "登录失败，请检查用户名和密码是否正确");
    }
}
