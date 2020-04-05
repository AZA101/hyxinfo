package com.mc.hyxinfo.exception;

import com.mc.hyxinfo.enums.ResultEnum;
import lombok.Getter;

@Getter
public class ReturnException extends RuntimeException {
    private Integer code;

    public ReturnException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code=resultEnum.getCode();
    }
}
