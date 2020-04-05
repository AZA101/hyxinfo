package com.mc.hyxinfo.service.impl;

import com.mc.hyxinfo.dataobject.EmpTransportData;
import com.mc.hyxinfo.dataobject.mapper.EmpTransportDataMapper;
import com.mc.hyxinfo.service.EmpTransportDataService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmpTransportDataServiceImplTest {
    @Autowired
    private EmpTransportDataService repository;
    @Autowired
    private EmpTransportDataService mapper;
   /* @Autowired
    private EmpTransportDataServiceImpl empTransportDataService;*/
    @Test
    public void findByBillIdTest() {
        EmpTransportData result = repository.findByBillId("2020030701");
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByUsernameAndCreateTimeTest() {
        Map<String,Object> map =new HashMap<>();
        map.put("username","袁庆");
        map.put("createTime","2020-03-05 00:00:00");
        map.put("endTime","2020-03-06 23:59:59");
        List<EmpTransportData> result =mapper.findByUsernameAndCreateTime(map);
        Assert.assertNotEquals(0,result.size());
    }
    @Test
    public void findList(){
        PageRequest request=PageRequest.of(0,5);
        Page<EmpTransportData> empTransportDataPage=repository.findList(request);
        Assert.assertTrue("查询所有人的所有列表", empTransportDataPage.getTotalElements() > 0);
    }

}