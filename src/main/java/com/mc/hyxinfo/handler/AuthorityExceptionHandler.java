package com.mc.hyxinfo.handler;

import com.mc.hyxinfo.exception.AuthorityException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ControllerAdvice，是Spring3.2提供的新注解,它是一个Controller增强器,可对controller中被 @RequestMapping注解的方法加一些逻辑处理。
 * 最常用的就是异常处理
 * 需要配合@ExceptionHandler使用。
 * 当将异常抛到controller时,可以对异常进行统一处理,规定返回的json格式或是跳转到一个错误页面
 */
@ControllerAdvice
public class AuthorityExceptionHandler {
    private String url="http://127.0.0.1:8080";
    @ExceptionHandler(value = AuthorityException.class)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView("redirect:"
                .concat(url)
                .concat("/hyxinfo/user/loginView"));
    }

}
