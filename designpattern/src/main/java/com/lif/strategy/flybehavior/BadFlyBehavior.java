package com.lif.strategy.flybehavior;

/**
 * @Author: lifan
 * @Date: 2020/3/2 11:20
 */
public class BadFlyBehavior implements FlyBehavior {

    public void fly() {
        System.out.println("--badfly--");
    }
}
