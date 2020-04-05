package com.mc.hyxinfo.service.impl;

import com.mc.hyxinfo.dataobject.EmpTransportData;
import com.mc.hyxinfo.dataobject.mapper.EmpTransportDataMapper;
import com.mc.hyxinfo.repository.EmpTransportDataRepository;
import com.mc.hyxinfo.service.EmpTransportDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class EmpTransportDataServiceImpl implements EmpTransportDataService {
    @Autowired
    private EmpTransportDataRepository repository;
    @Autowired
    private EmpTransportDataMapper mapper;
    /*根据货运单号查询*/
    @Override
    public EmpTransportData findByBillId (String billId) {
        return repository.findByBillId(billId);
    }
    /*利用名字和时间查询一段范围内的记录*/
    @Override
    public List<EmpTransportData> findByUsernameAndCreateTime (Map<String, Object> map) {
        return mapper.findByUsernameAndCreateTime(map);
    }

    /*查询所有的记录*/
    @Override
    public Page<EmpTransportData> findList(Pageable pageable) {
        Page<EmpTransportData>empTransportDataPage=repository.findAll(pageable);
        List<EmpTransportData>list=empTransportDataPage.getContent();
        Page<EmpTransportData>page=new PageImpl<EmpTransportData>(list,pageable,empTransportDataPage.getTotalElements());
        return page;
    }

    /*保存数据*/
    @Override
    public EmpTransportData save(EmpTransportData empTransportData) {
        return repository.save(empTransportData);
    }
}
