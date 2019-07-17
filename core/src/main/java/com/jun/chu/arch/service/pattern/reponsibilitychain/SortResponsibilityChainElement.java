package com.jun.chu.arch.service.pattern.reponsibilitychain;

/**
 * @author chujun
 * @date 2019-07-17 10:09
 */
public class SortResponsibilityChainElement<V extends Comparable<V>, T extends SortReponsibilityChainHandler<?, V>> implements Comparable<SortResponsibilityChainElement<V, T>> {
    private T handler;

    public SortResponsibilityChainElement(T handler) {
        this.handler = handler;
    }

    public T getHandler() {
        return this.handler;
    }

    public void setHandler(T handler) {
        this.handler = handler;
    }

    @Override
    public int compareTo(SortResponsibilityChainElement<V, T> o) {
        return this.handler.getOrder().compareTo(o.getHandler().getOrder());
    }
}
