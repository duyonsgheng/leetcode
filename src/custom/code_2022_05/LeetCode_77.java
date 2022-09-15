package custom.code_2022_05;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_77
 * @Author Duys
 * @Description
 * @Date 2022/5/11 15:52
 **/
// 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
//
//你可以按 任何顺序 返回答案。
public class LeetCode_77 {

    // 1~n这之间的数，选k个出来
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (n < k) {
            return res;
        }
        List<Integer> path = new ArrayList<>();
        // dfs
        dfs(1, k, n, path, res);
        return res;
    }

    public static void dfs(int cur, int k, int n, List<Integer> path, List<List<Integer>> res) {
        if (path.size() + (n - cur + 1) < k) {
            return;
        }
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        path.add(cur);
        dfs(cur + 1, k, n, path, res);
        path.remove(path.size() - 1);

        dfs(cur + 1, k, n, path, res);
    }

    public static List<List<Integer>> combine1(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (n < k) {
            return res;
        }
        List<Integer> path = new ArrayList<>();
        int[] arr = new int[n];
        for (int i = 1; i <= n; i++) {
            arr[i - 1] = i;
        }
        dfs2(arr, 0, k, path, res);
        return res;
    }

    public static void dfs2(int[] arr, int index, int k, List<Integer> path, List<List<Integer>> res) {
        if (path.size() + arr.length - index < k) {// 剩下的和当前不不够k了
            return;
        }
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 选择当前的，不要当前的
        path.add(arr[index]);
        dfs2(arr, index + 1, k, path, res);
        path.remove(path.size() - 1);
        dfs2(arr, index + 1, k, path, res);
    }

    public static void main(String[] args) {
        List<List<Integer>> combine = combine1(4, 2);
        for (List<Integer> list : combine) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
