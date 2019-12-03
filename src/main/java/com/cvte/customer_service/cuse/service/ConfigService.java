package com.cvte.customer_service.cuse.service;

import com.cvte.customer_service.cuse.entity.CustomerServiceConfig;
import org.springframework.stereotype.Service;
/**
*
*@author chenbo
*@Date 2019/12/3 4:53 下午
*/
@Service
public interface ConfigService {
    /**
     * 根据configType得到配置项
     *
     * @return
     */
    Integer getMaxLenConfig();

    /**
     * 插入一条配置记录
     * @param config
     * @return
     */
    int insertOneConfig(CustomerServiceConfig config);
}
