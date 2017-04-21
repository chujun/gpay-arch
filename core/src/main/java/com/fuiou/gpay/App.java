package com.fuiou.gpay;

import com.fuiou.gpay.utils.JsonUtils;
import com.sun.tools.javac.util.List;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ArrayList<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        System.out.println(JsonUtils.fromJsonList(JsonUtils.toJson(list),String.class));
    }
}
