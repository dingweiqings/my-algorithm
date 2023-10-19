package org.kurt.leecode;

import javax.swing.plaf.basic.BasicListUI;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class IsPalindromeList {
    public static class ListNode{
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 使用stack,把中点的下一个元素到某位都放到栈里面去
     * @param head
     * @return
     */
    public static boolean isPalindrome1(ListNode head) {
        if (head == null) {
            return false;
        }
        if (head.next == null) {
            return true;
        }
        if (head.next.next == null) {
            return head.val == head.next.val;
        }
        //先找到中点
        ListNode slow = head.next;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        LinkedList<ListNode> stack = new LinkedList<>();
        ListNode p = slow;
        while (p != null) {
            stack.push(p);
            p = p.next;
        }
        p = head;
        while (!stack.isEmpty()) {
            if (stack.pop().val != p.val) {
                return false;
            }
            p=p.next;
        }
        return true;
    }

    /**
     * 找到中点的后一个位置(标注做法,然后再往后移动一个)
     * 做字符串比对,再还原
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        if(head == null ){
            return false;
        }
        if(head.next == null){
            return true;
        }
        if(head.next.next == null ){
            return head.val == head.next.val;
        }
        //先找到中点或者上中位点
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        //slow保持不动,从slow的下一个节点开始反转
        ListNode last =  reverseLinkedList(slow.next);

        //左右半边比对
        ListNode right = last;
        ListNode left = head;
        boolean res = true;
        while (left != null && right != null) {
            if (left.val != right.val) {
                res = false;
            }
            left = left.next;
            right = right.next;
        }

        last = reverseLinkedList(last);
        //左右半边拼接上
        slow.next = last;

        return res;
    }

    public static ListNode reverseLinkedList(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        //3个变量,用来反转链表
        ListNode pre = node;
        ListNode cur = node.next;
        ListNode next = node.next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            //向后移动
            pre = cur;
            cur = next;
        }
        node.next = null;
        return pre;
    }

    public static void printLinkedList (ListNode head){
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
      ListNode  head = new ListNode(1);
      head.next = new ListNode(1);
      testOdd(head);
//      ListNode first = reverseLinkedList(head);
//      printLinkedList(first);
//      printLinkedList(reverseLinkedList(first));
      System.out.println(isPalindrome1(head));
        System.out.println(isPalindrome(head));
    }

    public static void testOdd(ListNode head){
        head.next = new ListNode(2);
        head.next.next = new ListNode(4);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(1);
    }

    public static void testEven(ListNode head){
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);
    }
}
