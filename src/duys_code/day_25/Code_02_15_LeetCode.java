package duys_code.day_25;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Code_02_15_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/3sum/
 * @Date 2021/11/17 11:11
 **/
public class Code_02_15_LeetCode {
    /**
     * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组。
     */
    // 三元组。我们首先思考二元组问题，当前来到 i位置 ，0~i-1 位置上有多少个和是 -arr[i] 的二元组
    public static List<List<Integer>> threeSum(int[] nums) {
        // 首先排序
        Arrays.sort(nums);
        int N = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = N - 1; i >= 0; i--) {
            // 当我们来到开始位置，直接可以去算，如果不是开头位置，一定要满足和算过的位置不相等。因为不能出现出伏的字面值
            if (i == N - 1 || nums[i] != nums[i + 1]) {
                List<List<Integer>> nest = towSum(nums, i - 1, -nums[i]);
                for (List<Integer> list : nest) {
                    list.add(nums[i]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    //二元组，首先数组是有序的
    public static List<List<Integer>> towSum(int[] nums, int end, int target) {
        int L = 0;
        int R = end;
        List<List<Integer>> ans = new ArrayList<>();
        while (L < R) {
            //
            if (nums[L] + nums[R] > target) {
                R--;
            } else if (nums[L] + nums[R] < target) {
                L++;
            } else {
                // 如果是左边界，直接算，如果不是，那么就要和已经算过的不相等
                if (L == 0 || nums[L] != nums[L - 1]) {
                    List<Integer> res = new ArrayList<>();
                    res.add(nums[L]);
                    res.add(nums[R]);
                    ans.add(res);
                }
                R--; // 或者L++ 都可以
            }
        }
        return ans;
    }
}
