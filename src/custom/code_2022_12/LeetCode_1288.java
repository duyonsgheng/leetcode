package custom.code_2022_12;

import java.util.Arrays;
import java.util.Stack;

/**
 * @ClassName LeetCode_1288
 * @Author Duys
 * @Description
 * @Date 2022/12/1 17:02
 **/
// 1288. 删除被覆盖区间
public class LeetCode_1288 {
    public static void main(String[] args) {
        int[][] intervals = {{1, 2}, {1, 4}, {3, 4}};
        System.out.println(removeCoveredIntervals(intervals));
    }

    public static int removeCoveredIntervals(int[][] intervals) {
        // 按照开始区间排序，开始区间一样的大的排在前面
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int n = intervals.length;
        int ans = 0;
        int pre = 0;
        for (int i = 0; i < n; i++) {
            if (intervals[i][1] > pre) {
                ans++; // 需要删除
                pre = intervals[i][1];
            }
        }
        return ans;
    }
}
