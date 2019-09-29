package com.zhenhao.service;


import com.zhenhao.pojo.User;

public interface UserService {

    void userRegister(User user);
    public User getUserByName(String name);
    public  void updateUser(User user);
}
