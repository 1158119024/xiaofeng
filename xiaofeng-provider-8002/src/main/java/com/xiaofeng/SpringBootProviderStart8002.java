package com.xiaofeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.util.StringUtils;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/20 11:48
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 900)
public class SpringBootProviderStart8002 {

    public static void main(String[] args) {
        System.out.println(StringUtils.isEmpty(null));
        SpringApplication.run(SpringBootProviderStart8002.class, args);
    }
}
