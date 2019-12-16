package com.cvte.customer_service.cuse.dao;

import com.cvte.customer_service.cuse.entity.CustomerServiceQuestion;
import org.springframework.stereotype.Repository;

/**
 * 询问问题的持久层
 *
 * @author chenbo
 * @Date 2019/12/3 4:51 下午
 */
@Repository
public interface CustomerServiceQuestionMapper {
    /**
     * 插入一条问题
     *
     * @param record
     * @return
     */
    int insertSelective(CustomerServiceQuestion record);

    /**
     * 根据uuid来查找问题
     *
     * @param uid
     * @return
     */
    CustomerServiceQuestion selectByUniqueKey(String uid);

    /**
     * 根据uuid来跟新
     *
     * @param record
     * @return
     */
    int updateByUniqueKeySelective(CustomerServiceQuestion record);

}