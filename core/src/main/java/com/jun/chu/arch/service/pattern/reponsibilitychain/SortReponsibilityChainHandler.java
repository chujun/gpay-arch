package com.jun.chu.arch.service.pattern.reponsibilitychain;

/**
 * @author chujun
 * @date 2019-07-17 09:40
 */
public interface SortReponsibilityChainHandler<K, T extends Comparable<T>> extends ReponsibilityChainHandler<K> {
    T getOrder();
}
