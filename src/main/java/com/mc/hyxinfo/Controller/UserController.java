package com.mc.hyxinfo.Controller;

import com.mc.hyxinfo.Util.CookieUtil;
import com.mc.hyxinfo.constant.CookieConstant;
import com.mc.hyxinfo.constant.RedisConstant;
import com.mc.hyxinfo.dataobject.PeopleInfo;
import com.mc.hyxinfo.enums.ResultEnum;
import com.mc.hyxinfo.form.LoginForm;
import com.mc.hyxinfo.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private PeopleService peopleService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    private String url = "http://127.0.0.1:8080";

    /*登陆操作*/
    @PostMapping("/loginIn")
    public ModelAndView loginIn(@Valid LoginForm form,
                                BindingResult bindingResult,
                                HttpServletResponse response,
                                Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/hyxinfo/user/loginView");
            return new ModelAndView("common/error", map);
        }
        PeopleInfo peopleInfo = peopleService.findByPhoneNumberAndPasswords(form.getPhoneNumber(), form.getPasswords());
        if (peopleInfo == null || peopleInfo.equals("")) {
            map.put("msg", ResultEnum.USER_NOT_EXIST.getMsg());
            map.put("url", "/hyxinfo/user/loginView");
            return new ModelAndView("common/error", map);
        }
        /*设置token至redis,opsforValue()表示拿到操作*/
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        /*设置四个变量,依次为redis的key值,value值,过期时间,时间单位*/
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), form.getPhoneNumber(), expire, TimeUnit.SECONDS);
        /*设置token到cookie中*/
        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.EXPIRE);
        return new ModelAndView("redirect:" + url + "/hyxinfo/emp/data/list");
    }

    /*登陆界面*/
    @GetMapping("/loginView")
    public ModelAndView loginView() {
        return new ModelAndView("common/login");
    }

    /*登出操作*/
    @GetMapping("/loginOut")
    public ModelAndView logOut(HttpServletResponse response,
                               HttpServletRequest request,
                               Map<String, Object> map) {
        /*获取cookie中的信息,传入request和要进行查询的token*/
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            /*将redis中的存储的信息删除*/
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }
        map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMsg());
        map.put("url", "/hyxinfo/user/loginView");
        return new ModelAndView("common/success", map);

    }


}
