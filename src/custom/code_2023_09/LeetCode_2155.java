package custom.code_2023_09;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2155
 * @date 2023年09月04日
 */
// 2155. 分组得分最高的所有下标
// https://leetcode.cn/problems/all-divisions-with-the-highest-score-of-a-binary-array/?envType=daily-question&envId=2023-09-01
public class LeetCode_2155 {
    public static List<Integer> maxScoreIndices(int[] nums) {
        // nums左侧多少0和右侧多少1
        //  0,0,1,0
        //  0 1 2 2 3
        //  1 1 1 0 0
        int n = nums.length;
        int[] left = new int[n + 1];
        int[] right = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            left[i] = left[i - 1] + (nums[i - 1] == 1 ? 0 : 1);
        }
        for (int i = n - 1; i >= 0; i--) {
            right[i] = right[i + 1] + (nums[i] == 1 ? 1 : 0);
        }
        List<Integer> ans = new ArrayList<>();
        int max = -1;
        for (int i = 0; i <= n; i++) {
            max = Math.max(max, left[i] + right[i]);
        }
        for (int i = 0; i <= n; i++) {
            if (left[i] + right[i] == max) {
                ans.add(i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        maxScoreIndices(new int[]{0, 0, 1, 0});
    }
}
