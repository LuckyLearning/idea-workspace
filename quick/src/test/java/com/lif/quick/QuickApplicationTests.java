package com.lif.quick;

import com.lif.quick.bean.OldPerson;
import com.lif.quick.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * springboot 单元测试
 *
 * @Author: lifan
 * @Date: 2019/8/21 0:11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuickApplicationTests {

    @Autowired
    Person person;

    @Autowired
    OldPerson oldPerson;

    /**
     * 测试@ConfigurationProperties
     *
     * @author: lifan
     * @return: void
     * @exception:
     * @date: 2019/8/21 22:37
     */
    @Test
    public void contextLoads() {
        System.out.println(person);
    }


    /**
     * 测试@Value
     *
     * @author: lifan
     * @return: void
     * @exception:
     * @date: 2019/8/21 22:47
     */
    @Test
    public void testOldPerson() {
        System.out.println(oldPerson);
    }


}
