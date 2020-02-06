package com.hmk.springboot.dao;


import com.hmk.springboot.entity.Users;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UsersDao {

    //    1.列出用户列表，展示用户信息;
    List<Users> getAllUserList();

    //    2.增加新用户（做name唯一性验证，做phone，email格式验证）
    void insert(Users user);

    //    3.通过name查询用户列表
    Users getUserByName(String name);

    //    4.通过id更新用户信息
    void update(Users user);

    //通过id查询用户
    Users getById(String id);

    //    5.通过id删除用户
    void deleteById(String id);

    //分页
     List<Users> findByPager(Map<String, Object> params);
     int count();

    //模糊查询
    List<Users> searchByParameter(String str);




}
