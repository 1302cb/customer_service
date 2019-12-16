package com.cvte.customer_service.cuse.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取远端请求的ip地址
 *
 * @author chenbo
 * @Date 2019/12/3 4:53 下午
 */
public class RemoteUrlUtil {

    /**
     * 获取真实地址的ip的函数
     *
     * @param request：请求实体
     * @return String
     */
    public static String getRealRemoteUrl(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");
        final String nuknown = "unknown";
        int flag = 1;
        switch (flag) {
            case 1:
                ip = request.getHeader("Proxy-Client-IP");
                if (ip != null) {
                    return ip;
                }
            case 2:
                ip = request.getHeader("WL-Proxy-Client-IP");
                if (ip != null) {
                    return ip;
                }
            case 3:
                ip = request.getHeader("HTTP_CLIENT_IP");
                if (ip != null) {
                    return ip;
                }
            case 4:
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                if (ip != null) {
                    return ip;
                }
            case 5:
                ip = request.getRemoteAddr();
                if (ip != null) {
                    return ip;
                }
            default:
                break;
        }
        return null;
    }
}
