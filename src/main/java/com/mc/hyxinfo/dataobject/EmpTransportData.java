package com.mc.hyxinfo.dataobject;

import com.mc.hyxinfo.enums.DataEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class EmpTransportData {
    /*货运单的id*/
    @Id
    private  String  billId;
    /*姓名*/
    private String username;
    /*车牌号*/
    private String signs;
    /*吨位*/
    private Double weights;
    /*运送地址*/
    private String address;
    /*创建时间*/
    private Date createTime;
    /*数据状态0为正常,1为删除*/
    private Integer del= DataEnum.NORMAL.getCode();


}
