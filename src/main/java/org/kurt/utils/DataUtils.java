package org.kurt.utils;

import java.util.List;

public class DataUtils {

    public static class TreeNode<T> {
        public TreeNode<T> left;
        public TreeNode<T> right;
        public T value;
        public TreeNode<T> parent;

        public TreeNode(TreeNode<T> left, TreeNode<T> right, T value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        public TreeNode(T value) {
            this.value = value;
        }
    }

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

    public static <T> String printTree(TreeNode<T> root) {
        if (root == null) {
            return null;
        }
        String child =root.value+"";
        StringBuffer left = new StringBuffer();
        StringBuffer right = new StringBuffer();
        if (root.left != null) {
            child += "/";
        }
        if (root.right != null) {
            child += "\\";
        }
        if(root.left!=null || root.right!=null){
            child+="\n";
        }
        if (root.left != null) {
            child += printTree(root.left);
        }
        if (root.right != null) {
            child += printTree(root.right);
        }
        return child;
    }
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.left = new TreeNode<>(2);
        root.right = new TreeNode<>(3);
        System.out.println(printTree(root));
    }
    public static  void printArray(String msg , int[] arr){
        System.out.print(msg + " testdata: [");
        for(int t:arr){
            System.out.print(t+",");
        }
        System.out.println("]");
    }
    public static  void printArray(int[] arr){
        System.out.print("testdata: [");
        for(int t:arr){
            System.out.print(t+",");
        }
        System.out.println("]");
    }
    public static int MAX_TEST_ROW = 10000;
}
