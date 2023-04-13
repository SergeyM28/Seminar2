package org.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[]testArray = new int[1000000];
        for (int i = 0; i < testArray.length; i++){
            Random rd = new Random();
            testArray[i] = rd.nextInt(0, 1000);
        }

        long start = System.currentTimeMillis();
        quickSort(testArray, 0, testArray.length - 1);
        long finish = System.currentTimeMillis();

        System.out.println("quick: " + (finish - start));

        System.out.println(quickSearch(testArray, 5, 0, testArray.length - 1));

        int[]testArray2 = new int[1000000];
        for (int i = 0; i < testArray2.length; i++) {
            Random rd = new Random();
            testArray2[i] = rd.nextInt(0, 1000);
        }

        start = System.currentTimeMillis();
        pileSort(testArray2);
        finish = System.currentTimeMillis();

        System.out.println("pile: " + (finish - start));

//        for (int elem : testArray){
//            System.out.print(elem + " ");
//        }
    }
    public static void bubbleSort(int[] array){
        boolean flag = false;
        do{
            flag = false;
            for(int i = 0; i < array.length - 1; i++){
                if (array[i] > array[i + 1]){
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    flag = true;
                }
            }
        }while (flag);
    }
    public static void quickSort(int[]array, int startIndex, int endIndex){
        int leftMark = startIndex;
        int rightMark = endIndex;
        int pivot = array[(leftMark + rightMark) / 2];
        do {
            while (array[leftMark] < pivot) {
                leftMark++;
            }
            while (array[rightMark] > pivot) {
                rightMark--;
            }
            if (leftMark <= rightMark) {

                    int temp = array[leftMark];
                    array[leftMark] = array[rightMark];
                    array[rightMark] = temp;

                leftMark++;
                rightMark--;
            }
        }while (leftMark <= rightMark);

        if (leftMark < endIndex){
            quickSort(array, leftMark, endIndex);
        }
        if(startIndex < rightMark) {
            quickSort(array, startIndex, rightMark);
        }
    }

    public static Integer quickSearch(int[]array, int value, int startIndex, int endIndex){

        if (endIndex < startIndex){
            return -1;
        }
        int midpoint = (endIndex + startIndex) / 2;
        if (array[midpoint] < value){
            return quickSearch(array, value, midpoint + 1, endIndex);
        } else if (array[midpoint] > value) {
            return quickSearch(array, value, startIndex, (midpoint - 1));
        }
        else {
            return midpoint;
        }
    }

    public static void pileSort(int[]array) {

        for (int i = array.length / 2 - 1; i >= 0; i--) {
           heapify(array, array.length, i);
        }
        for (int i = array.length - 1; i >= 0; i--){
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapify(array, i, 0);
        }
    }

    static void heapify(int[]array, int size, int rootIndex){
        int largest = rootIndex;
        int leftChild = rootIndex * 2 + 1;
        int rightChild = rootIndex * 2 + 2;

        if (leftChild < size && array[largest] < array[leftChild]){
            largest = leftChild;
        }
        if (rightChild < size && array[largest] < array[rightChild]){
            largest = rightChild;
        }
        if (rootIndex != largest){
            int temp = array[rootIndex];
            array[rootIndex] = array[largest];
            array[largest] = temp;

            heapify(array, size, largest);
        }
    }
}