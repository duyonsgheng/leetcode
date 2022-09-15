package custom.code_2022_05;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_78
 * @Author Duys
 * @Description
 * @Date 2022/5/11 16:28
 **/
// 78. 子集
// 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
//
//解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
public class LeetCode_78 {

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length <= 0) {
            return res;
        }
        boolean[] vi = new boolean[nums.length];
        List<Integer> path = new ArrayList<>();
        dfs(nums, 0, vi, path, res);
        return res;
    }

    public static void dfs(int[] arr, int index, boolean[] vi, List<Integer> path, List<List<Integer>> res) {
        if (index == arr.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (vi[index]) {
            return;
        }
        vi[index] = true;
        path.add(arr[index]);
        dfs(arr, index + 1, vi, path, res);
        path.remove(path.size() - 1);
        vi[index] = false;

        dfs(arr, index + 1, vi, path, res);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3};
        List<List<Integer>> combine = subsets(arr);
        System.out.println(combine.size());
        for (List<Integer> list : combine) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
