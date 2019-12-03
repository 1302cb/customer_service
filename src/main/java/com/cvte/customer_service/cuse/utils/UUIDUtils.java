package com.cvte.customer_service.cuse.utils;

import java.util.UUID;
/**
*
*@author chenbo
*@Date 2019/12/3 4:54 下午
*/
public class UUIDUtils {

    /**
     * 生成uuid
     * @return String
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
