package com.mc.hyxinfo.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * 填写的货运单数据
 * @author admin
 */
@Data
public class EmpForm {
    private String billId;
    private Double weights;
    @NotEmpty
    private String address;
    @DateTimeFormat (pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
