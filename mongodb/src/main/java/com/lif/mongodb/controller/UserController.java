package com.lif.mongodb.controller;

import com.lif.mongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: lifan
 * @Date: 2019/11/26 21:07
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 测试页面
     * @param
     * @return: java.lang.String
     * @createDate: 2019/12/1 12:50
     */
    @RequestMapping("/page")
    public String page() {
        return "page";
    }



}
