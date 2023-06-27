package week.week_2023_06_02;

import java.util.Arrays;

/**
 * @ClassName Code_04_LeetCode_646
 * @Author Duys
 * @Description
 * @Date 2023/6/15 10:04
 **/
// https://leetcode.cn/problems/maximum-length-of-pair-chain/
public class Code_04_LeetCode_646 {

    // 使用 最长递增子序列
    public static int findLongestChain(int[][] pairs) {
        int n = pairs.length;
        // 按照开始位置排个序
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        int[] ends = new int[n];
        int size = 0;
        for (int[] pair : pairs) {
            int l = 0, r = size - 1, m = 0;
            int find = -1;
            while (l <= r) {
                m = (l + r) / 2;
                if (ends[m] > pair[0]) {
                    find = m;
                    r = m - 1;
                } else l = m + 1;
            }
            if (find == -1) {
                ends[size++] = pair[1];
            } else {
                ends[find] = Math.min(ends[find], pair[1]);
            }
        }
        return size;
    }
}
