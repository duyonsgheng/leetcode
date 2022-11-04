package custom.code_2022_11;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1024
 * @Author Duys
 * @Description
 * @Date 2022/11/3 17:27
 **/
// 1024. 视频拼接
public class LeetCode_1024 {

    public int videoStitching(int[][] clips, int time) {
        // 按照开始时间排序
        // 开始时间相同，时间大的排在前面
        Arrays.sort(clips, (a, b) -> (a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]));

        if (clips[0][0] != 0) {
            return -1;
        }
        int[] dp = new int[time + 1];
        Arrays.fill(dp, -1);
        // 这个区间我们只需要一个
        for (int i = 0; i <= Math.min(time, clips[0][1]); i++) {
            dp[i] = 1;
        }
        int n = clips.length;
        for (int i = 1; i < n && clips[i][0] <= time; i++) {
            // 如果来到当前，看看左边界是否被覆盖了，没被覆盖，那么我当前也处理不了
            if (dp[clips[i][0]] == -1) {
                return -1;
            }
            //  如果我的左边界被处理，我就不管了，从没有处理的位置开始处理
            for (int j = clips[i][0]; j <= Math.min(time, clips[i][1]); j++) {
                if (dp[j] == -1) {
                    dp[j] = dp[clips[i][0]] + 1;
                }
            }
        }
        return dp[time];
    }
}
