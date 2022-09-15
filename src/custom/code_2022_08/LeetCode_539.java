package custom.code_2022_08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName LeetCode_539
 * @Author Duys
 * @Description
 * @Date 2022/8/23 14:45
 **/
// 539. 最小时间差
public class LeetCode_539 {

    public static int findMinDifference(List<String> timePoints) {
        int max = 24 * 60;
        // 转一次
        int n = timePoints.size();
        int[] times = new int[n * 2];
        for (int i = 0, index = 0; i < n; i++, index += 2) {
            String[] split = timePoints.get(i).split(":");
            int h = Integer.parseInt(split[0]);
            int m = Integer.parseInt(split[1]);
            times[index] = h * 60 + m; // 当天的
            times[index + 1] = times[index] + max; // 下一天的
        }
        Arrays.sort(times);
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < times.length; i++) {
            ans = Math.min(ans, times[i] - times[i - 1]);
        }
        return ans;
    }

    public static void main(String[] args) {
        List<String> ans = new ArrayList<>();
        // ["12:12","00:13"]
        ans.add("23:59");
        ans.add("00:00");
        System.out.println(findMinDifference(ans));
    }

}
