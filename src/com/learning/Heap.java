package com.learning;

import java.lang.reflect.Array;

public abstract class Heap<T extends Comparable<T>> {
    public static void main(String[] args){
        Integer[] elements = {3, 5, 1, 8, 12, 6, 0, 4, 25, 18, 15};
        Heap<Integer> minheap = new MinimumHeap<>(Integer.class, elements);
        minheap.insert(2);
        System.out.print("Finished!");
    }

    private T[] heap;
    private int length;
    private int capacity;
    private final Class<T> classT;

    Heap(Class<T> classT, T[] array) {
        this.classT = classT;
        this.capacity = (int) Math.pow(2, (int) (Math.log10(array.length + 1) / Math.log10(2) + 1.5));
        @SuppressWarnings("unchecked") final T[] tmpHeap = (T[]) Array.newInstance(this.classT, this.capacity);
        System.arraycopy(array, 0, tmpHeap, 1, array.length);
        this.length = array.length;
        this.heap = tmpHeap;
        for (int i = this.length / 2; i > 0; i--)
            this.sink(i);
    }

    protected abstract boolean isPriority(T me, T other);

    protected void swap(int here, int there) {
        T tmp = heap[here];
        heap[here] = heap[there];
        heap[there] = tmp;
    }

    protected void swim(int end) {
        for (int i = end; i > 0; ) {
            if (isPriority(heap[i], heap[i / 2])) {
                swap(i, i / 2);
                i /= 2;
            } else
                break;
        }
    }

    protected void sink(int start) {
        for (int i = start; i <= length / 2; ) {
            if ((i * 2 + 1 <= length) && isPriority(heap[i * 2 + 1], heap[i * 2]) && isPriority(heap[i * 2 + 1], heap[i])) {
                swap(i, i * 2 + 1);
                i = i * 2 + 1;
            } else if (isPriority(heap[i * 2], heap[i])) {
                swap(i, i * 2);
                i = i * 2;
            } else
                break;
        }
    }

    public void insert(T element) {
        if (length + 1 >= capacity) {
            capacity *= 2;
            @SuppressWarnings("unchecked") final T[] tmpHeap = (T[]) Array.newInstance(classT, capacity);
            System.arraycopy(heap, 1, tmpHeap, 1, this.length);
            heap = tmpHeap;
        }
        heap[++length] = element;
        swim(length);
    }

    public T pop() throws Exception {
        if (length < capacity / 2) {
            capacity /= 2;
            @SuppressWarnings("unchecked") final T[] tmpHeap = (T[]) Array.newInstance(classT, capacity);
            System.arraycopy(heap, 1, tmpHeap, 1, this.length);
            heap = tmpHeap;
        }
        if (length < 1)
            throw new Exception("Accessing invalid heap element!");
        T root = heap[1];
        heap[1] = heap[length];
        heap[length--] = null;
        sink(1);
        return root;
    }
}