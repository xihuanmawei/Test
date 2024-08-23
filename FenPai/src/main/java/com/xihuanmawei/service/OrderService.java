package com.xihuanmawei.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xihuanmawei.entity.OrderInfo;

import java.util.List;
import java.util.Map;

public interface OrderService extends IService<OrderInfo> {

    boolean saveOrder(OrderInfo order);

    boolean updateOrder(OrderInfo order);

    List<Map<String, Object>> getEveryDept(String yearMonthStr);

    List<Map<String, Object>> getEveryType(String yearMonthStr);

    boolean fenpai(long orderId, long deptId, String deptName);
}
