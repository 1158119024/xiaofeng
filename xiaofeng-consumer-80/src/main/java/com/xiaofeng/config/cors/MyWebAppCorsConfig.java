package com.xiaofeng.config.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/21 16:14
 * @Description: cors 跨域问题
 */
//@Configuration
public class MyWebAppCorsConfig extends WebMvcConfigurerAdapter {

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        //config.addAllowedMethod("OPTIONS");
        //config.addAllowedMethod("HEAD");
        //config.addAllowedMethod("GET");
        //config.addAllowedMethod("PUT");
        //config.addAllowedMethod("POST");
        //config.addAllowedMethod("DELETE");
        //config.addAllowedMethod("PATCH");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("*//**", config);
        return new CorsFilter(source);
    }
}
