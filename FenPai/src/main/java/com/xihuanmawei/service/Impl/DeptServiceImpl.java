package com.xihuanmawei.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xihuanmawei.common.aop.checkOrderNo.CheckOrderNo;
import com.xihuanmawei.entity.DeptInfo;
import com.xihuanmawei.entity.OrderInfo;
import com.xihuanmawei.mapper.DeptMapper;
import com.xihuanmawei.service.DeptService;
import com.xihuanmawei.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, DeptInfo> implements DeptService {



}
