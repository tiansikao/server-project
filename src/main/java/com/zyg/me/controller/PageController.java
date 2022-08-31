package com.zyg.me.controller;

import com.zyg.me.commons.JsonBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageController {


    @GetMapping("unauthorized")
    public JsonBean page403() {

        return JsonBean.builder().code(0).msg("权限不足").build();
    }
    @GetMapping("logout")
    public JsonBean logout() {
        return JsonBean.builder().code(0).msg("成功注销！").build();
    }
}
