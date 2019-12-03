package com.cvte.customer_service.cuse.service;

import com.cvte.customer_service.cuse.entity.CustomerServiceLog;
import org.springframework.stereotype.Service;
/**
*
*@author chenbo
*@Date 2019/12/3 4:52 下午
*/
@Service
public interface LogService {
    /**
     * 插入一条流水日志
     * @param log
     * @return
     */
    int insertOneLog(CustomerServiceLog log);
}
