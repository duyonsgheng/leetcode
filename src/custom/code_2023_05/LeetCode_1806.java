package custom.code_2023_05;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1806
 * @Author Duys
 * @Description
 * @Date 2023/5/23 9:43
 **/
// 1806. 还原排列的最少操作步数
// https://leetcode.cn/problems/minimum-number-of-operations-to-reinitialize-a-permutation/
public class LeetCode_1806 {
    public int reinitializePermutation(int n) {
        int[] pre = new int[n];
        int[] target = new int[n];
        for (int i = 0; i < n; i++) {
            pre[i] = i;
            target[i] = i;
        }
        int ans = 0;
        while (true) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                if ((i & 1) != 0) {
                    arr[i] = pre[n / 2 + (i - 1) / 2];
                } else {
                    arr[i] = pre[i / 2];
                }
            }
            pre = arr;
            ans++;
            if (Arrays.equals(pre, target)) {
                break;
            }
        }
        return ans;
    }

    public int reinitializePermutation1(int n) {
        int i = 1, ans = 1;
        while (true) {
            i = i % 2 == 0 ? i / 2 : (n - 1 + i) / 2;
            if (i == 1) {
                return ans;
            }
            ans++;
        }
    }
}
