package org.kurt.sort;

import org.kurt.datastructure.linkedlist.SmallerEqualBigger;
import org.kurt.utils.DataUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 快速排序
 */
public class QuickSort {
    public static void quickSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        //基准取最后一个元素
        int pivot = arr[end];
        //i和j都是边界
        int i = start-1;
        int j = end;
        while (i < j) {
            //先加再取到元素,所以i和j一开始要处于边界;
            //即使最外层检查了,这里还是要检查, i是不会越界的,因为基准是最右边的一个
            while ( i< j && arr[++i] < pivot) {

            }
            //否则对于极端的情况,比如右边的基准是最大的,j会一直来到start的位置
            while (j > i && arr[--j] > pivot) {

            }
            if (i < j) {
                swap(arr, i, j);
            }
        }
        // 小于i都是<=pivot
        swap(arr, i, end);
//        System.out.println("start " + start + "end " + end + " i"+ i +" j" +j);
//        DataUtils.printArray("Quick data ",arr);
        quickSort(arr, start, i - 1);
        quickSort(arr, i + 1, end);
    }





    public static void swap(int[] arr, int a, int  b) {
        int c = arr[a];
        arr[a] = arr[b];
        arr[b] = c;
    }

    public static boolean check(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
    public static void insertSort(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            int j = i - 1;
            for (; j >= 0 && arr[j] > tmp; j--) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = tmp;
        }
    }




    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int[] arr = DataUtils.randomArray();
            int[] copy = Arrays.copyOf(arr,arr.length);
//            insertSort(arr);
            quickSort(copy,0,copy.length-1);
//            if(!DataUtils.testListEquals(Arrays.stream(arr).boxed().toList(), Arrays.stream(copy).boxed().toList())){
//                System.out.println("ERROR");
//            }
            if (!check(copy)) {
                DataUtils.printArray("Testdata",copy);
            }
        }
        System.out.println("PASS");
//        quickSort(arr,0,arr.length-1);

//        DataUtils.printArray("Quick sort",arr);
    }

}
