package com.cvte.customer_service.cuse;

import com.alibaba.fastjson.JSONObject;
import com.cvte.customer_service.cuse.dao.CustomerServiceAnswerMapper;
import com.cvte.customer_service.cuse.entity.CustomerServiceAnswer;
import com.cvte.customer_service.cuse.utils.MybatisReverseProjectUtil;
import org.junit.jupiter.api.Test;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
public class CommentTest {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private CustomerServiceAnswerMapper customerServiceAnswerMapper;

    private static Logger logger = LoggerFactory.getLogger(CommentTest.class);
    private static String rank = "questionRank";
    @Test
    public void testGenerateTemplateCode(){

        try {
            MybatisReverseProjectUtil.generateTemplateCode();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLParserException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testpipeLined(){
        List<CustomerServiceAnswer> list = customerServiceAnswerMapper.selectAllAnswer();
        int len = list.size();
        for(CustomerServiceAnswer answer:list){
            logger.info("answer->"+answer);
        }
        redisTemplate.executePipelined((RedisCallback<Object>) redisConnection -> {
            for(int i=0;i<len;i++){
                redisTemplate.opsForZSet().add(rank, JSONObject.toJSONString(list.get(i)),(double)1);
            }
            return null;
        });
    }
}
