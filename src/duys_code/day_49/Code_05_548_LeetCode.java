package duys_code.day_49;

/**
 * @ClassName Code_05_548_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/split-array-with-equal-sum/
 * @Date 2021/10/27 17:02
 **/
public class Code_05_548_LeetCode {
    /**
     * 将数组分割成四个累加和相等得区域
     * 0 < i, i + 1 < j, j + 1 < k < n - 1
     * 子数组 (0, i - 1)，(i + 1, j - 1)，(j + 1, k - 1)，(k + 1, n - 1) 的和应该相等。
     * 思路：
     * 尝试左边给一个sum，然后从后面开始往前 这就是两个相等得区域了
     * 然后在把中间化成两个相等得区域
     */
    public static boolean splitArray(int[] nums) {
        if (nums == null || nums.length < 7) {
            return false;
        }
        // 第一个从左往右的
        int[] sumLeftToRight = new int[nums.length];
        // 第二哥从右往左的
        int[] sumRightToLeft = new int[nums.length];

        int s = 0;
        for (int i = 0; i < nums.length; i++) {
            sumLeftToRight[i] = s;
            s += nums[i];
        }
        s = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            sumRightToLeft[i] = s;
            s += nums[i];
        }
        // 至少还剩下4个，因为必须要四个区域
        for (int i = 1; i < nums.length - 5; i++) {
            // 右边至少剩下一个，左边至少剩下3个
            for (int j = nums.length - 2; j > i + 3; j--) {
                if (sumLeftToRight[i] == sumRightToLeft[j] && find(sumLeftToRight, sumRightToLeft, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean find(int[] sumLeftToRight, int[] sumRightToLeft, int l, int r) {
        int s = sumLeftToRight[l];
        int prefix = sumLeftToRight[l + 1];
        int subfix = sumRightToLeft[r - 1];
        for (int i = l + 2; i < r - 1; i++) {
            // 前缀和
            int p1 = sumLeftToRight[i] - prefix;
            // 后缀和
            int p2 = sumRightToLeft[i] - subfix;
            if (p1 == p2 && p1 == s) {
                return true;
            }
        }
        return false;
    }
}
