package com.lif.spring.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: lifan
 * @Date: 2020/2/10 14:09
 */

@ToString
@Getter
@Data
@Setter
public class Person {

    private String lastName;
    private Integer age;

    public Person(String lastName, int age) {
        super();
        this.lastName = lastName;
        this.age = age;
    }
}
