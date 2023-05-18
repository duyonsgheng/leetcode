package custom.code_2023_05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LeetCode_1743
 * @Author Duys
 * @Description
 * @Date 2023/5/17 9:22
 **/
// 1743. 从相邻元素对还原数组
public class LeetCode_1743 {
    public int[] restoreArray(int[][] adjacentPairs) {
        // 一个简单的建图
        // 然后从左往右递推
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] arr : adjacentPairs) {
            graph.putIfAbsent(arr[0], new ArrayList<>());
            graph.putIfAbsent(arr[1], new ArrayList<>());
            graph.get(arr[0]).add(arr[1]);
            graph.get(arr[1]).add(arr[0]);
        }
        int n = adjacentPairs.length + 1;
        int[] ans = new int[n];
        // 如果只有一个相邻的，找到了开头，
        for (int key : graph.keySet()) {
            if (graph.get(key).size() == 1) {
                ans[0] = key;
                break;
            }
        }
        ans[1] = graph.get(ans[0]).get(0);
        for (int i = 2; i < n; i++) {
            // a b c
            List<Integer> cur = graph.get(ans[i - 1]);
            ans[i] = ans[i - 2] == cur.get(0) ? cur.get(1) : cur.get(0);
        }
        return ans;
    }
}
