package com.cvte.customer_service.cuse;

import com.cvte.customer_service.cuse.dao.CustomerServiceAnswerMapper;
import com.cvte.customer_service.cuse.entity.CustomerServiceAnswer;
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
public class AnswerTest {
    @Autowired
    private CustomerServiceAnswerMapper customerServiceAnswerMapper;

    private static Logger logger = LoggerFactory.getLogger(AnswerTest.class);

    @Test
    public void testInsertIntoAnswer() {
        CustomerServiceAnswer answer = new CustomerServiceAnswer();
        answer.setQuestion("以上都不是");
        answer.setAnswer("以上都不是");
        String uuid = UUIDUtils.getUUID();
        logger.info("uuid->" + uuid);
        answer.setUid(uuid);
        customerServiceAnswerMapper.insertSelective(answer);
    }
}
