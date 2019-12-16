package com.cvte.customer_service.cuse.service.impl;

import com.cvte.customer_service.cuse.dao.CustomerServiceQuestionMapper;
import com.cvte.customer_service.cuse.entity.CustomerServiceQuestion;
import com.cvte.customer_service.cuse.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenbo
 * @Date 2019/12/3 4:53 下午
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private CustomerServiceQuestionMapper customerServiceQuestionMapper;

    @Override
    public int insertOneQuestion(CustomerServiceQuestion question) {
        return customerServiceQuestionMapper.insertSelective(question);
    }

    @Override
    public CustomerServiceQuestion selectOneQuestionByUid(String uid) {
        return customerServiceQuestionMapper.selectByUniqueKey(uid);
    }
}
