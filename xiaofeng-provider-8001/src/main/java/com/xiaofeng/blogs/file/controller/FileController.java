package com.xiaofeng.blogs.file.controller;

import com.xiaofeng.base.httpformat.ResponseData;
import com.xiaofeng.blogs.file.dto.ImageFileDto;
import com.xiaofeng.blogs.file.service.FileService;
import com.xiaofeng.checklogin.aop.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/6 16:38
 * @Description: 关于文件的操作： 上传，下载，删除
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 上传
     * @param file：文件
     * @param quality：清晰度
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseData fileUpload(@RequestParam("file") MultipartFile file,@RequestParam("quality") Float quality, HttpServletRequest request) throws IOException {
        Integer userId = AopUtils.getUserIdByToken(request);
        ImageFileDto imageFileDto = fileService.fileUpload(file, quality, userId);
        return ResponseData.success(imageFileDto);
    }
}
