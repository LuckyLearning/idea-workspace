package com.lif.mybatis.controller;

import com.lif.mybatis.pojo.User;
import com.lif.mybatis.service.UserBatchService;
import com.lif.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lifan
 * @Date: 2019/10/30 19:36
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserBatchService userBatchService;

    /**
     * 测试获取用户
     * @param id
     * @Return: com.lif.mybatis.pojo.User
     * @createDate: 2019/10/30 19:39
     */
    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(Long id) {
        return userService.getUser(id);
    }

    /**
     * 添加单个用户
     * @param
     * @Return: java.util.Map<java.lang.String,java.lang.Object>
     * @createDate: 2019/11/2 13:52
     */
    @RequestMapping("/insertUser")
    @ResponseBody
    public Map<String, Object> insertUser() {
        User user = new User();
        user.setUserName("aaaaa");
        user.setNote("note_test");
        int update = userService.insertUser(user);
        Map<String, Object> result = new HashMap<>();
        result.put("success", update == 1);
        result.put("user", user);
        return result;
    }


    /**
     * @param
     * @Return: java.util.Map<java.lang.String,java.lang.Object>
     * @createDate: 2019/11/2 14:04
     */
    @RequestMapping("/insertUsers")
    @ResponseBody
    public Map<String, Object> insertUsers() {
        User user1 = new User();
        user1.setUserName("tom1");
        user1.setNote("note_test1");
        User user2 = new User();
        user2.setUserName("jock2");
        user2.setNote("note_test2");
        ArrayList<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        int insertUsers = userBatchService.insertUsers(userList);
        HashMap<String, Object> result = new HashMap<>();
        result.put("success", insertUsers > 0);
        result.put("user", userList);
        return result;
    }
}
