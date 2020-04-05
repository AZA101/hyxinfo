package com.mc.hyxinfo.Controller;

import com.mc.hyxinfo.dataobject.EmpTransportData;
import com.mc.hyxinfo.enums.ResultEnum;
import com.mc.hyxinfo.exception.ReturnException;
import com.mc.hyxinfo.form.EmpForm;
import com.mc.hyxinfo.form.LoginForm;
import com.mc.hyxinfo.service.EmpTransportDataService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/emp/data")
public class EmpTransportDataController {
    @Autowired
    private EmpTransportDataService empTransportDataService;

    /**
     * 查询所有的数据
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String,Object>map) {
        PageRequest request = PageRequest.of(page - 1, size);
        Page<EmpTransportData> empDataPage=empTransportDataService.findList(request);
        map.put("empDataPage",empDataPage);
        map.put("empPage",page);
        map.put("size",size);
        return new ModelAndView("empData/list",map);
    }

    /**
     * 列出需要修改的那条数据或者新增一条数据
     * @param billId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "billId",required = false) String billId,
                              Map<String,Object>map){
        if(!StringUtils.isEmpty(billId)){
            EmpTransportData empTransportData =empTransportDataService.findByBillId(billId);
            map.put("empData",empTransportData);
            return new ModelAndView("empData/index",map);
        }
        map.put("empData",null);
        return new ModelAndView("empData/index",map);
    }

    /**
     * 保存修改的数据
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid EmpForm form,
                             BindingResult bindingResult,
                             Map<String,Object>map){
        if(bindingResult.hasErrors()){
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/hyxinfo/emp/data/index");
            return new ModelAndView("common/error",map);
        }
        try {
            if(form.getBillId()!=null && !form.getBillId().isEmpty()){
                EmpTransportData empTransportData=empTransportDataService.findByBillId(form.getBillId());
                BeanUtils.copyProperties(form,empTransportData);
                empTransportDataService.save(empTransportData);
            }
        } catch (ReturnException e) {
            map.put("msg", e.getMessage());
            map.put("url","/hyxinfo/emp/data/index");
            return new ModelAndView("common/error",map);
        }
        map.put("msg", ResultEnum.SUCCESS.getMsg());
        map.put("url","/hyxinfo/emp/data/list");
        return new ModelAndView("common/success",map);
    }

    @GetMapping("/test")
        public ModelAndView test(){
        return new ModelAndView("common/test");
    }



}
