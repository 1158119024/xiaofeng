package com.xiaofeng;

import com.xiaofeng.config.feign.FeignConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(defaultConfiguration = {FeignConfiguration.class})
@EnableZuulProxy//开启网关
public class SpringBootConsumerStart {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConsumerStart.class, args);
    }
}
