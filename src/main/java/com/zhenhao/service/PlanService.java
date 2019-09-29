package com.zhenhao.service;

import com.zhenhao.pojo.Plan;

import java.util.List;

public interface PlanService {

    List<Plan> getAllPlan();
    void insertPlan(Plan plan);
}
