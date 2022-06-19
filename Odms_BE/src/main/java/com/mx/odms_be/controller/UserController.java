package com.mx.odms_be.controller;

import com.mx.odms_be.aop.LogAnnotation;
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
    @LogAnnotation(module="查找",operator="查找所有用户")
    public List<User> findAllUser() {
        return userService.findAll();
    }

    @PostMapping("/findUserByUn")
    @LogAnnotation(module="查找",operator="根据用户名查找用户")
    public R findUser(@RequestBody User user) {
        User result = userService.findUserByUsername(user.getUsername());
        if (result != null) {
            return R.success(200, "查找成功", result);
        }
        return R.failure(400, "查找失败，请检查输入的用户是否真实存在");
    }

    @GetMapping("/findUserById/{id}")
    @LogAnnotation(module="查找",operator="根据id查找用户")
    public R findUser(@PathVariable("id") int id) {
        User result = userService.findUserById(id);
        if (result != null) {
            return R.success(200, "查找成功", result);
        }
        return R.failure(400, "查找失败，请检查输入的id是否真实存在");
    }

    @PostMapping("/register")
    @LogAnnotation(module="注册",operator="添加用户")
    public R addUser(@RequestBody User user) {
        int result = userService.addUser(user);
        if (result > 0) {
            return R.success(200, "注册成功", result);
        }
        return R.failure(400, "注册失败");
    }

    @PostMapping("/login")
    @LogAnnotation(module="登录",operator="用户登录验证")
    public R login(@RequestBody User user) {
        User result = userService.login(user.getUsername(), user.getPassword());
        if (result != null && result.getUserStatus() == 0) {
            String token = CreateJwt.getoken(result);
            return R.userSuccess(200, "登录成功", result.getId(), token);
        }
        return R.failure(400, "登录失败，请检查用户名和密码是否正确");
    }

    @PatchMapping("/deleteUser")
    @LogAnnotation(module="删除",operator="删除用户")
    public R deleteUser(@RequestBody User user) {
        int result = userService.deleteUser(user.getUsername());
        if (result > 0) {
            return R.success(200, "删除成功", result);
        }
        return R.failure(400, "删除失败，请检查您选中的用户是否已删除");
    }

    @PatchMapping("/updateUser")
    @LogAnnotation(module="更新",operator="更新用户账户")
    public R updateUser(@RequestBody User user) {
        int result = userService.updateUser(user);
        if(result > 0) {
            return R.success(200, "更新成功", user);
        }
        return R.failure(400, "更新失败，请检查你输入的信息是否正确");
    }

    @PatchMapping("/deleteUserById/{id}")
    @LogAnnotation(module="删除",operator="通过id删除用户")
    public R deleteUserById(@PathVariable int id) {
        int result = userService.deleteUserById(id);
        if (result > 0) {
            return R.success(200, "删除成功", result);
        }
        return R.failure(400, "删除失败，请检查您选中的用户是否已删除");
    }

}
