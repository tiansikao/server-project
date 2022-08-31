package com.zyg.me.controller;
import java.util.Properties;

import com.zyg.me.commons.JsonBean;
import com.zyg.me.commons.JwtUtil;
import com.zyg.me.service.Impl.UserServiceImpl;
import com.zyg.me.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@Validated
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public JsonBean login(HttpServletRequest request, @NotNull String username, @NotNull String password) {
        JsonBean bean = new JsonBean();

        boolean b=userService.verify(username.trim(), password.trim());

        JwtUtil jwtUtil = new JwtUtil();

        Map<String, Object> claim = new HashMap<>();
        claim.put("username", username);
        claim.put("User-Agent",request.getHeader("User-Agent"));
        String jwtToken = jwtUtil.encode(username, 100, claim);

        bean.setMsg("登录成功");
        bean.setData(jwtToken);
        bean.setCode(1);
        return bean;
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public void login(@Max(33) Integer a) {
        log.info("+++++++++++++");
    }
}
