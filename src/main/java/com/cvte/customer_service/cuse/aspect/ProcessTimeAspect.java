package com.cvte.customer_service.cuse.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
/**
*计算controller接口执行时间的一个切面
*@author chenbo
*@Date 2019/12/3 4:48 下午
*/
@Aspect
@Component
public class ProcessTimeAspect {
    private static Logger logger = LoggerFactory.getLogger(ProcessTimeAspect.class);

    @Pointcut("execution(* com.cvte.customer_service.cuse.controller.*.*(..))")
    public void logPointcut(){}

    /**
     * 环绕通知计算controller层的接口运行的时间
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @org.aspectj.lang.annotation.Around("logPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{

        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long end = System.currentTimeMillis();
            logger.info("" + joinPoint + "\t消耗 : " + (end - start) + " ms!");
            return result;

        } catch (Throwable e) {
            long end = System.currentTimeMillis();
            logger.error("" + joinPoint + "\t消耗: " + (end - start) + " 报错为: " + e.getMessage());
            throw e;
        }

    }

}
