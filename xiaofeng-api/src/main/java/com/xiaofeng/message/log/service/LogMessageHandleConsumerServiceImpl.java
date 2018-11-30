package com.xiaofeng.message.log.service;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/26 23:19
 * @Description:
 */
@Component
public class LogMessageHandleConsumerServiceImpl implements FallbackFactory<LogMessageHandleConsumerService> {

    @Override
    public LogMessageHandleConsumerService create(Throwable throwable) {

        return new LogMessageHandleConsumerService() {

            @Override
            public void add(String id) {
                System.out.println("熔断"+id);
            }

        };
    }
}
