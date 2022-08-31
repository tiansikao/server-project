package com.zyg.me.commons;


import com.zyg.me.controller.LoginController;
import com.zyg.me.controller.RoleController;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/*
@Slf4j
@ControllerAdvice(assignableTypes = RoleController.class)
public class SecondControllerExceptionHandler {

    @ExceptionHandler({AuthorizationException.class})
    public String exceptionHandler() {
        return "/unauthorized.do";
    }
}
*/
