package custom.code_2023_06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @ClassName LeetCode_1595
 * @Author Duys
 * @Description
 * @Date 2023/6/20 11:04
 **/
// 1595. 连通两组点的最小成本
public class LeetCode_1595 {
    //    1    2
    // A  15  96
    // B  36  2
    // 状态压缩
    public static int connectTwoGroups(List<List<Integer>> cost) {
        int size1 = cost.size();
        int size2 = cost.get(0).size();
        int n = 1 << size2;
        int[][] dp = new int[size1 + 1][n];
        for (int i = 0; i <= size1; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= size1; i++) {
            for (int status = 0; status < n; status++) {
                for (int j = 0; j < size2; j++) {
                    if ((status & (1 << j)) == 0) {
                        continue;
                    }
                    dp[i][status] = Math.min(dp[i][status], dp[i][status ^ (1 << j)] + cost.get(i - 1).get(j));
                    dp[i][status] = Math.min(dp[i][status], dp[i - 1][status] + cost.get(i - 1).get(j));
                    dp[i][status] = Math.min(dp[i][status], dp[i - 1][status ^ (1 << j)] + cost.get(i - 1).get(j));
                }
            }
        }
        return dp[size1][n - 1];
    }

    public static void main(String[] args) {
        // cost = [[1, 3, 5], [4, 1, 1], [1, 5, 3]]
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(1, 3, 5));
        list.add(Arrays.asList(4, 1, 1));
        list.add(Arrays.asList(1, 5, 3));
        System.out.println(connectTwoGroups(list));
        // cost = [[2, 5, 1], [3, 4, 7], [8, 1, 2], [6, 2, 4], [3, 8, 8]]
        list.clear();
        list.add(Arrays.asList(2, 5, 1));
        list.add(Arrays.asList(3, 4, 7));
        list.add(Arrays.asList(8, 1, 2));
        list.add(Arrays.asList(6, 2, 4));
        list.add(Arrays.asList(3, 8, 8));
        System.out.println(connectTwoGroups(list));

        // [[93,56,92],[53,44,18],[86,44,69],[54,60,30]]
        list.clear();
        list.add(Arrays.asList(93, 56, 92));
        list.add(Arrays.asList(53, 44, 18));
        list.add(Arrays.asList(86, 44, 69));
        list.add(Arrays.asList(54, 60, 30));
        System.out.println(connectTwoGroups(list));
    }
}
