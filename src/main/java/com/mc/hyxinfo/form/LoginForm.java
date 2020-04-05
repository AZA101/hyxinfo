package com.mc.hyxinfo.form;

import lombok.Data;

/**
 * 接收登陆信息
 */
@Data
public class LoginForm {
    private String phoneNumber;
    private String passwords;
}
