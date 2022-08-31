package com.zyg.me.service.Impl;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.zyg.me.commons.MD5Utils;
import com.zyg.me.domain.mapper.UserMapper;
import com.zyg.me.domain.model.User;
import com.zyg.me.domain.model.UserExample;
import com.zyg.me.service.Exception.UserNotFoundException;
import com.zyg.me.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User selectByPK(Long id) {
            Optional<User> userOptional =userMapper.selectByPrimaryKey(id);
            User user = userOptional.orElseThrow(UserNotFoundException::new);
        return user;
    }

    @Override
    public List<User> selectByName(String name) {
        UserExample userExample =new UserExample();
        userExample.or().andUsernameEqualTo(name);

        return userMapper.selectByExample(userExample);
    }

    @Override
    public boolean verify(String name, String password) {

        Preconditions.checkArgument(!Strings.isNullOrEmpty(name));
        Preconditions.checkArgument(!Strings.isNullOrEmpty(password));

        Preconditions.checkState(this.selectByName(name).size()==1,"该用户在数据库存储错误");
        String pass = this.selectByName(name).get(0).getPassword();
        String salt = this.selectByName(name).get(0).getPasswordSalt();
        Preconditions.checkState(pass.equals(MD5Utils.getMD5(password,salt)),"用户名密码错误");
        return true;
    }


}
