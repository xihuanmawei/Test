package com.xihuanmawei.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class OrderInfo {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String orderNo;
    private Integer orderType;
    private String title;
    private String content;
    private Long handleDeptId;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date fenpaiTime;
    private Integer isOverdue;
}
