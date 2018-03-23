package com.neweagle.api.comm.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * 删除标记枚举
 */
public enum DeleteEnum implements IEnum {
    NORMAL(0, "正常"),
    DELETE(1, "删除");
    private int value;
    private String desc;

    DeleteEnum(final int value, final String desc) {
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
