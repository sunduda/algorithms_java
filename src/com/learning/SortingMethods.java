package com.learning;

import java.lang.reflect.Array;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SortingMethods<T extends Comparable<T>> {
    public static void main(String[] args) throws Exception {
        // Define constants
        final int ARRAY_SIZE = (int) Math.pow(2, 14);
        final int DISPLAY_MAX_SIZE = 32;
        // Define time variables
        double starttime, endtime;
        // Declare an array of random integers, an array for saving sorted arrays and SortingMethods object
        Integer[] intArray = new Integer[ARRAY_SIZE];
        Integer[] sortedIntArray;
        SortingMethods<Integer> objSorting;
        // Initialize the integer array
        for (int i = 0; i < intArray.length; i++)
            intArray[i] = (int) (Math.random() * ARRAY_SIZE * 10 + 1);
        printArray(intArray, DISPLAY_MAX_SIZE);
        // Instantiate the SortingMethods object
        objSorting = new SortingMethods<>(Integer.class, intArray);
        System.out.print("\r\n");
        // Bubble sort
        starttime = System.nanoTime();
        sortedIntArray = objSorting.sortBubble();
        endtime = System.nanoTime();
        printArray(sortedIntArray, DISPLAY_MAX_SIZE);
        System.out.print(String.format("The time consumption of bubble sorting is: %f ms.\r\n", (endtime - starttime) / 1e6));
        // Select sort
        starttime = System.nanoTime();
        sortedIntArray = objSorting.sortSelection();
        endtime = System.nanoTime();
        printArray(sortedIntArray, DISPLAY_MAX_SIZE);
        System.out.print(String.format("The time consumption of bubble sorting is: %f ms.\r\n", (endtime - starttime) / 1e6));
        // Insert sort
        starttime = System.nanoTime();
        sortedIntArray = objSorting.sortInsertion();
        endtime = System.nanoTime();
        printArray(sortedIntArray, DISPLAY_MAX_SIZE);
        System.out.print(String.format("The time consumption of insert sorting is: %f ms.\r\n", (endtime - starttime) / 1e6));
        // Shell sort
        starttime = System.nanoTime();
        sortedIntArray = objSorting.sortShell();
        endtime = System.nanoTime();
        printArray(sortedIntArray, DISPLAY_MAX_SIZE);
        System.out.print(String.format("The time consumption of shell sorting is: %f ms.\r\n", (endtime - starttime) / 1e6));
        // Top-Down merge sort
        starttime = System.nanoTime();
        sortedIntArray = objSorting.sortMerge("Top-Down");
        endtime = System.nanoTime();
        printArray(sortedIntArray, DISPLAY_MAX_SIZE);
        System.out.print(String.format("The time consumption of top-down merge sorting is: %f ms.\r\n", (endtime - starttime) / 1e6));
        // Bottom-Up merge sort
        starttime = System.nanoTime();
        sortedIntArray = objSorting.sortMerge("Bottom-Up");
        endtime = System.nanoTime();
        printArray(sortedIntArray, DISPLAY_MAX_SIZE);
        System.out.print(String.format("The time consumption of bottom-up merge sorting is: %f ms.\r\n", (endtime - starttime) / 1e6));
        // Quick sort
        starttime = System.nanoTime();
        sortedIntArray = objSorting.sortQuick();
        endtime = System.nanoTime();
        printArray(sortedIntArray, DISPLAY_MAX_SIZE);
        System.out.print(String.format("The time consumption of quick sorting is: %f ms.\r\n", (endtime - starttime) / 1e6));
        // Heap sort
        starttime = System.nanoTime();
        sortedIntArray = objSorting.sortHeap();
        endtime = System.nanoTime();
        printArray(sortedIntArray, DISPLAY_MAX_SIZE);
        System.out.print(String.format("The time consumption of heap sorting is: %f ms.\r\n", (endtime - starttime) / 1e6));
    }

    public static <T> void printArray(final T[] array, final int sizeThrsh) {
        if (array.length > sizeThrsh)
            return;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1)
                System.out.print(", ");
            else
                System.out.print("\r\n");
        }
    }

    public static <T> void swapElements(final T[] a, final int i, final int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private final T[] originalArray;
    public final int length;
    private final Class<T> classT;

    SortingMethods(Class<T> classT, T[] originalArray) {
        this.originalArray = originalArray;
        this.length = this.originalArray.length;
        this.classT = classT;
    }

    public void shuffleArray(T[] array) {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = array.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            T a = array[index];
            array[index] = array[i];
            array[i] = a;
        }
    }

    public T[] sortBubble() {
        @SuppressWarnings("unchecked") final T[] sortedArray = (T[]) Array.newInstance(classT, length);
        if (length >= 0) System.arraycopy(originalArray, 0, sortedArray, 0, length);
        for (int i = length - 1; i > 0; i--)
            for (int j = 0; j < i; j++)
                if (sortedArray[j].compareTo(sortedArray[j + 1]) > 0)
                    swapElements(sortedArray, j, j + 1);
        return sortedArray;
    }

    public T[] sortSelection() {
        @SuppressWarnings("unchecked") final T[] sortedArray = (T[]) Array.newInstance(classT, length);
        if (length >= 0) System.arraycopy(originalArray, 0, sortedArray, 0, length);
        for (int i = 0; i < length; i++) {
            int minidx = i;
            for (int j = i + 1; j < length; j++)
                if (sortedArray[j].compareTo(sortedArray[minidx]) < 0)
                    minidx = j;
            swapElements(sortedArray, i, minidx);
        }
        return sortedArray;
    }

    public T[] sortInsertion() {
        @SuppressWarnings("unchecked") final T[] sortedArray = (T[]) Array.newInstance(classT, length);
        if (length >= 0) System.arraycopy(originalArray, 0, sortedArray, 0, length);
        for (int i = 1; i < length; i++)
            for (int j = i; (j - 1 >= 0) && (sortedArray[j].compareTo(sortedArray[j - 1]) < 0); j--)
                swapElements(sortedArray, j, j - 1);
        return sortedArray;
    }

    public T[] sortShell() {
        @SuppressWarnings("unchecked") final T[] sortedArray = (T[]) Array.newInstance(classT, length);
        if (length >= 0) System.arraycopy(originalArray, 0, sortedArray, 0, length);
        sortShell(sortedArray, 0, sortedArray.length - 1);
        return sortedArray;
    }

    protected void sortShell(final T[] array, int start, int end) {
        for (int h = (int) (Math.pow(2, Math.floor(Math.log(end - start + 1) / Math.log(2))) - 1); h > 0; h /= 2) {
            for (int i = h; i < end - start + 1; i++) {
                for (int j = i + start; j - h >= start; j -= h) {
                    if (array[j].compareTo(array[j - h]) < 0)
                        swapElements(array, j, j - h);
                    else
                        break;
                }
            }
        }
    }

    public T[] sortMerge(String method) throws Exception {
        @SuppressWarnings("unchecked") final T[] sortedArray = (T[]) Array.newInstance(classT, length);
        if (length >= 0) System.arraycopy(originalArray, 0, sortedArray, 0, length);
        @SuppressWarnings("unchecked") final T[] auxArray = (T[]) Array.newInstance(classT, length);
        if (length >= 0) System.arraycopy(sortedArray, 0, auxArray, 0, length);
        if (method.equals("Top-Down"))
            sortMerge(sortedArray, 0, sortedArray.length - 1, auxArray);
        else if (method.equals("Bottom-Up"))
            sortMerge(sortedArray, auxArray);
        else
            throw new Exception("Incorrect method call for merge sorting!");
        return sortedArray;
    }

    protected void sortMerge(T[] array, int lo, int hi, T[] aux) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        sortMerge(array, lo, mid, aux);
        sortMerge(array, mid + 1, hi, aux);
        merge(array, lo, mid, hi, aux);
    }

    protected void sortMerge(T[] array, T[] aux) {
        for (int size = 2; size / 2 < array.length; size += size) {
            for (int lo = 0, hi = size - 1; lo < length - size / 2; lo = hi + 1, hi = lo + size - 1) {
                merge(array, lo, lo + size / 2 - 1, Math.min(hi, length - 1), aux);
            }
        }

    }

    protected void merge(T[] array, int lo, int mid, int hi, T[] aux) {
        if (hi + 1 - lo >= 0) System.arraycopy(array, lo, aux, lo, hi + 1 - lo);
        for (int i = lo, i0 = lo, i1 = mid + 1; i < hi + 1; i++) {
            if (i0 > mid) {
                System.arraycopy(aux, i1, array, i, hi + 1 - i1);
                break;
            } else if (i1 > hi) {
                System.arraycopy(aux, i0, array, i, mid + 1 - i0);
                break;
            } else if (aux[i0].compareTo(aux[i1]) <= 0) array[i] = aux[i0++];
            else array[i] = aux[i1++];
        }
    }

    public T[] sortQuick() {
        @SuppressWarnings("unchecked") final T[] sortedArray = (T[]) Array.newInstance(classT, length);
        if (length >= 0) System.arraycopy(originalArray, 0, sortedArray, 0, length);
        shuffleArray(sortedArray);
        sortQuick(sortedArray, 0, sortedArray.length - 1);
        return sortedArray;
    }

    protected void sortQuick(T[] array, int start, int end) {
        if (end <= start)
            return;
//        if (end - start < 5)
//            sortShell(array, start, end);
        int lptr = start;
        int rptr = end;
        while (lptr < rptr) {
            while (lptr < rptr && array[rptr].compareTo(array[start]) >= 0)
                rptr--;
            while (lptr < rptr && array[lptr].compareTo(array[start]) <= 0)
                lptr++;
            if (lptr < rptr) {
                T tmp = array[lptr];
                array[lptr] = array[rptr];
                array[rptr] = tmp;
            }
        }
        T tmp = array[start];
        array[start] = array[lptr];
        array[lptr] = tmp;
        sortQuick(array, start, lptr - 1);
        sortQuick(array, lptr + 1, end);
    }

    protected T[] sortHeap() throws Exception {
        @SuppressWarnings("unchecked") final T[] sortedArray = (T[]) Array.newInstance(classT, length);
        if (length >= 0) System.arraycopy(originalArray, 0, sortedArray, 0, length);
        final Heap<T> miniheap = new MinimumHeap<>(classT, sortedArray);
        for (int i = 0; i < sortedArray.length; i++)
            sortedArray[i] = miniheap.pop();
        return sortedArray;
    }
}
