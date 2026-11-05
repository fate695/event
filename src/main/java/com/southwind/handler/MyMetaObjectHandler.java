package com.southwind.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.southwind.utils.ThreadLocalUtil;
import com.southwind.utils.TokenUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * 公共字段自动填充：createTime / updateTime / createUser
 * 注意：字段名必须使用实体属性名（驼峰），不能用数据库列名（下划线）
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Date now = new Date();
        this.strictInsertFill(metaObject, "createTime", Date.class, now);
        this.strictInsertFill(metaObject, "updateTime", Date.class, now);
        this.strictInsertFill(metaObject, "createUser", Integer.class, TokenUtils.getUserId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
    }


}

