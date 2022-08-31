package com.zyg.me.service;

import com.zyg.me.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User selectByPK(Long id);
    List<User> selectByName(String name);
     boolean verify(String name,String password);

}
