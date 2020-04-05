package com.mc.hyxinfo.form;

import lombok.Data;

/**
 * 填写的货运单数据
 * @author admin
 */
@Data
public class EmpForm {
    private String billId;
    private Double weights;
    private String address;
}
