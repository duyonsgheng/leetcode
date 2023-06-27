package custom.code_2023_05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName LeetCode_1817
 * @Author Duys
 * @Description
 * @Date 2023/5/24 9:10
 **/
// 1817. 查找用户活跃分钟数
public class LeetCode_1817 {

    public static int[] findingUsersActiveMinutes(int[][] logs, int k) {
        // 用户 - 分钟
        Map<Integer, Set<Integer>> cnt = new HashMap<>();
        int[] ans = new int[k];
        for (int[] log : logs) {
            cnt.putIfAbsent(log[0], new HashSet<>());
            cnt.get(log[0]).add(log[1]);
        }
        // 分钟数，用户数
        Map<Integer, Integer> map = new HashMap<>();
        for (int key : cnt.keySet()) {
            map.put(cnt.get(key).size(), map.getOrDefault(cnt.get(key).size(), 0) + 1);
        }
        for (int i = 0; i < k; i++) {
            ans[i] = map.getOrDefault(i + 1, 0);
        }
        return ans;
    }

    public static void main(String[] args) {
        // logs = [[0,5],[1,2],[0,2],[0,5],[1,3]], k = 5
        findingUsersActiveMinutes(new int[][]{{0, 5}, {1, 2}, {0, 2}, {0, 5}, {1, 3}}, 5);
        findingUsersActiveMinutes(new int[][]{{1, 1}, {2, 2}, {2, 3}}, 4);
    }
}
