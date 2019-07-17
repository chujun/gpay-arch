package com.jun.chu.arch.service.pattern.reponsibilitychain;

/**
 * 责任链模式
 *
 * @author chujun
 * @date 2019-07-17 09:49
 */
public interface ReponsibilityChain<K, T extends ReponsibilityChainHandler<K>> {
    T getHandler(K var1);

    T getNextHandler(T var1);

    T register(T var1);
}
