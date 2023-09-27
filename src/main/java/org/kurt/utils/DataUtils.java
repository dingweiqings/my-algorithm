package org.kurt.utils;

import java.util.List;

public class DataUtils {
    public static int[] randomArray(int len, int maxValue) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        return arr;
    }

    public static int[] randomArrayPositive() {
        int len = (int) Math.random() * 10000;
        int maxValue = Integer.MAX_VALUE;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        return arr;
    }

    public static int[] randomArray() {
        int len = (int) (Math.random() * 10);
        int maxValue = 100;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue) - (int)(Math.random() * maxValue);
        }
        return arr;
    }

    public static <T> boolean testListEquals(List<T> a, List<T> b) {
        if (a == null || b == null || a.size() != b.size()) {
            return false;
        }
        for (int i = 0; i < a.size(); i++) {
            if (!a.get(i).equals(b.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(testListEquals(List.of(0,1,0),List.of(0,0,1)));
    }

    public static  void printArray(int[] arr){
        System.out.println("testdata: [");
        for(int t:arr){
            System.out.println(t+",");
        }
        System.out.println("]");
    }
    public static int MAX_TEST_ROW = 10000;
}
