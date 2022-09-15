package custom.code_2022_05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName LeetCode_90
 * @Author Duys
 * @Description
 * @Date 2022/5/17 10:29
 **/
// 90. 子集 II
// 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
//解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
//链接：https://leetcode.cn/problems/subsets-ii
public class LeetCode_90 {


    public List<List<Integer>> subsetsWithDup(int[] nums) {

        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length <= 0) {
            return ans;
        }
        List<Integer> path = new ArrayList<>();
        Arrays.sort(nums);
        dfs(false, 0, nums, path, ans);
        return ans;
    }

    public void dfs(boolean choosePre, int cur, int[] nums, List<Integer> path, List<List<Integer>> ans) {
        if (cur == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        dfs(false, cur + 1, nums, path, ans);
        // 选择了上一个数据，那么选择当前数据的时候需要校验一下，是否和前一个相同，如果相同了，就不选择当前了
        if (!choosePre && cur > 0 && nums[cur - 1] == nums[cur]) {
            return;
        }
        path.add(nums[cur]);
        dfs(true, cur + 1, nums, path, ans);
        path.remove(path.size() - 1);
    }
}
