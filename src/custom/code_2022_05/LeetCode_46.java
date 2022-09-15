package custom.code_2022_05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName LeetCode_46
 * @Author Duys
 * @Description
 * @Date 2022/5/5 16:29
 **/
// 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
public class LeetCode_46 {

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        for (int num : nums) {
            ans.add(num);
        }
        process(nums, 0, ans, res);
        return res;
    }

    public static void process(int[] nums, int index, List<Integer> path, List<List<Integer>> res) {
        if (index == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            Collections.swap(path, index, i);
            process(nums, index + 1, path, res);
            Collections.swap(path, index, i);
        }
    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        System.out.println(permute(arr));
    }
}
