package com.zhenhao.dao;

import com.zhenhao.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper {

    @Select("select * from user where username=#{username}")
    User findByUserName(String userName);


    @Insert("insert into user(username,password,createTime,status) values(#{username},#{password},now(),1)")
    void userRegister(User user);

    @Select("select * from user where username=#{username}")
    public User getUserByName(String name);

    @Update("update user set imgUrl=#{imgUrl} where username=#{username}")
    public void updateUser(User user);
}
