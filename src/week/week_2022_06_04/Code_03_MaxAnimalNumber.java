package week.week_2022_06_04;

/**
 * @ClassName Code_03_MaxAnimalNumber
 * @Author Duys
 * @Description
 * @Date 2022/6/30 10:03
 **/
// 有n个动物重量分别是a1、a2、a3.....an，
// 这群动物一起玩叠罗汉游戏，
// 规定从左往右选择动物，每只动物左边动物的总重量不能超过自己的重量
// 返回最多能选多少个动物，求一个高效的算法。
// 比如有7个动物，从左往右重量依次为：1，3，5，7，9，11，21
// 则最多能选5个动物：1，3，5，9，21
// 注意本题给的例子是有序的，但是实际给定的动物数组，可能是无序的，
// 要求从左往右选动物，且不能打乱原始数组
public class Code_03_MaxAnimalNumber {

    // 1.背包问题的动态规划
    public static int maxAnimals1(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return 0;
        }
        if (arr.length == 1) {
            return 1;
        }
        int sum = 0;
        int n = arr.length;
        for (int num : arr) {
            sum += num;
        }
        int[][] dp = new int[n + 1][sum + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int pre = sum; pre >= 0; pre--) {
                int p1 = dp[i + 1][pre];
                int p2 = 0;
                if (pre <= arr[i] && pre + arr[i] <= sum) {
                    p2 = 1 + dp[i + 1][pre + arr[i]];
                }
                dp[i][pre] = Math.max(p1, p2);
            }
        }
        return dp[0][0];
    }

    // 最长递增子序列的解法
    public static int maxAnimals2(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return 0;
        }
        if (arr.length == 1) {
            return 1;
        }
        int n = arr.length;
        // 最长递增子序列问题，使用ends数组
        int[] ends = new int[n + 1];
        ends[0] = 0;
        // ends数组从1开始
        int endSize = 1;
        // 单独得算一个，所以起码一个
        int max = 1;
        for (int i = 0; i < n; i++) {
            int l = 0;
            int r = endSize - 1;
            int m = 0;
            int find = 0;
            // 二分找ends中满足 <= arr[i]的位置
            while (l <= r) {
                m = l + ((r - l) >> 1);
                // 看看还有没有更长的位置
                if (ends[m] <= arr[i]) {
                    l = m + 1;
                    find = m;
                } else {
                    r = m - 1;
                }
            }
            // ends被推高了
            if (find == endSize - 1) {
                ends[endSize] = ends[endSize - 1] + arr[i];
                endSize++;
            } else {
                // 看看find + 1 位置的数能不能被更新
                if (ends[find + 1] > ends[find] + arr[i]) {
                    ends[find + 1] = ends[find] + arr[i];
                }
            }
            max = Math.max(max, find + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 9, 17, 43, 23, 33, 57, 88};
        System.out.println(maxAnimals1(arr));
        System.out.println(maxAnimals2(arr));
    }
}
