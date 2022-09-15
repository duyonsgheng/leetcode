package custom.code_2022_08;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @ClassName LeetCode_491
 * @Author Duys
 * @Description
 * @Date 2022/8/17 11:28
 **/
// 491. 递增子序列
public class LeetCode_491 {
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        dfs(nums, new ArrayList<>(), 0, Integer.MIN_VALUE);
        return ans;
    }

    public void dfs(int[] arr, List<Integer> path, int index, int last) {
        if (index == arr.length) {
            if (path.size() > 1) {
                ans.add(new ArrayList<>(path));
            }
            return;
        }
        if (arr[index] >= last) {
            path.add(arr[index]);
            dfs(arr, path, index + 1, arr[index]);
            path.remove(path.size() - 1);
        }
        if (arr[index] != last) {
            dfs(arr, path, index + 1, last);
        }
    }

}
