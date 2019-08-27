package com.shl.algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author songhengliang
 * @date 2019/8/27
 */
public class BubbleSort {

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{5, 1, 6, 4, 2, 8, 9};

        //排序前的顺序
        System.out.println(Arrays.asList(arr));

        bubbleSort(arr);

        //排序后的顺序
        System.out.println(Arrays.asList(arr));
    }

    //冒泡排序
    public static void bubbleSort(Integer[] arr) {
        for (int x = 0; x < arr.length - 1; x++) {

            for (int y = 0; y < arr.length - x - 1; y++) {
                if (arr[y] > arr[y + 1]) {
                    int temp = arr[y];
                    arr[y] = arr[y + 1];
                    arr[y + 1] = temp;
                }
            }
        }
    }


}
