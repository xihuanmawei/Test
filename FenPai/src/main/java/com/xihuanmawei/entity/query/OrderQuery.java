package com.xihuanmawei.entity.query;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class OrderQuery {
    private String orderNo;
    private Integer orderType;
    private String title;
    private Long handleDeptId;
    private Integer isOverdue;


    @TableField(fill = FieldFill.INSERT)
    private String createTime;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String fenpaiTime;

}
