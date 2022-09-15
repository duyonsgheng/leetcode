package duys_code.day_34;

import java.util.PriorityQueue;

/**
 * @ClassName Code_03_295_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/find-median-from-data-stream/
 * @Date 2021/12/6 17:49
 **/
public class Code_03_295_LeetCode {

    // 准备两个堆 一个大根堆，一个小根堆
    class MedianFinder {
        // 为了加速和空间占用，可以使用数组来替代系统提供的堆
        private PriorityQueue<Integer> maxHeap; // 大根堆
        private PriorityQueue<Integer> minHeap; // 小根堆

        public MedianFinder() {
            maxHeap = new PriorityQueue<>((a, b) -> b - a);
            // 默认就是小根堆
            minHeap = new PriorityQueue<>((a, b) -> a - b);
        }


        public void addNum(int num) {
            // 大根堆为空 或者 大根堆堆顶是大于等于当前数的，往大根堆放
            if (maxHeap.isEmpty() || maxHeap.peek() >= num) {
                maxHeap.add(num);
            } else {
                minHeap.add(num);
            }
            balance();
        }

        public double findMedian() {
            // 相等一定是偶数个
            if (maxHeap.size() == minHeap.size()) {
                return (double) (maxHeap.peek() + minHeap.peek()) / 2;
            } else {
                // 否则一定是奇数个
                return maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek();
            }
        }

        // 两个堆进行平衡调节，方便求中位数
        private void balance() {
            // 两个堆的大小超过了2
            if (Math.abs(maxHeap.size() - minHeap.size()) == 2) {
                if (maxHeap.size() > minHeap.size()) { // 如果大根堆比小根堆多
                    minHeap.add(maxHeap.poll());
                } else {
                    maxHeap.add(minHeap.poll());
                }
            }
        }
    }
}
