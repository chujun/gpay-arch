package com.fuiou.gpay.arch.utils;


import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by chujun on 2017/3/7.
 */
public class FileUtils {
    final static Logger LOGGER = Logger.getLogger(FileUtils.class);

    /**
     * @param file
     * @return 如果读取文件配置失败, 则抛出异常
     */
    public static String readConfig4file(File file) {
        try {
            return new String(read4file(file.getAbsolutePath()));
        } catch (Exception e) {
            LOGGER.error("读取文件配置失败,file=" + file.getAbsolutePath(), e);
            throw new RuntimeException("读取文件配置失败,file=" + file.getAbsolutePath(), e);
        }
    }

    public static byte[] read4file(String filename) throws Exception {
        FileInputStream fis = null;
        ByteArrayOutputStream baos = null;

        try {
            fis = new FileInputStream(filename);
            baos = new ByteArrayOutputStream();
            byte[] bytes = new byte[1028];
            boolean n = false;

            int n1;
            while ((n1 = fis.read(bytes)) > 0) {
                baos.write(bytes, 0, n1);
            }
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }

                if (baos != null) {
                    baos.close();
                }
            } catch (Exception e) {
                LOGGER.error("文件流关闭异常,", e);
            }

        }

        return baos.toByteArray();
    }

    /**
     * 保存byte数组到指定路径的文件中
     *
     * @param bytes
     * @param path
     * @throws Exception
     */
    public static void save2file(byte[] bytes, String path) throws Exception {
        //1.检查父类目录是否存在,不存在则创建之。
        checkOrCreateDirectory(path);
        //2.将内容写入到文件当中去
        saveToFile(bytes, path);
    }

    private static void saveToFile(byte[] bytes, String filename) throws Exception {
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(filename);
            fos.write(bytes);
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                LOGGER.error("文件流关闭异常,", e);
            }

        }

    }

    /**
     * 检查指定文件路径，目录是否创建。
     * 若父类目录没有创建就创建。
     *
     * @param originFilePath
     */
    private static void checkOrCreateDirectory(String originFilePath) {
        File tmp = new File(originFilePath);
        File parentFile = tmp.getParentFile();
        if (!parentFile.exists()) {
            LOGGER.info("目录不存在:" + parentFile.getAbsolutePath());
            parentFile.mkdirs();
        }
    }
}
