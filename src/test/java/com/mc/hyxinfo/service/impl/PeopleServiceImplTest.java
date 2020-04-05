package com.mc.hyxinfo.service.impl;

import com.mc.hyxinfo.dataobject.PeopleInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class PeopleServiceImplTest {
  @Autowired
  private PeopleServiceImpl peopleService;

    @Test
    public void findOne() {
      PeopleInfo result =peopleService.findOne("袁继军");
      Assert.assertNotNull(result);
    }

    @Test
    public void findAll() {
        List<PeopleInfo> peopleInfoList=peopleService.findAll();
        System.out.println(peopleInfoList);
        Assert.assertNotEquals(0,peopleInfoList.size());
    }
    @Test
    public void findByPhoneNumberAndPasswords(){
        PeopleInfo result=peopleService.findByPhoneNumberAndPasswords("13987537730","222");
        Assert.assertNotNull(result);
    }
}