package week.week_2023_07_02;

/**
 * @author Mr.Du
 * @ClassName Code_04_LeetCode_2127
 * @date 2023年07月13日
 */
// https://leetcode.cn/problems/maximum-employees-to-be-invited-to-a-meeting/
// 一个公司准备组织一场会议，邀请名单上有 n 位员工
// 公司准备了一张 圆形 的桌子，可以坐下 任意数目 的员工
// 员工编号为 0 到 n - 1 。每位员工都有一位 喜欢 的员工
// 每位员工 当且仅当 他被安排在喜欢员工的旁边，他才会参加会议
// 每位员工喜欢的员工 不会 是他自己。
// 给你一个下标从 0 开始的整数数组 favorite
// 其中 favorite[i] 表示第 i 位员工喜欢的员工。请你返回参加会议的 最多员工数目
public class Code_04_LeetCode_2127 {
    // 拓扑排序
    // 如果图有多个两两形成的小环，那么就以每一个小的环开始算最长的链路
    // 如果图有多个形成的环，那么一定是这个环上的人
    public static int maximumInvitations(int[] favorite) {
        int[][] loved = beLoved(favorite);
        int[] degree = degree(loved);
        int n = favorite.length;
        int[] queue = new int[n];
        int l = 0;
        int r = 0;
        for (int i = 0; i < n; i++) {
            if (degree[i] == 0) {
                queue[r++] = i;
            }
        }
        // 记录遍历过程中那些被删除的节点
        boolean[] zeroVisited = new boolean[n];
        int visited = 0;
        while (l < r) {
            int cur = queue[l++];
            zeroVisited[cur] = true;
            visited++;
            if (--degree[favorite[cur]] == 0) {
                queue[r++] = favorite[cur];
            }
        }
        if (visited == n) {
            return 0;
        }
        boolean[] cycleVisited = new boolean[n];
        int arrangeTwoCycle = 0;
        int arrangeMoreCycle = 0;
        for (int i = 0; i < n; i++) {
            if (!zeroVisited[i] && !cycleVisited[i]) {
                cycleVisited[i] = true;
                // 如果只是两两形成的环，则找最长的链路
                if (favorite[favorite[i]] == i) {
                    cycleVisited[favorite[i]] = true;
                    arrangeTwoCycle += maxFollow(i, zeroVisited, loved) + maxFollow(favorite[i], zeroVisited, loved);
                } else {
                    // 是一个大的环
                    int cur = favorite[i];
                    int curAns = 1;
                    while (cur != i) {
                        cycleVisited[cur] = true;
                        curAns++;
                        cur = favorite[cur];
                    }
                    arrangeMoreCycle = Math.max(arrangeMoreCycle, curAns);
                }
            }
        }
        // 如果多个两两形成的环和大的环同时存在，则找最大的。
        return Math.max(arrangeTwoCycle, arrangeMoreCycle);
    }

    // 生成被喜欢表
    public static int[][] beLoved(int[] favorite) {
        int n = favorite.length;
        int[] size = new int[n];
        for (int love : favorite) {
            size[love]++;
        }
        int[][] loved = new int[n][];
        for (int i = 0; i < n; i++) {
            loved[i] = new int[size[i]];
        }
        for (int i = 0; i < n; i++) {
            loved[favorite[i]][--size[favorite[i]]] = i;
        }
        return loved;
    }

    // 求每个点的入度
    public static int[] degree(int[][] loved) {
        int n = loved.length;
        int[] degree = new int[n];
        for (int i = 0; i < n; i++) {
            degree[i] = loved[i].length;
        }
        return degree;
    }

    // cur不在环上的追随者链条最大长度
    public static int maxFollow(int cur, boolean[] zeroed, int[][] from) {
        if (from[cur].length == 0) {
            return 1;
        }
        int ans = 0;
        for (int pre : from[cur]) {
            if (zeroed[pre]) {
                ans = Math.max(ans, maxFollow(pre, zeroed, from));
            }
        }
        return ans + 1;
    }
}
