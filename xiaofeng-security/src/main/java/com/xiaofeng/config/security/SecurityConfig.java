package com.xiaofeng.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/19 14:33
 * @Description: 将不想暴露在外界的服务保护起来（权限验证）
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        //表示所有的访问都必须进行认证请求处理后才能正常进行
        http.httpBasic().and().authorizeRequests().anyRequest().fullyAuthenticated();
        //设置session为无状态,提升操作效率
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication().withUser("xiaofeng").password("lzj1158119024").roles("USER").and().withUser("admin").password("lzj1158119024").roles("adminstrator");

    }
}
