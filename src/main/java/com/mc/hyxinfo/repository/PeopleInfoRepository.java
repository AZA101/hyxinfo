package com.mc.hyxinfo.repository;

import com.mc.hyxinfo.dataobject.PeopleInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeopleInfoRepository extends JpaRepository<PeopleInfo,Integer> {
    List<PeopleInfo> findByUsernameIn (List<String> usernameList);
    PeopleInfo findByUsername(String username);
    PeopleInfo findByPhoneNumberAndPasswords(String phoneNumber,String passwords);
    PeopleInfo findByPhoneNumber(String phoneNumber);
}
