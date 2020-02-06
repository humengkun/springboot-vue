package com.hmk.springboot.controller;

import com.hmk.springboot.entity.Users;
import com.hmk.springboot.service.UsersService;
import com.hmk.springboot.util.CreatUuid;
import com.hmk.springboot.util.DataMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UsersController {
    @Autowired
    private UsersService usersService;

    //    1.分页 列出全部用户列表，展示用户信息
//    @RequestMapping("/list")
//    public DataMsg list(int page,int size) {
//        Pager<Users> userList = usersService.findByPager(page, size);
//        return DataMsg.ok(userList);
//    }
    @RequestMapping("/list")
    public DataMsg list() {
        List<Users> userList = usersService.getAll();
        return DataMsg.ok(userList);
    }

    //   2.增加新用户（做name唯一性验证，做phone，email格式验证）
    @PostMapping("/addUser")
    public DataMsg addOrUpdate(@Validated Users user) {
        System.out.println("user1==" + user);
        if (user.getName() != null && usersService.getUser(user.getName()) == null) {
            user.setId(CreatUuid.getUUID());
            System.out.println(user);
            usersService.insertUser(user);
        } else {
            return DataMsg.error("用户名已被使用！");
        }
        return DataMsg.ok();
    }

    //    3.通过name查询用户列表
    @RequestMapping("/selectUserByName")
    public DataMsg getUser(String name) {
        Users user;
        if (name != null && usersService.getUser(name) != null) {
            user = usersService.getUser(name);
            return  DataMsg.ok(user);
        }else {
            return DataMsg.error("用户不存在");
        }
    }

        //4.通过id修改用户信息
        @PostMapping("/update")
        public DataMsg modify(@Validated  Users user){
            if (user.getId() != null && usersService.getUserById(user.getId()) != null) {
                usersService.modify(user);
            }else {
                return DataMsg.error("添加失败");
            }
            return DataMsg.ok();
        }

    //    5.通过id删除用户
    @RequestMapping("/del")
    public DataMsg delete(@RequestParam(name = "id") String id) {
        if(id != null && usersService.getUserById(id) != null){
             usersService.delete(id);
        }else {
            return DataMsg.error("id不存在,请输入正确的id!");
        }
        return DataMsg.ok();
    }

    //search模糊查询
    @RequestMapping("search")
    public DataMsg search(String string){
        if (string != null && !"".equals(string) && usersService.searchByParameter(string) != null) {
            List<Users> users = usersService.searchByParameter(string);
            return DataMsg.ok(users);
        }else {
            return DataMsg.error(null);
        }
    }
}
