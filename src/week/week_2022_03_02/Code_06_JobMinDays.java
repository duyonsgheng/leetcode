package week.week_2022_03_02;

/**
 * @ClassName Code_06_JobMinDays
 * @Author Duys
 * @Description TODO
 * @Date 2022/3/23 13:35
 **/
public class Code_06_JobMinDays {

    // 来自微软
// 给定一个正数数组arr，长度为N，依次代表N个任务的难度，给定一个正数k
// 你只能从0任务开始，依次处理到N-1号任务结束，就是一定要从左往右处理任务
// 只不过，难度差距绝对值不超过k的任务，可以在一天之内都完成
// 返回完成所有任务的最少天数
    public static int minDay(int[] arr, int k) {

        int n = arr.length;
        int[] dp = new int[n];
        dp[0] = 1;// 当只有第0号任务，需要1天
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + 1;// 当前任务单独一天完成，那么就是i-1个任务完成的天数+1
            int min = arr[i];
            int max = arr[i];
            for (int j = i - 1; i >= 0; j--) {
                min = Math.min(min, arr[j]);
                max = Math.max(max, arr[j]);
                if (max - min <= k) {
                    // 把 差小于 = k的算到1天，然后其他的多+1天
                    dp[i] = Math.min(dp[i], 1 + (j - 1 >= 0 ? dp[j - 1] : 0));
                } else {
                    break;
                }
            }
        }
        return dp[n - 1];
    }
}
