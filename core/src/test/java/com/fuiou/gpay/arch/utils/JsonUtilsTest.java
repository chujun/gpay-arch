package com.fuiou.gpay.arch.utils;

import com.fuiou.gpay.arch.exception.SystemException;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by chujun on 2017/4/21.
 */
public class JsonUtilsTest {
    @Test
    public void case01(){
        ArrayList<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        System.out.println(JsonUtils.fromJsonList(JsonUtils.toJson(list),String.class));
        //[1, 2]
    }

    @Test
    public void case01_toJson(){
        System.out.println(JsonUtils.toJson(new SystemException(),false));
        System.out.println(JsonUtils.toJson(new SystemException(), true));
        //{"stackTrace":[],"suppressedExceptions":[]}
        //{"errorCode":null,"errorMsg":null,"detailMessage":null,"stackTrace":[],"suppressedExceptions":[]}

    }
}
