package com.xihuanmawei.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class DeptInfo {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String deptName;
}
