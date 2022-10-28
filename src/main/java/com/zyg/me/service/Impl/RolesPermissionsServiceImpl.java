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

    private final RolesPermissionsMapper rolesPermissionsMapper;//local

    @Autowired
    public RolesPermissionsServiceImpl(RolesPermissionsMapper r1){//local
        this.rolesPermissionsMapper =r1;
    }

    @Override
    public List<RolesPermissions> selectByRoleName(String name) {//origin1111222

        RolesPermissionsExample permissionsExample =new RolesPermissionsExample();//origin
        permissionsExample.createCriteria().andRoleNameEqualTo(name);

        return rolesPermissionsMapper.selectByExample(permissionsExample);
    }
}
