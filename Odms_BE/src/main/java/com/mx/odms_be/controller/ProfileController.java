package com.mx.odms_be.controller;

import com.mx.odms_be.aop.LogAnnotation;
import com.mx.odms_be.entity.Profile;
import com.mx.odms_be.entity.R;
import com.mx.odms_be.service.ProfileService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Resource
    private ProfileService profileService;

    @PostMapping("/findProfile")
    @LogAnnotation(module = "查找", operator = "用户信息查找")
    public R findProfile(@RequestBody Profile profile) {
        Profile result = profileService.findProfile(profile.getUser_id());
        if (result != null) {
            return R.success(200, "查找成功", result);
        }
        return R.failure(400, "查找失败，请检查查找内容是否存在");
    }

    @PatchMapping("/updateProfile")
    @LogAnnotation(module = "更新", operator = "更新用户个人信息")
    public R updateProfile(@RequestBody Profile profile) {
        int result = profileService.updateProfile(profile);
        if (result > 0) {
            return R.success(200, "更新成功", profile);
        }
        return R.failure(400, "更新失败，请检查输入内容");
    }

    @GetMapping("/findAllProfile")
    public R findAllProfile() {
        List<Profile> profileList = profileService.findAllProfile();
        return R.success(200, "查询成功", profileList);
    }
}
