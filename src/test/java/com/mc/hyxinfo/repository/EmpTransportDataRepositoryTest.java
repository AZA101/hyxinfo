package com.mc.hyxinfo.repository;

import com.mc.hyxinfo.dataobject.EmpTransportData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmpTransportDataRepositoryTest {
    @Autowired
    private EmpTransportDataRepository repository;

    @Test
    public void findByBillId() {
        EmpTransportData result=repository.findByBillId("2020030701");
        System.out.println(result);
        Assert.assertNotNull(result);

    }

    @Test
    public void saveTest() {
        EmpTransportData empTransportData = new EmpTransportData();
        empTransportData.setBillId("2020030705");
        empTransportData.setAddress("莽水");
        empTransportData.setSigns("云M32232");
        empTransportData.setUsername("袁继军");
        empTransportData.setCreateTime(new Date());
        empTransportData.setWeights(40.00);
        EmpTransportData result = repository.save(empTransportData);
        Assert.assertNotNull(result);
    }

    @Test
    public void findAllTest(){
        List<EmpTransportData> result =repository.findAll();
        Assert.assertNotNull(result);
    }

}