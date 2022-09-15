package duys_code.day_38;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Code_02_448_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/
 * @Date 2021/12/20 13:23
 **/
public class Code_02_448_LeetCode {
    // 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。

    // 比如 1 该去0位置 2该去1位置 ，意思就是 n该去n-1位置
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            walk(nums, i);
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                ans.add(i + 1);
            }
        }
        return ans;
    }

    public void walk(int[] nums, int i) {
        while (nums[i] != i + 1) {
            // nums[i] 这个数该去 nums[i] - 1 的位置
            int nextIndex = nums[i] - 1;
            if (nums[nextIndex] == nextIndex + 1) {
                break;
            }
            swap(nums, i, nextIndex);
        }
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
