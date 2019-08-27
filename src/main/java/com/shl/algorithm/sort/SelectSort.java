package com.shl.algorithm.sort;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 选择排序
 *
 * //最快的排序方式：希尔排序
 *
 * @author songhengliang
 * @date 2019/8/27
 */
public class SelectSort {


    public static void main(String[] args) {
        Integer[] arr = new Integer[]{5, 1, 6, 4, 2, 8, 9};

        //排序前的顺序
        System.out.println(Arrays.asList(arr));

        selectSort(arr);

        //排序后的顺序
        System.out.println(Arrays.asList(arr));
    }

    public static void selectSort(Integer[] arr) {
        for (int x = 0; x < arr.length - 1; x++) {
            for (int y = x + 1; y < arr.length; y++) {
                if (arr[x] > arr[y]) {
                    int temp = arr[x];
                    arr[x] = arr[y];
                    arr[y] = temp;
                }
            }
        }
    }

}
