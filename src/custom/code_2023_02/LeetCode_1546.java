package custom.code_2023_02;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName LeetCode_1546
 * @Author Duys
 * @Description
 * @Date 2023/2/10 9:35
 **/
// 1546. 和为目标值且不重叠的非空子数组的最大数目
public class LeetCode_1546 {
    public static int maxNonOverlapping(int[] nums, int target) {
        int n = nums.length;
        // -2,6,6,3,5,4,1,2,8   10
        int ans = 0;
        Set<Integer> set = new HashSet<>();
        set.add(0);
        for (int i = 0, sum = 0; i < n; i++) {
            sum += nums[i];
            // 如果sum == target
            if (set.contains(sum - target)) {
                ans++;
                set = new HashSet<>();
                set.add(0);
                sum = 0;
            } else {
                set.add(sum);
            }
        }
        return ans;
    }

    // [-5,5,-4,5,4]
    // 5
    public static void main(String[] args) {
        System.out.println(maxNonOverlapping(new int[]{-2, 6, 6, 3, 5, 4, 1, 2, 8}, 10));
        System.out.println(maxNonOverlapping(new int[]{-5, 5, -4, 5, 4}, 5));
    }
}
