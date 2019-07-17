package com.jun.chu.arch.service.pattern.reponsibilitychain;

/**
 * @author chujun
 * @date 2019-07-17 09:47
 */
public interface SortReponsibilityChain<V extends Comparable<V>, K, T extends SortReponsibilityChainHandler<K, V>> extends ReponsibilityChain<K, T> {
    T getHandler(K k, V v);
}
