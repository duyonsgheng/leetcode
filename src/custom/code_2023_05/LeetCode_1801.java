package custom.code_2023_05;

import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_1801
 * @Author Duys
 * @Description
 * @Date 2023/5/23 9:04
 **/
// 1801. 积压订单中的订单总数
// https://leetcode.cn/problems/number-of-orders-in-the-backlog/
public class LeetCode_1801 {
    // 妥妥得阅读理解题
    // 优先级队列
    int mod = 1_000_000_007;

    public int getNumberOfBacklogOrders(int[][] orders) {
        // buy队列为大根堆
        PriorityQueue<int[]> buyQueue = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        // sell队列为小根堆
        PriorityQueue<int[]> sellQueue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        // 0 表示这是一批采购订单 buy
        // 1 表示这是一批销售订单 sell
        for (int[] order : orders) {
            int price = order[0], amount = order[1], type = order[2];
            if (type == 0) {
                while (amount > 0 && !sellQueue.isEmpty() && sellQueue.peek()[0] <= price) {
                    int[] sell = sellQueue.poll();
                    int cur = Math.min(amount, sell[1]);
                    // 根据题意同时去掉
                    amount -= cur;
                    sell[1] -= cur;
                    // 如果当前还剩余，那么需要加到队列去
                    if (sell[1] > 0) {
                        sellQueue.add(sell);
                    }
                }
                if (amount > 0) {
                    buyQueue.add(new int[]{price, amount});
                }
            } else {
                while (amount > 0 && !buyQueue.isEmpty() && buyQueue.peek()[0] >= price) {
                    int[] buy = buyQueue.poll();
                    int cur = Math.min(amount, buy[1]);
                    amount -= cur;
                    buy[1] -= cur;
                    if (buy[1] > 0) {
                        buyQueue.add(buy);
                    }
                }
                if (amount > 0) {
                    sellQueue.add(new int[]{price, amount});
                }
            }
        }
        int ans = 0;
        while (!buyQueue.isEmpty()) {
            ans = (ans + buyQueue.poll()[1]) % mod;
        }
        while (!sellQueue.isEmpty()) {
            ans = (ans + sellQueue.poll()[1]) % mod;
        }
        return ans;
    }
}
