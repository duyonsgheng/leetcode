package week.week_2023_05_02;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * @ClassName Code_05_LeetCode_2612
 * @Author Duys
 * @Description
 * @Date 2023/5/15 14:45
 **/
// 2612. 最少翻转操作数
public class Code_05_LeetCode_2612 {
    public int[] minReverseOperations(int n, int p, int[] banned, int k) {
        TreeSet<Integer> oddSet = new TreeSet<>(); // 奇数下标
        TreeSet<Integer> evenSet = new TreeSet<>(); //偶数下标
        for (int i = 1; i < n; i += 2)
            oddSet.add(i);
        for (int i = 0; i < n; i += 2)
            evenSet.add(i);
        for (int i : banned) {
            oddSet.remove(i);
            evenSet.remove(i);
        }
        oddSet.remove(p);
        evenSet.remove(p);
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        int[] queue = new int[n];
        int l = 0, r = 0;
        queue[r++] = p;
        int leve = 0;
        while (l < r) {
            int end = r;
            // 宽度优先
            for (; l < end; l++) {
                int cur = queue[l];
                ans[cur] = leve;
                int left = Math.max(cur - k + 1, k - cur - 1);
                int right = Math.min(cur + k - 1, n * 2 - k - cur - 1);
                TreeSet<Integer> curSet = (left & 1) == 1 ? oddSet : evenSet;
                Integer ceiling = curSet.ceiling(left);
                while (ceiling != null && ceiling <= right) {
                    queue[r++] = ceiling;
                    curSet.remove(ceiling);
                    ceiling = curSet.ceiling(left);
                }
            }
            leve++;
        }
        return ans;
    }
}
