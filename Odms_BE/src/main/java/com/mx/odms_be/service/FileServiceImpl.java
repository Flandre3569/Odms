package com.mx.odms_be.service;

import cn.hutool.core.date.DateTime;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.mx.odms_be.entity.File;
import com.mx.odms_be.mapper.FileMapper;
import com.mx.odms_be.utils.AliyunOSSConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Resource
    private FileMapper fileMapper;
    @Override
    public int addFile(int user_id, String name, String address) {
        return fileMapper.addFile(user_id, name, address);
    }

    @Override
    public List<File> findFile(int user_id) {
        return fileMapper.findFileByUID(user_id);
    }

    @Override
    public String upload(MultipartFile file, int user_id) {
        String endpoint = AliyunOSSConfig.END_POINT;
        String accessKeyId = AliyunOSSConfig.AccessKey_ID;
        String accessKeySecret = AliyunOSSConfig.AccessKey_Secret;
        String bucketName = AliyunOSSConfig.BUCKE_NAME;

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        InputStream inputStream = null;
        try {
            //获取文件流
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取文件名称
        String filename = file.getOriginalFilename();
        String storeFile = filename;
        //1.在文件名称中添加随机唯一的值
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        filename = uuid+filename;

        //2.把文件按日期分类
        String datePath = new DateTime().toString("yyyy/MM/dd");
        filename = datePath +"/"+filename;

        //调用OSS方法实现上传
        ossClient.putObject(bucketName, filename, inputStream);
        ossClient.shutdown();
        //把上传之后的文件路径返回
        //需要把上传到阿里云oss路径手动拼接出来
        //路径规则：https://edu-cgq.oss-cn-chengdu.aliyuncs.com/QQ%E5%9B%BE%E7%89%8720200502111905.jpg
        String url = "https://"+bucketName+"."+endpoint+"/"+filename;
        int result = fileMapper.addFile(user_id, storeFile, url);
        if (result > 0) {
            return url;
        }
        return "上传失败";
    }
}
