package com.lif.config01;

import com.lif.config01.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Config01ApplicationTests {

    @Autowired
    Person person;

    @Autowired
    ApplicationContext ico;

    @Test
    public void TestBean() {
        boolean b = ico.containsBean("helloService");
        System.out.println(b);
    }

    @Test
    public void contextLoads() {
        System.out.println(person);
    }
}
