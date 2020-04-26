package com.mc.hyxinfo.Controller;



import com.mc.hyxinfo.dataobject.EmpTransportData;
import com.mc.hyxinfo.dataobject.PeopleInfo;
import com.mc.hyxinfo.enums.DataEnum;
import com.mc.hyxinfo.enums.ResultEnum;
import com.mc.hyxinfo.exception.ReturnException;
import com.mc.hyxinfo.form.EmpForm;
import com.mc.hyxinfo.form.UserForm;
import com.mc.hyxinfo.repository.PeopleInfoRepository;
import com.mc.hyxinfo.service.EmpTransportDataService;
import com.mc.hyxinfo.service.PeopleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import javax.validation.Valid;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @Autowired
    private EmpTransportDataService empTransportDataService;

    SimpleDateFormat  df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String nowTime = df.format(new Date());
    Timestamp dates =Timestamp.valueOf(nowTime);//把时间转换成Date类型

    /**
     * 查询员工列表
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
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView indexData(@RequestParam(value = "personId", required = false) Integer personId,
                                  Map<String, Object> map) {

        if(personId!=null&&!"".equals(personId)){
           PeopleInfo peopleInfo=peopleService.findByPersonId(personId);
            map.put("peopleInfo",peopleInfo);
            return new ModelAndView("peopleData/updatePeople", map);
        }
        map.put("peopleInfo",null);
        return new ModelAndView("peopleData/updatePeople",map);
    }

    /**
     * 保存人员信息
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView saveData(@Valid UserForm form,
                                 BindingResult bindingResult,
                                 Map<String, Object> map) {

        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/hyxinfo/people/index?personId=" + form.getPersonId());
            return new ModelAndView("common/error", map);
        }
        try {
            if (form.getPersonId() != null && !"".equals(form.getPersonId())) {
                PeopleInfo peopleInfo = peopleService.findByPersonId(form.getPersonId());
                BeanUtils.copyProperties(form, peopleInfo);
                peopleService.save(peopleInfo);
            }//新增人员信息
            else if ((form.getPersonId() == null || "".equals(form.getPersonId()))
                    && !form.getUsername().isEmpty()
                    && !form.getPhoneNumber().isEmpty()
                    && !form.getSigns().isEmpty()
                    && !form.getSigns().isEmpty()) {
                /*将前端的信息赋值到PeopleInfo中*/
                PeopleInfo peopleInfo=new PeopleInfo();
                BeanUtils.copyProperties(form,peopleInfo);
                peopleInfo.setCreateTime(dates);
                peopleService.save(peopleInfo);
            }else{
                map.put("msg",ResultEnum.FORM_DATA_ERROR.getMsg() );
                map.put("url","/hyxinfo/people/index?personId="+form.getPersonId());
                return new ModelAndView("common/error",map);
            }
        } catch (ReturnException e) {
            map.put("msg",ResultEnum.FORM_DATA_ERROR.getMsg() );
            map.put("url","/hyxinfo/people/index?personId="+form.getPersonId());
            return new ModelAndView("common/error",map);
        }
         /*操作成功提示*/
        map.put("msg", ResultEnum.SUCCESS.getMsg());
        map.put("url", "/hyxinfo/people/list");
        return new ModelAndView("common/success", map);
    }

    /**
     * 查询员工的运输数据
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("transportList")
    public ModelAndView transportList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                      @RequestParam(value = "size", defaultValue = "10") Integer size,
                                      Map<String,Object>map){
        PageRequest request = PageRequest.of(page - 1, size);
        Page<EmpTransportData> empDataPage=empTransportDataService.findList(request);
        map.put("empDataPage",empDataPage);
        map.put("empPage",page);
        map.put("size",size);
        return new ModelAndView("peopleData/transportList",map);
    }

    /**
     * 删除运输数据
     * @param billId
     * @param map
     * @return
     */
    @GetMapping("/deleteList")
    public ModelAndView deleteList(@RequestParam(value = "billId", required = false) String billId,
                                   Map<String,Object>map){
        if(StringUtils.isEmpty(billId)){
            map.put("msg",ResultEnum.PARAM_NOT_EXIST.getMsg() );
            map.put("url", "/hyxinfo/people/transportList");
            return new ModelAndView("common/error", map);
        }
        EmpTransportData empTransportData=empTransportDataService.findByBillId(billId);
        empTransportData.setDel(DataEnum.DEl.getCode());
        EmpTransportData empdata=empTransportDataService.save(empTransportData);
        if(empdata!=null){
            map.put("msg", ResultEnum.SUCCESS.getMsg());
            map.put("url", "/hyxinfo/people/transportList");
            return new ModelAndView("common/success", map);
        }
        map.put("msg", ResultEnum.UPDATE_FALL.getMsg());
        map.put("url", "/hyxinfo/people/transportList");
        return new ModelAndView("common/error", map);

    }

    /**
     * 选择需要修改的货运数据
     * @param billId
     * @param map
     * @return
     */
    @GetMapping("/indexList")
    public ModelAndView indexList(@RequestParam(value = "billId",required = false) String billId,
                                  Map<String,Object>map){
        if(!StringUtils.isEmpty(billId)){
            EmpTransportData empTransportData=empTransportDataService.findByBillId(billId);
            map.put("empData",empTransportData);
            return new ModelAndView("peopleData/indexList",map);
        }
        map.put("msg", ResultEnum.PARAM_NOT_EXIST.getMsg());
        map.put("url", "/hyxinfo/people/transportList");
        return new ModelAndView("common/error", map);
    }

    /**
     * 保存修改的运输数据
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/saveList")
    public ModelAndView saveList(@Valid EmpForm form,
                                 BindingResult bindingResult,
                                 Map<String,Object>map){
        if(bindingResult.hasErrors()){
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/hyxinfo/people/indexList?billId="+form.getBillId());
            return new ModelAndView("common/error",map);
        }
        if(form.getBillId()!=null && !form.getBillId().isEmpty()){
            EmpTransportData empTransportData=empTransportDataService.findByBillId(form.getBillId());
            BeanUtils.copyProperties(form,empTransportData);
            EmpTransportData empdata=empTransportDataService.save(empTransportData);
            if(empdata!=null){
                map.put("msg", ResultEnum.SUCCESS.getMsg());
                map.put("url","/hyxinfo/people/transportList");
                return new ModelAndView("common/success",map);
            }
        }
        map.put("msg", ResultEnum.PARAM_NOT_EXIST.getMsg());
        map.put("url", "/hyxinfo/people/transportList");
        return new ModelAndView("common/error", map);
    }

}
