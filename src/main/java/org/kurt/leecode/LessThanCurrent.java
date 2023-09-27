package org.kurt.leecode;

import org.kurt.utils.DataUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/count-of-smaller-numbers-after-self/description/
 */
public class LessThanCurrent {
    static class Info{
       public int index;
       public  int count;
       public Info(int index,int count){
           this.index = index;
           this.count = count;
       }
    }
    public static List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length < 2) {
            return List.of(0);
        }
//        int[] result = new int[nums.length];
        Info[] result = new Info[nums.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = new Info(i, 0);
        }
        process(nums, 0, nums.length - 1, result);

        List<Integer> list = new ArrayList<>(result.length);
        for(int i=0;i<result.length;i++){
            list.add(null);
        }
        for (Info info : result) {
            list.set(info.index,info.count);
        }
        return list;
    }

    public static int process(int[] arr, int l, int r, Info[] result) {
        if (l == r) {
            return 0;
        }
        // l < r
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid, result) + process(arr, mid + 1, r, result) + merge(arr, l, mid, r, result);
    }

    public static int merge(int[] arr, int L, int m, int r, Info[] result) {
        int[] help = new int[r - L + 1];
        Info[] helpInfo = new Info[r-L+1];
        //逆序对,从大的方向拷贝
        int i = help.length - 1;
//        int j= help.length-1;
        int p1 = m;
        int p2 = r;
        int res = 0;
        //这里不能单独统计,会超时,需要放到下面归并比较的流程内
//        int lastIndex = m;
//        for (j = L; j <= m; j++) {
//            p2 = r;
//            while (p2 > lastIndex && arr[p2] >= arr[j]) {
//                p2--;
//            }
//            lastIndex = p2;
//            result[j].count += p2 - m;
//        }

        while (p1 >= L && p2 > m) {
            helpInfo[i] = arr[p1] > arr[p2] ? result[p1] : result[p2];
            result[p1].count += arr[p1] > arr[p2] ? p2 - m : 0;
            help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        //归并排序的框架，保持有序性
        while (p1 >= L) {
            helpInfo[i]= result[p1];
            help[i--] = arr[p1--];
        }
        while (p2 > m) {
            helpInfo[i]= result[p2];
            help[i--] = arr[p2--];
        }
        for (i = 0; i < help.length; i++) {
            result[L+i] = helpInfo[i];
            arr[L + i] = help[i];
        }
        return res;
    }

    public static List<Integer> fun1(int[] nums) {
        if (nums == null || nums.length < 2) {
            return List.of(0);
        }
        int[] result = new int[nums.length];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    result[i] += 1;
                }
            }
        }
        for (int i : result) {
            list.add(i);
        }
        return list;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int[] testdata = DataUtils.randomArray();
            int [] testdataCopy = Arrays.copyOf(testdata,testdata.length);
//        int[] testdata = new int[]{25,40};
            if (!DataUtils.testListEquals(fun1(testdata), countSmaller(testdataCopy))) {
                System.out.println("ERROR");
                DataUtils.printArray(testdata);
                System.out.println("fun1 " + fun1(testdata) + "," + "myfunc " + countSmaller(testdata));
            }

        }
        System.out.println("SUCCESS");

    }
}
