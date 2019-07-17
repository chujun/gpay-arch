package com.jun.chu.arch.service.pattern.data;

/**
 * @author chujun
 * @date 2019-07-17 10:11
 */
public class PriorityList<T extends Comparable<T>> {
    private PriorityListNode<T> head;

    public PriorityList() {
    }

    public PriorityList(PriorityListNode<T> head) {
        this.head = head;
    }

    public void setFirst(PriorityListNode<T> first) {
        this.head = first;
    }

    public PriorityListNode<T> getFirst() {
        return this.head;
    }

    public PriorityListNode<T> append(T element) {
        if (this.head == null) {
            this.head = new PriorityListNode(element);
            return this.head;
        } else {
            PriorityListNode<T> currentElement = this.head;

            PriorityListNode appendElement;
            for (appendElement = new PriorityListNode(element); currentElement.getNext() != null || currentElement.getElement().compareTo(element) > 0; currentElement = currentElement.getNext()) {
                if (currentElement.getElement().compareTo(element) > 0) {
                    appendElement.setPre(currentElement.getPre());
                    appendElement.setNext(currentElement);
                    if (currentElement.getPre() != null) {
                        currentElement.getPre().setNext(appendElement);
                    } else {
                        this.head = appendElement;
                    }

                    currentElement.setPre(appendElement);
                    return appendElement;
                }
            }

            currentElement.setNext(appendElement);
            appendElement.setPre(currentElement);
            return appendElement;
        }
    }
}
