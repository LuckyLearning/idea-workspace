package com.lif.strategy.quackbehavior;

/**
 * @Author: lifan
 * @Date: 2020/3/2 11:24
 */
public class NoQuackBehavior implements QuackBehavior {
    public void quack() {
        System.out.println("_noquack_");
    }
}
