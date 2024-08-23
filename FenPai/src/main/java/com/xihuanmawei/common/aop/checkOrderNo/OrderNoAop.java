package com.xihuanmawei.common.aop.checkOrderNo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xihuanmawei.entity.OrderInfo;
import com.xihuanmawei.service.OrderService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OrderNoAop {
    @Autowired
    private OrderService orderService;

    @Pointcut("@annotation(com.xihuanmawei.common.aop.checkOrderNo.CheckOrderNo)")
    public void checkOrderNoPointcut(){}

    @Around("checkOrderNoPointcut()")
    public Object checkOrderNo(ProceedingJoinPoint joinPoint) throws Throwable{
        Object[] args = joinPoint.getArgs();
        OrderInfo orderInfo = null;
        for (Object arg : args) {
            if(arg instanceof OrderInfo){
                orderInfo = (OrderInfo) arg;
                break;
            }
        }
        if (orderInfo!=null){
            QueryWrapper<OrderInfo> wrapper = new QueryWrapper<>();
            wrapper.eq("order_no", orderInfo.getOrderNo());
            int count = orderService.count(wrapper);
            if (count > 0) {
                throw new IllegalArgumentException("工单编号已经存在");
            }
        }
        return joinPoint.proceed();
    }
}
