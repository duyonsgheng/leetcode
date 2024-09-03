package hope.class86_dpPath1;

import java.io.*;
import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code03_LIS
 * @date 2024年05月20日 下午 04:15
 */
// 最长递增子序列字典序最小的结果
// 给定数组arr，设长度为n
// 输出arr的最长递增子序列
// 如果有多个答案，请输出其中字典序最小的
// 注意这道题的字典序设定（根据提交的结果推论的）：
// 每个数字看作是单独的字符，比如120认为比36的字典序大
// 保证从左到右每个数字尽量小
// 测试链接 : https://www.nowcoder.com/practice/30fb9b3cab9742ecae9acda1c75bf927
// 测试链接 : https://www.luogu.com.cn/problem/T386911
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class Code03_LIS {
    public static int MAXN = 100001;
    public static int[] nums = new int[MAXN];
    public static int[] dp = new int[MAXN];
    public static int[] ends = new int[MAXN];
    public static int[] ans = new int[MAXN];
    public static int n, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                nums[i] = (int) in.nval;
            }
            lis();
            for (int i = 0; i < k - 1; i++) {
                out.print(ans[i] + " ");
            }
            out.println(ans[k - 1]);
        }
        out.flush();
        out.close();
        br.close();
    }

    public static void lis() {
        k = dp();
        Arrays.fill(ans, 0, k, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            if (dp[i] == k) { // 直接填
                ans[0] = nums[i];
            } else {
                // 如果上一个位置是小于当前的，当前位置可以直接更新，因为dp含义已经确定了，当前位置比较小，而且长度合适
                if (ans[k - dp[i] - 1] < nums[i]) {
                    dp[k - dp[i]] = nums[i];
                }
            }
        }
    }

    // dp[i] ：必须以i位置数字开头的情况下，最长递增子序列的长度
    public static int dp() {
        int len = 0;
        // 从右往左递推
        for (int i = n - 1, find; i >= 0; i--) {
            find = bs(len, nums[i]);
            if (find == -1) {
                ends[len++] = nums[i];
                dp[i] = len;
            } else {
                ends[find] = nums[i];
                dp[i] = find + 1;
            }
        }
        return len;
    }

    // ends数组中从大到小
    // 二分找到 <= num最左的位置
    public static int bs(int len, int num) {
        int l = 0, r = len - 1, m, ans = -1;
        while (l <= r) {
            m = (l + r) / 2;
            if (ends[m] <= num) {
                r = m - 1;
                ans = m;
            } else l = m + 1;
        }
        return ans;
    }
}
