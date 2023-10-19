package org.kurt.datastructure.linkedlist;

import org.kurt.utils.DataUtils;

/**
 * list 数组玩partition
 */
public class SmallerEqualBigger {
    public static class Node{
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    public static Node partition1(Node head, int pivot) {
        int len = 0;
        Node cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        Node[] arr = new Node[len];
        cur = head;
        int i = 0;
        while (cur != null) {
            arr[i++] = cur;
            cur = cur.next;
        }
        arrPartition(arr, pivot);
        //将数组重新串成链表
        int k = 0;
        for (k = 1; k < arr.length; k++) {
            arr[k - 1].next = arr[k];
        }
        arr[k - 1].next = null;
        return arr[0];
    }

    public static Node partition2(Node head , int pivot){
        Node lessHead = null, lessTail = null, equalHead = null,
                equalTail = null, bigHead = null,bigTail = null;

        while (head!=null){
            if (head.val < pivot) {
                lessHead = lessHead == null ? head : lessHead;
                if (lessTail == null) {
                    lessTail = head;
                } else {
                    lessTail.next = head;
                    lessTail = head;
                }
            } else if (head.val == pivot) {
                equalHead = equalHead == null ? head : equalHead;
                if (equalTail == null) {
                    equalTail = head;
                } else {
                    equalTail.next = head;
                    equalTail = head;
                }
            }else {
                bigHead = bigHead == null ? head : bigHead;
                if (bigTail == null) {
                    bigTail = head;
                } else {
                    bigTail.next = head;
                    bigTail = head;
                }
            }
            head = head.next;
        }
        //把3个部分串起来
        Node newhead = null;
    }



    public static void arrPartition(Node[] arr, int pivot){
        int index = 0;
        int L = -1;
        int R = arr.length;
        while (index < R) {
            if (arr[index].val < pivot) {
                swap(arr, index++, ++L);
            } else if (arr[index].val == pivot) {
                index++;
            } else {
                //index不要递增,刚交换过来的数还没看过
                swap(arr, index, --R);
            }
        }
    }

    public static boolean check(Node head, int pivot) {
        while (head != null && head.val < pivot) {
            head = head.next;
        }
        while (head != null && head.val == pivot) {
            head = head.next;
        }
        while (head != null) {
            if (head.val <= pivot) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public static boolean check(Node[] arr, int pivot) {
        int i = 0, j = arr.length - 1;
        while (i < arr.length - 1 && arr[i++].val < pivot) {

        }
        while (j >= 0 && arr[j--].val > pivot) {

        }

        for (int k = i; k <= j; k++) {
            if (arr[k].val != pivot) {
                return false;
            }
        }
        return true;

    }




    public static void swap(Node[] arr, int a, int  b) {
        Node c = arr[a];
        arr[a] = arr[b];
        arr[b] = c;
    }

    public static void printLinkedList(Node head){
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        for (int r = 0; r < 1000; r++) {

            int len = (int) (Math.random() * 10);
            //随机选择0-len-1的一个下标
            int pivotIndex = (int) (Math.random() * len);
            int pivot  = 0;
            Node head = new Node(7);
            Node last = head;
            for (int i = 0; i < len - 1; i++) {
                Node cur = new Node((int) (Math.random() * 1000));
                last.next = cur;
                last = cur;
                //如果中了这个下标
                if(pivotIndex == i){
                    pivot = cur.val;
                }
            }
//            System.out.println("Cur test round "+ r + " len "+len + " pivot "+ pivot);
            head = partition1(head,pivot);
//            printLinkedList(head);
            if(!check(head,pivot)){
                System.out.println("ERROR");
            }
        }
        System.out.println("PASS");

//        Node head1 = new Node(1);
//        head1.next = new Node(9);
//        head1.next.next = new Node(1);
//        head1.next.next.next = new Node(8);
//        head1.next.next.next.next = new Node(8);
//        head1.next.next.next.next.next = new Node(2);
//        head1.next.next.next.next.next.next = new Node(5);
//        printLinkedList(head1);
//        head1 =  partition1(head1,100);
//
//        // head1 = listPartition1(head1, 4);
////        head1 = listPartition2(head1, 5);
//        printLinkedList(head1);
    }
}
