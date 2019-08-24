package com.lif.quick.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 测试使用@Value
 *
 * @ClassName OldPerson
 * @Author lifan
 * @Date 2019/8/21 22:40
 **/
@Component
public class OldPerson {

    @Value("${person.last-name}")
    private String lastName;
    @Value("#{10*2}")
    private int age;
    @Value("true")
    private Boolean boss;
    @Value("${person.birthday}")
    private Date birthday;

    @Override
    public String toString() {
        return "OldPerson{" +
                "lastName='" + lastName + '\'' +
                ", age=" + age +
                ", boss=" + boss +
                ", birthday=" + birthday +
                '}';
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Boolean getBoss() {
        return boss;
    }

    public void setBoss(Boolean boss) {
        this.boss = boss;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
