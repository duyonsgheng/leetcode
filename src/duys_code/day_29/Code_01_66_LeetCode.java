package duys_code.day_29;

/**
 * @ClassName Code_01_66_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/plus-one/
 * @Date 2021/11/25 13:58
 **/
public class Code_01_66_LeetCode {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        // 因为只需要加+1
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] s = new int[n + 1];
        s[0] = 1;
        return s;
    }
}
