package com.xihuanmawei.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xihuanmawei.entity.OrderInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderMapper extends BaseMapper<OrderInfo> {
    List<Map<String, Object>> getEveryDept(String yearMonthStr);

    List<Map<String, Object>> getEveryType(String yearMonthStr);
}
