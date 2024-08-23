package com.xihuanmawei.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xihuanmawei.common.aop.checkIsNull.CheckIsNull;
import com.xihuanmawei.common.aop.checkOrderNo.CheckOrderNo;
import com.xihuanmawei.entity.OrderInfo;
import com.xihuanmawei.mapper.OrderMapper;
import com.xihuanmawei.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderInfo> implements OrderService {
    @Autowired
    private OrderService orderService;
    @Override
    @CheckOrderNo
    @CheckIsNull
    public boolean saveOrder(OrderInfo order) {
       return orderService.save(order);
    }

    @Override
    public boolean updateOrder(OrderInfo order) {
        return orderService.updateById(order);
    }

    @Override
    public List<Map<String, Object>> getEveryDept(String yearMonthStr) {
        return baseMapper.getEveryDept(yearMonthStr);
    }

    @Override
    public List<Map<String, Object>> getEveryType(String yearMonthStr) {
        return baseMapper.getEveryType(yearMonthStr);
    }

    @Override
    @CheckOrderNo
    public boolean fenpai(long orderId, long deptId, String deptName) {
        OrderInfo order = orderService.getById(orderId);
        order.setHandleDeptId(deptId);
        return orderService.updateById(order);
    }
}
