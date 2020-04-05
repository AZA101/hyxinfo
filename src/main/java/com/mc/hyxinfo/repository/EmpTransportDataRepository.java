package com.mc.hyxinfo.repository;

import com.mc.hyxinfo.dataobject.EmpTransportData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpTransportDataRepository extends JpaRepository<EmpTransportData,String> {
    EmpTransportData findByBillId (String billId);
}
