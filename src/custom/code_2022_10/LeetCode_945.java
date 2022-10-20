package custom.code_2022_10;

import java.util.Arrays;

/**
 * @ClassName LeetCode_945
 * @Author Duys
 * @Description
 * @Date 2022/10/19 14:37
 **/
// 945. 使数组唯一的最小增量
public class LeetCode_945 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 2};
        System.out.println(minIncrementForUnique(arr));
    }

    public static int minIncrementForUnique(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int min = nums[0];
        int ans = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] <= min) {
                min++;
                ans += min - nums[i];
            } else {
                min = nums[i];
            }
        }
        return ans;
    }
}
