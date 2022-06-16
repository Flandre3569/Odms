package com.mx.odms_be.service;

import com.mx.odms_be.entity.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    int addFile(int user_id, String name, String address);

    List<File> findFile(int user_id);

    String upload(MultipartFile file, int user_id);

    List<File> fileAllFile();
}
