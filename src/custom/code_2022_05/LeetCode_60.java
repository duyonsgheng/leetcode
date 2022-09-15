package custom.code_2022_05;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_60
 * @Author Duys
 * @Description TODO
 * @Date 2022/5/6 14:03
 **/
// 给出集合[1,2,3,...,n]，其所有元素共有n! 种排列。
//
//按大小顺序列出所有排列情况，并一一标记，当n = 3 时, 所有排列如下：
//
//"123"
//"132"
//"213"
//"231"
//"312"
//"321"
//给定n 和k，返回第k个排列。
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/permutation-sequence
public class LeetCode_60 {
    // 用堆来实现

    public static String getPermutation1(int n, int k) {
        PriorityQueue<Long> heap = new PriorityQueue<>();
        // 一边生成序列，一边放入堆里
        int[] arr = new int[n];
        boolean[] map = new boolean[n];
        for (int i = 1; i <= n; i++) {
            arr[i - 1] = i;
        }
        process(arr, 0, new ArrayList<>(), map, heap);
        int index = 1;
        while (!heap.isEmpty()) {
            Long poll = heap.poll();
            if (index == k) {
                return "" + poll;
            }
            index++;
        }
        return "";
    }

    public static void process(int[] nums, int index, List<Integer> path, boolean[] map, PriorityQueue<Long> heap) {
        // 收集答案
        if (index == nums.length) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < path.size(); i++) {
                sb.append(path.get(i));
            }
            if (!heap.contains(Long.valueOf(sb.toString()))) {
                heap.add(Long.valueOf(sb.toString()));
            }
            return;
        }
        // 每一次都从头开始，过滤掉哪些已经使用了的
        for (int i = 0; i < nums.length; i++) {
            if (map[i] || (i > 0 && nums[i] == nums[i - 1] && !map[i - 1])) {
                continue;
            }
            path.add(nums[i]);
            map[i] = true;
            process(nums, index + 1, path, map, heap);
            map[i] = false;
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(getPermutation(9, 5449));
    }

    /**
     * 记录数字是否使用过
     */
    /**
     * 阶乘数组
     */


    public static String getPermutation(int n, int k) {
        // 把不同阶段的数组排列个数算出来
        int[] factorial = createArr(n);

        boolean[] used = new boolean[n + 1];
        StringBuilder sb = new StringBuilder();
        dfs(0, sb, n, k, factorial, used);
        return sb.toString();
    }

    // index-当前来到哪一个位置做抉择
    // sb - 之前选择的数
    public static void dfs(int index, StringBuilder sb, int n, int k, int[] factorial, boolean[] used) {
        if (index == n) {
            return;
        }
        int cnt = factorial[n - 1 - index];
        for (int i = 1; i <= n; i++) {
            if (used[i]) {
                continue;
            }
            if (cnt < k) {
                k -= cnt;
                continue;
            }
            sb.append(i);
            used[i] = true;
            dfs(index + 1, sb, n, k, factorial, used);
            return;
        }
    }

    public static int[] createArr(int n) {
        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
        return factorial;
    }
}
