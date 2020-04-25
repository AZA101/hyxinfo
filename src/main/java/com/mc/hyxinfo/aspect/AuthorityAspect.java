package com.mc.hyxinfo.aspect;

import com.mc.hyxinfo.Util.CookieUtil;
import com.mc.hyxinfo.constant.CookieConstant;
import com.mc.hyxinfo.constant.RedisConstant;
import com.mc.hyxinfo.dataobject.PeopleInfo;
import com.mc.hyxinfo.enums.LevelEnum;
import com.mc.hyxinfo.exception.AuthorityException;
import com.mc.hyxinfo.service.PeopleService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 验证哪些操作只能由管理员进行操作
 *
 * @author admin
 * @Aspect 注解把当前类标识为一个切面供容器读取
 * @Component 把普通pojo实例化到spring容器中
 */
@Aspect
@Component
@Slf4j
public class AuthorityAspect {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private PeopleService peopleService;

    /*添加切入点，填写切入点的范围*/
    @Pointcut("execution(public * com.mc.hyxinfo.Controller.PeopleDataController.*(..))")
    public void verify() {
    }

    /*具体的验证逻辑,在切入点之前运行*/
    @Before("verify()")
    public void doVerify() {
        /*不通过Controller层传参的方式而获得HttpServletRequest*/
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        /*查询cookie*/
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        String Telephone;
        if (cookie != null) {
            Telephone = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
            PeopleInfo peopleInfo = peopleService.findByPhoneNumber(Telephone);
            if(peopleInfo.getLevels()!= LevelEnum.admin.getCode()){
                log.warn("权限不符，只有管理员才能操作");
                throw new AuthorityException();
            }
        }else{
            log.warn("[登陆校验]cookie中查不到");
            throw new AuthorityException();
        }

    }

}
