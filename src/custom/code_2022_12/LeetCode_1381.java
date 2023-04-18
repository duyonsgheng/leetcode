package custom.code_2022_12;

// 1381. 设计一个支持增量操作的栈
public class LeetCode_1381 {
    class CustomStack {
        int[] arr;
        int index;
        int maxSize;

        public CustomStack(int maxSize) {
            arr = new int[maxSize];
            index = 0;
            this.maxSize = maxSize;
        }

        public void push(int x) {
            if (index == maxSize) {
                return;
            }
            arr[index++] = x;
        }

        public int pop() {
            if (index == 0) {
                return -1;
            }
            return arr[--index];
        }

        public void increment(int k, int val) {
            for (int i = 0; i < Math.min(k, index); i++) {
                arr[i] += val;
            }
        }

    }

}
