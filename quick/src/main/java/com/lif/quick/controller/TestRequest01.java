package com.lif.quick.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lifan
 * @Date: 2020/2/21 9:21
 */
@RestController
@RequestMapping("/aa")
public class TestRequest01 {

    @RequestMapping("/01")
    public String test01() {
        return "aa/01";
    }
}
