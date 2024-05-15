package hope.class83_enum2;

import java.io.*;

/**
 * @author Mr.Du
 * @ClassName Code04_LongestSubarraySumNoMoreK
 * @date 2024年05月15日 下午 02:47
 */
// 累加和不大于k的最长子数组
// 给定一个无序数组arr，长度为n，其中元素可能是正、负、0
// 给定一个整数k，求arr所有的子数组中累加和不大于k的最长子数组长度
// 要求时间复杂度为O(n)
// 测试链接 : https://www.nowcoder.com/practice/3473e545d6924077a4f7cbc850408ade
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class Code04_LongestSubarraySumNoMoreK {
    public static int MAXN = 100001;

    public static int[] nums = new int[MAXN];
    public static int[] minSums = new int[MAXN];

    public static int[] minSumEnds = new int[MAXN];

    public static int n, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            k = (int) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                nums[i] = (int) in.nval;
            }
            out.println(compute1());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute2() {
        minSums[n - 1] = nums[n - 1];
        minSumEnds[n - 1] = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            if (minSums[i + 1] < 0) {
                minSums[i] = nums[i] + minSums[i + 1];
                minSumEnds[i] = minSumEnds[i + 1];
            } else {
                minSums[i] = nums[i];
                minSumEnds[i] = i;
            }
        }
        int ans = 0;
        for (int i = 0, sum = 0, end = 0; i < n; i++) {
            while (end < n && sum + minSums[end] <= k) {
                sum += minSums[end];
                end = minSumEnds[end] + 1;
            }
            if (end > i) {
                // 窗口范围：i...end-1，那么窗口有效
                ans = Math.max(ans, end - i);
                sum -= nums[i];
            } else {
                // 如果end == i，那么说明窗口根本没扩出来，代表窗口无效
                // end来到i+1位置，然后i++了
                // 继续以新的i位置做开头去扩窗口
                end = i + 1;
            }
        }
        return ans;
    }

    // 普通的解答
    public static int compute1() {
        int[] sums = new int[n + 1];
        for (int i = 0, sum = 0; i < n; i++) {
            sum += nums[i];
            //前i+1个，包括一个数也没有的时候，所有累加和最大的值
            sums[i + 1] = Math.max(sum, sums[i]);
        }
        int ans = 0;
        for (int i = 0, sum = 0, pre, len; i < n; i++) {
            sum += nums[i];
            // 100  k = 80，那么就去前面找有没有 <=20的最左位置
            pre = find(sums, sum - k);
            len = pre == -1 ? 0 : i - pre + 1;
            ans = Math.max(ans, len);
        }
        return ans;
    }

    public static int find(int[] sums, int num) {
        int l = 0, r = n, m = 0, ans = -1;
        while (l <= r) {
            m = (l + r) / 2;
            if (sums[m] >= num) {
                ans = m;
                r = m - 1;
            } else l = m + 1;
        }
        return ans;
    }
}
