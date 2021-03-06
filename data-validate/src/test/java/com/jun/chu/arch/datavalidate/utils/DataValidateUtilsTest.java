package com.jun.chu.arch.datavalidate.utils;

import com.jun.chu.arch.datavalidate.bean.http.CefilresRequest;
import com.jun.chu.arch.datavalidate.constant.Constants;
import com.jun.chu.dataValidate.utils.DataValidateUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by chujun on 2017/4/28.
 */
public class DataValidateUtilsTest {
    @Test
    public void case01_validate_null() {
        CefilresRequest data = null;
        try {
            DataValidateUtils.validate(data);
            throw new RuntimeException("can't run here",null);
        }catch (Exception e){
            Assert.assertEquals(e.getMessage(),"[validatedObject] must not be null");
        }
    }

    @Test
    public void case01_validate() {
        CefilresRequest data = new CefilresRequest();
        try {
            DataValidateUtils.validate(data);
            throw new RuntimeException("can't run here",null);
        }catch (Exception e){
            Assert.assertEquals("数据格式不合法,net.sf.oval.ConstraintViolation: com.jun.chu.arch.datavalidate.bean.http.CefilresRequest.e3rdPayNo cannot be null",e.getMessage());
        }
    }

    @Test
    public void case01_validate_yyyymmddhhmmss_error() {
        CefilresRequest data = new CefilresRequest();
        data.setE3rdPayNo(Constants.e3rdPayNo);
        data.setTransTime("20160223 332233");
        try {
            DataValidateUtils.validate(data);
            throw new RuntimeException("can't run here",null);
        }catch (Exception e){
            Assert.assertEquals("数据格式不合法,net.sf.oval.ConstraintViolation: 日期格式不符合要求,YYYYMMDD HH:MM:SS",e.getMessage());
        }
    }

    @Test
    public void case01_validate_yyyymmddhhmmss() {
        CefilresRequest data = new CefilresRequest();
        data.setE3rdPayNo(Constants.e3rdPayNo);
        data.setTransTime("20160223 13:22:33");
        try {
            DataValidateUtils.validate(data);
        }catch (Exception e){
            Assert.assertEquals("数据格式不合法,net.sf.oval.ConstraintViolation: com.jun.chu.arch.datavalidate.bean.http.CefilresRequest.getHelloWorld() cannot be null",e.getMessage());
        }
    }

    @Test
    public void case02_validateAndGetErrorMessages_null() {
        CefilresRequest data = new CefilresRequest();
        List<String> errorMessage = DataValidateUtils.validateAndGetErrorMessages(data);
        System.out.println("errorMessage:" + errorMessage);
        Assert.assertTrue(errorMessage.size()>0);
        //errorMessage:[com.jun.chu.arch.datavalidate.bean.http.CefilresRequest.e3rdPayNo cannot be null, com.jun.chu.arch.datavalidate.bean.http.CefilresRequest.transTime cannot be null, com.jun.chu.arch.datavalidate.bean.http.CefilresRequest.getHelloWorld() cannot be null, com.jun.chu.arch.datavalidate.bean.http.CefilresRequest.getBankName() cannot be null]
    }

    @Test
    public void case03_validateAndGetErrorMessages_memberOf() {
        CefilresRequest data = new CefilresRequest();
        data.setE3rdPayNo("我是不合法的支付机构编号");
        List<String> errorMessage = DataValidateUtils.validateAndGetErrorMessages(data);
        System.out.println("errorMessage:" + errorMessage);
        Assert.assertTrue(errorMessage.contains("默认分配的编号不符合"));
    }


    /**
     * 验证getter方法返回值是否符合约束
     * 暂时没想到什么场景会要求设置getter方法约束
     * --->查看源代码可知ClassCheck.constrainedFields,只要是非静态方法,且没有方法参数的方法,添加@IsInvariant都会进行验证
     * --->这样的话,其实应该是有应用场景的,比如方法内部拼装后对象,验证是否为null
     */


