package hope.class76_rangeDP1;

/**
 * @author Mr.Du
 * @ClassName Code06_BooleanEvaluation
 * @date 2024年03月15日 11:24
 */
// 布尔运算
// 给定一个布尔表达式和一个期望的布尔结果 result
// 布尔表达式由 0 (false)、1 (true)、& (AND)、 | (OR) 和 ^ (XOR) 符号组成
// 布尔表达式一定是正确的，不需要检查有效性
// 但是其中没有任何括号来表示优先级
// 你可以随意添加括号来改变逻辑优先级
// 目的是让表达式能够最终得出result的结果
// 返回最终得出result有多少种不同的逻辑计算顺序
// 测试链接 : https://leetcode.cn/problems/boolean-evaluation-lcci/
public class Code06_BooleanEvaluation {
    // 记忆化搜索
    public static int countEval(String str, int result) {
        char[] s = str.toCharArray();
        int n = s.length;
        int[][][] dp = new int[n][n][];
        int[] ft = f1(s, 0, n - 1, dp);
        return ft[result];
    }

    // 返回值 0表示为0的答案 1表示1的答案
    // arr[l...r] 满足 0/1  符号 0/1 符号 0/1 符号 .... 0/1
    public static int[] f1(char[] arr, int l, int r, int[][][] dp) {
        if (dp[l][r] != null) {
            return dp[l][r];
        }
        int f = 0;
        int t = 0;
        if (l == r) {// 只剩下一个字符
            f = arr[l] == '0' ? 1 : 0;
            t = arr[l] == '1' ? 1 : 0;
        } else {
            int[] tmp;
            for (int k = l + 1, a, b, c, d; k < r; k += 2) {
                // 枚举每一个逻辑符号
                // 左边的
                tmp = f1(arr, l, k - 1, dp);
                a = tmp[0];
                b = tmp[1];
                tmp = f1(arr, k + 1, r, dp);
                c = tmp[0];
                d = tmp[1];
                if (arr[k] == '&') {
                    f += a * c + a * d + b * c;
                    t += b * d;
                } else if (arr[k] == '|') {
                    f += a * c;
                    t += a * d + b * c + b * d;
                } else {
                    f += a * c + b * d;
                    t += a * d + b * c;
                }
            }
        }
        int[] ft = new int[]{f, t};
        dp[l][r] = ft;
        return ft;
    }
}
