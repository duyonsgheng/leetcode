package custom.code_2022_05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName LeetCode_39
 * @Author Duys
 * @Description
 * @Date 2022/5/5 10:19
 **/
// 给你一个 无重复元素 的整数数组candidates 和一个目标整数target，找出candidates中可以使数字和为目标数target 的 所有不同组合 ，
//并以列表形式返回。你可以按 任意顺序 返回这些组合。
//candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
//对于给定的输入，保证和为target 的不同组合数少于 150 个。
//链接：https://leetcode-cn.com/problems/combination-sum
public class LeetCode_39 {

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        process(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    // 当前来到的i位置
    // 还剩下rest没搞定
    // 前面做的决定在path里
    // res收集答案
    public static void process(int[] arr, int rest, int index, List<Integer> path, List<List<Integer>> res) {
        for (int i = index; i < arr.length; i++) {
            int cur = arr[i];
            if (cur == rest) {
                path.add(cur);
                List<Integer> ans = new ArrayList<>();
                ans.addAll(path);
                res.add(ans);
                path.remove(path.size() - 1);
            }
            if (cur < rest) {
                path.add(cur);
                process(arr, rest - cur, i, path, res);
                path.remove(path.size() - 1);
            }
            if (cur > rest) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 6, 7};
        int t = 7;
        combinationSum(arr, t);

    }
}