    @Test
    public void case10_getter_method_isInvariant(){
        CefilresRequest data = new CefilresRequest();
        data.setE3rdPayNo(Constants.e3rdPayNo);
        data.setTransTime("20160223 13:22:33");
        List<String> errorMessage = DataValidateUtils.validateAndGetErrorMessages(data);
        System.out.println("errorMessage:" + errorMessage);
        //errorMessage:[com.jun.chu.arch.datavalidate.bean.http.CefilresRequest.getHelloWorld() cannot be null, com.jun.chu.arch.datavalidate.bean.http.CefilresRequest.getBankName() cannot be null]

    }


    @Test
    public void case10_getter_method_isInvariant_for_not_getter_method(){
        CefilresRequest data = new CefilresRequest();
        data.setE3rdPayNo(Constants.e3rdPayNo);
        data.setTransTime("20160223 13:22:33");
        data.setBankName("citi");
        List<String> errorMessage = DataValidateUtils.validateAndGetErrorMessages(data);
        System.out.println("errorMessage:" + errorMessage);
        //errorMessage:[com.jun.chu.arch.datavalidate.bean.http.CefilresRequest.getHelloWorld() cannot be null]

    }


    @Test
    public void case100_YYYYMMDDHHMMSSRegEp() {

        Pattern yYYYMMDDpattern = Pattern.compile("\\d{4}(0[1-9]|1[0-2])((0[1-9])|((1|2)[0-9])|30|31)");
        Assert.assertEquals(true, yYYYMMDDpattern.matcher("20160321").matches());

        Pattern hhmmssPattern = Pattern.compile("([0-1]\\d|2[0-3]):([0-5]\\d):([0-5]\\d)");
        Assert.assertEquals(true, hhmmssPattern.matcher("12:23:34").matches());

        //Pattern pattern = Pattern.compile("^\\d{4}(0[1-9]|1[0-2])((0[1-9])|((1|2)[0-9])|30|31) ([0-1]\\d|2[0-3]):([0-5]\\d):([0-5]\\d)$");
        Pattern pattern = Pattern.compile(DataValidateUtils.YYYYMMDDHHMMSSRegEp);
        Assert.assertEquals(true, pattern.matcher("20160321 23:00:00").matches());
        Assert.assertEquals(false, pattern.matcher("20161321 23:00:00").matches());
        Assert.assertEquals(false, pattern.matcher("20161232 23:00:00").matches());
        Assert.assertEquals(false, pattern.matcher("20161231 24:00:00").matches());
        Assert.assertEquals(false, pattern.matcher("20161231 23:60:00").matches());
        Assert.assertEquals(false, pattern.matcher("20161231 23:00:60").matches());
        Assert.assertEquals(false, pattern.matcher("20161231 23:00:601").matches());
    }

    @Test
    public void case100_YYYYYMMRegEp(){
        Pattern pattern = Pattern.compile(DataValidateUtils.YYYYMMRegEp);
        Assert.assertEquals(true,pattern.matcher("201701").matches());
        Assert.assertEquals(true,pattern.matcher("201712").matches());
        Assert.assertEquals(false,pattern.matcher("201700").matches());
        Assert.assertEquals(false,pattern.matcher("201713").matches());
        Assert.assertEquals(false,pattern.matcher("20170").matches());
        Assert.assertEquals(false,pattern.matcher("2017100").matches());
    }

    @Test
    public void case100_HHMMSSRegEp(){
        Pattern pattern = Pattern.compile(DataValidateUtils.HHMMSSRegEp);
        Assert.assertEquals(true,pattern.matcher("23:00:00").matches());
        Assert.assertEquals(true,pattern.matcher("00:00:00").matches());
        Assert.assertEquals(false,pattern.matcher("24:00:00").matches());
        Assert.assertEquals(false,pattern.matcher("25:00:00").matches());
        Assert.assertEquals(false,pattern.matcher("20170").matches());
        Assert.assertEquals(false,pattern.matcher("2017100").matches());
    }
}
