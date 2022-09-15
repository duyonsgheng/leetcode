package custom.code_2022_08;

/**
 * @ClassName LeetCode_622
 * @Author Duys
 * @Description
 * @Date 2022/8/29 17:52
 **/
// 622. 设计循环队列
public class LeetCode_622 {
    class MyCircularQueue {
        int k;
        int head;
        int tail;
        int[] arr;

        public MyCircularQueue(int k) {
            this.k = k;
            arr = new int[k];
        }

        public boolean enQueue(int value) {
            if (isFull()) {
                return false;
            }
            arr[tail % k] = value;
            tail++;
            return tail >= 0;
        }

        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            }
            // 弹出一个
            head++;
            return head >= 0;
        }

        public int Front() {
            return isEmpty() ? -1 : arr[head % k];
        }

        public int Rear() {
            return isEmpty() ? -1 : arr[(tail - 1) % k];
        }

        public boolean isEmpty() {
            return tail == head;
        }

        public boolean isFull() {
            return tail - head >= k;
        }
    }
}
