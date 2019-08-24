package com.lif.quick.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: HelloController.java
 * @Description: java类作用描述
 * @Author: lifna
 * @Date: 2019/8/20 22:45
 */
@RestController
public class HelloController {

    @Value("${person.last-name}")
    private String lastName;

    @RequestMapping("/hello")
    public String hello() {
        return "hello world quick! " + lastName;
    }
}
