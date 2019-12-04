package com.cvte.customer_service.cuse;

import com.alibaba.fastjson.JSONObject;
import com.cvte.customer_service.cuse.dao.CustomerServiceAnswerMapper;
import com.cvte.customer_service.cuse.dao.CustomerServiceConfigMapper;
import com.cvte.customer_service.cuse.dto.CustomerServiceConfigDTO;
import com.cvte.customer_service.cuse.entity.CustomerServiceAnswer;
import com.cvte.customer_service.cuse.entity.CustomerServiceConfig;
import com.cvte.customer_service.cuse.service.AnswerService;
import com.cvte.customer_service.cuse.service.ConfigService;
import com.cvte.customer_service.cuse.utils.UUIDUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ConfigTest {
    @Autowired
    private CustomerServiceConfigMapper config;

    @Autowired
    private ConfigService configService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private CustomerServiceAnswerMapper customerServiceAnswerMapper;

    private static Logger logger = LoggerFactory.getLogger(ConfigTest.class);

    @Test
    public void testInsertIntoConfig() {
        CustomerServiceAnswer answer = customerServiceAnswerMapper.selectOneQuestionByUid("2c444f7367d64ba4b3541eb02b4b57b3");
        String configValue = JSONObject.toJSONString(answer);
        logger.info("configValue:" + configValue);
        CustomerServiceConfig vo = new CustomerServiceConfig();
        vo.setContent("{\"config_value\":" + configValue + "}");
        vo.setUid(UUIDUtils.getUUID());
        vo.setDescription("提供用户容错并重新选择的选项");
        vo.setConfigType(2);
        logger.info("insert-->" + config.insertSelective(vo));
    }

    @Test
    public void testSelectContentByContentType() {
        String content = config.selectContentByConfigType(2);
        CustomerServiceConfigDTO contentDTO = JSONObject.parseObject(content,CustomerServiceConfigDTO.class);
        logger.info("type-->" + config.selectContentByConfigType(2));
        logger.info("contentDTO->"+contentDTO);
        CustomerServiceAnswer answer = JSONObject.parseObject(contentDTO.getConfigValue().get(0),CustomerServiceAnswer.class);
        logger.info("answer->"+answer);
    }

    @Test
    public void testConfigJsonToObject() {

    }
}
