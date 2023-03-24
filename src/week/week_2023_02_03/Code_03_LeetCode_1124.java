package week.week_2023_02_03;

import java.util.HashMap;

/**
 * @ClassName Code_03_LeetCode_1124
 * @Author Duys
 * @Description
 * @Date 2023/2/16 13:28
 **/
// 1124. 表现良好的最长时间段
public class Code_03_LeetCode_1124 {
    // 当前题出现在体系学习班，数组三连问题中
    public static int longestWPI(int[] hours) {
        // 前缀和出现的位置
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int ans = 0;
        int sum = 0;
        // 数组转换，大于8的是1，小于等于8的是-1
        for (int i = 0; i < hours.length; i++) {
            sum += hours[i] > 8 ? 1 : -1;
            if (sum > 0) {
                // 一路都是满足的，那么就是i+1长度
                ans = i + 1;
            } else {
                // 是不是包含了少于当前的前缀
                // 比如当前是-4 那么我需要知道-5在哪里
                if (map.containsKey(sum - 1)) {
                    ans = Math.max(ans, i - map.get(sum - 1));
                }
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return ans;
    }
}
