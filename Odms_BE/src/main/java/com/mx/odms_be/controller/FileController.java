package com.mx.odms_be.controller;

import com.mx.odms_be.entity.File;
import com.mx.odms_be.entity.R;
import com.mx.odms_be.service.FileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {
    @Resource
    private FileService fileService;
    @PostMapping("/uploadOssFile")
    public R uploadOssFile(MultipartFile file, int user_id){
        //获取上传文件MultipartFile
        //返回上传到oss的路径
        String url = fileService.upload(file, user_id);
        if(url.equals("上传失败")){
            return R.failure(400, "上传失败，请检查上传文件");
        }
        return R.success(200, "上传成功", url);
    }

    @PostMapping("/findFileList/{user_id}")
    public R findFileList(@PathVariable int user_id) {
        List<File> fileList = fileService.findFile(user_id);
        if (fileList != null) {
            return R.success(200, "查找成功", fileList);
        }
        return R.failure(400, "查找失败，请检查是否有此用户");
    }

}
