package org.springframework.mytest.test;

import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mytest.bean.MyTestBean;

/**
 * Created by chujun on 2017/5/19.
 */
public class BeanFactoryTest {
    @Test
    public void case01_testSimpleLoad(){
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("spring/application-test.xml"));
        MyTestBean bean =(MyTestBean) beanFactory.getBean("myTestBean");
        Assert.assertEquals("testStr",bean.getTestStr());
    }
}
