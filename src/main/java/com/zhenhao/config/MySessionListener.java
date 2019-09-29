package com.zhenhao.config;

import com.zhenhao.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import javax.security.auth.Subject;

public class MySessionListener implements SessionListener {
    @Override
    public void onStart(Session session) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        session.setAttribute("user",user);
        session.setAttribute("name","zhen");
    }

    @Override
    public void onStop(Session session) {

    }

    @Override
    public void onExpiration(Session session) {

    }
}
