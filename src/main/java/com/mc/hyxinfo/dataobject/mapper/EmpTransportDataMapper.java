package com.mc.hyxinfo.dataobject.mapper;

import com.mc.hyxinfo.dataobject.EmpTransportData;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author admin
 * 利用mybaties查询需要多条件查询的内容
 */
public interface EmpTransportDataMapper {
    @Select("select * from emp_transport_data where username=#{username} and create_time >#{createTime} and create_time <=#{endTime}  and del=0 ")
    @Results({
            @Result(column = "bill_id", property = "billId"),
            @Result(column = "username", property = "username"),
            @Result(column = "signs", property = "signs"),
            @Result(column = "weights", property = "weights"),
            @Result(column = "address", property = "address"),
            @Result(column = "create_time", property = "createTime")
    })
    List<EmpTransportData> findByUsernameAndCreateTime(Map<String, Object> map);
}
