package custom.code_2023_01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName LeetCode_1462
 * @Author Duys
 * @Description
 * @Date 2023/1/17 17:11
 **/
// 1462. 课程表 IV
public class LeetCode_1462 {
    public List<Boolean> checkIfPrerequisite1(int numCourses, int[][] prerequisites, int[][] queries) {
        int[][] dp = new int[numCourses][numCourses];
        for (int i = 0; i < numCourses; i++) {
            Arrays.fill(dp[i], (Integer.MAX_VALUE >> 1) - 2);
        }
        for (int i = 0; i < numCourses; i++)
            dp[i][i] = 0;
        for (int[] pre : prerequisites) {
            dp[pre[0]][pre[1]] = 0;
        }
        for (int i = 0; i < numCourses; i++) {
            for (int from = 0; from < numCourses; from++) {
                for (int to = 0; to < numCourses; to++) {
                    dp[from][to] = Math.min(dp[from][to], dp[from][i] + dp[i][to]);
                }
            }
        }
        List<Boolean> ans = new ArrayList<>();
        for (int[] qu : queries) {
            if (dp[qu[0]][qu[1]] == 0) {
                ans.add(true);
            } else {
                ans.add(false);
            }
        }
        return ans;
    }

    // prerequisites[i][0] 是 [i][1] 的先决条件
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        int[][] dp = new int[numCourses][numCourses];
        int[] in = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        for (int[] pre : prerequisites) {
            dp[pre[0]][pre[1]] = 1; // 有路
            in[pre[1]]++; // 入度也有
        }
        // 入度为0的点，表示没有先决条件
        for (int i : in) {
            if (i == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int i = 0; i < numCourses; i++) {
                if (dp[cur][i] == 1) { // 有路
                    for (int j = 0; j < numCourses; j++) {
                        if (dp[j][cur] == 1) {
                            dp[j][i] = 1;
                        }
                    }
                }
                // 从cur到i已经算过了，那么i的入度减少
                in[i]--;
                if (in[i] == 0) {
                    queue.add(i);
                }
            }
        }
        List<Boolean> ans = new ArrayList<>();
        for (int[] q : queries) {
            // 如果从0到1有路，则0是1的先决条件
            if (dp[q[0]][q[1]] == 1) {
                ans.add(true);
            } else {
                ans.add(false);
            }
        }
        return ans;
    }
}
