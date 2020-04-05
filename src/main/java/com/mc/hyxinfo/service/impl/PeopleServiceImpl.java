package com.mc.hyxinfo.service.impl;

import com.mc.hyxinfo.dataobject.PeopleInfo;
import com.mc.hyxinfo.repository.PeopleInfoRepository;
import com.mc.hyxinfo.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<PeopleInfo> findAll() {
        return repository.findAll();
    }

    @Override
    public PeopleInfo findByPhoneNumberAndPasswords(String phoneNumber, String passwords) {
        return repository.findByPhoneNumberAndPasswords(phoneNumber,passwords);
    }
}
