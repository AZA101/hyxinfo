package com.mc.hyxinfo.dataobject;

import com.mc.hyxinfo.enums.DataEnum;
import com.mc.hyxinfo.enums.LevelEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author admin
 * 人员表
 */
@Entity
@Data
@DynamicUpdate
public class PeopleInfo {
    /*人员id  主键设置由数据库自增*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer personId;
    /*姓名*/
    private String username;
    /*密码*/
    private String passwords;
    /*用户级别*/
    private Integer levels= LevelEnum.EMP.getCode();
    /*创建时间*/
    private Date createTime;
    /*更改时间*/
   // private Date updateTime;
    /*删除标识*/
    private Integer del= DataEnum.NORMAL.getCode();
    /*车牌号*/
    private String signs;
    /*电话号码*/
    private String phoneNumber;
    //构建无参构造方法
    public PeopleInfo() {
    }

}
