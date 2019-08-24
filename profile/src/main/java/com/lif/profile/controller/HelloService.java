package com.lif.profile.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * service类
 * @author lifan
 */
@RestController
public class HelloService {

    @RequestMapping("/hello")
    private String hello() {
        return "hello";
    }
}
