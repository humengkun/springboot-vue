package com.hmk.springboot.service.impl;


import com.hmk.springboot.dao.UsersDao;
import com.hmk.springboot.entity.Pager;
import com.hmk.springboot.entity.Users;
import com.hmk.springboot.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class UsersServiceImpl implements UsersService {
    @Autowired
    private  UsersDao usersDao;


    @Override
    public List<Users> getAll() {
        return usersDao.getAllUserList();
    }

    @Override
    public void insertUser(Users user) {
        usersDao.insert(user);
    }

    @Override
    public Users getUser(String name) {
        return usersDao.getUserByName(name);
    }

    @Override
    public void modify(Users user) {
        usersDao.update(user);
    }

    @Override
    public Users getUserById(String id) {
        return usersDao.getById(id);
    }

    @Override
    public void delete(String id) {
        usersDao.deleteById(id);
    }

    //分页查询
    public Pager<Users> findByPager(int page, int size){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("page", (page-1)*size);
        params.put("size", size);
        Pager<Users> pager = new Pager<Users>();
        List<Users> list = usersDao.findByPager(params);
        pager.setRows(list);
        pager.setTotal(usersDao.count());
        return pager;
    }

    //模糊查询
    @Override
    public List<Users> searchByParameter(String str) {
        return usersDao.searchByParameter(str);
    }

}
