package custom.code_2022_12;

/**
 * @ClassName LeetCode_1283
 * @Author Duys
 * @Description
 * @Date 2022/12/1 15:46
 **/
// 1283. 使结果不超过阈值的最小除数
public class LeetCode_1283 {

    public int smallestDivisor(int[] nums, int threshold) {
        int r = 0;
        for (int num : nums) {
            r = Math.max(r, num);
        }
        int l = 1;
        while (l < r) {
            int m = (l + r) / 2;
            int cur = sum(nums, m);
            if (cur > threshold) {// 说明选小了
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return l;
    }

    public int sum(int[] nums, int d) {
        int total = 0;
        for (int num : nums) {
            total += (num + d - 1) / d;
        }
        return total;
    }

}
