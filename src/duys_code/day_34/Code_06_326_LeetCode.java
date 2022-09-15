package duys_code.day_34;

/**
 * @ClassName Code_06_326_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/power-of-three/
 * @Date 2021/12/7 14:14
 **/
public class Code_06_326_LeetCode {
    // 给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false
    // 1162261467 是整数范围内，3的19次幂
    public boolean isPowerOfThree(int n) {
        return (n > 0 && 1162261467 % n == 0);
    }
}
