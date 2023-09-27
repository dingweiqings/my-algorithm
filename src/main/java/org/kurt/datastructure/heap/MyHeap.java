package org.kurt.datastructure.heap;

/**
 * 手写堆,
 * 堆结构,实际上是完全二叉树,不过以数组形式存储
 */
public class MyHeap {
    private int size;
    private int[] arr;

    /**
     * 把参数放到index位置
     * @param arr
     * @param index
     */
    private void heapfiy(int []  arr ,int index){
        //从index开始向下调整
        //调整到什么时候停
        int large = Integer.MAX_VALUE;
        int largeLeft = Integer.MIN_VALUE;
        int largeRight = Integer.MIN_VALUE;
        int child =0;
        while (index < size) {
            largeLeft = 2 * index < size ? arr[2 * index] : Integer.MIN_VALUE;
            largeRight = 2 * index + 1 < size ? arr[2 * index + 1] : Integer.MIN_VALUE;
            if (largeLeft > largeRight) {
                child = 2 * index;
                large = largeLeft;
            } else {
                child = 2 * index + 1;
                large = largeRight;
            }
            //如果比子节点小,则调整,
            if (arr[index] < large) {
                swap(arr, index, child);
            }else {//不小，说明已经到位了
                break;
            }

            index = child;
        }

    }

    private void swap(int arr[], int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    /**
     * 向上调整
     * @param arr
     * @param value
     */
    private void heapInsert(int[] arr, int value) {
        arr[size++] = value;
        int index = size - 1;
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }


}
