package week.week_2022_01_04;

/**
 * @ClassName Code_02_SplitToMArraysMinScore
 * @Author Duys
 * @Description
 * @Date 2022/3/30 15:13
 **/
// 来自美团
// 小团去参加军训，军训快要结束了，长官想要把大家一排n个人分成m组，然后让每组分别去参加阅兵仪式
// 只能选择相邻的人一组，不能随意改变队伍中人的位置
// 阅兵仪式上会进行打分，其中有一个奇怪的扣分点是每组的最大差值，即每组最大值减去最小值
// 长官想要让这分成的m组总扣分量最小，即这m组分别的极差之和最小
// 长官正在思索如何安排中，就让小团来帮帮他吧
public class Code_02_SplitToMArraysMinScore {

    // 先来一个暴利的
    public static int minScore1(int[] arr, int m) {
        if (m == 0) {
            return 0;
        }
        return process(arr, 1, m, arr[0], arr[0]);
    }

    public static int process(int[] arr, int index, int rest, int preMin, int preMax) {
        if (index == arr.length) {
            return rest == 1 ? preMax - preMin : -1;
        }
        // 当前的index 和之前的搞到一组去
        int p1 = process(arr, index + 1, rest, Math.min(preMin, arr[index]), Math.max(preMax, arr[index]));
        // 从index开始，新开一组
        int p2 = process(arr, index + 1, rest - 1, arr[index], arr[index]);
        int next = p2 == -1 ? -1 : preMax - preMin + p2;// 总得扣分、
        if (p1 == -1) {
            return next;
        }
        if (next == -1) {
            return p1;
        }
        return Math.min(p1, p2);
    }

    // 使用dp的
    public static int minScore2(int[] arr, int m) {
        if (m == 0) return 0;
        int n = arr.length;
        int[][] socre = new int[n][n]; // 记录
        for (int i = 0; i < n; i++) {
            int max = arr[i];
            int min = arr[i];
            socre[i][i] = max - min;
            for (int j = i + 1; j < n; j++) {
                max = Math.max(max, arr[j]);
                min = Math.min(min, arr[j]);
                socre[i][j] = max - min;
            }
        }
        // dp[i][j] = 当有i组的时候，j 个人能取得的最小扣分
        int[][] dp = new int[m + 1][n];

        for (int i = 0; i < n; i++) {
            dp[1][i] = socre[0][i];
        }
        // 组
        for (int j = 2; j <= m; j++) {
            //
            for (int p = j; p < n; p++) {
                // 当前的人和上一组搞到一组去
                dp[j][p] = dp[j - 1][p];
                // 如果重新分组 ，当前最后一个单独一组
                // 当前最后一个和前一个一组，和前两个一组，，前三个一组，，画家问题
                for (int x = 1; x <= p; x++) {
                    dp[j][p] = Math.min(dp[j][p], dp[j - 1][x - 1] + socre[x][p]);
                }
            }
        }
        return dp[m][n - 1];
    }
}
