package com.cvte.customer_service.cuse.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cvte.customer_service.cuse.dao.CustomerServiceAnswerMapper;
import com.cvte.customer_service.cuse.entity.CustomerServiceAnswer;
import com.cvte.customer_service.cuse.service.AnswerService;
import com.cvte.customer_service.cuse.service.ConfigService;
import com.cvte.customer_service.cuse.utils.JcsegUtil;
import org.lionsoul.jcseg.tokenizer.core.JcsegException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/**
*
*@author chenbo
*@Date 2019/12/3 4:53 下午
*/
@Service
public class AnswerServiceImpl implements AnswerService {

    private static Logger logger = LoggerFactory.getLogger(AnswerServiceImpl.class);
    public final static String QUESTION_RANK = "questionRank";

    @Autowired
    private CustomerServiceAnswerMapper customerServiceAnswerMapper;

    @Autowired
    private ConfigService configService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 根据输入返回推荐列表
     *
     * @param str
     * @return
     */
    @Override
    public List<CustomerServiceAnswer> getRecommendListByQuestion(String str) {
        logger.info("question-->" + str);
        List<String> values = new ArrayList<>();
        int maxLen = configService.getMaxLenConfig();
        //对输入提取关键词
        try {
            values = JcsegUtil.segment(str);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JcsegException e) {
            e.printStackTrace();
        }
        //对提取对关键词进行拼接
        String param = JcsegUtil.spliceJcsegArray(values);
        logger.info("param-->" + param);
        //关键词并没有命中，就返回热门推荐
        if (param == null) {
            return getHotRecommend(maxLen);
        }
        //全文索引查找匹配项
        List<CustomerServiceAnswer> res = customerServiceAnswerMapper.selectRecommendByQuestion(param);
        return processRecommendList(res);
    }


    /*
     增减从数据库中查询得到的数据项
     */
    private List<CustomerServiceAnswer> processRecommendList(List<CustomerServiceAnswer> list) {
        int maxLen = configService.getMaxLenConfig();
        int len = list.size();
        logger.info("recommend len from mysql:" + len);
        if (len == maxLen) {
            return list;
        } else if (len < maxLen) {
            //小于则用热门问题补上
            list.addAll(getHotRecommend(maxLen - len));
            return list;
        } else {
            //大于则截取一部分
            list = list.subList(0, maxLen);
            return list;
        }
    }

    /*
     从redis中得到最热门到几个问题,问题到长度通过配置表得到，如果配置表没有配置，默认是4个
     */
    private List<CustomerServiceAnswer> getHotRecommend(int len) {
        long maxLen = configService.getMaxLenConfig() - len;
        Set<Object> set = redisTemplate.opsForZSet().reverseRange(QUESTION_RANK, 0L, maxLen);
        List<CustomerServiceAnswer> res = new ArrayList<>();
        assert set != null;
        for (Object object : set) {
            String str = (String) object;
            logger.info("answer from redis object:"+str);
            CustomerServiceAnswer answer = JSONObject.parseObject(str, CustomerServiceAnswer.class);
            logger.info("answer from redis object:" + answer);
            res.add(answer);
        }
        return res;
    }

    /*
    插入一条数据
     */
    @Override
    public int insertOneAnswer(CustomerServiceAnswer answer) {
        return customerServiceAnswerMapper.insertSelective(answer);
    }

    @Override
    public CustomerServiceAnswer selectOneQuestionByUid(String questionUid) {
        return customerServiceAnswerMapper.selectOneQuestionByUid(questionUid);
    }

}
