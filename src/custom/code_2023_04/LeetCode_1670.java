package custom.code_2023_04;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ClassName LeetCode_1670
 * @Author Duys
 * @Description
 * @Date 2023/4/25 14:44
 **/
// 1670. 设计前中后队列
public class LeetCode_1670 {
    static class FrontMiddleBackQueue {
        Deque<Integer> q1, q2;

        public FrontMiddleBackQueue() {
            q1 = new LinkedList<>();
            q2 = new LinkedList<>();
        }

        public void pushFront(int val) {
            q1.addFirst(val);
        }

        // 5
        // 1 2 3 9 8 7
        public void pushMiddle(int val) {
            while (q1.size() != 0 && q1.size() >= q2.size()) {
                q2.addFirst(q1.pollLast());
            }
            while (q1.size() + 1 < q2.size()) {
                q1.addLast(q2.pollFirst());
            }
            q1.addLast(val);
        }

        public void pushBack(int val) {
            q2.addLast(val);
        }

        public int popFront() {
            return q1.size() == 0 ? q2.size() == 0 ? -1 : q2.pollFirst() : q1.pollFirst();
        }

        public int popMiddle() {
            while (q1.size() > q2.size()) {
                q2.addFirst(q1.pollLast());
            }
            while (q1.size() < q2.size()) {
                q1.addLast(q2.pollFirst());
            }
            return q1.size() == 0 ? -1 : q1.pollLast();
        }

        public int popBack() {
            return q2.size() == 0 ? q1.size() == 0 ? -1 : q1.pollLast() : q2.pollLast();
        }

    }

    public static void main(String[] args) {
        // 0 1 2 3 4

        FrontMiddleBackQueue queue = new FrontMiddleBackQueue();
        queue.pushFront(1);
        queue.pushFront(2);
        queue.pushFront(3);

        queue.pushBack(7);
        queue.pushBack(8);
        queue.pushBack(9);

        queue.pushMiddle(6);

        queue.popMiddle();
    }
}
