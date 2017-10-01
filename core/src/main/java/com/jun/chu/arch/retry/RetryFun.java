package com.jun.chu.arch.retry;

/**
 * Created by chujun on 2017/8/24.
 */
@FunctionalInterface
public interface RetryFun<T> {
    T execute();
}
