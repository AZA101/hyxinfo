package com.mc.hyxinfo.service.impl;

import com.mc.hyxinfo.dataobject.PeopleInfo;
import com.mc.hyxinfo.enums.DataEnum;
import com.mc.hyxinfo.enums.LevelEnum;
import com.mc.hyxinfo.repository.PeopleInfoRepository;
import com.mc.hyxinfo.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Service
public class PeopleServiceImpl implements PeopleService {
    @Autowired
    private PeopleInfoRepository repository;
    @Override
    public PeopleInfo findOne(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public PeopleInfo findByPersonId(Integer personid) {
        return repository.findByPersonId(personid);
    }

    @Override
    public PeopleInfo save(PeopleInfo peopleInfo) {
        return repository.save(peopleInfo);
    }

    /*分页查询所有的记录*/
    @Override
    public Page<PeopleInfo> findList(Pageable pageable) {
        Page<PeopleInfo>peopleInfoPage=repository.findAll(pageable);
        //TODO 必须将peopleInfoPage.getContent()转化为arraylist，否则没有remove方法，也就不存在调用
        List<PeopleInfo>list=new ArrayList<>(peopleInfoPage.getContent()) ;

        Iterator<PeopleInfo> it = list.iterator();
        while(it.hasNext()){
            PeopleInfo x = it.next();
            /*筛选出员工的信息*/
            if(x.getLevels()==LevelEnum.admin.getCode()){
                it.remove();
                /*筛掉打了删除标记的员工信息*/
            }else if(x.getDel()!=DataEnum.NORMAL.getCode()){
                it.remove();
            }
        }
        /*按照创建时间进行倒序排序*/
         list.sort((p1,p2)-> p2.getCreateTime().compareTo(p1.getCreateTime()));
        //TODO 后续查询getContent()方法和getTotalElements()方法
        Page<PeopleInfo>page=new PageImpl<>(list,pageable,peopleInfoPage.getTotalElements());
        return page;
    }

    @Override
    public List<PeopleInfo> findAll() {
        return repository.findAll();
    }

    @Override
    public PeopleInfo findByPhoneNumberAndPasswords(String phoneNumber, String passwords) {
        return repository.findByPhoneNumberAndPasswords(phoneNumber,passwords);
    }

    @Override
    public PeopleInfo findByPhoneNumber(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber);
    }
}
