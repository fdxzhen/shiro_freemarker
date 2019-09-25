package com.zhenhao.service.impl;

import com.zhenhao.dao.UserMapper;
import com.zhenhao.pojo.User;
import com.zhenhao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void userRegister(User user) {
        userMapper.userRegister(user);
    }
}
