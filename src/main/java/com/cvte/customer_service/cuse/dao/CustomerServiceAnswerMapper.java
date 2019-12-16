package com.cvte.customer_service.cuse.dao;

import com.cvte.customer_service.cuse.entity.CustomerServiceAnswer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 知识库的持久层
 *
 * @author chenbo
 * @Date 2019/12/3 4:50 下午
 */
@Repository
public interface CustomerServiceAnswerMapper {
    /*
    插入一条记录到答案表
     */
    int insertSelective(CustomerServiceAnswer record);

    /*
    根据主键修改一条数据
     */
    int updateByPrimaryKeySelective(CustomerServiceAnswer record);

    /*
    根据问题通过全文索引来查找答案，返回推荐
     */
    List<CustomerServiceAnswer> selectRecommendByQuestion(String str);

    /*
    根据uid找到一个知识库的一条数据
     */
    CustomerServiceAnswer selectOneQuestionByUid(String questionUid);

    /*
    仅在测试是使用
     */
    List<CustomerServiceAnswer> selectAllAnswer();
}