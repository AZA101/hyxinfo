package com.mc.hyxinfo.handler;

import com.mc.hyxinfo.exception.AuthorizeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class AuthorizeExceptionHandler {

    private String url="http://127.0.0.1:8080";
    @ExceptionHandler(value = AuthorizeException.class)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView("redirect:"
                .concat(url)
                .concat("/hyxinfo/user/loginView"));
    }

}
