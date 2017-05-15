package com.fuiou.gpay.arch.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * 通过PropertyPlaceholderConfigurer
 */
public class ConfigUtils extends PropertyPlaceholderConfigurer {
    final static Logger LOGGER = Logger.getLogger(ConfigUtils.class);

    /**
     * 默认分割符
     */
    private static final String decollator = ";";

    private static final Map<String, Object> propertiesMap = new HashMap<String, Object>();

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        for (Object key : props.keySet()) {
            String keyStr = key.toString().trim();
            propertiesMap.put(keyStr, props.getProperty(keyStr).trim());
        }
        LOGGER.debug("total loaded properties " + propertiesMap.size() + ",");
    }

    public static String getString(String key) {
        return propertiesMap.get(key).toString();
    }

    /**
     * 从配置文件中读取属性值,转化为String类型的list列表
     * @param key
     * @return
     */
    public static List<String> getStringList(String key){
        String value = propertiesMap.get(key).toString();
        return Arrays.asList(value.split(decollator));
    }

    public static Integer getInt(String key) {
        return Integer.valueOf(getString(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.valueOf(getString(key));
    }

    /**
     * 支持绝对路径和classpath路径配置
     * 例如:
     * classpath:cer/file/citi/test/CNCBolpserver.cer
     *
     * /Users/chujun/Documents/ideaspace/kjpay-zxbank/src/main/resources/cer/file/citi/test/CNCBolpserver.cer
     * @param key
     * @return
     */
    public static File getFile(String key){
        try {
            LOGGER.info("开始加载资源文件:key="+key);
            return ResourceUtils.getFile(getString(key));
        } catch (FileNotFoundException e) {
            LOGGER.error("文件未发现异常,key="+key,e);
            throw new RuntimeException(e);
        }
    }

    public static String getFileContent(String key){
        return FileUtils.readConfig4file(getFile(key));
    }
}
