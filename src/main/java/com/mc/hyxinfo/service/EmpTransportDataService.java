package com.mc.hyxinfo.service;

import com.mc.hyxinfo.dataobject.EmpTransportData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @author admin
 * 员工货运数据查询
 */
public interface EmpTransportDataService {
    EmpTransportData findByBillId(String billId);

    List<EmpTransportData> findByUsernameAndCreateTime(Map<String, Object> map);

    Page<EmpTransportData> findList(Pageable pageable);

    EmpTransportData save(EmpTransportData empTransportData);
}
