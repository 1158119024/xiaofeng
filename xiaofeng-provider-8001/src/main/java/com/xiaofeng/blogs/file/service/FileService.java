package com.xiaofeng.blogs.file.service;

import com.xiaofeng.blogs.file.dto.ImageFileDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/7 21:54
 * @Description:
 */
public interface FileService {

    ImageFileDto fileUpload(MultipartFile file, Float quality, Integer userId) throws IOException;
}
