package com.neweagle.api.comm.plugin.mybatisplus;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.neweagle.api.comm.enums.EnableEnum;
import com.neweagle.api.comm.utils.DateHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *  注入公共字段自动填充,任选注入方式即可
 */
@Component
@Slf4j
public class MyMetaObjectHandler extends MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        //新增的时候插入创建时间
        Object createTime = getFieldValByName("createTime", metaObject);
        if (createTime == null) {
            setFieldValByName("createTime", DateHelper.getCurrentTimeMillis(), metaObject);
        }
        //新增的时候插入启用禁用状态
        Object enabled = getFieldValByName("enabled", metaObject);
        if (enabled == null) {
            setFieldValByName("enabled", EnableEnum.ENABLE, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //更新的时候修改更新时间
        setFieldValByName("updateTime", DateHelper.getCurrentTimeMillis(), metaObject);
    }
}
