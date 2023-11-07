package custom.code_2023_08;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2145
 * @date 2023年08月31日
 */
// 2145. 统计隐藏数组数目
// https://leetcode.cn/problems/count-the-hidden-sequences/
public class LeetCode_2145 {
    public int numberOfArrays(int[] differences, int lower, int upper) {
        int x = 0, y = 0, cur = 0;
        for (int i : differences) {
            cur += i;
            x = Math.min(x, cur);
            y = Math.max(y, cur);
            if (y - x > upper - lower) {
                return 0;
            }
        }
        return upper - lower - (y - x) + 1;
    }
}
