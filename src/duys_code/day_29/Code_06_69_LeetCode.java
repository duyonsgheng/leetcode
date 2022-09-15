package duys_code.day_29;

/**
 * @ClassName Code_06_69_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/sqrtx/
 * @Date 2021/11/25 17:32
 **/
public class Code_06_69_LeetCode {

    // 使用二分
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        if (x < 3) {
            return 1;
        }
        long ans = 1;
        long L = 1;
        long R = x;
        long M = 0;
        while (L <= R) {
            M = L + ((R - L) >> 1);
            if (M * M > x) {
                R = M - 1;
            } else {
                ans = M;
                L = M + 1;
            }
        }
        return (int) ans;
    }
}
