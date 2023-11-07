package custom.code_2023_09;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2177
 * @date 2023年09月06日
 */
// 2177. 找到和为给定整数的三个连续整数
// https://leetcode.cn/problems/find-three-consecutive-integers-that-sum-to-a-given-number/
public class LeetCode_2177 {
    public long[] sumOfThree(long num) {
        if (num % 3 == 0) {
            return new long[]{num / 3 - 1, num / 3, num / 3 + 1};
        }
        return new long[]{};
    }
}
