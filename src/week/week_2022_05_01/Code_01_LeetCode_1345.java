package week.week_2022_05_01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Code_01_JumMinSameValue
 * @Author Duys
 * @Description
 * @Date 2022/5/7 15:33
 **/
// 来自蔚来汽车
// 给你一个整数数组arr，你一开始在数组的第一个元素处（下标为 0）。
// 每一步，你可以从下标i跳到下标i + 1 、i - 1 或者 j ：
// i + 1 需满足：i + 1 < arr.length
// i - 1需满足：i - 1 >= 0
// j需满足：arr[i] == arr[j]且i != j
// 请你返回到达数组最后一个元素的下标处所需的最少操作次数。
// 注意：任何时候你都不能跳到数组外面。
// leetcode测试链接 : https://leetcode-cn.com/problems/jump-game-iv/
public class Code_01_LeetCode_1345 {

    // 这道题三个条件，i-1 i+1 和 j，扎眼一看就是一个递归，但是这道题使用递归可能就复杂了
    // 我们就宽度优先遍历
    // 1.先把相同值的位置标记出来
    // 2.然后每一次搞一层，当前层带出下一层，如果遇到了i -1 位置就停下
    // 3.使用数组来模拟栈
    // 4.再已经去过的位置就不要重复去了
    public static int minJumps(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        if (arr.length == 2) {
            return 1;
        }
        // 1.先把相同值的位置拿出来
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (!indexMap.containsKey(arr[i])) {
                indexMap.put(arr[i], new ArrayList<>());
            }
            indexMap.get(arr[i]).add(i);
        }
        boolean[] visited = new boolean[n];
        int[] queue = new int[n];
        int l = 0;
        int r = 0;
        visited[r] = true;
        queue[r++] = 0;
        int ans = 0;
        // 如果队列有数据，就继续
        while (l != r) {
            int curIndex = r;
            // 遍历当前层了
            for (; l < curIndex; l++) {
                int cur = queue[l];
                if (cur == n - 1) {
                    return ans;
                }
                // 去i+1
                if (cur + 1 < n && !visited[cur + 1]) {
                    queue[r++] = cur + 1;
                    visited[cur + 1] = true;
                }
                // 去i-1
                if (cur - 1 >= 0 && !visited[cur - 1]) {
                    queue[r++] = cur - 1;
                    visited[cur - 1] = true;
                }
                // 去 j
                List<Integer> nexts = indexMap.get(arr[cur]);
                if (nexts == null) {
                    continue;
                }
                for (int next : nexts) {
                    if (visited[next]) {
                        continue;
                    }
                    queue[r++] = next;
                    visited[next] = true;
                }
                // 和当前相同的值的位置已经算过一次了，就不重复算了
                indexMap.remove(arr[cur]);
            }
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {100, -23, -23, 404, 100, 23, 23, 23, 3, 404};
        System.out.println(minJumps(arr));
    }
}
