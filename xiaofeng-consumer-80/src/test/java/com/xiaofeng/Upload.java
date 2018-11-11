package com.xiaofeng;

import com.xiaofeng.base.httpformat.ResponseData;
import com.xiaofeng.blogs.file.service.FileConsumerService;
import com.xiaofeng.config.feign.FeignSpringFormEncoder;
import com.xiaofeng.config.feign.InMemoryMultipartFile;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/6 23:42
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Upload {

    @Test
    public void test(){
        Feign.Builder encoder = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new FeignSpringFormEncoder());

        FileConsumerService service = encoder.target(FileConsumerService.class, "http://localhost:8002");

        MultipartFile file = new InMemoryMultipartFile("filename.tmp", new byte[]{65, 66, 67, 68});

        ResponseData s = service.fileUpload(file, 1.0f);
        System.out.println("Upload test: " + s);
    }
}
