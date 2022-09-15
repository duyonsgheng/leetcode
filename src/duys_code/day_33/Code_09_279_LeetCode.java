package duys_code.day_33;

/**
 * @ClassName Code_09_279_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/perfect-squares/
 * @Date 2021/12/6 15:35
 **/
public class Code_09_279_LeetCode {
    // 四平方和定理
    // 任何一个数都由不会超过4个数平方和，就是拆成平方数的项不会超过四项

    // 数学解
    // 1）四平方和定理
    // 2）任何数消掉4的因子，结论不变
    public static int numSquares(int n) {
        while (n % 4 == 0) {
            n /= 4;
        }
        if (n % 8 == 7) {
            return 4;
        }
        for (int a = 0; a * a <= n; ++a) {
            // a * a +  b * b = n
            int b = (int) Math.sqrt(n - a * a);
            if (a * a + b * b == n) {
                return (a > 0 && b > 0) ? 2 : 1;
            }
        }
        return 3;
    }
}
