package com.mc.hyxinfo.Controller;


import com.mc.hyxinfo.Util.CookieUtil;
import com.mc.hyxinfo.constant.CookieConstant;
import com.mc.hyxinfo.constant.RedisConstant;
import com.mc.hyxinfo.dataobject.PeopleInfo;
import com.mc.hyxinfo.enums.DataEnum;
import com.mc.hyxinfo.enums.LevelEnum;
import com.mc.hyxinfo.enums.ResultEnum;
import com.mc.hyxinfo.form.UserForm;
import com.mc.hyxinfo.repository.PeopleInfoRepository;
import com.mc.hyxinfo.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

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
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 查询所有的员工数据
     *
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageRequest request = PageRequest.of(page - 1, size);
        Page<PeopleInfo> peopleInfoPage = peopleService.findList(request);
        map.put("peopleInfoPage", peopleInfoPage);
        map.put("peoplePage", page);
        map.put("size", size);
        return new ModelAndView("peopleData/peopleList", map);
    }

    /**
     * 删除员工数据
     *
     * @param personId
     * @param map
     * @return
     */
    @GetMapping("/delete")
    public ModelAndView deleteData(@RequestParam("personId") Integer personId,
                                   Map<String, Object> map) {
        try {
            if (personId != null && !"".equals(personId)) {
                PeopleInfo peopleInfo = peopleService.findByPersonId(personId);
                if (peopleInfo != null) {
                    peopleInfo.setDel(DataEnum.DEl.getCode());
                    peopleInfoRepository.save(peopleInfo);
                } else {
                    map.put("msg", ResultEnum.USER_NOT_EXIST.getMsg());
                    map.put("url", "/hyxinfo/people/list");
                    return new ModelAndView("common/error", map);
                }
            } else {
                map.put("msg", ResultEnum.PARAM_ERROR.getMsg());
                map.put("url", "/hyxinfo/people/list");
                return new ModelAndView("common/error", map);
            }
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            map.put("url", "/hyxinfo/people/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.DELETE_SUCCESS.getMsg());
        map.put("url", "/hyxinfo/people/list");
        return new ModelAndView("common/success", map);
    }

    /**
     * 新增修改人员信息
     * @param personId
     * @param request
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView indexData(@RequestParam(value = "personId", required = false) Integer personId,
                                  HttpServletRequest request,
                                  Map<String, Object> map) {

        /*获取人员信息，如电话号码，先获取cookie中token,利用token从redis中获取value值*/
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        String Telephone;
        PeopleInfo peopleInfo = new PeopleInfo();
        if (cookie != null) {
            Telephone = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
            peopleInfo = peopleService.findByPhoneNumber(Telephone);
        }
        /*只有管理员才能新增人员信息*/
        if (peopleInfo.getLevels() == LevelEnum.EMP.getCode()) {
            if (personId != null) {
                PeopleInfo Info = peopleService.findByPersonId(personId);
                map.put("peopleInfo", Info);
                return new ModelAndView("peopleData/updatePeople", map);
            } else {
                map.put("msg", ResultEnum.PARAM_ERROR.getMsg());
                map.put("url", "/hyxinfo/emp/data/list");
                return new ModelAndView("common/error", map);
            }
        } else {
            if (personId != null) {
                PeopleInfo Info = peopleService.findByPersonId(personId);
                map.put("peopleInfo", Info);
                return new ModelAndView("peopleData/updatePeople", map);
            } else {
                map.put("peopleInfo", null);
                return new ModelAndView("peopleData/updatePeople", map);
            }
        }
    }


    @PostMapping("/save")
    public ModelAndView saveData(@Valid UserForm form,
                                   BindingResult bindingResult,
                                   Map<String, Object> map) {


        return new ModelAndView();
    }


}
