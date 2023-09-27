package org.kurt.zuo.class01;

import org.kurt.utils.DataUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 有序数组中找出<value最右的数.
 *  这里主要理解二分的过程,不断缩小区间，最终会变成2个数，1个数。在区间左端点向右移动的过程中,把index不断向右推。推到最后的index就是结果。
 *  至少还有1个数的时候也要执行循环体
 */
public class BinarySearchNearestRight {
   static int find(int arr[], int value) {
        int index = -1;
        int L = 0;
        int R = arr.length - 1;
        int mid;
        while (L <= R) {
            mid = L + ((R - L) >> 1);
            if (arr[mid] < value) {
                L = mid + 1;
                index = mid;
            } else {
                R = mid - 1;
            }
        }
        return index;
    }

    static int find1(int arr[], int value) {
        for (int i = arr.length-1; i >=0; i--) {
            if (arr[i] < value) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int[] testdata = DataUtils.randomArray(10, 1000);
            Arrays.sort(testdata);
//            List<Integer> print = new ArrayList<>();
//            for(int it : testdata){
//                print.add(it);
//            }
//            System.out.println("Data: " + print + ",  answer:" + find(testdata, 100));
            if (find(testdata, 100) != find1(testdata, 100)) {
                System.out.println("ERROR");
            }
        }
        System.out.println("Passed");
    }
}
