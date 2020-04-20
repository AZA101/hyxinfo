package com.mc.hyxinfo.repository;

import com.mc.hyxinfo.dataobject.PeopleInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PeopleInfoRepositoryTest {

    @Autowired
    private PeopleInfoRepository repository;

    @Test
        public void findOneTest(){
        PeopleInfo peopleInfo =repository.findById(1).orElse(null);
        System.out.println("输出查询到的对象："+peopleInfo.toString());
        Assert.assertNotNull(peopleInfo);
    }

    @Test
    public void saveTest(){
         PeopleInfo peopleInfo=new PeopleInfo();
         peopleInfo.setUsername("袁继宏");
         peopleInfo.setPasswords("333");
         peopleInfo.setSigns("云M32330");
         PeopleInfo result=repository.save(peopleInfo);
         Assert.assertNotNull(result);
    }

    @Test
    public void findByUsernameInTest(){
        List<String> list= Arrays.asList("袁继军","袁庆");
        List<PeopleInfo> result=repository.findByUsernameIn(list);
        Assert.assertNotEquals(0,result.size());
    }

    @Test
    public void findByUsernameTest(){
         PeopleInfo result=repository.findByUsername("袁庆");
         Assert.assertNotNull(result);
    }
    @Test
    public void findByPhoneNumberAndPasswordsTest(){
        PeopleInfo result=repository.findByPhoneNumberAndPasswords("13987537730","222");
        Assert.assertNotNull(result);
    }

    @Test
    public void findByPhoneNumberTest(){
        PeopleInfo result=repository.findByPhoneNumber("13987537730");
        System.out.println(result);
        Assert.assertNotNull(result);
    }

}