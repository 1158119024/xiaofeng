package com.xiaofeng.config.feign;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/22 14:45
 * @Description: feign日志
 */
@Configuration
public class FeignLogConfiguration {
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
