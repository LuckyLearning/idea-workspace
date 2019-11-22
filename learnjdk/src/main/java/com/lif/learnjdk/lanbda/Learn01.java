package com.lif.learnjdk.lanbda;

/**
 * @Author: lifan
 * @Date: 2019/11/6 20:57
 */
public class Learn01 {

    private void thread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello Thread!");
            }
        });

        new Thread(() -> System.out.println("hello Thread!"));
    }
}
