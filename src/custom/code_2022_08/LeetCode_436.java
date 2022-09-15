package custom.code_2022_08;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @ClassName LeetCode_436
 * @Author Duys
 * @Description
 * @Date 2022/8/11 14:18
 **/
// 436. 寻找右区间
public class LeetCode_436 {

    public static int[] findRightInterval(int[][] intervals) {
        // 记录每一个start的位置
        TreeMap<Integer, Integer> startMap = new TreeMap<>();
        int n = intervals.length;
        for (int i = 0; i < n; i++) {
            int start = intervals[i][0];
            startMap.put(start, i);
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int end = intervals[i][1];
            // 返回大于或等于给定键的最小键，如果没有这样的键，则null
            Integer integer = startMap.ceilingKey(end);
            if (integer == null) {
                ans[i] = -1;
            } else {
                ans[i] = startMap.get(integer);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] arr = {{4, 5}, {2, 3}, {1, 2}};
        int[] ans = findRightInterval(arr);
        System.out.println(ans);
    }
}
