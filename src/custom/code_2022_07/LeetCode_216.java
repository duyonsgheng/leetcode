package custom.code_2022_07;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_216
 * @Author Duys
 * @Description
 * @Date 2022/7/7 14:32
 **/
// 216. 组合总和 III
// 找出所有相加之和为n 的k个数的组合，且满足下列条件：
//只使用数字1到9
//每个数字最多使用一次
//返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
//链接：https://leetcode.cn/problems/combination-sum-iii
public class LeetCode_216 {

    public static void main(String[] args) {
        List<List<Integer>> list = combinationSum3(3, 7);
        System.out.println();
    }

    public static List<List<Integer>> combinationSum3(int k, int n) {
        int[] arr = new int[9];
        for (int i = 0; i < 9; i++) {
            arr[i] = i + 1;
        }
        List<List<Integer>> ans = new ArrayList<>();
        process(arr, k, n, 0, new ArrayList<>(), ans);
        return ans;
    }

    public static void process(int[] arr, int k, int rest, int index, List<Integer> path, List<List<Integer>> res) {
        if (index == arr.length) {
            if (rest == 0 && path.size() == k) {
                List<Integer> ans = new ArrayList<>();
                ans.addAll(path);
                res.add(ans);
            }
            return;
        }
        if (rest < 0 || path.size() > k) {
            return;
        }
        if (rest == 0 && path.size() == k) {
            List<Integer> ans = new ArrayList<>();
            ans.addAll(path);
            res.add(ans);
            return;
        }
        process(arr, k, rest, index + 1, path, res);
        if (rest - arr[index] < 0) {
            return;
        }
        path.add(arr[index]);
        process(arr, k, rest - arr[index], index + 1, path, res);
        path.remove(path.size() - 1);
    }
}
