package custom.code_2022_08;

/**
 * @ClassName LeetCode_633
 * @Author Duys
 * @Description
 * @Date 2022/8/30 17:00
 **/
// 633. 平方数之和
public class LeetCode_633 {
    public boolean judgeSquareSum(int c) {
        long left = 0;
        long right = (long) Math.sqrt(c);
        while (left <= right) {
            long sum = left * left + right * right;
            if (sum == c) {
                return true;
            } else if (sum > c) {
                right--;
            } else {
                left++;
            }
        }
        return false;
    }
}
