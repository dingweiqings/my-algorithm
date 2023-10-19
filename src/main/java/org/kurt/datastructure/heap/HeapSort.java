package org.kurt.datastructure.heap;

import org.kurt.utils.DataUtils;

import java.awt.*;

/**
 * 手写堆,
 * 堆结构,实际上是完全二叉树,不过以数组形式存储
 */
public class HeapSort {

    private static void sort(int [] arr){
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
//        DataUtils.printArray("heap insert",arr);
        int size = arr.length;
        while (--size > 0) {
            swap(arr, 0, size);
            heapfiyNew(arr, 0, size);
//            DataUtils.printArray("heapfiy", arr);
        }
    }
    private static void heapfiyNew(int []  arr ,int index, int size){
        //从index开始向下调整
        //调整到什么时候停
        int left =0;
        int right =0;
        int large =0;
        int child =0;
        while (index < size) {
            left = 2 * index + 1 < size ? arr[2 * index + 1] : Integer.MIN_VALUE;
            right = 2 * index + 2 < size ? arr[2 * index + 2] : Integer.MIN_VALUE;
            if (left > right) {
                large = left;
                child = 2 * index + 1;
            } else {
                large = right;
                child = 2 * index + 2;
            }
            //如果比子节点小,则调整,
            if (arr[index] < large) {
                swap(arr, index, child);
            } else {//不小，说明已经到位了
                break;
            }

            index = child;
        }

    }
    /**
     * 把参数放到index位置,找下标
     * @param arr
     * @param index
     */
    private static void heapfiy(int []  arr ,int index, int size){
        //从index开始向下调整
        //调整到什么时候停
        while (index < size) {
            int child = 2*index +1 ;
            if (child >= size) {
                break;
            }
            child = child + 1 < size && arr[child + 1] > arr[child] ? child + 1 : child;
            //如果比子节点小,则调整,
            if (arr[index] < arr[child]) {
                swap(arr, index, child);
            } else {//不小，说明已经到位了
                break;
            }

            index = child;
        }

    }

    private static void swap(int arr[], int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    /**
     * 向上调整
     * @param arr
     * @param value
     */
    private static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private static boolean check(int arr[]) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
//            System.out.print("Origin Data ");
            int [] arr = DataUtils.randomArray();
//            DataUtils.printArray(arr);
            sort(arr);
            if (!check(arr)) {
                System.out.println("ERROR");
                DataUtils.printArray(arr);
            }
        }
        System.out.println("PASS");
    }
//    public static void main(String[] args) {
//           System.out.print("Origin Data ");
//            int [] arr = {2,-18,-5,-65,1,-18,39};
//            sort(arr);
//            if (!check(arr)) {
//                System.out.println("ERROR");
//            }
//    }

}
