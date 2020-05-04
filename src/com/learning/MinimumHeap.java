package com.learning;

public class MinimumHeap<T extends Comparable<T>> extends Heap<T> {
    MinimumHeap(Class<T> classT, T[] array) {
        super(classT, array);
    }

    @Override
    protected boolean isPriority(T me, T other) {
        return me.compareTo(other) < 0;
    }
}
