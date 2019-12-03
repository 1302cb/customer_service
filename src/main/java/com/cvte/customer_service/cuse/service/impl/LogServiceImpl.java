package com.cvte.customer_service.cuse.service.impl;

import com.cvte.customer_service.cuse.dao.CustomerServiceLogMapper;
import com.cvte.customer_service.cuse.entity.CustomerServiceLog;
import com.cvte.customer_service.cuse.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
*
*@author chenbo
*@Date 2019/12/3 4:53 下午
*/
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private CustomerServiceLogMapper customerServiceLogMapper;

    @Override
    public int insertOneLog(CustomerServiceLog log) {
        return customerServiceLogMapper.insertSelective(log);
    }
}
