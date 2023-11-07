package custom.code_2023_09;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2162
 * @date 2023年09月05日
 */
// 2162. 设置时间的最少代价
// https://leetcode.cn/problems/minimum-cost-to-set-cooking-time/?envType=daily-question&envId=2023-09-05
public class LeetCode_2162 {

    // 模拟
    public int minCostSetTime(int startAt, int moveCost, int pushCost, int targetSeconds) {
        int m1 = targetSeconds / 60;
        int s1 = targetSeconds % 60;
        int ans1 = count(startAt, moveCost, pushCost, m1, s1);

        m1 = m1 - 1;
        s1 = s1 + 60;
        int ans2 = count(startAt, moveCost, pushCost, m1, s1);

        return Math.min(ans2, ans1);
    }

    public int count(int s, int m, int p, int min, int sec) {
        if (min > 99 || min < 0 || sec > 99) {
            return Integer.MAX_VALUE;
        }
        int ans = 0;
        int[] arr = new int[4];
        arr[0] = min / 10;
        arr[1] = min % 10;
        arr[2] = sec / 10;
        arr[3] = sec % 10;
        int i = 0;
        while (arr[i] == 0)
            i++;
        int cur = s;
        while (i < 4) {
            if (arr[i] == cur) {
                ans += p;
            } else {
                ans += m + p;
                cur = arr[i];
            }
            i++;
        }
        return ans;
    }
}
