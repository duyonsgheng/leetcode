package week.week_2022_04_03;

/**
 * @ClassName Code_02_RMQ
 * @Author Duys
 * @Description
 * @Date 2022/4/21 9:24
 **/
// 其实这个RMQ功能和线段树类似，但是没有线段树强大，RMQ仅仅只是支持某一个区间最大值或者最小值的查询，构建RMQ也是 n*log n的复杂度
public class Code_02_RMQ {

    //
    public static class RMQ {
        private int[][] max;

        /**
         * 整体的流程是：
         * max[i][j] :
         * max[1][0]：从第一个数开始，下标是2^0的最大值，dp[1][1]:从第一个数开始，下标是2^1 范围内最大值
         */
        public RMQ(int[] arr) {
            int n = arr.length;
            int k = power2(n);
            // 注意下标从1开始
            max = new int[n + 1][k + 1];
            for (int i = 1; i <= n; i++) {
                max[i][0] = arr[i - 1];
            }
            // 2 3 4 5 6 7 8 9
            // i=2 j=2
            // 1 2 3 4 5 6 7 8 9
            //  |      |
            //  拆分成两部分
            // 比如 当前的问题是 2 - 5
            // 那么我查询的时候 先来前半部分 2 - 3 然后来后半部分 4 -5 这两个区域取一个大的

            for (int j = 1; (1 << j) <= n; j++) {
                for (int i = 1; i + (1 << j) - 1 <= n; i++) {
                    max[i][j] = Math.max(max[i][j - 1], max[i + (1 << (j - 1))][j - 1]);
                }
            }
        }

        public int max(int l, int r) {
            int k = power2(r - l + 1);
            // 依然两部分，
            return Math.max(max[l][k], max[r - (1 << (k - 1)) + 1][k]);
        }

        //计算num这个数是2的几次方最接近
        private int power2(int num) {
            int ans = 0;
            while ((1 << ans) <= (num >> 1)) {
                ans++;
            }
            return ans;
        }
    }
}
