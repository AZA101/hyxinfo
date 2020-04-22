package com.mc.hyxinfo.service;

import com.mc.hyxinfo.dataobject.PeopleInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 人员信息的service
 */
public interface PeopleService {
    PeopleInfo findOne (String username);
    List<PeopleInfo> findAll();
    PeopleInfo findByPhoneNumberAndPasswords(String phoneNumber,String passwords);
    PeopleInfo findByPhoneNumber(String phoneNumber);
    Page<PeopleInfo>findList(Pageable pageable);
    PeopleInfo findByPersonId(Integer personid);
}
