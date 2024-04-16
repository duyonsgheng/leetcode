package hope.subArray3;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code04_MaximumLengthOfPairChain
 * @date 2023年12月28日 14:22
 */
// 最长数对链
// 给你一个由n个数对组成的数对数组pairs
// 其中 pairs[i] = [lefti, righti] 且 lefti < righti
// 现在，我们定义一种 跟随 关系，当且仅当 b < c 时
// 数对 p2 = [c, d] 才可以跟在 p1 = [a, b] 后面
// 我们用这种形式来构造 数对链
// 找出并返回能够形成的最长数对链的长度
// 测试链接 : https://leetcode.cn/problems/maximum-length-of-pair-chain/
public class Code04_MaximumLengthOfPairChain {
    public static int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        int n = pairs.length;
        int[] ends = new int[n];
        int len = 0;
        for (int i = 0, find; i < n; i++) {
            find = bs(ends, len, pairs[i][0]);
            if (find == -1) {
                ends[len++] = pairs[i][1];
            } else
                ends[find] = Math.min(ends[find], pairs[i][1]);
        }
        return len;
    }

    public static int bs(int[] ends, int len, int num) {
        int l = 0, r = len - 1, m, ans = -1;
        while (l <= r) {
            m = (l + r) / 2;
            if (ends[m] >= num) {
                ans = m;
                r = m - 1;
            } else l = m + 1;
        }
        return ans;
    }
}
