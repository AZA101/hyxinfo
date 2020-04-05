package com.mc.hyxinfo.dataobject.mapper;

import com.mc.hyxinfo.HyxinfoApplication;
import com.mc.hyxinfo.dataobject.EmpTransportData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HyxinfoApplication.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT )
public class EmpTransportDataMapperTest {
    @Autowired
    private EmpTransportDataMapper mapper;

    @Test
    public void findByUsernameAndCreateTime(){
        Map<String,Object> map =new HashMap<>();
        map.put("username","袁庆");
        map.put("createTime","2020-03-05 00:00:00");
        map.put("endTime","2020-03-06 23:59:59");
        List<EmpTransportData> result =mapper.findByUsernameAndCreateTime(map);
        Assert.assertNotEquals(0,result.size());
    }
}