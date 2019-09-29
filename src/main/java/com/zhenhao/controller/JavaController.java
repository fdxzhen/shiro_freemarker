package com.zhenhao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/java")
public class JavaController {

    @RequestMapping("/list")
    public String list(){
        return "/java/javaList";
    }

}
