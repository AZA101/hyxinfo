package com.mc.hyxinfo.service;

import com.mc.hyxinfo.dataobject.PeopleInfo;

import java.util.List;

/**
 * 人员信息的service
 */
public interface PeopleService {
    PeopleInfo findOne (String username);
    List<PeopleInfo> findAll();
    PeopleInfo findByPhoneNumberAndPasswords(String phoneNumber,String passwords);
    PeopleInfo findByPhoneNumber(String phoneNumber);
}
