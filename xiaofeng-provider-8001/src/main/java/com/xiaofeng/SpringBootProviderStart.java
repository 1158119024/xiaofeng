package com.xiaofeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
//@EnableRedisHttpSession //启用redis 存储 session
public class SpringBootProviderStart {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootProviderStart.class, args);
    }
}
