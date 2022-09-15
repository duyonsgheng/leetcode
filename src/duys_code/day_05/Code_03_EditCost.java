package duys_code.day_05;

/**
 * @ClassName Code_03_EditCost
 * @Author Duys
 * @Description 经典的距离编辑问题
 * @Date 2021/9/24 13:21
 **/
public class Code_03_EditCost {
    /**
     * 距离编辑问题：比如现在有一个字符串s1 = abdcg s2 = acdcb
     * 删除一个字符的代价是a 添加一个字符的代价是b 替换一个字符的代价是c 保留一个字符不需要代价是0
     * 问把s1变成s2 最少的代价是多少
     */
    /**
     * 典型的样本对应模型：样本对应模型通常是以结束位置考虑可能性，
     * dp[i][j] - 含义 s1的前 0到 i-1 位置所有的字符 变成 s2 的前 0到j-1 位置的所有字符最小的代价
     * 可能性分析1；s1 的前0到i-2位置所有字符变成了 s2的前0到j-1位置所有字符，需要删除s1的第i-1位置的字符就变成了s2了
     * 可能性分析2：s1 的前0到i-1位置所有字符变成了 s2的前0到j-2位置所有字符，只需要s2增加一个字符就实现了s1变成了s2的最小代价了
     * 可能性分析3：s1 的前0到i-2位置所有字符 和  s2的前0到j-2位置所有字符 已经都实现了，那么s1的i-1位置和s2的j-1位置相等就不用任何代价，不相等需要进行替换
     */
    // ic -添加代价，dc-删除代价，rc- 替换代价
    public static int minCost1(String s1, String s2, int ic, int dc, int rc) {
        if (s1 == null || s2 == null) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        // N ， M 都是长度，下面进行下标转换的时候，需要 -1，下标从0开始计算，长度从1开始计算
        int N = str1.length + 1;
        int M = str2.length + 1;
        int[][] dp = new int[N][M];
        // dp[0][0] = 0 s1 取0个字符变成s2的 0个字符需要0的代价
        // 第一行含义：s1 取0个字符，变成s2 ，需要添加 需要添加 (0~M)*ic，只需要添加
        for (int j = 1; j < M; j++) {
            dp[0][j] = j * ic;
        }
        // 第一列含义：s1 变成0个字符的s2，需要删除 (0~N)*dc
        for (int i = 1; i < N; i++) {
            dp[i][0] = i * dc;
        }
        // 普遍位置就是我们的可能性
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                int p1 = dp[i - 1][j] + dc;
                int p2 = dp[i][j - 1] + ic;
                int p3 = dp[i - 1][j - 1];
                if (str1[i - 1] != str2[j - 1]) {
                    p3 += rc;
                }
                dp[i][j] = Math.min(p1, Math.min(p2, p3));
            }
        }
        return dp[N - 1][M - 1];
    }

    // 空间压缩，从上面的dp位置依赖来看，惊奇的发现每一行的格子只依赖我上一行的格子准确的来说，只依赖我上一行的和我前面的格子，可以做状态压缩
    public static int minCost2(String s1, String s2, int ic, int dc, int rc) {
        if (s1 == null || s2 == null) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        // N ， M 都是长度，下面进行下标转换的时候，需要 -1，下标从0开始计算，长度从1开始计算
        int N = str1.length;
        int M = str2.length;
        char[] longs = null;
        char[] shorts = null;
        if (N >= M) {
            longs = str1;
            shorts = str2;
        } else {
            longs = str2;
            shorts = str1;
        }

        int[] dp = new int[shorts.length + 1];
        // 第一行含义：s1 取0个字符，变成s2 ，需要添加 需要添加 (0~M)*ic，只需要添加
        for (int j = 1; j < M; j++) {
            dp[j] = j * ic;
        }
        // 普遍位置就是我们的可能性
        for (int i = 1; i <= longs.length; i++) {
            int pre = dp[0];// 记录当前行的前一个位置
            dp[0] = i * dc;// 当前行要更新了，参考方法1中，s1怎么操作才能变成s2的空，只有删除才能做到
            for (int j = 1; j <= shorts.length; j++) {
                int cur = dp[j];
                int p1 = cur + dc;
                int p2 = dp[j - 1] + ic;
                int p3 = pre;
                if (str1[i - 1] != str2[j - 1]) {
                    p3 += rc;
                }
                dp[j] = Math.min(p1, Math.min(p2, p3));
                pre = cur;
            }
        }
        return dp[shorts.length];
    }

}
