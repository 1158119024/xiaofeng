package com.xiaofeng.blogs.file.controller;

import com.xiaofeng.base.httpformat.ResponseData;
import com.xiaofeng.blogs.file.service.FileConsumerService;
import com.xiaofeng.config.Constant;
import com.xiaofeng.config.feign.FeignSpringFormEncoder;
import feign.Feign;
import feign.Request;
import feign.jackson.JacksonDecoder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/6 22:10
 * @Description:
 */
@RestController
@RequestMapping("/file")
public class FileConsumerController {

//    @Autowired
//    private FileConsumerService fileConsumerService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseData fileUpload(@RequestParam(value = "file") MultipartFile file,@RequestParam("quality") Float quality){

        Feign.Builder encoder = Feign.builder()
                .decoder(new JacksonDecoder())
                .options(new Request.Options(3000, 15000))// 连接超时时长及响应超时时长
                .encoder(new FeignSpringFormEncoder());

        FileConsumerService service = encoder.target(FileConsumerService.class, Constant.fileUpload);
        return service.fileUpload(file, quality);
//        return fileConsumerService.fileUpload(file);
    }
}
