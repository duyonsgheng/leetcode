package custom.code_2023_02;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * @ClassName LeetCode_1509
 * @Author Duys
 * @Description
 * @Date 2023/2/3 16:45
 **/
// 1509. 三次操作后最大值与最小值的最小差
public class LeetCode_1509 {

    public int minDifference(int[] nums) {
        // 排序找到最大的四个数 和最小的四个数
        if (nums.length <= 4) {
            return 0;
        }
        Arrays.sort(nums);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            ans = Math.min(nums[nums.length - 4 + i] - nums[i],ans);
        }
        return ans;
    }

    public static void main(String[] args) {

    }
}
