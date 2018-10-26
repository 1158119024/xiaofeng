package com.xiaofeng.config.redis;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/20 21:57
 * @Description: 将spring session Redis的序列化的格式变为json
 */
@Configuration
public class RedisSerializableJSONConfig {

    //spring session redis序列化
    @Bean("springSessionDefaultRedisSerializer")
    public RedisSerializer<Object> defaultRedisSerializer(){
        return valueSerializer();
    }
    private RedisSerializer<Object> valueSerializer() {
        return new FastJsonRedisSerializer(Object.class);
    }

    //redis cache序列化
    @Bean
    public RedisTemplate<Object, Object> myRedisTemplate(
            RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

}
