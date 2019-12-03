package com.cvte.customer_service.cuse.dao;

import com.cvte.customer_service.cuse.entity.CustomerServiceLog;
import org.springframework.stereotype.Repository;
/**
*流水的持久层
*@author chenbo
*@Date 2019/12/3 4:50 下午
*/
@Repository
public interface CustomerServiceLogMapper {
    /**
     * 插入一条流水记录
     * @param record
     * @return
     */
    int insertSelective(CustomerServiceLog record);

}