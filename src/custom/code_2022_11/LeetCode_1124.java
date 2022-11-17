package custom.code_2022_11;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_1124
 * @Author Duys
 * @Description
 * @Date 2022/11/15 11:04
 **/
// 1124. 表现良好的最长时间段
public class LeetCode_1124 {

    public static int longestWPI1(int[] hours) {
        int n = hours.length;
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            int score = hours[i] > 8 ? 1 : -1;
            sum += score;
            if (sum > 0) {
                max = Math.max(max, i + 1);
            } else if (map.containsKey(sum - 1)) {
                int pre = i - map.get(sum - 1);
                max = Math.max(max, pre);
            }
            map.putIfAbsent(sum, i);
        }
        return max;
    }

    public static int longestWPI(int[] hours) {
        int l = 0;
        int r = 0;
        int yes = 0;
        int n = hours.length;
        int ans = 0;
        while (l <= r && r < n) {

            if (hours[r] > 8) {
                yes++;
            }
            while (yes != 0 && yes <= r - l + 1 - yes && l < r) {
                if (hours[l] > 8) {
                    yes--;
                }
                l++;
            }
            if (yes > 0) {
                ans = Math.max(ans, r - l + 1);
            }
            r++;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] hours = {9, 9, 6, 0, 6, 6, 9};
        System.out.println(longestWPI(hours));
    }
}
