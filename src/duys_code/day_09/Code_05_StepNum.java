package duys_code.day_09;

/**
 * @ClassName Code_06_StepNum
 * @Author Duys
 * @Description
 * @Date 2021/10/12 11:09
 **/
public class Code_05_StepNum {

    /**
     * 题意：
     * 定义何为step sum？
     * 比如680，680 + 68 + 6 = 754，680的step sum叫754
     * 给定一个正数num，判断它是不是某个数的step sum
     */
    /**
     * 思路1：可以使用公式来做 比如这个数是X  x /= 10 然后相加 （一个while循环，然后 ans = x/=10 直到ans =0停止），枚举所有的可能 一个O(X)
     * 思路2：二分，我们想啊比如现在 和为754，判断是不是某一个数的步骤和，这个数不可能大于754 .所以我们使用2分。
     */
    public static boolean isStepNum(int num) {
        int L = 0;
        int R = num;
        int M = 0;
        int cur = 0;
        while (L <= R) {
            M = L + ((R - L) >> 1);
            cur = stepNum(M);
            if (cur == num) {
                return true;
            } else if (cur < num) {
                L = M + 1;
            } else {
                R = M - 1;
            }
        }
        return false;
    }

    public static int stepNum(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num;
            num /= 10;
        }
        return sum;
    }
}
