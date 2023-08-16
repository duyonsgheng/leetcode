package hope.stract;

import java.util.PriorityQueue;

/**
 * @author Mr.Du
 * @ClassName Video_035_5_MedianFinder
 * @date 2023年08月16日
 */
// https://leetcode.cn/problems/find-median-from-data-stream/
// 295. 数据流的中位数
public class Video_035_5_MedianFinder {
    class MedianFinder {
        // 一个大根堆和小根堆
        PriorityQueue<Integer> maxHeap;
        PriorityQueue<Integer> minHeap;

        public MedianFinder() {
            maxHeap = new PriorityQueue<>((a, b) -> b - a);
            minHeap = new PriorityQueue<>((a, b) -> a - b);
        }

        public void addNum(int num) {
            if (maxHeap.isEmpty() || maxHeap.peek() >= num) {
                maxHeap.add(num);
            } else {
                minHeap.add(num);
            }
            balance();
        }

        public void balance() {
            if (Math.abs(maxHeap.size() - minHeap.size()) == 2) {
                if (maxHeap.size() > minHeap.size()) {
                    minHeap.add(maxHeap.poll());
                } else {
                    maxHeap.add(minHeap.poll());
                }
            }
        }

        public double findMedian() {
            if (minHeap.size() == maxHeap.size()) {
                return (double) (minHeap.peek() + maxHeap.peek()) / 2;
            } else {
                return maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek();
            }
        }
    }
}
