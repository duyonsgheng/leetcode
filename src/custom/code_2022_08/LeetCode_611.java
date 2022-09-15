package custom.code_2022_08;

import java.util.Arrays;

/**
 * @ClassName LeetCode_611
 * @Author Duys
 * @Description
 * @Date 2022/8/29 17:40
 **/
// 611. 有效三角形的个数
public class LeetCode_611 {
    public int triangleNumber(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        int n = nums.length;
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int index = i;
            // 两条边确定，找第三条边
            for (int j = i + 1; j < n; j++) {
                while (index + 1 < n && nums[i] + nums[j] > nums[index + 1]) {
                    index++;
                }
                ans += Math.max(index - j, 0);
            }
        }
        return ans;
    }
}
