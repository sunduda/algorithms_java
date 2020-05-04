package com.learning;

public class MaximumHeap<T extends Comparable<T>> extends Heap<T> {
    MaximumHeap(Class<T> classT, T[] array) {
        super(classT, array);
    }

    @Override
    protected boolean isPriority(T me, T other) {
        return me.compareTo(other) > 0;
    }
}
