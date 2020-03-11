package com.lif.quick.controller;

import com.sun.deploy.net.HttpResponse;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * @Author: lifan
 * @Date: 2020/2/21 9:21
 */
@RestController
@RequestMapping("/aa")
public class TestRequest02 {

    @RequestMapping("/02")
    public String test02() {
        return "aa/02";
    }

    @RequestMapping("/*")
    public String test03(HttpRequest req, HttpResponse res) {
        URI uri = req.getURI();
        return "aa/03";
    }
}
