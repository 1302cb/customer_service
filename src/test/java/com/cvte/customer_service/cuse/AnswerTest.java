package com.cvte.customer_service.cuse;

import com.cvte.customer_service.cuse.dao.CustomerServiceAnswerMapper;
import com.cvte.customer_service.cuse.entity.CustomerServiceAnswer;
import com.cvte.customer_service.cuse.utils.UUIDUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AnswerTest {
    @Autowired
    private CustomerServiceAnswerMapper customerServiceAnswerMapper;

    @Test
    public void testInsertIntoAnswer(){
        CustomerServiceAnswer answer = new CustomerServiceAnswer();
        answer.setQuestion("如何创建多种分组方案？");
        answer.setAnswer("请在班级成员列表页面，点击小组标签进入小组列表页面\n" +
                "点击分组下拉列表，选择添加分组方案\n" +
                "输入分组方案名称，对学生进行新的分组");
        answer.setUid(UUIDUtils.getUUID());
        customerServiceAnswerMapper.insertSelective(answer);
    }
}
