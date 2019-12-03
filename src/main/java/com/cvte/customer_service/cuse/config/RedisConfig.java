package com.cvte.customer_service.cuse.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
/**
*redis的一个配置类，方便使用redisTemplate
*@author chenbo
*@Date 2019/12/3 4:48 下午
*/
@Configuration
@EnableCaching
public class RedisConfig{
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;


    /**
     * redis的配置类，序列化value和key
     * @param factory 连接工厂类
     * @return RedisTemplate<String,Object>
     */
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setValueSerializer(stringRedisSerializer);
        template.setHashValueSerializer(stringRedisSerializer);
//        开启Redis的事务支持
//        template.setEnableTransactionSupport(true);
        template.afterPropertiesSet();
        return template;
    }
}
