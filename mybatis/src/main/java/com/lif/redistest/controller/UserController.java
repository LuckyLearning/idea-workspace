package com.lif.redistest.controller;

import com.lif.redistest.pojo.User;
import com.lif.redistest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: lifan
 * @Date: 2019/11/24 11:02
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/getuser")
    @ResponseBody
    public User getUser(Long id) {
        User user = userService.getUser(id);
        return user;
    }

    @RequestMapping("/insertuser")
    @ResponseBody
    public User insertUser(String userName, String note) {
        User user = new User();
        user.setUserName(userName);
        user.setNote(note);
        userService.insertUser(user);
        return user;
    }

    @RequestMapping("/findusers")
    @ResponseBody
    public List<User> findUsers(String userName, String note) {
        List<User> users = userService.findUsers(userName, note);
        return users;
    }

    @RequestMapping("/updateusername")
    @ResponseBody
    public Map<String, Object> updateUserName(Long id, String userName) {
        User user = userService.updateUserName(id, userName);
        boolean flag = user != null;
        String message = flag ? "更新成功" : "更新失败";
        return resultMap(flag, message);
    }

    @RequestMapping("/deleteuser")
    @ResponseBody
    public Map<String, Object> deleteUser(Long id) {
        int del = userService.deleteUser(id);
        boolean flag = del == 1;
        String message = flag ? "更新成功" : "更新失败";
        return resultMap(flag, message);
    }

    private Map<String, Object> resultMap(boolean success, String message) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("success", success);
        map.put("message", message);
        return map;
    }

}
