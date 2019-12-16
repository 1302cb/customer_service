package com.cvte.customer_service.cuse.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cvte.customer_service.cuse.dao.CustomerServiceAnswerMapper;
import com.cvte.customer_service.cuse.dto.CustomerServiceAnswerDTO;
import com.cvte.customer_service.cuse.entity.CustomerServiceAnswer;
import com.cvte.customer_service.cuse.service.AnswerService;
import com.cvte.customer_service.cuse.service.ConfigService;
import com.cvte.customer_service.cuse.utils.EntityConversionDTOUtil;
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
 * @author chenbo
 * @Date 2019/12/3 4:53 下午
 */
@Service
public class AnswerServiceImpl implements AnswerService {

    private static Logger logger = LoggerFactory.getLogger(AnswerServiceImpl.class);

    //存储到redis的zset名称
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
    public List<CustomerServiceAnswerDTO> getRecommendListByQuestion(String str) {
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
        List<CustomerServiceAnswer> answers = customerServiceAnswerMapper.selectRecommendByQuestion(param);
        //转化成dto
        List<CustomerServiceAnswerDTO> res = EntityConversionDTOUtil.conversionToAnswerDTOList(answers);
        return processRecommendList(res, maxLen);
    }

    /**
     * 全文索引查找匹配项
     *
     * @param msg
     * @return
     */
    public List<String> getCommendFromDatabase(String msg) {
        List<String> values = new ArrayList<>();
        List<String> resultCommends = new ArrayList<>();

        //对输入提取关键词
        try {
            values = JcsegUtil.segment(msg);
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
            return null;
        }
        //全文索引查找匹配项
        List<CustomerServiceAnswer> answers = customerServiceAnswerMapper.selectRecommendByQuestion(param);
        //转化成dto
        List<CustomerServiceAnswerDTO> res = EntityConversionDTOUtil.conversionToAnswerDTOList(answers);
        for (CustomerServiceAnswerDTO dto : res) {
            resultCommends.add(dto.getQuestion());
        }
        return resultCommends;
    }

    /*
     增减从数据库中查询得到的数据项
     */
    private List<CustomerServiceAnswerDTO> processRecommendList(List<CustomerServiceAnswerDTO> list, int maxLen) {
        CustomerServiceAnswerDTO defaultLine = configService.getDefaultFaultToleranceOption();
        int len = list.size();
        logger.info("recommend len from mysql:" + len);
        if (len == maxLen) {
            logger.info("长度刚好");
        } else if (len < maxLen) {
            //小于则用热门问题补上
            list.addAll(getHotRecommend(maxLen - len));
            list.remove(list.size() - 1);
        } else {
            //大于则截取一部分
            list = list.subList(0, maxLen);
        }
        //添加默认选项
        list.add(defaultLine);
        return list;
    }

    /*
     从redis中得到最热门到几个问题,问题到长度通过配置表得到，如果配置表没有配置，默认是4个
     */
    private List<CustomerServiceAnswerDTO> getHotRecommend(int maxLen) {
        CustomerServiceAnswerDTO defaultLine = configService.getDefaultFaultToleranceOption();
        Set<Object> set = redisTemplate.opsForZSet().reverseRange(QUESTION_RANK, 0L, maxLen - 1);
        List<CustomerServiceAnswerDTO> res = new ArrayList<>();
        assert set != null;
        for (Object object : set) {
            String str = (String) object;
            logger.info("answer from redis object:" + str);
            CustomerServiceAnswer answer = JSONObject.parseObject(str, CustomerServiceAnswer.class);
            logger.info("answer from redis object:" + answer);
            res.add(EntityConversionDTOUtil.conversionToAnswerDTO(answer));
        }
        //默认行加进去
        res.add(defaultLine);
        return res;
    }

    /*
    插入一条数据
     */
    @Override
    public int insertOneAnswer(CustomerServiceAnswer answer) {
        return customerServiceAnswerMapper.insertSelective(answer);
    }

    /*
    根据uid来进行查找一条数据
     */
    @Override
    public CustomerServiceAnswerDTO selectOneQuestionByUid(String questionUid) {
        CustomerServiceAnswer answer = customerServiceAnswerMapper.selectOneQuestionByUid(questionUid);
        return EntityConversionDTOUtil.conversionToAnswerDTO(answer);
    }

}
