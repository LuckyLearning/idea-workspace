package com.lif.learnjdk.learnString;

import org.springframework.util.Assert;

public class String_Add {

    /**
     * 创建了一个新的String对象
     * @param str
     * @return
     */
    private static String upcase(String str) {
        if(str != null) {
            return str.toUpperCase();
        }
        return null;
    }

    public static void main(String[] args) {
        String hello = "hello";
        String upcase = upcase(hello);
        System.out.println(hello);
        System.out.println(upcase);
    }
}
