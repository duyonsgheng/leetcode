package duys_code.day_32;

/**
 * @ClassName Code_05_172_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/factorial-trailing-zeroes/
 * @Date 2021/12/6 10:20
 **/
public class Code_05_172_LeetCode {
    // n 的阶乘有多个个0再尾巴上
    // 相乘是0的情况，2*5 末尾是0
    // 1到10有几个5 1个
    // 11 - 20   15 - 3个 20 -4个

    public int trailingZeroes(int n) {
        int ans = 0;
        while (n != 0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }
}
