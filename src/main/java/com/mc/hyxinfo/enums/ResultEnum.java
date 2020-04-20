package com.mc.hyxinfo.enums;

import lombok.Getter;

import javax.persistence.criteria.CriteriaBuilder;

@Getter
public enum ResultEnum {
    SUCCESS(1,"成功"),
    UPDATE_FALL(2,"数据更新失败"),
    DATA_NOT_EXIST(3,"数据不存在"),
    PARAM_ERROR(4,"参数不正确"),
    PARAM_NOT_EXIST(5,"货运单号不能为空"),
    USER_NOT_EXIST(6,"用户不存在"),
    LOGOUT_SUCCESS(7,"退出成功"),
    FORM_DATA_ERROR(8,"表单数据类型不正确"),
    COOKIE_ERROR(9,"未查询到cookie值或cookie内容为空")
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
