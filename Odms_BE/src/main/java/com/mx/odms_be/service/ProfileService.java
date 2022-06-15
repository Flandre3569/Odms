package com.mx.odms_be.service;

import com.mx.odms_be.entity.Profile;

public interface ProfileService {
//    查找用户的个人信息
    Profile findProfile(int user_id);
//    更新用户个人信息
    int updateProfile(Profile profile);
}
