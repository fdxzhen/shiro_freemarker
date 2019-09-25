package com.zhenhao.dao;

import com.zhenhao.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPermissionMapper {

    @Select("select p.id,p.url,p.name from role r left join user_role ur on(r.id = ur.rid) left join user u on(u.id = ur.uid) left join role_permission rp on(rp.rid = r.id) left join permission p on(p.id = rp.pid ) where u.username = #{username}")
    List<Permission> findByUserName(String username);
}