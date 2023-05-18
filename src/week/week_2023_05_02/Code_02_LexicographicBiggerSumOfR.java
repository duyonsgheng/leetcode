package week.week_2023_05_02;

/**
 * @ClassName Code_02_LexicographicBiggerSumOfR
 * @Author Duys
 * @Description
 * @Date 2023/5/15 11:38
 **/
// 塔子哥最近在处理一些字符串相关的任务
// 他喜欢 R 字符，因为在某些任务中，这个字符通常表示“正确”的结果
// 另一方面，他不喜欢 B 字符，因为在某些任务中，这个字符通常表示“错误”的结果
// 为了解决他的任务，塔子哥定义了字符串的权值为字符串中 R 字符的出现次数
// 例如，对于字符串 BBRBRB，它的权值为 2，因为其中有 2 个 R 字符
// 现在，塔子哥面临一个问题，他有一个长度为 n 的字符串 s，它仅由 R 和 B 组成
// 他想知道，长度为 n 的仅由 R 和 B组成的字符串中，
// 字典序不小于 s 的字符串的权值之和是多少？
// 因此，他需要编写一个程序来解决这个问题
// 输入第一行为一个整数 n ，表示字符串的长度
// 输入第二行为一个长度为 n 的字符串 s ，字符串中元素组成仅为 R 和 B
// 输出一个整数，代表长度为 n 的、字典序不小于 s 的字符串权值之和
// 输入样例：
// 3
// RBR
// 输出：
// 7
// 解释：共有 3 个字符串字典序大于等于"RBR"，RBR权值为2，RRB为2，RRR为3
// 1 <= n <= 100000
// 结果可能很大，对1000000007取模
// 帖子链接 : https://www.mashibing.com/question/detail/67223
public class Code_02_LexicographicBiggerSumOfR {
    // 先打表
    // 如果当前已经是B了，那么当前位置可以填R也可以填B
    // 如果当前位置是R了，那么当前位置不能变
    public static int max = 100001;
    // 长度为i的字符串一共有多少RB的字串
    public static int[] pow2 = new int[max];
    // 长度为i的字符串一共有多少权值和
    public static int[] f = new int[max];
    public static int mod = 1_000_000_007;

    static {
        pow2[0] = 1; // 空串
        for (int i = 1; i < max; i++) {
            pow2[i] = (pow2[i - 1] * 2) % mod;
        }
        f[1] = 1;
        for (int i = 1; i < max; i++) {
            // 2^(i-1) + 2*f[i-1]
            f[i] = (pow2[i - 1] + f[i - 1]) % mod;
            f[i] = (f[i] + f[i - 1]) % mod;
        }
    }

    public static int ways1(String str) {
        int n = str.length();
        char[] arr = str.toCharArray();
        int[] num = new int[n];
        num[0] = arr[0] == 'R' ? 1 : 0;
        for (int i = 1; i < n; i++) {
            num[i] = num[i - 1] + (arr[i] == 'R' ? 1 : 0);
        }
        return process(arr, num, n, 0);
    }

    // 0...index-1位置上已经决定过了，接下来填写index位置了，答案都在num中
    public static int process(char[] arr, int[] num, int n, int index) {
        int ans = 0;
        if (index == n) {
            return num[n - 1];
        } else {
            // 当前位置是R，只能是R了，
            // 当前位置是B，可以是B，可以是R
            if (arr[index] == 'R') {
                ans = process(arr, num, n, index + 1);
            } else {
                // 当前位置是R，那么后续不需要递归了，因为后续所有的串都比原串更大
                int p1 = (int) (((long) (num[index] + 1) * pow2[n - index - 1]) % mod);
                p1 = (p1 + f[n - index - 1]) % mod;
                // 当前位置继续是B
                int p2 = process(arr, num, n, index + 1);
                ans = (p1 + p2) % mod;
            }
        }
        return ans;
    }

    public static int ways2(String str) {
        int n = str.length();
        char[] arr = str.toCharArray();
        int[] num = new int[n];
        num[0] = arr[0] == 'R' ? 1 : 0;
        for (int i = 1; i < n; i++) {
            num[i] = num[i - 1] + (arr[i] == 'R' ? 1 : 0);
        }
        int[] dp = new int[n + 1];
        dp[n] = num[n - 1];
        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] == 'R') {
                dp[i] = dp[i + 1];
            } else {
                // 当前位置是R，那么后续不需要递归了，因为后续所有的串都比原串更大
                int p1 = (int) (((long) (num[i] + 1) * pow2[n - i - 1]) % mod);
                p1 = (p1 + f[n - i - 1]) % mod;
                // 当前位置继续是B
                int p2 = dp[i + 1];
                dp[i] = (p1 + p2) % mod;
            }
        }
        return dp[0];
    }

}
