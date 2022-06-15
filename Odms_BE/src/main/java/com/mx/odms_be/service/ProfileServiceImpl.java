package com.mx.odms_be.service;

import com.mx.odms_be.entity.Profile;
import com.mx.odms_be.mapper.ProfileMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Resource
    private ProfileMapper profileMapper;
    @Override
    public Profile findProfile(int user_id) {
        return profileMapper.findProfile(user_id);
    }

    @Override
    public int updateProfile(Profile profile) {
        return profileMapper.updateProfile(profile);
    }
}
