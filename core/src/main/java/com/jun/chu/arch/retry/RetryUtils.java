package com.jun.chu.arch.retry;

import com.jun.chu.arch.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by chujun on 2017/8/24.
 */
@Slf4j
public class RetryUtils {
    private static final int DEFAULT_RETRY_COUNT = 3;

    public RetryUtils() {
    }

    public static <T> T retry(RetryFun<T> fun, Class<?> clazz, String methodName) {
        return retry(fun, clazz, methodName, DEFAULT_RETRY_COUNT);
    }

    public static <T> T retry(RetryFun<T> fun, Class<?> clazz, String methodName, int count) {
        return retry(fun, clazz, methodName, count, true);
    }

    public static <T> T retry(RetryFun<T> fun, Class<?> clazz, String methodName, int count, boolean recordTimeMetric) {
        String logStr = clazz.getCanonicalName() + methodName;
        int i = 1;

        while (true) {
            long start = System.currentTimeMillis();

            try {
                T t = fun.execute();
                if (recordTimeMetric) {
                    log.info("第{}次执行任务{}总共耗时time={}ms,", i, logStr, (System.currentTimeMillis() - start));
                }
                return t;
            } catch (Exception e) {
                if (e instanceof BusinessException) {
                    log.error("第【{}】次执行【{}】任务时【业务异常】msg={}不需重试", i, logStr, e.getMessage());
                    throw e;
                }

                if (i >= count) {
                    log.error("第【{}】次执行【{}】任务时【其它异常】不再重试", i, logStr, e);
                    throw e;
                }

                log.error("第{}次执行{}任务时【其它异常】继续重试", i, logStr, e);
                if (i++ >= count) {
                    log.error("执行{}任务时出现严重错误！！！", logStr);
                    throw e;
                }
                try {
                    //休眠20ms后再重试,而非立刻重试
                    Thread.sleep(20);
                } catch (InterruptedException e1) {
                    //异常可忽略
                    log.warn("线程休眠失败,可忽略.msg={}", e.getMessage());
                }
            }
        }
    }
}
