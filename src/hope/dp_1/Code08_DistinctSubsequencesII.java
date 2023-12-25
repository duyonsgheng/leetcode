package hope.dp_1;

/**
 * @author Mr.Du
 * @ClassName Code08_DistinctSubsequencesII
 * @date 2023年12月05日 9:44
 */
// 不同的子序列 II
// 给定一个字符串 s，计算 s 的 不同非空子序列 的个数
// 因为结果可能很大，所以返回答案需要对 10^9 + 7 取余
// 字符串的 子序列 是经由原字符串删除一些（也可能不删除）
// 字符但不改变剩余字符相对位置的一个新字符串
// 例如，"ace" 是 "abcde" 的一个子序列，但 "aec" 不是
// 测试链接 : https://leetcode.cn/problems/distinct-subsequences-ii/
public class Code08_DistinctSubsequencesII {
    // 子序列以每一个字符结尾的情况下
    // 每次纯新增的等于总数减去当前字符之前的答案
    public static int distinctSubseqII(String s) {
        int mod = 1000000007;
        char[] arr = s.toCharArray();
        int[] cnt = new int[26];
        int all = 1, newAdd;
        for (char x : arr) {
            newAdd = (all - cnt[x - 'a'] + mod) % mod;
            cnt[x - 'a'] = (cnt[x - 'a'] + newAdd) % mod;
            all = (all + newAdd) % mod;
        }
        return (all - 1 + mod) % mod;
    }
}
