package com.xihuanmawei.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
