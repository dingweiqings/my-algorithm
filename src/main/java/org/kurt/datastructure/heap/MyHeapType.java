package org.kurt.datastructure.heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 手写堆,支持修改元素的字段，并依旧保持堆的性质，
 * 插入O(logn),修改O(logn),修改元素字段O(logn)
 * 堆结构,实际上是完全二叉树,不过以数组形式存储。实现的是小根堆
 */
public class MyHeapType<T extends Comparable> {
    /**
     * 堆的大小
     */
    private int size;
    private ArrayList<T> arr;
    /**
     * 记录元素子啊堆中的位置
     */
    private HashMap<T,Integer> indexMap ;

    public MyHeapType(){
        arr = new ArrayList<>();
        indexMap = new HashMap<>();
    }

    public void push(T value) {
        heapInsert(value, size++);
    }

    public T pop() {
        if (size > 0) {
            swap(0, size - 1);
            T ans = arr.get(size-1);
            size--;
            heapify(0);
            return ans;
        }
        return null;
    }

    public T peek(){
        if(size>0){
            return arr.get(0);
        }
        return null;
    }

    /**
     * 修改堆中元素
     * @param t
     */
    public void resign(T t) {
        heapInsert(t, indexMap.get(t));
        heapify(indexMap.get(t));
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
            child = child + 1 < size && arr.get(child + 1).compareTo(arr.get(child))<0 ? child + 1 : child;
            if (arr.get(index).compareTo(arr.get(child)) > 0) {
                swap(index, child);
            } else {
                break;
            }
            index = child;
        }

    }

    private void swap(int a, int b) {
        T tmp = arr.get(a);
        arr.set(a, arr.get(b));
        arr.set(b, tmp);
        indexMap.put(arr.get(a), a);
        indexMap.put(arr.get(b), b);
    }

    /**
     * 向上调整
     * @param arr
     * @param value
     */
    private void heapInsert(T value, int index) {
        if(index >= arr.size()){
            arr.add(value);
        }else{
            arr.add(index, value);
        }
        indexMap.put(value, index);
        while (arr.get(index).compareTo(arr.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static class Student implements Comparable<Student>{
        public int score;
        public String name;

        public Student(int score, String name) {
            this.score = score;
            this.name = name;
        }

        /**
         *
         * @param o the object to be compared.
         * @return
         */
        @Override
        public int compareTo(Student o) {
            if (score > o.score) {
                return -1;
            } else if (score < o.score) {
                return 1;
            }
            return 0;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "score=" + score +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        //对数器和系统的优先级队列做比对
//        for (int i = 0; i < 1000; i++) {
//            MyHeapType<Student> heapType = new MyHeapType<>();
//            PriorityQueue<Student> queue = new PriorityQueue<>();
//            int size = (int) (Math.random() * 1000);
//            for (int j = 0; j < size; j++) {
//                int score = (int) (Math.random() * 1000);
//                Student tmp = new Student(score, "A" + score);
//                heapType.push(tmp);
//                queue.offer(tmp);
//            }
//            System.out.println(queue.peek() + "    " + heapType.peek());
//            if (queue.peek() != heapType.peek()) {
//                System.out.println(queue.peek() + "    " + heapType.peek());
//                System.out.println("ERROR");
//            }
//        }
//        System.out.println("PASS");

        MyHeapType<Student> heapType = new MyHeapType<>();
        PriorityQueue<Student> queue = new PriorityQueue<>();
        Student a = new Student(100,"a");
        Student  b  = new Student(18,"b");
        Student c = new Student(30,"c");
        heapType.push(a);
        heapType.push(b);
        heapType.push(c);
        queue.offer(a);
        queue.offer(b);
        queue.offer(c);
        System.out.println(heapType.peek());
        System.out.println(queue.peek());
        //修改学生的分数,再调用resign,这样堆会重新做调整,系统堆做不到
        b.score=110;
        heapType.resign(b);
        System.out.println(heapType.peek());
        System.out.println(queue.peek());
    }

}
