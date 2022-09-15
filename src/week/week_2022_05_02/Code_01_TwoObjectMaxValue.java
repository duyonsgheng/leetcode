package week.week_2022_05_02;

import java.util.Arrays;

/**
 * @ClassName Code_01_TwoObjectMaxValue
 * @Author Duys
 * @Description
 * @Date 2022/5/12 10:18
 **/
// 来自字节
// 5.6笔试
// 给定N件物品，每个物品有重量(w[i])、有价值(v[i])
// 只能最多选两件商品，重量不超过bag，返回价值最大能是多少？
// N <= 10^5, w[i] <= 10^5, v[i] <= 10^5, bag <= 10^5
// 本题的关键点：什么数据范围都很大，唯独只需要最多选两件商品，这个可以利用一下
public class Code_01_TwoObjectMaxValue {

    //  看似背包问题，但是我们如果使用dp的话，在当前数据量情况下，就直接挂了
    // 我们应该怎么做呢？？？

    /**
     * 例如背包是10
     * 先根据货物重量排个序，当我们选择 重量1的货物了，那么在剩下1到9的重量中选一个价值最大的，不能选自己了
     * 怎么加速这个过程？
     */
    // 先来一个暴力解答 n*n 会超时
    public static int max1(int[] w, int[] v, int bag) {
        return process1(w, v, 0, 2, bag);
    }

    // index 当前来到index号货物做选择，
    // 还剩下restNumber 个货物需要选择
    // 剩下的背包容量是 restWeight
    public static int process1(int[] w, int[] v, int index, int restNumber, int restWeight) {
        if (restNumber < 0 || restWeight < 0) { // 没得选了
            return -1;
        }
        if (index == w.length) {
            return 0;
        }
        // 我就不要当前的货物
        int p1 = process1(w, v, index + 1, restNumber, restWeight);
        int p2 = -1;
        int next = process1(w, v, index + 1, restNumber - 1, restWeight - w[index]);
        if (next != -1) {
            p2 = next + v[index];
        }
        return Math.max(p1, p2);
    }

    public static int max(int[] w, int[] v, int bag) {
        int n = w.length;
        // 每个货物两个属性，一个重量，一个价值
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = w[i];
            arr[i][1] = v[i];
        }
        // 根据重量排序
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        RMQ rmq = new RMQ(arr);
        int ans = 0;
        // 重量是小于bag的
        for (int i = 0, j = 1; i < n && arr[i][0] <= bag; i++, j++) {
            int right = right(arr, arr[i][0]) + 1;// 找到小于我的最右位置，在RMQ中是要+1的
            int rest = 0;
            // 说明最右位置正好就是自己，那么就需要找 1到right-1上的最大就好了
            if (right == j) {
                rest = rmq.max(1, right - 1);
            } else if (right < j) { // 全部在我左边
                rest = rmq.max(1, right);
            } else { // 找出的位置在我右边，那么就需要分两段求出
                rest = Math.max(rmq.max(1, j - 1), rmq.max(j + 1, right));
            }
            ans = Math.max(rest + arr[i][1], ans);
        }
        return ans;
    }

    public static int right(int[][] arr, int limit) {
        int l = 0;
        int r = arr.length - 1;
        int m = 0;
        int ans = -1;
        while (l <= r) {
            m = (l + r) / 2;
            if (arr[m][0] > limit) {
                r = m - 1;
            } else {
                l = m + 1;
                ans = m;
            }
        }
        return ans;
    }

    public static class RMQ {

        public int[][] max;

        public RMQ(int[][] arr) {
            int n = arr.length;
            int power = power2(n);
            // max的下标是从1开始的
            max = new int[n + 1][n + 1];
            for (int i = 0; i < n + 1; i++) {
                // 从i开始，后面有2的0次方这个多个数的范围内，最大值是arr[i - 1][1]
                max[i][0] = arr[i - 1][1];// 这是价值
            }
            // 2的1次方 2的2次方。。。。
            // 当前 来到第2的j次方这一列 j =3  8
            // 重量是1的货物 重量从1到8最大价值
            // 重量是2的货物。重量从2到9最大价值
            for (int j = 1; (1 << j) <= n; j++) {
                for (int i = 1; i + (1 << j) - 1 <= n; i++) {
                    max[i][j] = Math.max(max[i][j - 1], max[i + (1 << (j - 1))][j - 1]);
                }
            }
        }

        public int max(int l, int r) {
            if (r < l) {
                return 0;
            }
            // 区间几个数，是2的多少次方
            int k = power2(r - l + 1);
            // 例如显示 r = 5 l = 3 k = 1
            // 3 4  范围内最大
            // 4 5  范围内最大
            return Math.max(max[l][k], max[r - (1 << k) + 1][k]);
        }

        // 取num这个数是2的几次方，最接近， <= num
        private int power2(int num) {
            int ans = 0;
            while ((1 << ans) <= (num >> 1)) {
                ans++;
            }
            return ans;
        }
    }

}
