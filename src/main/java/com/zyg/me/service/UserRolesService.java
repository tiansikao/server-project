package com.zyg.me.service;

import com.zyg.me.domain.model.UserRoles;

import java.util.List;

public interface UserRolesService {
    List<UserRoles> selectByUserName(String name);
}
