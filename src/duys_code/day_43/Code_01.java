package duys_code.day_43;

import java.util.Arrays;

/**
 * @ClassName Code_01
 * @Author Duys
 * @Description
 * @Date 2022/1/5 13:11
 **/
public class Code_01 {

    // 来自微软面试
    // 给定一个正数数组arr长度为n、正数x、正数y
    // 你的目标是让arr整体的累加和<=0
    // 你可以对数组中的数num执行以下三种操作中的一种，且每个数最多能执行一次操作 :
    // 1）不变
    // 2）可以选择让num变成0，承担x的代价
    // 3）可以选择让num变成-num，承担y的代价
    // 返回你达到目标的最小代价
    // 数据规模 : 面试时面试官没有说数据规模
    public static int min1(int[] arr, int x, int y) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return process1(arr, x, y, 0, sum);
    }

    // 这就是一个从左往右的尝试模型
    public static int process1(int[] arr, int x, int y, int index, int sum) {
        if (sum <= 0) {
            return 0;
        }
        // 搞不定
        if (arr.length == index) {
            return Integer.MAX_VALUE;
        }
        // 1. 什么也不做
        int p1 = process1(arr, x, y, index + 1, sum);

        int p2 = Integer.MAX_VALUE;
        int next1 = process1(arr, x, y, index + 1, sum - arr[index]);
        if (next1 != Integer.MAX_VALUE) {
            p2 = x + next1;
        }
        int p3 = Integer.MAX_VALUE;
        int next2 = process1(arr, x, y, index + 1, sum - 2 * arr[index]);
        if (next2 != Integer.MAX_VALUE) {
            p3 = y + next2;
        }
        return Math.min(Math.min(p1, p2), p3);
    }

    // 贪心的解答
    public static int min2(int[] arr, int x, int y) {
        Arrays.sort(arr); // 从小到大
        int n = arr.length;
        // 变成从大到小
        for (int l = 0, r = n - 1; l <= r; l++, r--) {
            int tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;
        }
        // 如果我的代价x是变成0 的大于了y，y是变成负的
        if (x >= y) {
            int sum = 0;
            for (int num : arr) {
                sum += num;
            }
            int cost = 0;
            for (int i = 0; i < n && sum > 0; i++) {
                sum -= arr[i] << 1;
                cost += y;
            }
            return cost;
        } else {
            // 来一个后缀和
            for (int i = n - 2; i >= 0; i--) {
                arr[i] += arr[i + 1];
            }
            int be = 0;
            // 看看 小于=0的位置在哪里
            // 执行y操作，有0个数的时候，意思就是全部都执行x的操作
            int left = mostLeft(arr, 0, be);
            int cost = left * x;
            for (int i = 0; i < n - 1; i++) {
                // 部分执行x，部分执行y
                // 左边的前缀和和右边的后缀和相等的时候，说明我执行y操作，正好可以抵消掉，剩下的部分全部执行x，全部变0
                be += arr[i] - arr[i + 1];
                left = mostLeft(arr, i + 1, be);
                cost = Math.min(cost, (i + 1) * y + (left - i - 1) * x);
            }
            return cost;
        }
    }

    // 找到<=v最左的位置
    public static int mostLeft(int[] arr, int l, int v) {
        int r = arr.length - 1;
        int m = 0;
        int ans = arr.length;
        while (l <= r) {
            m = (l + r) / 2;
            if (arr[m] <= v) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }
}
