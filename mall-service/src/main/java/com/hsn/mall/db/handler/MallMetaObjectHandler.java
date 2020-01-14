package com.hsn.mall.db.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 自动更新插入和更新时间处理器
 * @author huisunan
 */
@Component
public class MallMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("addTime", LocalDateTime.now(),metaObject);
        setFieldValByName("updateTime", LocalDateTime.now(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateTime",LocalDateTime.now(),metaObject);
    }
}
