package com.lif.strategy.flybehavior;

/**
 * @Author: lifan
 * @Date: 2020/3/2 11:21
 */
public class NoFlyBehavior implements FlyBehavior {
    public void fly() {
        System.out.println("--nofly--");
    }
}
