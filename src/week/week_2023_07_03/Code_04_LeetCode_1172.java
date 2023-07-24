package week.week_2023_07_03;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author Mr.Du
 * @ClassName Code_04_LeetCode_1172
 * @date 2023年07月20日
 */
// 1172. 餐盘栈
// https://leetcode.cn/problems/dinner-plate-stacks/
public class Code_04_LeetCode_1172 {
    class DinnerPlates {
        private final int N = 100001;
        private int capacity;

        private List<List<Integer>> stacks = new ArrayList<>();
        private int[] cnt = new int[N + 1]; // 记录每一个位置的栈的数据数量
        private PriorityQueue<Integer> heap = new PriorityQueue<>(); // 记录那些使用了
        private int limitIndex;

        public DinnerPlates(int capacity) {
            this.capacity = capacity;
            this.limitIndex = 0;
        }

        public void push(int val) {
            if (heap.isEmpty()) { // 如果没有空洞的地方
                // 先确定好具体的栈的编号
                if (cnt[limitIndex] == capacity && limitIndex < N) {
                    limitIndex++;
                }
                // 是否需要新开一个栈
                if (stacks.size() == limitIndex) {
                    stacks.add(new ArrayList<>());
                }
                stacks.get(limitIndex).add(val);
                cnt[limitIndex]++;
            } else { // 有空洞
                // 照样需要清理过期位置
                while (cnt[limitIndex] == 0 && limitIndex > 0) {
                    limitIndex--;
                }
                // 如果空洞的最小位置都过期了
                if (heap.peek() >= limitIndex) {
                    // 把所有的空洞都去掉
                    heap.clear();
                    if (cnt[limitIndex] == capacity) {
                        limitIndex++;
                    }
                    if (stacks.size() == limitIndex) {
                        stacks.add(new ArrayList<>());
                    }
                    stacks.get(limitIndex).add(val);
                    cnt[limitIndex]++;
                } else {
                    // 空洞还有效
                    int curIndex = heap.peek();
                    cnt[curIndex]++;
                    stacks.get(curIndex).add(val);
                    if (cnt[curIndex] == capacity) {
                        heap.poll();
                    }
                }
            }
        }

        public int pop() {
            // 弹出的时候先把过期的位置清理掉
            while (cnt[limitIndex] == 0 && limitIndex > 0)
                limitIndex--;
            // 来到0号了栈了，都还没数据，
            if (cnt[limitIndex] == 0) {
                return -1;
            }
            // 获取对应位置的数据，然后对应位置的计数-1
            return stacks.get(limitIndex).remove(--cnt[limitIndex]);
        }

        public int popAtStack(int index) {
            if (cnt[index] == 0) {
                return -1;
            }
            // 先把对应位置的数据去掉
            int ans = stacks.get(index).remove(cnt[index] - 1);
            // 如果当前位置是第一次弹出的，并且不为空的，那么需要记录下当前位置的栈，有空位置
            if (cnt[index] == capacity) {
                heap.add(index);
            }
            cnt[index]--;
            return ans;
        }
    }
}
