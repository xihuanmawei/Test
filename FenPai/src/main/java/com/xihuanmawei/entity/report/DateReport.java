package com.xihuanmawei.entity.report;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DateReport {
    private Data Date;
    private Integer total;
    private Integer overCount;
    private BigDecimal overRate;
}
