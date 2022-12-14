package com.zyg.me.service.Impl;

import com.zyg.me.domain.mapper.RolesPermissionsMapper;
import com.zyg.me.domain.model.RolesPermissions;
import com.zyg.me.domain.model.RolesPermissionsExample;
import com.zyg.me.service.RolesPermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Service
public class RolesPermissionsServiceImpl implements RolesPermissionsService {

    private final RolesPermissionsMapper rolesPermissionsMapper;

    @Autowired
    public RolesPermissionsServiceImpl(RolesPermissionsMapper r1){
        this.rolesPermissionsMapper =r1;
    }

    @Override
    public List<RolesPermissions> selectByRoleName(String name) {

        RolesPermissionsExample permissionsExample =new RolesPermissionsExample();
        permissionsExample.createCriteria().andRoleNameEqualTo(name);//origin

        return rolesPermissionsMapper.selectByExample(permissionsExample);//origin
    }
}
