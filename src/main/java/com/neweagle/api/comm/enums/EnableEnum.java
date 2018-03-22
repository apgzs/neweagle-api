package com.neweagle.api.comm.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * 启用/禁用枚举
 */
public enum EnableEnum implements IEnum {
    DISABLE(0, "禁用"),
    ENABLE(1, "启用");
    private int value;
    private String desc;

    EnableEnum(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }
    @Override
    public Serializable getValue() {
        return this.value;
    }

    @JsonValue
    public String getDesc(){
        return this.desc;
    }
}
