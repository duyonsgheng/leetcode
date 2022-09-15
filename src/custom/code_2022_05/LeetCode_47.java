package custom.code_2022_05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName LeetCode_47
 * @Author Duys
 * @Description
 * @Date 2022/5/5 16:43
 **/
//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
public class LeetCode_47 {
    static boolean[] map = null;

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        map = new boolean[nums.length];
        Arrays.sort(nums);
        process(nums, 0, ans, res);
        return res;
    }

    public static void process(int[] nums, int index, List<Integer> path, List<List<Integer>> res) {
        // 收集答案
        if (index == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 每一次都从头开始，过滤掉哪些已经使用了的
        for (int i = 0; i < nums.length; i++) {
            if (map[i] || (i > 0 && nums[i] == nums[i - 1] && !map[i - 1])) {
                continue;
            }
            path.add(nums[i]);
            map[i] = true;
            process(nums, index + 1, path, res);
            map[i] = false;
            path.remove(path.size() - 1);
        }
    }
}
