package com.xiaofeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/26 21:22
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient
public class SpringBootMessageHandle {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMessageHandle.class, args);
    }
}
