package week.week_2022_11_01;

import java.util.Arrays;

/**
 * @ClassName Code_03_HappyLimitLessGap
 * @Author Duys
 * @Description
 * @Date 2022/11/3 10:09
 **/
// 来自蚂蚁金服
// 小红有n个朋友, 她准备开个宴会，邀请一些朋友
// i号朋友的愉悦值为a[i]，财富值为b[i]
// 如果两个朋友同时参加宴会，这两个朋友之间的隔阂是其财富值差值的绝对值
// 宴会的隔阂值，是财富差距最大的两人产生的财富值差值的绝对值
// 宴会的愉悦值，是所有参加宴会朋友的愉悦值总和
// 小红希望宴会的愉悦值不能小于k的情况下， 宴会的隔阂值能最小是多少
// 如果做不到，返回-1
// 1 <= n <= 2 * 10^5
// 1 <= 愉悦值、财富值 <= 10^9
// 1 <= k <= 10^14
public class Code_03_HappyLimitLessGap {

    // 数据预处理
    // 然后窗口去滑，省去二分
    // 满足欢乐值 >= k就收集
    public static int lessGap1(int[] a, int[] b, long k) {
        int n = a.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = a[i];
            arr[i][1] = b[i];
        }
        // 根据财富值排序
        Arrays.sort(arr, (q, p) -> q[1] - p[1]);
        int l = 0;
        int curHappy = 0;
        int ans = Integer.MAX_VALUE;
        for (int r = 0; r < n; r++) {
            if (curHappy < k) {
                curHappy += arr[r][0];
            }
            // 缩进
            while (curHappy >= k) {
                ans = Math.min(ans, arr[r][1] - arr[l][1]);
                curHappy -= arr[l++][0];
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    // 正式方法
    // 二分答案
    public static int lessGap2(int[] a, int[] b, long k) {
        int n = a.length;
        int[][] f = new int[n][2];
        int min = b[0];
        int max = b[0];
        for (int i = 0; i < n; i++) {
            f[i][0] = a[i];
            f[i][1] = b[i];
            min = Math.min(min, b[i]);
            max = Math.max(max, b[i]);
        }
        Arrays.sort(f, (x, y) -> x[1] - y[1]);
        int l = 0;
        int r = max - min;
        int m = 0;
        int ans = -1;
        while (l <= r) {
            m = (l + r) / 2;
            if (maxHappy(f, m) >= k) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    public static long maxHappy(int[][] f, int limit) {
        int n = f.length;
        long sum = 0;
        long ans = 0;
        for (int l = 0, r = 0; l < n; l++) {
            while (r < n && f[r][1] - f[l][1] <= limit) {
                sum += f[r++][0];
            }
            ans = Math.max(ans, sum);
            sum -= f[l][0];
            r = Math.max(r, l + 1);
        }
        return ans;
    }

    // 为了验证
    public static int[] randomArray(int n, int v) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * v) + 1;
        }
        return arr;
    }


    // 为了验证
    public static void main(String[] args) {
        int N = 15;
        int V = 20;
        int testTime = 5000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int n = (int) (Math.random() * N) + 1;
            int[] a = randomArray(n, V);
            int[] b = randomArray(n, V);
            int k = (int) (Math.random() * n * V) + 1;
            int ans1 = lessGap1(a, b, k);
            int ans2 = lessGap2(a, b, k);
            if (ans1 != ans2) {
                System.out.println("出错了!");
            }
        }
        System.out.println("功能测试结束");
    }
}
