package com.mc.hyxinfo.service.impl;

import com.mc.hyxinfo.Util.CookieUtil;
import com.mc.hyxinfo.constant.CookieConstant;
import com.mc.hyxinfo.constant.RedisConstant;
import com.mc.hyxinfo.dataobject.EmpTransportData;
import com.mc.hyxinfo.dataobject.PeopleInfo;
import com.mc.hyxinfo.dataobject.mapper.EmpTransportDataMapper;
import com.mc.hyxinfo.enums.DataEnum;
import com.mc.hyxinfo.enums.LevelEnum;
import com.mc.hyxinfo.exception.AuthorityException;
import com.mc.hyxinfo.repository.EmpTransportDataRepository;
import com.mc.hyxinfo.service.EmpTransportDataService;
import com.mc.hyxinfo.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class EmpTransportDataServiceImpl implements EmpTransportDataService {
    @Autowired
    private EmpTransportDataRepository repository;
    @Autowired
    private EmpTransportDataMapper mapper;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private PeopleService peopleService;

    /*根据货运单号查询*/
    @Override
    public EmpTransportData findByBillId(String billId) {
        return repository.findByBillId(billId);
    }

    /*利用名字和时间查询一段范围内的记录*/
    @Override
    public List<EmpTransportData> findByUsernameAndCreateTime(Map<String, Object> map) {
        return mapper.findByUsernameAndCreateTime(map);
    }

    /*查询所有的记录*/
    @Override
    public Page<EmpTransportData> findList(Pageable pageable) {
        Page<EmpTransportData> empTransportDataPage = repository.findAll(pageable);
        List<EmpTransportData> list = new ArrayList<>(empTransportDataPage.getContent());
        /*不通过Controller层传参的方式而获得HttpServletRequest*/
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        String Telephone;
        Iterator<EmpTransportData> it = list.iterator();
        if (cookie != null) {
            Telephone = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
            PeopleInfo peopleInfo = peopleService.findByPhoneNumber(Telephone);
            while(it.hasNext()){
                EmpTransportData x = it.next();
                if(x.getDel()==DataEnum.DEl.getCode()){
                    it.remove();
                }else if(peopleInfo.getLevels()!=LevelEnum.admin.getCode()){
                    if(!x.getUsername().equals(peopleInfo.getUsername())){
                        it.remove();
                    }
                }
            }
        }
        Page<EmpTransportData> page = new PageImpl<EmpTransportData>(list, pageable, empTransportDataPage.getTotalElements());
        return page;
    }

    /*保存数据*/
    @Override
    public EmpTransportData save(EmpTransportData empTransportData) {
        return repository.save(empTransportData);
    }
}
