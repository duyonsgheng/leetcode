package custom.code_2023_08;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2139
 * @date 2023年08月31日
 */
// 2139. 得到目标值的最少行动次数
// https://leetcode.cn/problems/minimum-moves-to-reach-target-score/
public class LeetCode_2139 {
    // 反向操作+贪心
    public int minMoves(int target, int maxDoubles) {
        int ans = 0;
        while (maxDoubles != 0 && target != 1) {
            ans++;
            if (target % 2 == 1) {
                target--;
            } else {
                maxDoubles--;
                target /= 2;
            }
        }
        // 如果 maxDoubles =0 了，表示不能减半了。
        ans += (target - 1);
        return ans;
    }
}
