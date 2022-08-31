package com.zyg.me.service;

import com.zyg.me.domain.model.RolesPermissions;

import java.util.List;

public interface RolesPermissionsService {
    List<RolesPermissions> selectByRoleName(String name);
}
