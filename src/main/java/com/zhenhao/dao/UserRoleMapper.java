package com.zhenhao.dao;

import com.zhenhao.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleMapper {

    @Select("select r.id,r.name,r.memo from role r left join user_role ur on(r.id = ur.rid) left join user u on(u.id = ur.uid) where u.username = #{username}")
    List<Role> findByUserName(String username);
}