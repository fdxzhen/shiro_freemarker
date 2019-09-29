package com.zhenhao.controller;

import com.zhenhao.pojo.Plan;
import com.zhenhao.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    PlanService planService;

    @GetMapping("/list")
    public String list(Model model){
        List<Plan> planList =  planService.getAllPlan();
        model.addAttribute("planList",planList);

        return "/plan/list";
    }
    @PostMapping("/list")
    public String list1(HttpServletRequest request, Model model, Plan plan){
//        Plan plan1 =new Plan(request.getParameter("title"),request.getParameter("content"));
        planService.insertPlan(plan);

        List<Plan> planList =  planService.getAllPlan();
        model.addAttribute("planList",planList);
        return "/plan/list";
    }


    @GetMapping("/insert")
    public String insert(Model model){
//        List<Plan> planList =  planService.getAllPlan();
//        model.addAttribute("planList",planList);
        return "/plan/insert";
    }



}
