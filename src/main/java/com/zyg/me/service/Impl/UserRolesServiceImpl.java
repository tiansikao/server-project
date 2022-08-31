package com.zyg.me.service.Impl;

import com.zyg.me.domain.mapper.UserRolesMapper;
import com.zyg.me.domain.model.UserRoles;
import com.zyg.me.domain.model.UserRolesExample;
import com.zyg.me.service.UserRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRolesServiceImpl implements UserRolesService {

    private final UserRolesMapper userRolesMapper;

    @Autowired
    public UserRolesServiceImpl(UserRolesMapper r1){
        this.userRolesMapper =r1;
    }


    @Override
    public List<UserRoles> selectByUserName(String name) {
        UserRolesExample userRolesExample =new UserRolesExample();
        userRolesExample.createCriteria().andUsernameEqualTo(name);

        return userRolesMapper.selectByExample(userRolesExample);
    }
}
