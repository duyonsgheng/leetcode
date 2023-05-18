package week.week_2023_05_02;

import java.util.Arrays;

/**
 * @ClassName Code_01_ImageArrayAverageGotoHalf
 * @Author Duys
 * @Description
 * @Date 2023/5/15 11:10
 **/
// 来自华为OD，学员问题
// 一个图像有n个像素点，存储在一个长度为n的数组arr里，
// 每个像素点的取值范围[0,s]的整数
// 请你给图像每个像素点值加上一个整数k（可以是负数）
// 像素值会自动截取到[0,s]范围，
// 当像素值<0，会更改为0，当新像素值>s，会更改为s
// 这样就可以得到新的arr，想让所有像素点的平均值最接近中位值s/2, 向下取整
// 请输出这个整数k, 如有多个整数k都满足, 输出小的那个
// 1 <= n <= 10^6
// 1 <= s <= 10^18
public class Code_01_ImageArrayAverageGotoHalf {
    // 排序+二分
    // 从 -s 到 s二分
    public static int best(int[] arr, int s) {
        Arrays.sort(arr);
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        int l = -s;
        int r = s;
        int m = 0;
        int limit = s / 2;
        int average = -s;
        int ans = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            int cur = average(arr, sum, mid, s);
            if (Math.abs(limit - cur) < Math.abs(limit - average) || ((Math.abs(limit - cur) == Math.abs(limit - average)) && mid < ans)) {
                ans = mid;
                average = cur;
            }
            if (cur >= limit) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    public static int average(int[] arr, int[] sum, int k, int s) {
        int n = arr.length;

        if (k < 0) {
            // 找到 arr[i]+k>=0 得最右位置，那么从0到这个位置都是0
            int l = bsZero(arr, k);
            int sum1 = rangeSum(sum, l + 1, n - 1);
            return (sum1 + (k * (n - l - 1))) / n;
        } else {
            // 大于0，那么就需要找到超过s的位置
            int r = bsS(arr, k, s);
            int sum1 = rangeSum(sum, 0, r - 1);
            return (sum1 + (k * r) + (s * (n - r))) / n;
        }
    }

    // >= 0 的最右位置
    public static int bsZero(int[] arr, int k) {
        int ans = -1, l = 0, r = arr.length - 1, m = 0;
        while (l <= r) {
            m = (l + r) / 2;
            if (arr[m] + k <= 0) {
                ans = m;
                l = m + 1;
            } else r = m - 1;
        }
        return ans;
    }

    // >= s 的最左位置
    public static int bsS(int[] arr, int k, int s) {
        int ans = -1, l = 0, r = arr.length - 1, m = 0;
        while (l <= r) {
            m = (l + r) / 2;
            if (arr[m] + k >= s) {
                ans = m;
                r = m - 1;
            } else l = m + 1;
        }
        return ans;
    }

    public static int rangeSum(int[] pre, int l, int r) {
        if (l > r) {
            return 0;
        }
        return l == 0 ? pre[r] : (pre[r] - pre[l - 1]);
    }

}
