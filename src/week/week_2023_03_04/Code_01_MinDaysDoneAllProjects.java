package week.week_2023_03_04;

/**
 * @ClassName Code_01_MinDaysDoneAllProjects
 * @Author Duys
 * @Description
 * @Date 2023/3/23 11:14
 **/
// 一共有n个项目，每个项目都有两个信息
// projects[i] = {a, b}
// 表示i号项目做完要a天，但是当你投入b个资源，它就会缩短1天的时间
// 你一共有k个资源，你的目标是完成所有的项目，但是希望总天数尽可能缩短
// 在所有项目同时开工的情况下，返回尽可能少的天数
// 1 <= n <= 10^5
// 1 <= k <= 10^7
public class Code_01_MinDaysDoneAllProjects {
    // 经典的二分答案
    public static int minDays(int[][] projects, int k) {
        // 最大的天数不外乎就是 0到 projects中的max
        int l = 0;
        int r = 0;
        for (int[] p : projects) {
            r = Math.max(r, p[0]);
        }
        int m, ans = r;
        while (l <= r) {
            m = (l + r) / 2;
            // 如果能完成。那么就看看能不能更少的天数来
            if (process(projects, m) <= k) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    // 看看 在day天下完成所有的任务至少需要投入多少资源
    public static int process(int[][] pos, int day) {
        int ans = 0;
        for (int[] p : pos) {
            // 如果当前任务需要的天数大于了day。那么需要投入资源
            if (p[0] > day) {
                // 当前任务要想在day天内完成，至少需要投入这么资源
                ans += (p[0] - day) * p[1];
            }
        }
        return ans;
    }
}
