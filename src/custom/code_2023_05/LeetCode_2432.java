package custom.code_2023_05;

/**
 * @ClassName LeetCode_2432
 * @Author Duys
 * @Description
 * @Date 2023/5/5 10:41
 **/
// 2432. 处理用时最长的那个任务的员工
public class LeetCode_2432 {
    public int hardestWorker(int n, int[][] logs) {
        int max = logs[0][1];
        int ans = logs[0][0];
        for (int i = 1; i < logs.length; i++) {
            int idx = logs[i][0];
            int cost = logs[i][1] - logs[i - 1][1];
            if (cost > max || cost == max && idx < ans) {
                ans = idx;
                max = cost;
            }
        }
        return ans;
    }
}
