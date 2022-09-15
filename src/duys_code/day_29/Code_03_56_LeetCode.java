package duys_code.day_29;

import java.util.Arrays;

/**
 * @ClassName Code_03_56_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/merge-intervals/
 * @Date 2021/11/25 13:32
 **/
public class Code_03_56_LeetCode {

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 0 || intervals[0] == null || intervals[0].length <= 0) {
            return new int[0][0];
        }
        // 根据第一维排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int start = intervals[0][0];
        int end = intervals[0][1];
        int index = 0;
        for (int i = 1; i < intervals.length; i++) {
            // 需要新开一组了
            if (intervals[i][0] > end) {
                intervals[index][0] = start;
                intervals[index++][1] = end;
                start = intervals[i][0];
                end = intervals[i][1];
            } else {
                // 没有新开一组，看看能不能推高end
                end = Math.max(intervals[i][1], end);
            }
        }
        // 最后要单独算组后一组的
        intervals[index][0] = start;
        intervals[index++][1] = end;
        return Arrays.copyOf(intervals, index);
    }
}
