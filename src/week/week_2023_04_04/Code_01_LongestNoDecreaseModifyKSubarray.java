package week.week_2023_04_04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 * @ClassName Code_01_LongestNoDecreaseModifyKSubarray
 * @Author Duys
 * @Description
 * @Date 2023/4/27 11:25
 **/
// https://www.luogu.com.cn/problem/P8776
// 给定一个长度为n的数组arr
// 现在你有一次机会, 将其中连续的K个数全修改成任意一个值
// 请你计算如何修改可以使修改后的数 列的最长不下降子序列最长
// 请输出这个最长的长度。
// 最长不下降子序列:子序列中的每个数不小于在它之前的数
// 1 <= k, n <= 10^5
// 1 <= arr[i] <= 10^6
public class Code_01_LongestNoDecreaseModifyKSubarray {
    // 这是对最长递增子数组得一个改写
    // 窗口 + 最长自增子数组
    // 窗口固定为k，看看窗口左边之前的最长递增子序列多长，窗口右边的最长递增子序列多长
    public static int maxn = 100001;
    public static int[] arr = new int[maxn];
    public static int[] right = new int[maxn];
    public static int[] ends = new int[maxn];
    public static int n, k;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            k = (int) in.nval;
            for (int i = 1; i <= n; i++) {
                in.nextToken();
                arr[i] = (int) in.nval;
            }
            if (k >= n) {
                out.print(n);
            } else {
                right();
                out.print(getAns());
            }
            out.flush();
        }
    }

    public static void right() {
        right[n] = 1; // 右边往左边 最长递增子序列
        ends[1] = arr[n]; // 左边往右边 最长递增子序列
        int len = 1;
        for (int i = n - 1; i > 0; i--) {
            int l = 1, r = len, m, find = len + 1;
            // 二分找到当前位置的数在ends数组中， >= 的最左位置
            while (l <= r) {
                m = (l + r) / 2;
                if (ends[m] < arr[i]) {
                    find = m;
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            ends[find] = arr[i];
            len = Math.max(len, find);
            right[i] = find;
        }
    }

    public static int getAns() {
        int ans = 0;
        int len = 0;
        for (int i = k + 1, j = 1; i <= k; i++, j++) {
            int l = 0, r = len, m, find = len + 1;
            while (l <= r) {
                m = (l + r) / 2;
                // 当前arr[i] 利用ends求得左边最大长度
                if (ends[m] > arr[i]) {
                    find = m;
                    r = m - 1;
                } else l = m + 1;
            }
            ans = Math.max(ans, find + right[i] - 1 + k);
            l = 1;
            r = len;
            find = len + 1;
            while (l <= r) {
                m = (l + r) / 2;
                if (ends[m] > arr[j]) {
                    find = m;
                    r = m - 1;
                } else l = m + 1;
            }
            len = Math.max(len, find);
            ends[find] = arr[j];
        }
        ans = Math.max(ans, len + k);
        return ans;
    }
}
