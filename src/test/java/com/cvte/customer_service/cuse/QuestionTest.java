package com.cvte.customer_service.cuse;

import com.cvte.customer_service.cuse.dao.CustomerServiceQuestionMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class QuestionTest {

    @Autowired
    private CustomerServiceQuestionMapper customerServiceQuestionMapper;

    @Test
    public void test(){

    }
}
