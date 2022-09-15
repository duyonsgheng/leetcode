package custom.code_2022_05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName LeetCode_40
 * @Author Duys
 * @Description
 * @Date 2022/5/5 12:53
 **/
// 给定一个候选人编号的集合candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
//candidates中的每个数字在每个组合中只能使用一次。
//注意：解集不能包含重复的组合。
//
//链接：https://leetcode-cn.com/problems/combination-sum-ii
public class LeetCode_40 {

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        //process1(candidates, target, 0, new ArrayList<>(), res);
        process(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    public static void process1(int[] arr, int rest, int index, List<Integer> path, List<List<Integer>> res) {
        if (rest == 0) {
            List<Integer> ans = new ArrayList<>();
            ans.addAll(path);
            res.add(ans);
            return;
        }
        for (int i = index; i < arr.length; i++) {
            if (arr[i] > rest) {
                break;
            }
            // 有重复的
            if (i > index && arr[i] == arr[i - 1]) {
                continue;
            }
            path.add(arr[i]);
            process1(arr, rest - arr[i], i, path, res);
            path.remove(path.size() - 1);
        }
    }

    public static void process(int[] arr, int rest, int index, List<Integer> path, List<List<Integer>> res) {
        if (index == arr.length) {
            if (rest == 0) {
                List<Integer> ans = new ArrayList<>();
                ans.addAll(path);
                res.add(ans);
            }
            return;
        }
        if (rest < 0) {
            return;
        }
        if (rest == 0) {
            List<Integer> ans = new ArrayList<>();
            ans.addAll(path);
            res.add(ans);
            return;
        }
        process(arr, rest, index + 1, path, res);
        if (rest - arr[index] < 0) {
            return;
        }
        if (rest - arr[index] == 0) {
            List<Integer> ans = new ArrayList<>();
            ans.addAll(path);
            ans.add(arr[index]);
            res.add(ans);
            return;
        }
        path.add(arr[index]);
        process(arr, rest - arr[index], index + 1, path, res);
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 1, 1, 1};
        int t = 4;
        combinationSum2(arr, t);
    }
}
