package com.jun.chu.arch.service.pattern.data;

/**
 * @author chujun
 * @date 2019-07-17 10:12
 */
public class PriorityListNode<T extends Comparable<T>> {
    private T element;
    private PriorityListNode<T> pre;
    private PriorityListNode<T> next;

    public PriorityListNode(T element) {
        this.element = element;
    }

    public T getElement() {
        return this.element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public PriorityListNode<T> getPre() {
        return this.pre;
    }

    public void setPre(PriorityListNode<T> pre) {
        this.pre = pre;
    }

    public PriorityListNode<T> getNext() {
        return this.next;
    }

    public void setNext(PriorityListNode<T> next) {
        this.next = next;
    }
}
