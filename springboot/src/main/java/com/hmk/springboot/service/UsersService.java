package com.hmk.springboot.service;


import com.hmk.springboot.entity.Pager;
import com.hmk.springboot.entity.Users;
import java.util.List;


public interface UsersService {
    //    1.列出用户列表，展示用户信息;
    List<Users> getAll();

    //    2.增加新用户（做name唯一性验证，做phone，email格式验证）
    void insertUser(Users user);

    //    3.通过name查询用户列表
    Users getUser(String name);

    //    4.通过id更新用户信息
    void modify(Users user);

    //通过id查询用户
    Users getUserById(String id);

    //    5.通过id删除用户
    void delete(String id);

    //分页
    Pager<Users> findByPager(int page, int size);

    //模糊查询
    List<Users> searchByParameter(String str);

}
