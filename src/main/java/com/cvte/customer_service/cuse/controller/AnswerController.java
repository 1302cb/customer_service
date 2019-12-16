package com.cvte.customer_service.cuse.controller;

import com.cvte.customer_service.cuse.dto.CustomerServiceAnswerDTO;
import com.cvte.customer_service.cuse.entity.CustomerServiceQuestion;
import com.cvte.customer_service.cuse.entity.ResultData;
import com.cvte.customer_service.cuse.service.AnswerService;
import com.cvte.customer_service.cuse.service.QuestionService;
import com.cvte.customer_service.cuse.utils.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 答案的控制器类
 *
 * @author chenbo
 * @Date 2019/12/4 2:16 下午
 */
@RestController
public class AnswerController {
    private Logger logger = LoggerFactory.getLogger(AnswerController.class);

    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuestionService questionService;

    @PostMapping("/answer/getRecommend")
    public ResultData getRecommend(String question, String uid) {
        //校验前端输入
        if ("".equals(question)) {
            return new ResultData("输入为空", ResultData.EMPTY);
        }
        //先将问题插入到问题表中去
        questionService.insertOneQuestion(new CustomerServiceQuestion(UUIDUtils.getUUID(), uid, question));
        //去知识库查找推荐
        List<CustomerServiceAnswerDTO> list = answerService.getRecommendListByQuestion(question);

        return new ResultData(list, "推荐成功", ResultData.SUCCESS);
    }
}
