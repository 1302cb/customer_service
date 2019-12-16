package com.cvte.customer_service.cuse.controller;

import com.alibaba.fastjson.JSONObject;
import com.cvte.customer_service.cuse.dto.CustomerServiceAnswerDTO;
import com.cvte.customer_service.cuse.entity.CustomerServiceLog;
import com.cvte.customer_service.cuse.entity.ResultData;
import com.cvte.customer_service.cuse.service.AnswerService;
import com.cvte.customer_service.cuse.service.LogService;
import com.cvte.customer_service.cuse.utils.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 流水的控制器类
 *
 * @author chenbo
 * @Date 2019/12/4 2:15 下午
 */
@RestController
public class LogController {

    private static Logger logger = LoggerFactory.getLogger(LogController.class);
    private static String rank = "questionRank";
    @Autowired
    private LogService logService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/log/insertOne")
    public ResultData insertOneLog(String uid, String questionUid) {
        // TODO: 2019/12/3 后期加入一个以上都不是的选项
        logService.insertOneLog(new CustomerServiceLog(UUIDUtils.getUUID(), questionUid, uid));
        //找出用户选择的问题
        CustomerServiceAnswerDTO answer = answerService.selectOneQuestionByUid(questionUid);
        //增加zset中的score
        redisTemplate.opsForZSet().incrementScore(rank, JSONObject.toJSONString(answer), 0.1);

        return new ResultData(answer, "查询成功", ResultData.SUCCESS);
    }
}
