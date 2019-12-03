package com.cvte.customer_service.cuse.service;

import com.cvte.customer_service.cuse.entity.CustomerServiceQuestion;
import org.springframework.stereotype.Service;
/**
*
*@author chenbo
*@Date 2019/12/3 4:52 下午
*/
@Service
public interface QuestionService {
    /**
     * 插入一条问题
     * @param question
     * @return
     */
    int insertOneQuestion(CustomerServiceQuestion question);


    /**
     * 根据uuid查询一条问题记录
     * @param uid
     * @return
     */
    CustomerServiceQuestion selectOneQuestionByUid(String uid);
}
