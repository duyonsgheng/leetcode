package week.week_2023_05_02;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Code_03_LeetCode_1125
 * @Author Duys
 * @Description
 * @Date 2023/5/15 14:16
 **/
// 1125. 最小的必要团队
public class Code_03_LeetCode_1125 {
    // 题意：1 <= req_skills.length <= 16
    // 可以做状态压缩
    public static int[] smallestSufficientTeam(String[] skills, List<List<String>> people) {
        Arrays.sort(skills);
        int n = skills.length;
        int m = people.size();
        int[] status = new int[m];
        for (int i = 0; i < m; i++) {
            int curStatus = 0;
            List<String> list = people.get(i);
            // 排个序，升序就可以了，跟skills保持一致
            list.sort((a, b) -> a.compareTo(b));
            int p1 = 0, p2 = 0;
            while (p1 < n && p2 < list.size()) {
                int compare = skills[p1].compareTo(list.get(p2));
                // 匹配，哪些命中了
                if (compare < 0) {
                    p1++;
                } else if (compare > 0) {
                    p2++;
                } else {
                    // 把当前人员会的技能或到状态中去
                    curStatus |= 1 << p1;
                    p1++;
                    p2++;
                }
            }
            status[i] = curStatus;
        }
        int[][] dp = new int[m][1 << n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        // 需要几个人
        int near = process(status, 0, n, 0, dp);
        int[] ans = new int[near];
        // 开始回溯
        int i = 0;
        int ansi = 0;
        int sta = 0;
        while (sta != (1 << n) - 1) {
            // i+1 ==m了，都还没满，那么一定选择了当前的人
            // i位置是3人，i+1位置也是3人，说明i位置的没选啊，
            if (i + 1 == m || dp[i][sta] != dp[i + 1][sta]) {
                ans[ansi++] = i;
                sta |= status[i];
            }
            i++;
        }
        return ans;
    }

    public static int process(int[] people, int status, int n, int i, int[][] dp) {
        // 如果技能凑齐了，就不需要更多的人了
        if (status == (1 << n) - 1) {
            return 0;
        }
        // 还没凑齐，但是人不够了
        if (i == people.length) {
            return Integer.MAX_VALUE;
        }
        if (dp[i][status] != -1) {
            return dp[i][status];
        }
        //要当前的人，和不要当前的人
        int p1 = process(people, status, n, i + 1, dp);
        int p2 = Integer.MAX_VALUE;
        // 要当前的，就需要把当前人的状态或进去
        int next = process(people, status | people[i], n, i + 1, dp);
        if (next != Integer.MAX_VALUE) {
            p2 = 1 + next;
        }
        int ans = Math.min(p1, p2);
        dp[i][status] = ans;
        return ans;
    }
}
