package com.xihuanmawei.controller;

import com.xihuanmawei.common.Result.R;
import com.xihuanmawei.entity.OrderInfo;
import com.xihuanmawei.entity.query.DateQuery;
import com.xihuanmawei.service.DeptService;
import com.xihuanmawei.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
@RequestMapping("post/dept")
public class DeptController {
    @Autowired
    private OrderService orderService;
    //后台自行选择分配时间
    @PostMapping("fenpaiTime/{orderId}")
    public R fenpaiTime(@PathVariable long orderId, @RequestBody DateQuery date){
        String dateString = date.getFenpaiTime();
        if (dateString == null || dateString.isEmpty()) {
            return R.error().message("Date parameter is missing or invalid.");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);
        Date fenpaiTime = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        OrderInfo order = orderService.getById(orderId);
        order.setFenpaiTime(fenpaiTime);
        if(orderService.updateById(order)){
            return R.ok();
        }else {
            return R.error();
        }

    }
}
