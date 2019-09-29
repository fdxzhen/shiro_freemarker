package com.zhenhao.dao;

import com.zhenhao.pojo.Plan;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanMapper {

    @Select("select * from plan")
    List<Plan> getAllPlan();

    @Insert("insert into plan(title,content,createTime) values(#{title},#{content},now())")
    void insertPlan(Plan plan);
}
