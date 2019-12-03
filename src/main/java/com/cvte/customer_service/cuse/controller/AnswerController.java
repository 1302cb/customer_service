package com.cvte.customer_service.cuse.controller;

import com.cvte.customer_service.cuse.dto.CustomerServiceAnswerDTO;
import com.cvte.customer_service.cuse.entity.CustomerServiceAnswer;
import com.cvte.customer_service.cuse.entity.CustomerServiceQuestion;
import com.cvte.customer_service.cuse.entity.ResultData;
import com.cvte.customer_service.cuse.service.AnswerService;
import com.cvte.customer_service.cuse.service.QuestionService;
import com.cvte.customer_service.cuse.utils.EntityConversionDTOUtil;
import com.cvte.customer_service.cuse.utils.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AnswerController {
    private Logger logger = LoggerFactory.getLogger(AnswerController.class);

    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuestionService questionService;

    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/answer/getRecommend")
    public ResultData getRecommend(String question, String uid) {
        //校验前端输入
        if ("".equals(question)) {
            return new ResultData("输入为空", ResultData.EMPTY);
        }
        //先将问题插入到问题表中去
        questionService.insertOneQuestion(new CustomerServiceQuestion(UUIDUtils.getUUID(), uid, question));
        //去知识库查找推荐
        List<CustomerServiceAnswer> list = answerService.getRecommendListByQuestion(question);
        List<CustomerServiceAnswerDTO> res = new ArrayList<>();
        //返回时转换为dto，直接返回pojo容易泄漏表结构
        for (CustomerServiceAnswer item : list) {
            res.add(EntityConversionDTOUtil.conversionToAnswerDTO(item));
        }
        return new ResultData(res, "推荐成功", ResultData.SUCCESS);
    }
}
