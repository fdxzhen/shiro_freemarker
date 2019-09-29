package com.zhenhao.controller;

import com.zhenhao.config.MySessionListener;
import com.zhenhao.pojo.ResponseBo;
import com.zhenhao.pojo.User;
import com.zhenhao.service.UserService;
import com.zhenhao.utils.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.apache.shiro.SecurityUtils.*;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseBo login(String username, String password, Boolean rememberMe) {
        // 密码MD5加密
        password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        // 获取Subject对象
        Subject subject = getSubject();
        try {
            subject.login(token);
            return ResponseBo.ok();
        } catch (UnknownAccountException e) {
            return ResponseBo.error(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return ResponseBo.error(e.getMessage());
        } catch (LockedAccountException e) {
            return ResponseBo.error(e.getMessage());
        } catch (AuthenticationException e) {
            return ResponseBo.error("认证失败！");
        }
    }

    @RequestMapping("/")
    public String redirectIndex(Model model) {

        User user = (User) getSubject().getPrincipal();
        model.addAttribute("user", user);
        return "/index";
    }

    @RequestMapping("/index")
    public String index(Model model, HttpServletRequest request) throws Exception{
        // 登录成后，即可通过Subject获取登录的用户信息
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String name = user.getUsername();

        User user1 = userService.getUserByName(name);
        model.addAttribute("user", user1);
        HttpSession session = request.getSession();
//        String name = user.getUsername();
        session.setAttribute("user", user1);
        session.setAttribute("name", name);
        session.setAttribute("imgUrl", "http://t.cn/RCzsdCq");

        return "/index";
    }
}
