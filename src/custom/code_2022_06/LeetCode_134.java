package custom.code_2022_06;


import java.util.LinkedList;

/**
 * @ClassName LeetCode_134
 * @Author Duys
 * @Description
 * @Date 2022/6/29 16:38
 **/
// 134. 加油站
// 在一条环路上有 n个加油站，其中第 i个加油站有汽油gas[i]升。
//你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1个加油站需要消耗汽油cost[i]升。你从其中的一个加油站出发，开始时油箱为空。
//给定两个整数数组 gas 和 cost ，如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
//链接：https://leetcode.cn/problems/gas-station
public class LeetCode_134 {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        boolean[] good = isSuccess(gas, cost);
        for (int i = 0; i < gas.length; i++) {
            if (good[i]) {
                return i;
            }
        }
        return -1;
    }

    // -3 -2 -1  5  3  1 -3 -2 -1  5  3  1
    // -3 -5 -6 -1  2  3  0 -2 -3  2  5  6
    //  变成了窗口内的最小值是否满足大于等于0
    public static boolean[] isSuccess(int[] g, int[] c) {
        int n = g.length;
        int m = n << 1;// 一圈
        int[] arr = new int[m];
        for (int i = 0; i < n; i++) {
            arr[i] = g[i] - c[i];// 当前加站站的油和消耗的油量
            arr[i + n] = g[i] - c[i];
        }
        // 做前缀和
        for (int i = 1; i < m; i++) {
            arr[i] += arr[i - 1];
        }
        // 记录窗口内的最小值
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            while (!queue.isEmpty() && arr[queue.peekLast()] >= arr[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
        }
        boolean[] ans = new boolean[n];
        for (int offset = 0, i = 0, j = n; j < m; offset = arr[i++], j++) {
            if (arr[queue.peekFirst()] - offset >= 0) {
                ans[i] = true;
            }
            if (queue.peekFirst() == i) {
                queue.pollFirst();
            }
            while (!queue.isEmpty() && arr[queue.peekLast()] >= arr[j]) {
                queue.pollLast();
            }
            queue.addLast(j);
        }
        return ans;
    }
}
