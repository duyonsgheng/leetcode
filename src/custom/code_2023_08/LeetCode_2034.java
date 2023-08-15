package custom.code_2023_08;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2034
 * @date 2023年08月01日
 */
// 2034. 股票价格波动
// https://leetcode.cn/problems/stock-price-fluctuation/
public class LeetCode_2034 {
    static class StockPrice {

        Map<Integer, Integer> times;
        PriorityQueue<int[]> maxQ;
        PriorityQueue<int[]> minQ;
        int maxTime;

        public StockPrice() {
            times = new HashMap<>();
            maxTime = 0;
            maxQ = new PriorityQueue<>((a, b) -> b[0] - a[0]);
            minQ = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        }

        public void update(int timestamp, int price) {
            maxTime = Math.max(maxTime, timestamp);
            times.put(timestamp, price);
            minQ.add(new int[]{price, timestamp});
            maxQ.add(new int[]{price, timestamp});
        }

        public int current() {
            return times.get(maxTime);
        }

        public int maximum() {
            while (true) {
                int[] peek = maxQ.peek();
                int p = peek[0], time = peek[1];
                if (times.get(time) == p) {
                    return p;
                }
                // 清理掉过期的
                maxQ.poll();
            }
        }

        public int minimum() {
            while (true) {
                int[] peek = minQ.peek();
                int p = peek[0], time = peek[1];
                if (times.get(time) == p) {
                    return p;
                }
                minQ.poll();
            }
        }
    }

    public static void main(String[] args) {
        StockPrice stockPrice = new StockPrice();
        stockPrice.update(1, 10);
        stockPrice.update(2, 5);
        System.out.println(stockPrice.current());
        System.out.println(stockPrice.maximum());
        stockPrice.update(1, 3);
        System.out.println(stockPrice.maximum());
        stockPrice.update(4, 2);
        System.out.println(stockPrice.minimum());
    }

}
