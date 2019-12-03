package com.cvte.customer_service.cuse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@MapperScan(basePackages = "com.cvte.customer_service.cuse.dao")
@SpringBootApplication
public class CuseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CuseApplication.class, args);
    }

}
