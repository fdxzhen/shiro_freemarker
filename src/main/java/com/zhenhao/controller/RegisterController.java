package com.zhenhao.controller;

import com.zhenhao.pojo.ResponseBo;
import com.zhenhao.pojo.User;
import com.zhenhao.service.UserService;
import com.zhenhao.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseBo register(String username, String password, String secondPassword) {

        if(secondPassword.equals(password)){
            password = MD5Utils.encrypt(username, password);
            User user = new User(username,password);
            userService.userRegister(user);
            return ResponseBo.ok();
        }else{
            return ResponseBo.error("密码输入不一致");
        }
    }

}
