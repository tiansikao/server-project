package com.zyg.me.commons;


import com.zyg.me.controller.LoginController;
import com.zyg.me.service.Exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice(assignableTypes = LoginController.class)
@ResponseBody
public class FirstControllerExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public JsonBean demo1(Exception e) {
        return JsonBean.builder().code(0).msg("参数不合法").build();
    }
    @ExceptionHandler(IllegalStateException.class)
    public JsonBean demo4(IllegalStateException e) {
        return JsonBean.builder().code(0).msg(e.toString().substring(e.toString().indexOf(":")+1)).build();
    }
    @ExceptionHandler(UserNotFoundException.class)
    public JsonBean demo2(Exception e) {
        return JsonBean.builder().code(0).msg("用户信息没有找到").build();
    }

    @ExceptionHandler(Exception.class)
    public JsonBean demo3(Exception e) {
        return JsonBean.builder().code(0).msg(e.getClass().toString()).build();
    }
}
