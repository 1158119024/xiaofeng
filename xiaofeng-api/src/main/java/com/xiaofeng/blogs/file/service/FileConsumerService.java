package com.xiaofeng.blogs.file.service;

import com.xiaofeng.base.httpformat.ResponseData;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/6 22:07
 * @Description:
 */
//@FeignClient(name = "xiaofeng-provider")
public interface FileConsumerService {

    @RequestLine("POST /file/upload")
    ResponseData fileUpload(@Param("file") MultipartFile file, @Param("quality") Float quality);

}
