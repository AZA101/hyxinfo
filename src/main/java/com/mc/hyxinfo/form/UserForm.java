package com.mc.hyxinfo.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author admin
 * 修改员工的信息
 */
@Data
public class UserForm {
    private Integer personId;
    @NotEmpty
    private String username;
    @NotEmpty
    private String phoneNumber;
    @NotEmpty
    private String signs;
    @NotEmpty
    private String passwords;
}
