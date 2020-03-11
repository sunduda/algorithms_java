package com.learning;

public class SortingMethods<T extends Comparable<T>> {
    public static void main(String[] args) {
        // Define constants
        final int ARRAY_SIZE = (int) Math.pow(2, 15);
        final int DISPLAY_MAX_SIZE = 1000;
        // Declare an array of random integers, an array for saving sorted arrays and SortingMethods object
        Integer[] intArray = new Integer[ARRAY_SIZE];
        Integer[] sortedIntArray;
        SortingMethods<Integer> objSorting;
        // Initialize the integer array
        for (int i = 0; i < intArray.length; i++)
            intArray[i] = (int) (Math.random() * ARRAY_SIZE * 10 + 1);
        printArray(intArray, DISPLAY_MAX_SIZE);
        // Instantiate the SortingMethods object
        objSorting = new SortingMethods<Integer>(intArray);
        System.out.print("\r\n");
        // Bubble sort
        sortedIntArray = objSorting.sortBubble();
        printArray(sortedIntArray, DISPLAY_MAX_SIZE);
        System.out.print("\r\n");
        // Select sort
        sortedIntArray = objSorting.sortSelect();
        printArray(sortedIntArray, DISPLAY_MAX_SIZE);
    }

    public static <T> void printArray(T[] array, final int sizeThrsh) {
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

    private final T[] objArray;
    public final long length;

    SortingMethods(T[] objArray) {
        this.objArray = objArray;
        this.length = this.objArray.length;
    }

    public T[] sortBubble() {
        double startTime = System.nanoTime();
        for (int i = objArray.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (objArray[j].compareTo(objArray[j + 1]) > 0) {
                    T tmp = objArray[j];
                    objArray[j] = objArray[j + 1];
                    objArray[j + 1] = tmp;
                }
            }
        }
        double endTime = System.nanoTime();
        System.out.printf("Elapsed time of bubble sorting is %.3f s!\r\n", (endTime - startTime) / 1e9);
        return objArray;
    }

    public T[] sortSelect() {
        double startTime = System.nanoTime();
        for (int i = 0; i < objArray.length; i++) {
            int minidx = i;
            for (int j = i + 1; j < objArray.length; j++)
                if (objArray[j].compareTo(objArray[i]) < 0)
                    minidx = j;
            T tmp = objArray[i];
            objArray[i] = objArray[minidx];
            objArray[minidx] = tmp;
        }
        double endTime = System.nanoTime();
        System.out.printf("Elapsed time of select sorting is %.3f s!\r\n", (endTime - startTime) / 1e9);
        return objArray;
    }
}
