package com.mc.hyxinfo.aspect;

import com.mc.hyxinfo.Util.CookieUtil;
import com.mc.hyxinfo.constant.CookieConstant;
import com.mc.hyxinfo.constant.RedisConstant;
import com.mc.hyxinfo.exception.AuthorizeException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 通过aop去验证是否已经登陆，列出需要验证的操作
 *
 * @author admin
 */
@Aspect
@Component
@Slf4j
public class AuthorizeAspect {
    @Autowired
    private StringRedisTemplate redisTemplate;
    /*添加切入点*/
    @Pointcut("execution(public * com.mc.hyxinfo.Controller.EmpTransportDataController.*(..))" +
            "&& !execution(public * com.mc.hyxinfo.Controller.UserController.*(..))")
    public void verify() {
    }

    /*具体的验证逻辑,在切入点之前运行*/
    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        /*查询cookie*/
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            log.warn("[登陆校验]cookie中查询不到token");
            throw new AuthorizeException();
        }
        /*去到redis中查询数据*/
        String tokenValue =  redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if (StringUtils.isEmpty(tokenValue)) {
          log.warn("[登陆校验]Redis中查不到");
          throw new AuthorizeException();
        }
    }

}