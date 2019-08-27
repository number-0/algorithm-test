package com.shl.algorithm.search;

import java.util.Arrays;

/**
 * 折半查找（二分查找）
 *
 * @author songhengliang
 * @date 2019/8/27
 */
public class HalfSearch {
    //顺序查找：获取key第一次在数组中出现的角标；如果返回-1，就说明key在数组中不存在

    //必须先知道折半查找的过程
    //折半查找，必须要保证数组是有序的数组

    //折半查找：方法一
    public static int halfSearch(int[] arr, int key) {
        int min = 0, max = arr.length - 1, mid;

        while (true) {
            mid = (max + min) / 2;

            if (arr[mid] == key) {
                return mid;
            } else if (key > arr[mid]) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }

            if (min > max) {
                return -1;
            }
        }
    }

    //折半查找：方法二
    public static int halfSearch_2(int[] arr, int key) {
        int min = 0, max = arr.length - 1, mid;
        while (min <= max) {
            //mid=(min+max)/2;
            mid = (min + max) >> 1;
            if (key == arr[mid]) {
                return mid;
            } else if (key > arr[mid]) {
                min = mid + 1;
            } else if (key < arr[mid]) {
                max = mid - 1;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 4, 5, 6, 8, 9};

        int index = halfSearch(arr, 8);

        System.out.println(index);
    }
}
