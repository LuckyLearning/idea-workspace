package com.lif.quick.bean;

/**
 * dog实体类
 *
 * @Author: lifan
 * @Date: 2019/8/21 22:39
 */
public class Dog {
    private String name;
    private String color;

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
