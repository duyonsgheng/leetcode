package hope.class86_dpPath1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author Mr.Du
 * @ClassName Code02_SmallestSufficientTeam
 * @date 2024年05月20日 下午 03:40
 */
// 最小的必要团队
// 作为项目经理，你规划了一份需求的技能清单req_skills
// 并打算从备选人员名单people中选出些人组成必要团队
// 编号为i的备选人员people[i]含有一份该备选人员掌握的技能列表
// 所谓必要团队，就是在这个团队中
// 对于所需求的技能列表req_skills中列出的每项技能，团队中至少有一名成员已经掌握
// 请你返回规模最小的必要团队，团队成员用人员编号表示
// 你可以按 任意顺序 返回答案，题目数据保证答案存在
// 测试链接 : https://leetcode.cn/problems/smallest-sufficient-team/
public class Code02_SmallestSufficientTeam {
    public static int[] smallestSufficientTeam(String[] skills, List<List<String>> people) {
        // 信息转换
        int n = skills.length;
        int m = people.size();
        HashMap<String, Integer> map = new HashMap<>();
        int cnt = 0;
        for (String s : skills) {
            map.put(s, cnt++); // 任务编号
        }
        int[] arr = new int[m]; // 每个人掌握的必要的技能，用状态位表示
        for (int i = 0, status; i < m; i++) {
            status = 0;
            for (String skill : people.get(i)) {
                if (map.containsKey(skill)) {
                    status |= 1 << map.get(skill);
                }
            }
            arr[i] = status;
        }
        int[][] dp = new int[m][1 << n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        int size = f(arr, n, m, 0, 0, dp);
        int[] ans = new int[size];
        for (int j = 0, i = 0, s = 0; s != (1 << n) - 1; i++) {
            // 到了最后一个人了，还没满，说明最后一个人一定选择了
            // i和i+1位置不等，说明i号人一定选择了
            if (i == m - 1 || dp[i][s] != dp[i + 1][s]) {
                ans[j++] = i;
                s |= arr[i];
            }
        }
        return ans;
    }

    // 所有人技能情况再arr中
    // 当前来到i位置，
    // 之前选择的信息在s中
    // 返回满足条件下最少的人数
    public static int f(int[] arr, int n, int m, int i, int s, int[][] dp) {
        if (s == (1 << n) - 1) { // 已经凑齐了
            return 0;
        }
        // 还没齐，但是没有人了
        if (i == m) {
            return Integer.MAX_VALUE;
        }
        if (dp[i][s] != -1) {
            return dp[i][s];
        }
        // 不选当前的人
        int p1 = f(arr, n, n, i + 1, s, dp);
        // 选择当前人
        int p2 = Integer.MAX_VALUE;
        int next = f(arr, n, m, i + 1, s | arr[i], dp);
        if (next != Integer.MAX_VALUE) {
            p2 = 1 + next;
        }
        int ans = Math.min(p1, p2);
        dp[i][s] = ans;
        return ans;
    }
}
