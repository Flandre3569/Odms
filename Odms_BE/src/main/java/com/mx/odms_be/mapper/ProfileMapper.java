package com.mx.odms_be.mapper;

import com.mx.odms_be.entity.Profile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ProfileMapper {
    @Select("select * from `profile` where user_id = #{user_id}")
    Profile findProfile(int user_id);
    @Update({"<script>",
            "update profile",
            "<set>",
            "<if test = 'name != null'> name = #{name},</if>",
            "<if test = 'avatar != null'> avatar = #{avatar},</if>",
            "<if test = 'email != null'> email = #{email},</if>",
            "<if test = 'university != null'> university = #{university},</if>",
            "</set>",
            "where user_id = #{user_id}",
            "</script>"})
    int updateProfile(Profile profile);
}
