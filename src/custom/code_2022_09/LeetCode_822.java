package custom.code_2022_09;

import java.util.Map;

/**
 * @ClassName LeetCode_822
 * @Author Duys
 * @Description
 * @Date 2022/9/23 15:56
 **/
// 822. 翻转卡片游戏
public class LeetCode_822 {
    // fronts = [1,2,4,4,7]
    // backs = [1,3,4,1,3]
    public int flipgame(int[] fronts, int[] backs) {
        int n = fronts.length;
        int max = -1;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, Math.max(fronts[i], backs[i]));
        }
        boolean[] euq = new boolean[max + 1];
        for (int i = 0; i < n; i++) {
            if (fronts[i] == backs[i]) {
                euq[fronts[i]] = true;
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (!euq[fronts[i]]) {
                ans = Math.min(ans, fronts[i]);
            }
            if (!euq[backs[i]]) {
                ans = Math.min(ans, backs[i]);
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
