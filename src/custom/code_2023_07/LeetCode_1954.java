package custom.code_2023_07;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1954
 * @date 2023年07月14日
 */
// 1954. 收集足够苹果的最小花园周长
// https://leetcode.cn/problems/minimum-garden-perimeter-to-collect-enough-apples/
public class LeetCode_1954 {
    // 二分答案
    public long minimumPerimeter(long neededApples) {
        long l = 0, r = 1_000_000l, ans = 0;
        while (l <= r) {
            // 边长
            long m = l + (r - l) / 2;
            // 边长为m的时候，可以有几个苹果
            if (2 * m * (m + 1) * (m * 2 + 1) >= neededApples) {
                ans = m;
                r = m - 1;
            } else l = m + 1;
        }
        return ans * 8;
    }
}
