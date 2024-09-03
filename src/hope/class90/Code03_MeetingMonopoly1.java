package hope.class90;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code03_MeetingMonopoly1
 * @date 2024年08月26日 下午 05:13
 */
// 会议必须独占时间段的最大会议数量
// 给定若干会议的开始、结束时间
// 你参加某个会议的期间，不能参加其他会议
// 返回你能参加的最大会议数量
// 来自真实大厂笔试，没有在线测试，对数器验证
public class Code03_MeetingMonopoly1 {
    // 暴力方法
    // 为了验证
    // 时间复杂度O(n!)
    public static int maxMeeting1(int[][] meeting) {
        return f(meeting, meeting.length, 0);
    }

    // 把所有会议全排列
    // 其中一定有安排会议次数最多的排列
    public static int f(int[][] meeting, int n, int i) {
        int ans = 0;
        if (i == n) {
            for (int j = 0, cur = -1; j < n; j++) {
                if (cur <= meeting[j][0]) {
                    ans++;
                    cur = meeting[j][1];
                }
            }
        } else {
            for (int j = i; j < n; j++) {
                swap(meeting, i, j);
                ans = Math.max(ans, f(meeting, n, i + 1));
                swap(meeting, i, j);
            }
        }
        return ans;
    }

    public static void swap(int[][] meeting, int i, int j) {
        int[] tmp = meeting[i];
        meeting[i] = meeting[j];
        meeting[j] = tmp;
    }

    // 按照结束时间排序
    // 谁先结束，我就开哪一个会议
    public static int maxMeeting2(int[][] meeting) {
        Arrays.sort(meeting, (a, b) -> a[1] - b[1]);
        int n = meeting.length;
        int ans = 0;
        for (int i = 0, cur = -1; i < n; i++) {
            if (cur < meeting[i][0]) {
                ans++;
                cur = meeting[i][1];
            }
        }
        return ans;
    }
}
