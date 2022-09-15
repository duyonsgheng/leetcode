package custom.code_2022_05;

/**
 * @ClassName LeetCode_96
 * @Author Duys
 * @Description
 * @Date 2022/5/17 17:41
 **/
// 96. 不同的二叉搜索树
// 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
public class LeetCode_96 {

    public static int numTrees(int n) {
        if (n <= 1) {
            return n;
        }
        // 枚举以每一个n为头
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            // 长度是2，那么以每一个<=2 的数为头
            // 长度是3，那么以每一个 <=3 的数为头
            for (int j = 1; j <= i; j++) {
                // 从我的左边去拿左树
                // 从我得右边去拿我的右树
                // 例如 长度是4
                // 1 2 3 4 ，以3为头 那就是 dp[3] * dp[1]
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
}
