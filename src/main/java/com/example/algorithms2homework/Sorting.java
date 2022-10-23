package com.example.algorithms2homework;

import java.util.Random;
import java.util.function.Consumer;

public class Sorting {

    private static final Random RANDOM = new Random();


    public static void main(String[] args) {
        double timeForBubleSoft = measureTime(10,Sorting::sortBubble);
        System.out.printf("Среднее время сортировки пузыртковым методом %2f%n", timeForBubleSoft);
        double timeForSelektionSoft = measureTime(10,Sorting::sortSelektion);
        System.out.printf("Среднее время сортировки выбором %2f%n", timeForSelektionSoft);
        double timeForInsertioSoft = measureTime(10,Sorting::sortInsertion);
        System.out.printf("Среднее время сортировки вставками %2f%n", timeForInsertioSoft);
    }

    private static  double measureTime(int iteration, Consumer<int[]>sort){ //метод замеряет время   (лямбда которая принемает но не возвращает, для сортировки)Ы
        long times = 0;
        for (int i = 0; i < iteration; i++) {
            int[] array =genereteArray(100_000);
            long start = System.currentTimeMillis(); //вызов сортировки
            sort.accept(array);
            times = times + (System.currentTimeMillis() - start);

        }
        return times/(double) iteration;
    }

    private static int[] genereteArray(int size){
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = RANDOM.nextInt();
        }
        return array;
    }
    private  static void  sortBubble(int[] arr){ // пузырьковая
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length -1 - i; j++) {
              if (arr[j] > arr[j+1]) {
                  swapElements(arr, j, j + 1);
              }
            }
        }
    }
    private static void  swapElements(int[] arr, int indexA, int indexB){
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    private static void  sortSelektion(int[] arr){         //выбором
        for (int i = 0; i < arr.length; i++) {
            int minElementIndex = 1;
            for (int j = i + 1; j < arr.length ; j++) {
                if (arr[j] < arr[minElementIndex]){
                    minElementIndex = j;
                }

            }
            swapElements(arr, i, minElementIndex);
        }
    }
    private static void sortInsertion(int[] arr){               // вставками
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            int j=i;
            while (j>0 && arr[j-1]>= temp){
                arr[j]= arr[j -1];
                j--;
            }
            arr[j] = temp;
        }
    }
}
