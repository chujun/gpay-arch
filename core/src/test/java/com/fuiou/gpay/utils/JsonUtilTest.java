package com.fuiou.gpay.utils;

import com.fuiou.gpay.exception.BusinessException;
import com.fuiou.gpay.exception.SystemException;
import com.google.gson.Gson;
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
        //[1, 2]
    }

    @Test
    public void case01_toJson(){
        System.out.println(JsonUtil.toJson(new SystemException(),false));
        System.out.println(JsonUtil.toJson(new SystemException(), true));
        //{"stackTrace":[],"suppressedExceptions":[]}
        //{"errorCode":null,"errorMsg":null,"detailMessage":null,"stackTrace":[],"suppressedExceptions":[]}

    }
}
