package com.cvte.customer_service.cuse.service;

import com.cvte.customer_service.cuse.dto.CustomerServiceAnswerDTO;
import com.cvte.customer_service.cuse.entity.CustomerServiceAnswer;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenbo
 * @Date 2019/12/3 4:53 下午
 */
@Service
public interface AnswerService {
    /**
     * 通过问题查找推荐列表
     *
     * @param str
     * @return
     */
    List<CustomerServiceAnswerDTO> getRecommendListByQuestion(String str);


    /**
     * 向知识库中插入一条问题
     *
     * @param answer
     * @return
     */
    int insertOneAnswer(CustomerServiceAnswer answer);


    CustomerServiceAnswerDTO selectOneQuestionByUid(String questionUid);


    /**
     * 全文索引查找匹配项
     *
     * @param msg
     * @return
     */
    public List<String> getCommendFromDatabase(String msg);
}
