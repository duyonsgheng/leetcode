package custom.code_2022_08;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ClassName LeetCode_641
 * @Author Duys
 * @Description
 * @Date 2022/8/15 9:34
 **/
// 641. 设计循环双端队列
public class LeetCode_641 {
    public static void main(String[] args) {
        MyCircularDeque circularDeque = new MyCircularDeque(3);
        System.out.println(circularDeque.insertLast(1));
        System.out.println(circularDeque.insertLast(2));
        System.out.println(circularDeque.insertFront(3));
        System.out.println(circularDeque.insertFront(4));
        System.out.println(circularDeque.getRear());
        System.out.println(circularDeque.isFull());
    }

    static class MyCircularDeque {
        private Deque<Integer> queue;
        private Integer size;

        public MyCircularDeque(int k) {
            queue = new LinkedList<>();
            size = k;
        }

        public boolean insertFront(int value) {
            if (queue.size() >= size) {
                return false;
            }
            queue.addFirst(value);
            return true;
        }

        public boolean insertLast(int value) {
            if (queue.size() >= size) {
                return false;
            }
            queue.addLast(value);
            return true;
        }

        public boolean deleteFront() {
            if (queue.isEmpty()) {
                return false;
            }
            queue.pollFirst();
            return true;
        }

        public boolean deleteLast() {
            if (queue.isEmpty()) {
                return false;
            }
            queue.pollLast();
            return true;
        }

        public int getFront() {
            if (queue.isEmpty()) {
                return -1;
            }
            return queue.peekFirst();
        }

        public int getRear() {
            if (queue.isEmpty()) {
                return -1;
            }
            return queue.peekLast();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

        public boolean isFull() {
            return queue.size() == size;
        }
    }
}
