package com.xiaofeng.blogs.collect.service;

import com.xiaofeng.base.httpformat.ResponseData;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/17 10:59
 * @Description: 服务熔断实现
 */
//@Component
/*
public class CollectConsumerServiceImpl implements FallbackFactory<CollectConsumerService> {

    @Override
    public CollectConsumerService create(Throwable throwable) {
        return new CollectConsumerService() {
            @Override
            public ResponseData list() {
                return ResponseData.hystrix();
            }

            @Override
            public Map<String, Object> firstResp() {
                return null;
            }
        };
    }
}
*/
