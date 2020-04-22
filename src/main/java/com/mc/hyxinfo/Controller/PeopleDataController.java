package com.mc.hyxinfo.Controller;


import com.mc.hyxinfo.dataobject.PeopleInfo;
import com.mc.hyxinfo.enums.DataEnum;
import com.mc.hyxinfo.enums.ResultEnum;
import com.mc.hyxinfo.repository.PeopleInfoRepository;
import com.mc.hyxinfo.service.PeopleService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author admin
 * 管理员界面，管理员工的个人信息数据
 */
@Controller
@RequestMapping("/people")
public class PeopleDataController {
    @Autowired
    private PeopleService peopleService;
    @Autowired
    private PeopleInfoRepository peopleInfoRepository;

    /**
     * 查询所有的员工数据
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String,Object> map) {
        PageRequest request = PageRequest.of(page - 1, size);
        Page<PeopleInfo> peopleInfoPage=peopleService.findList(request);
        map.put("peopleInfoPage",peopleInfoPage);
        map.put("peoplePage",page);
        map.put("size",size);
        return new ModelAndView("peopleData/peopleList",map);
    }
    @GetMapping("/delete")
    public ModelAndView deleteData(@RequestParam( "personId")Integer personId,
                                   Map<String,Object>map){
        try {
            if(personId!=null && !"".equals(personId)){
                PeopleInfo peopleInfo=peopleService.findByPersonId(personId);
                if(peopleInfo!=null){
                    peopleInfo.setDel(DataEnum.DEl.getCode());
                    peopleInfoRepository.save(peopleInfo);
                }else{
                    map.put("msg", ResultEnum.USER_NOT_EXIST.getMsg() );
                    map.put("url","/hyxinfo/people/list");
                    return new ModelAndView("common/error",map);
                }
            }else{
                map.put("msg", ResultEnum.PARAM_ERROR.getMsg() );
                map.put("url","/hyxinfo/people/list");
                return new ModelAndView("common/error",map);
            }
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            map.put("url","/hyxinfo/people/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg", ResultEnum.DELETE_SUCCESS.getMsg() );
        map.put("url","/hyxinfo/people/list");
        return new ModelAndView("common/success",map);
    }



}
