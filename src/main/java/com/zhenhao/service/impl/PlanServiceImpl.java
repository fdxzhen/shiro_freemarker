package com.zhenhao.service.impl;

import com.zhenhao.dao.PlanMapper;
import com.zhenhao.pojo.Plan;
import com.zhenhao.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    PlanMapper planMapper;

    @Override
    public List<Plan> getAllPlan() {
       return planMapper.getAllPlan();
    }

    @Override
    public void insertPlan(Plan plan) {
        planMapper.insertPlan(plan);
    }
}
