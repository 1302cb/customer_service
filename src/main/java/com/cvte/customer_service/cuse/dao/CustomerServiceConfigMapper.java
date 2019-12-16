package com.cvte.customer_service.cuse.dao;

import com.cvte.customer_service.cuse.entity.CustomerServiceConfig;
import org.springframework.stereotype.Repository;

/**
 * 配置类的持久层
 *
 * @author chenbo
 * @Date 2019/12/3 4:50 下午
 */
@Repository
public interface CustomerServiceConfigMapper {
    /*
    插入一条配置记录
     */
    int insertSelective(CustomerServiceConfig record);

    /*
    根据配置类型修改一条记录
     */
    int updateByUniqueKeySelective(CustomerServiceConfig record);

    /*
    根据configType查找对应的配置项的内容
     */
    String selectContentByConfigType(Integer configType);
}