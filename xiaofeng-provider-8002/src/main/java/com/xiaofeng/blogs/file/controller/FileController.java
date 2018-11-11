package com.xiaofeng.blogs.file.controller;

import com.xiaofeng.config.Constant;
import com.xiaofeng.utils.FileCompress;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/6 16:38
 * @Description: 关于文件的操作： 上传，下载
 */
@RestController
@RequestMapping("/file")
public class FileController {


    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String fileUpload(@RequestParam("file") MultipartFile file,@RequestParam("quality") String quality, HttpServletRequest request) throws IOException {


        InputStream inputStream = file.getInputStream();
        String fileName = file.getOriginalFilename();
        // 拼接临时文件地址，压缩文件
        String tempPath = Constant.fileTempPath + fileName;
        File targetFile = new File(tempPath);
        FileCompress.fileCompress(inputStream, targetFile, FileCompress.qualityBad);

        // 拼接上传所需要的key
        StringBuffer cosKey = new StringBuffer();
//        CosFileConfig.uploadFile()

//        File target = new File();
//        FileCompress.fileCompress(inputStream, target, FileCompress.qualityBad);
        return "123";
    }
}
