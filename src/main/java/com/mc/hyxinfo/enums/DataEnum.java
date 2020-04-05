package com.mc.hyxinfo.enums;

import lombok.Getter;

/**
 * @author admin
 * 数据状态，是否被删除
 */
@Getter
public enum DataEnum {

    NORMAL(0, "存在"),
    DEl(1, "删除");
    private Integer code;
    private String msg;

    DataEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
