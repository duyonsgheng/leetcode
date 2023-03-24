package week.week_2023_02_01;

import java.util.TreeMap;

/**
 * @ClassName Code_02_LeetCode_975
 * @Author Duys
 * @Description
 * @Date 2023/2/2 9:36
 **/
// 975. 奇偶跳
public class Code_02_LeetCode_975 {
    // 分析。
    // 从前往后跳，奇数的时候只能跳到右边，大于等于当前且最小的值的位置，如果多个相同的，则最左的位置
    // 从前往后跳，偶数的时候只能跳到右边，小于等于当前且最大的值的位置，如果多个相同的，则最左的位置
    public int oddEvenJumps(int[] arr) {
        int n = arr.length;
        // 来到i位置，如果是奇数，则该去odd[i]
        int[] odd = new int[n];
        // 来到i位置，如果是偶数，则该去even[i]
        int[] even = new int[n];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = n - 1; i >= 0; i--) {
            // 奇数规则
            Integer to = map.ceilingKey(arr[i]);
            odd[i] = to == null ? -1 : map.get(to);
            // 偶数规则
            to = map.floorKey(arr[i]);
            even[i] = to == null ? -1 : map.get(to);
            map.put(arr[i], i);
        }
        // 0-奇数规则
        // 1-偶数规则
        boolean[][] dp = new boolean[n][2];
        dp[n - 1][0] = true;
        dp[n - 1][1] = true;
        int ans = 1;
        for (int i = n - 2; i >= 0; i--) {
            // 奇数规则下，能跳到下一个位置，并且下一个位置的偶数规则能跳到后面，则当前可以跳
            dp[i][0] = odd[i] != -1 && dp[odd[i]][1];
            dp[i][1] = even[i] != -1 && dp[even[i]][0];
            ans += dp[i][0] ? 1 : 0;
        }
        return ans;
    }
}
