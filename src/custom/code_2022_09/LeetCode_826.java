package custom.code_2022_09;

import java.util.Arrays;

/**
 * @ClassName LeetCode_826
 * @Author Duys
 * @Description
 * @Date 2022/9/26 14:51
 **/
// 826. 安排工作以达到最大收益
public class LeetCode_826 {
    // 思路：
    // 排序，
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;
        int m = worker.length;
        int[][] job = new int[n][2];
        for (int i = 0; i < n; i++) {
            job[i] = new int[]{difficulty[i], profit[i]};
        }
        // 按照难度排序
        Arrays.sort(job, (a, b) -> a[0] - b[0]);
        Arrays.sort(worker);
        int ans = 0;
        int max = 0;
        for (int i = 0, j = 0; i < m; i++) {
            int cur = worker[i];
            while (j < n && job[j][0] <= cur) {
                max = Math.max(max, job[j][1]);
                j++;
            }
            ans += max;
        }
        return ans;
    }
}
