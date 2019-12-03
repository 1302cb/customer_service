package com.cvte.customer_service.cuse;

import com.cvte.customer_service.cuse.dao.CustomerServiceConfigMapper;
import com.cvte.customer_service.cuse.entity.CustomerServiceConfig;
import com.cvte.customer_service.cuse.service.ConfigService;
import com.cvte.customer_service.cuse.utils.UUIDUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ConfigTest {
    @Autowired
    private CustomerServiceConfigMapper config;

    @Autowired
    private ConfigService configService;

    private static Logger logger = LoggerFactory.getLogger(ConfigTest.class);

    @Test
    public void testInsertIntoConfig() {
        CustomerServiceConfig vo = new CustomerServiceConfig();
        vo.setContent("{\"config_value\":\"4\"}");
        vo.setUid(UUIDUtils.getUUID());
        vo.setDescription("每次返回给用户的列表长度");
        vo.setConfigType(1);
        logger.info("insert-->" + config.insertSelective(vo));
    }

    @Test
    public void testSelectContentByContentType() {
        logger.info("type-->" + config.selectContentByConfigType(1));
    }

    @Test
    public void testConfigJsonToObject() {

    }
}
