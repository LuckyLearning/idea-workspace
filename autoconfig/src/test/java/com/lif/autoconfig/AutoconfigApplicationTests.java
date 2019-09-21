package com.lif.autoconfig;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试方法
 * @Author: lifan
 * @Date: 2019/8/24 23:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AutoconfigApplicationTests {

    /**
     * 
     * @return void
     */
    @Test
    public void contextLoads() {
        Assert.assertNull("");
        System.out.println("test1");
    }

    /**
     * 
     * @return void
     */
    public void test1() {
        System.out.println("test1");
    }

}
