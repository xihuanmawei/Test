package com.xihuanmawei.controller;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xihuanmawei.common.Result.R;
import com.xihuanmawei.entity.OrderInfo;
import com.xihuanmawei.entity.query.DateQuery;
import com.xihuanmawei.entity.query.OrderQuery;
import com.xihuanmawei.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/post/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    //增
    @PostMapping("save")
    public R addOrder(@RequestBody OrderInfo order){
        System.out.println("-------------"+order);
        boolean result = orderService.saveOrder(order);
        if(result){
            return R.ok();
        }else {
            return R.error();
        }
    }
    //删
    @DeleteMapping("delete/{orderId}")
    public R deleteOrder(@PathVariable long orderId){
       boolean result =  orderService.removeById(orderId);
       if(result){
            return R.ok();
       }else {
           return R.error();
       }
    }
    //改
    @PostMapping("update")
    public R updateOrder(@RequestBody OrderInfo order){
       boolean result =  orderService.updateOrder(order);
        if(result){
            return R.ok();
        }else {
            return R.error();
        }
    }
    //条件分页查询
    @GetMapping("search/{current}/{limit}")
    public R search(@PathVariable long current,@PathVariable long limit,
                    @RequestBody(required = false) OrderQuery orderQuery){
        //创建page对象
        Page<OrderInfo> pageOrder = new Page<>(current,limit);
        QueryWrapper<OrderInfo> wrapper = new QueryWrapper<>();
        String orderNo = orderQuery.getOrderNo();
        Integer orderType = orderQuery.getOrderType();
        String title = orderQuery.getTitle();
        String createTime = orderQuery.getCreateTime();
        String fenpaiTime = orderQuery.getFenpaiTime();
        if(!StringUtils.isEmpty(orderNo)){
            wrapper.eq("order_no",orderNo);
        }
        if(orderType!=null){
            wrapper.eq("order_type",orderType);
        }
        if(!StringUtils.isEmpty(title)){
            wrapper.eq("title",title);
        }
        if(!StringUtils.isEmpty(createTime)){
            wrapper.eq("create_time",createTime);
        }
        if(!StringUtils.isEmpty(fenpaiTime)){
            wrapper.eq("fenpai_time",fenpaiTime);
        }
        wrapper.orderByDesc("create_time");
        orderService.page(pageOrder,wrapper);
        long total = pageOrder.getTotal();
        List<OrderInfo> records = pageOrder.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }
    //查询7月每个部门的工单总量、超期率
    @GetMapping("everydept")
    public R EveryDept(@RequestBody DateQuery date){
        String dateString = date.getCreateTime();

        if (dateString == null || dateString.isEmpty()) {
            return R.error().message("Date parameter is missing or invalid.");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);
        DateTimeFormatter yearMonthFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String yearMonthStr = localDateTime.format(yearMonthFormatter);
        System.out.println(yearMonthStr+"------------------");
        List<Map<String, Object>> res = orderService.getEveryDept(yearMonthStr);
        return R.ok().data("rows", res);
    }
    //查询7月每个工单类型的工单总量、超期率
    @GetMapping("everyType")
    public R EveryType(@RequestBody DateQuery date){
        String dateString = date.getCreateTime();

        if (dateString == null || dateString.isEmpty()) {
            return R.error().message("Date parameter is missing or invalid.");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);
        DateTimeFormatter yearMonthFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String yearMonthStr = localDateTime.format(yearMonthFormatter);
        System.out.println(yearMonthStr+"------------------");
        List<Map<String, Object>> res = orderService.getEveryType(yearMonthStr);
        return R.ok().data("rows",res);
    }
    //分派 这里理论上是不需要部门名称的，传进来也不知道有什么用，如果这里对部门名称有需求的话，通过部门id去查询会好一点？
    @PostMapping("/fenpai/{orderId}/{deptId}")
    public R fenpai(@PathVariable long orderId, @PathVariable long deptId,
                    @RequestBody String deptName){
        boolean result = orderService.fenpai(orderId,deptId,deptName);
        if(result){
            return R.ok();
        }
        else {
            return R.error();
        }
    }

}
