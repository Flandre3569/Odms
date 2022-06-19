package com.mx.odms_be.mapper;

import com.mx.odms_be.entity.File;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileMapper {
    @Insert({"insert into `file`(user_id, name, address) value(#{user_id}, #{name}, #{address})"})
    int addFile(int user_id, String name, String address);

    @Select({"select * from `file` where user_id = #{user_id}"})
    List<File> findFileByUID(int user_id);

    @Select({"select * from `file`"})
    List<File> findAllFile();

    @Delete({"delete from `file` where id = #{id}"})
    int deleteFile(int id);

    @Select({"select * from `file` where id = #{id}"})
    File findFileById(int id);

    @Select({"select count(*) from `file` where user_id = #{user_id}"})
    int fileCount(int user_id);
}
