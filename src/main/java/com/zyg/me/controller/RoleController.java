package com.zyg.me.controller;

import com.zyg.me.commons.JsonBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RoleController {

    @RequestMapping("/role-admin")
    @RequiresRoles("admin")
    public JsonBean adminDo() {
        return JsonBean.builder().code(0).msg("只有拥有【管理员】身份，才能访问到这个URL").build();
    }
    @RequestMapping("/role-user")
    @RequiresRoles("user")
    public ResponseEntity<String> userDo() {
        return ResponseEntity.ok("只有拥有【会员】身份，才能访问到这个URL");
    }

    @RequestMapping("/opr-1")
    @RequiresPermissions("user:query")
    public ResponseEntity<String> queryUser() {
        return ResponseEntity.ok("只有拥有【user:query】权限，才能访问到这个URL");
    }

    @RequestMapping("/opr-2")
    @RequiresPermissions("user:delete")
    public ResponseEntity<String> deleteUser() {
        return ResponseEntity.ok("只有拥有【user:delete】权限，才能访问到这个URL");
    }
}
