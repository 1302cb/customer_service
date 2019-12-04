package com.cvte.customer_service.cuse.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cvte.customer_service.cuse.dao.CustomerServiceConfigMapper;
import com.cvte.customer_service.cuse.dto.CustomerServiceAnswerDTO;
import com.cvte.customer_service.cuse.dto.CustomerServiceConfigDTO;
import com.cvte.customer_service.cuse.entity.CustomerServiceAnswer;
import com.cvte.customer_service.cuse.entity.CustomerServiceConfig;
import com.cvte.customer_service.cuse.service.ConfigService;
import com.cvte.customer_service.cuse.utils.EntityConversionDTOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenbo
 * @Date 2019/12/3 4:53 下午
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private CustomerServiceConfigMapper config;

    private Logger logger = LoggerFactory.getLogger(ConfigServiceImpl.class);

    @Override
    public Integer getMaxLenConfig() {
        String content = config.selectContentByConfigType(1);
        CustomerServiceConfigDTO dto = JSONObject.parseObject(content, CustomerServiceConfigDTO.class);
        String res = dto.getConfigValue().get(0);
        logger.info("configLen:" + res);
        return res == null ? 4 : Integer.parseInt(res);
    }

    @Override
    public CustomerServiceAnswerDTO getDefaultFaultToleranceOption() {
        String content = config.selectContentByConfigType(2);
        CustomerServiceConfigDTO contentDTO = JSONObject.parseObject(content, CustomerServiceConfigDTO.class);
        CustomerServiceAnswer answer = JSONObject.parseObject(contentDTO.getConfigValue().get(0), CustomerServiceAnswer.class);
        return EntityConversionDTOUtil.conversionToAnswerDTO(answer);
    }

    @Override
    public int insertOneConfig(CustomerServiceConfig record) {
        return config.insertSelective(record);
    }

}
