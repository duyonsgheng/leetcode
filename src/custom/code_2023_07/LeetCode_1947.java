package custom.code_2023_07;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1947
 * @date 2023年07月12日
 */
// 1947. 最大兼容性评分和
// https://leetcode.cn/problems/maximum-compatibility-score-sum/
public class LeetCode_1947 {

    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        int m = mentors.length, n = mentors[0].length;
        // 压缩学生答案
        int[] sa = this.answer(m, n, students);
        // 压缩导师答案
        int[] ma = this.answer(m, n, mentors);
        // 最大状态数量，可用状态数量
        int limit = 1 << m;
        int[] dp = new int[limit];
        Arrays.fill(dp, -1);
        // n道题，学生答案sa，导师答案ma，匹配完状态limit -1，当前已经匹配的导师状态0，当前匹配学生索引0，缓存。
        return process(n, sa, ma, limit - 1, 0, 0, dp);
    }

    private int process(int n, int[] sa, int[] ma, int limit, int used, int index, int[] dp) {
        if (limit == used) {
            return 0;
        }
        if (dp[used] != -1) {
            return dp[used];
        }
        int comp = 0;
        int stu = sa[index];
        for (int i = 0; i < ma.length; i++) {
            int pos = 1 << i;
            // i导师可以参与当前匹配
            if ((used & pos) == 0) {
                // 匹配得分
                int score = score(stu, ma[i], n);
                // i导师匹配index学生的最大得分 == 后续最大得分 + 当前得分。
                comp = Math.max(comp, process(n, sa, ma, limit, used | pos, index + 1, dp) + score);
            }
        }
        dp[used] = comp;
        return comp;
    }

    // n题情况下， 学生和导师得分情况
    private int score(int student, int mentor, int n) {
        int compare = student ^ mentor;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if ((compare & (1 << i)) == 0) {
                ans++;
            }
        }
        return ans;
    }

    // 压缩答案
    private int[] answer(int m, int n, int[][] arr) {
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int answer = 0;
            for (int j = 0; j < n; j++) {
                answer |= arr[i][j] << j;
            }
            ans[i] = answer;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(3 & 5);
    }
}
