package org.kurt.zuo.class01;

import org.kurt.utils.DataUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 有序数组中找出>value最左的数
 */
public class BinarySearchNearestLeft {
   static int find(int arr[], int value) {
        int index = -1;
        int L = 0;
        int R = arr.length - 1;
        int mid;
        while (L <= R) {
            mid = L + ((R - L) >> 1);
            if (arr[mid] > value) {
                index = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return index;
    }

    static int find1(int arr[], int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > value) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            int[] testdata = DataUtils.randomArray(10, 1000);
            Arrays.sort(testdata);
            List<Integer> print = new ArrayList<>();
            for(int it : testdata){
                print.add(it);
            }
            System.out.println("Data: " + print + ",  answer:" + find(testdata, 100));
            if (find(testdata, 100) != find1(testdata, 100)) {
                System.out.println("ERROR");
            }
        }
        System.out.println("Passed");
    }
}
