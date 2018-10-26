package com.xiaofeng.config.health;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/17 21:58
 * @Description:
 */
@Configuration
public class HealthCheckConfig {
    @Bean
    public MyHealthIndicator myHealthIndicator(){
        return new MyHealthIndicator();
    }
}
