package com.google.guava;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

/**
 * Created by chujun on 2017/5/9.
 */
public class SplitterTest {
    @Test
    public void case00(){
        Iterable<String> split = Splitter.on(',').trimResults().omitEmptyStrings().split("foo,bar,,   qux");
        List<String> result = Lists.newArrayList(split);
        System.out.println(result.size()+","+result);
    }

    @Test
    public void case01_() {
        String content = "1|2|3|4|||";
        Iterable<String> it = Splitter.on("|").split(content);
        Iterator<String> iterator = it.iterator();
        String temp="";
        int i =0;
        while(iterator.hasNext()){
            temp+=iterator.next();
            i++;
        }
        System.out.println(i+","+temp);

        List<String> result = Lists.newArrayList(it);
        System.out.println(result.size()+","+result);
    }
}
