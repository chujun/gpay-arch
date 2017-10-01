package com.jun.chu.arch.retry;

import org.junit.Test;

/**
 * Created by chujun on 2017/10/1.
 */
public class RetryUtilsTest {
    @Test
    public void case01(){
        String result = RetryUtils.retry(() -> call("cj"), this.getClass(), "call");
        System.out.println(result);
    }

    private String call(String name){
        System.out.println(name);
        return "cj"+name;
    }
}
