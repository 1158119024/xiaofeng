package com.xiaofeng;

import com.xiaofeng.config.feign.FeignConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/22 17:31
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(defaultConfiguration = {FeignConfiguration.class})
public class SpringBootConsumerStart9001 {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConsumerStart9001.class, args);
    }
}
