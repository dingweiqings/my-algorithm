package org.kurt.datastructure.heap;

import org.kurt.utils.DataUtils;

/**
 * 手写堆,
 * 堆结构,实际上是完全二叉树,不过以数组形式存储
 */
public class MyHeap {
    private int size;
    private int[] arr;

    public MyHeap(int[] arr) {
        this.arr = arr;
    }

    public void sort(){
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr[i]);
        }
        while (size-->0){
            swap(0,size);
            heapify(0);
        }
    }

    public  int[] getArr(){
        return  arr;
    }

    /**
     * 把参数放到index位置
     * @param arr
     * @param index
     */
    private void heapify(int index) {
        //从index开始向下调整
        //调整到什么时候停
        int child = 0;
        while (index < size) {
            child = 2 * index + 1;
            if (child >= size) {
                break;
            }
            child = child + 1 < size && arr[child + 1] > arr[child] ? child + 1 : child;
            if (arr[index] < arr[child]) {
                swap(index, child);
            } else {
                break;
            }
            index = child;
        }

    }

    private void swap( int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    /**
     * 向上调整
     * @param arr
     * @param value
     */
    private void heapInsert(int value) {
        arr[size++] = value;
        int index = size - 1;
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private boolean check(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            MyHeap heap = new MyHeap(DataUtils.randomArray());
            heap.sort();
            if(!heap.check(heap.arr)){
                System.out.println("ERROR");
            }
        }
        System.out.println("PASS");
    }

}
