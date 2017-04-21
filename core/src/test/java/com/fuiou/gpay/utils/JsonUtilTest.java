package com.fuiou.gpay.utils;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by chujun on 2017/4/21.
 */
public class JsonUtilTest {
    @Test
    public void case01(){
        ArrayList<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        System.out.println(JsonUtil.fromJsonList(JsonUtil.toJson(list),String.class));
    }
}
