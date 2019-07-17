package com.jun.chu.arch.service.pattern.reponsibilitychain;

import com.jun.chu.arch.service.pattern.data.PriorityList;
import com.jun.chu.arch.service.pattern.data.PriorityListNode;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chujun
 * @date 2019-07-17 10:04
 */
@NoArgsConstructor
public class SortReponsibilityChainImpl<V extends Comparable<V>, K, T extends SortReponsibilityChainHandler<K, V>> implements SortReponsibilityChain<V, K, T> {
    private Map<K, PriorityList<SortResponsibilityChainElement<V, T>>> handlerMap = new HashMap();

    @Override
    public T getHandler(K k, V order) {
        PriorityList<SortResponsibilityChainElement<V, T>> list = this.handlerMap.get(k);
        if (list != null) {
            for(PriorityListNode node = list.getFirst(); node != null; node = node.getNext()) {
                if (order == null || ((SortResponsibilityChainElement)node.getElement()).getHandler().getOrder().compareTo(order) == 0) {
                    return ((SortResponsibilityChainElement)node.getElement()).getHandler();
                }
            }
        }

        return null;
    }

    @Override
    public T getHandler(K k) {
        return this.getHandler(k, null);
    }

    @Override
    public T getNextHandler(T t) {
        PriorityList<SortResponsibilityChainElement<V, T>> list = (PriorityList)this.handlerMap.get(t.getKey());
        if (list != null) {
            for(PriorityListNode node = list.getFirst(); node != null; node = node.getNext()) {
                if (((SortResponsibilityChainElement)node.getElement()).getHandler().getOrder().equals(t.getOrder())) {
                    if (node.getNext() != null) {
                        return ((SortResponsibilityChainElement)node.getNext().getElement()).getHandler();
                    }

                    return null;
                }
            }
        }

        return null;
    }

    @Override
    public T register(T t) {
        if (!this.handlerMap.containsKey(t.getKey())) {
            PriorityListNode<SortResponsibilityChainElement<V, T>> node = new PriorityListNode(new SortResponsibilityChainElement(t));
            this.handlerMap.put(t.getKey(), new PriorityList(node));
            return t;
        } else {
            PriorityList<SortResponsibilityChainElement<V, T>> list = (PriorityList)this.handlerMap.get(t.getKey());
            list.append(new SortResponsibilityChainElement(t));
            return t;
        }
    }
}
