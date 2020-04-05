package com.mc.hyxinfo.enums;

import lombok.Getter;

/**
 * @author admin
 * 用户级别的枚举
 */
@Getter
public enum LevelEnum {
    EMP(0,"普通员工"),
    admin(1,"管理员");
    private Integer code;
    private String msg;

    LevelEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
