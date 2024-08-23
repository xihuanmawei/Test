package com.xihuanmawei.common.aop.checkIsNull;

import com.alibaba.druid.util.StringUtils;
import com.xihuanmawei.entity.OrderInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class IsNullAop {
    @Pointcut("@annotation(com.xihuanmawei.common.aop.checkIsNull.CheckIsNull)")
    public void checkIsNullPointcut(){}

    @Around("checkIsNullPointcut()")
    public Object checkIsNull(ProceedingJoinPoint joinPoint) throws Throwable{
        Object[] args = joinPoint.getArgs();
        OrderInfo orderInfo = null;
        for (Object arg : args) {
            if(arg instanceof OrderInfo){
                orderInfo = (OrderInfo) arg;
                break;
            }
        }
        if(orderInfo!=null){
            if(StringUtils.isEmpty(orderInfo.getOrderNo()) ||
               orderInfo.getOrderType() == null ||
               StringUtils.isEmpty(orderInfo.getTitle()) ||
               StringUtils.isEmpty(orderInfo.getContent()) ||
               orderInfo.getIsOverdue() == null){
                throw new IllegalArgumentException("必填项为空");
            }
        }
        return joinPoint.proceed();
    }
}
